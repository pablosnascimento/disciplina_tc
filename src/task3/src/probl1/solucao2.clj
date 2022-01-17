(ns task3.probl1.solucao2
  (:require [clojure.string :as str]
            [clojure.core.match :refer [match]]
            [clojure.pprint :as pp]
            [clojure.set :as set]
            [clojure.math.combinatorics :as combo]))

(def automato1
  "Lê o arquivo e define o primeiro automato."
  (first (vec (read-string (slurp "automatos.txt")))))

(def automato2
  "Lê o arquivo e define o primeiro automato."
  (second (vec (read-string (slurp "automatos.txt")))))

(defn renomeia
  [conjunto char]
  (set (map (fn [x] (str char x)) conjunto)))

;(["q0" 1] "q3") -> {["char_q0 1] "char_q3}
(defn renomeia-t
  "Dado uma única transição renomeia o estado da transição com a string passada"
  [x char]
  (let [estado (str char (first (first x)))
        caracter (second (first x))
        alvo (str char (second x))]
    (assoc {} [estado caracter] alvo)))

;{["s0" 0] "s1", ["s0" 1] "s0"} ->{["char_s0" 0] "char_s1", ["char_s0" 1] "char_s0"}
(defn renomeia-transaction
  "Dado um conjunto de transições retorna o novo conjunto com as transições renomeadas com string informada."
  [transacoes char]
  (reduce conj {} (map (fn [x] (renomeia-t x char)) transacoes)))


(defn get-alphabet
  [aut]
  (first (vals (select-keys aut [:alphabet]))))

(defn get-states
  [aut]
  (first (vals (select-keys aut [:states]))))

(defn get-initial
  [aut]
  (first (vals (select-keys aut [:initial]))))

(defn get-accepting
  [aut]
  (first (vals (select-keys aut [:accepting]))))

(defn get-transitions
  [aut]
  (first (vals (select-keys aut [:transitions]))))

(defn in?
  "Verdadeiro se coll contém elm"
  [coll elm]
  (some #(= elm %) coll))

(defn new-alphabet
  [aut1]
  (first (vals (select-keys aut1 [:alphabet]))))

;automato1 automato2 -> list (() () ())
(defn new-states
  "Recebe dois autômatos e retorna o conjunto de estados do produto cartesiano entre eles."
  [aut1 aut2]
  (let [s1 (get-states aut1)
        s2 (get-states aut2)]
  (combo/cartesian-product s1 s2)))

;automato1 automato2 -> (q0_aut1,s0_aut2)
(defn new-initial
  "Recebe dois autômatos e retorna o par-ordenado que define o estado inicial do novo automato assíncrono."
  [aut1 aut2]
  (let [s1 (get-initial aut1)
        s2 (get-initial aut2)]
    (conj (conj () s2) s1)))

(defn new-accepting
  "Recebe dois autômatos e retorna o conjunto de estados aceitáveis do novo automato."
  [aut1 aut2]
  (let [acc1 (get-accepting aut1)
        acc2 (get-accepting aut2)
        n-states (new-states aut1 aut2)
        aceitaveis (concat acc1 acc2)
        final-states (for [item n-states]
                       (if (or (in? aceitaveis (first item)) (in? aceitaveis (second item)))
                         item))]
    (filter some? final-states)))

;verificar se esta correto amanhã e converter para conjunto as estruturas que estiverem como lista
(defn new-transitions
  "Recebe dois autômatos e retorna o conjunto de transições do novo automato."
  [aut1 aut2]
  (let [all-states (new-states aut1 aut2)
        alfabeto (new-alphabet aut1)
        transitions-aut1 (get-transitions aut1)
        transitions-aut2 (get-transitions aut2)
        transicoes (for [s all-states
                         a alfabeto
                         :let [qi (second (first (filter (fn [x] (and (= (first (first x)) (first s)) (= (second (first x)) a))) transitions-aut1)))
                               pi (second (first (filter (fn [x] (and (= (first (first x)) (second s)) (= (second (first x)) a))) transitions-aut2)))]]
                     (assoc {} [s a] (conj (conj () pi) qi)))]
    (reduce conj {} transicoes)))

(defn renomear-estados
  "Recebe um automato e o caracter com o qual irá renomear os estados."
  [aut char]
  (let [alfabeto (get-alphabet aut)
        estados (renomeia (get-states aut) char)
        inicial (str char (get-initial aut))
        aceitaveis (renomeia (get-accepting aut) char)
        transicoes (renomeia-transaction (get-transitions aut) char)]
    (assoc {} :alphabet alfabeto
              :states estados
              :initial inicial
              :accepting aceitaveis
              :transitions transicoes)))

;testes
;(println (renomear-estados automato1 "a_"))

(defn assinc-aut
  "Define o autômato assíncrono entre dois autômatos aut1 e aut2"
  [aut1 aut2]
  (let [alfabeto (set (new-alphabet aut1))
        estados (set (new-states aut1 aut2))
        inicial (new-initial aut1 aut2)
        aceitaveis (set (new-accepting aut1 aut2))
        transicoes (set (new-transitions aut1 aut2))]
    (assoc {} :alphabet alfabeto
              :states estados
              :initial inicial
              :accepting aceitaveis
              :transitions transicoes)))

;(println (new-states automato1 automato2))
;(println (new-initial automato1 automato2))
;(println (new-accepting automato1 automato2))
;(println (in? '(100 109 102) automato1 ))
;(println (new-transitions automato1 automato2))

;chamada principal
;(println (assinc-aut automato1 automato2))
(println (assinc-aut (renomear-estados automato1 "a_") (renomear-estados automato2 "b_")))

