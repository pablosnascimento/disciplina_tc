(ns task3.probl1.solucao2
  (:require [clojure.string :as str]
            [clojure.core.match :refer [match]]
            [clojure.pprint :as pp]
            [clojure.set :as set]
            [clojure.math.combinatorics :as combo]))

(def automato1 {:alphabet #{0 1}
           :states #{"q0" "q1" "q2" "q3"}
           :initial "q0"
           :accepting #{"q3"}
           :transitions {["q0" 0] "q1"
                         ["q0" 1] "q0"
                         ["q1" 0] "q2"
                         ["q1" 1] "q0"
                         ["q2" 0] "q2"
                         ["q2" 1] "q3"
                         ["q3" 0] "q3"
                         ["q3" 1] "q3"}})

(def automato2 {:alphabet #{0 1}
           :states #{"s0" "s1" "s2" "s3"}
           :initial "s1"
           :accepting #{"s1" "s2"}
           :transitions {["s0" 0] "s1"
                         ["s0" 1] "s0"
                         ["s1" 0] "s2"
                         ["s1" 1] "s0"
                         ["s2" 0] "s2"
                         ["s2" 1] "s3"
                         ["s3" 0] "s3"
                         ["s3" 1] "s3"}})

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
  (let [s1 (first (vals (select-keys aut1 [:states])))
        s2 (first (vals (select-keys aut2 [:states])))]
  (combo/cartesian-product s1 s2)))

(defn new-initial
  "Recebe dois autômatos e retorna o par-ordenado que define o estado inicial do novo automato assíncrono."
  [aut1 aut2]
  (let [s1 (first (vals (select-keys aut1 [:initial])))
        s2 (first (vals (select-keys aut2 [:initial])))]
    (conj (conj () s2) s1)))

(defn new-accepting
  "Recebe dois autômatos e retorna o conjunto de estados aceitáveis do novo automato."
  [aut1 aut2]
  (let [acc1 (first (vals (select-keys aut1 [:accepting])))
        acc2 (first (vals (select-keys aut2 [:accepting])))
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
        transitions-aut1 (first (vals (select-keys aut1 [:transitions])))
        transitions-aut2 (first (vals (select-keys aut2 [:transitions])))
        transicoes (for [s all-states
                         a alfabeto
                         :let [qi (second (first (filter (fn [x] (and (= (first (first x)) (first s)) (= (second (first x)) a))) transitions-aut1)))
                               pi (second (first (filter (fn [x] (and (= (first (first x)) (second s)) (= (second (first x)) a))) transitions-aut2)))]]
                     (assoc {} [s a] (conj (conj () pi) qi)))]
    (reduce conj {} transicoes)))

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
(println (assinc-aut automato1 automato2))

