(ns task2.probl4.exec_nand
  (:require [clojure.string :as str]
            [clojure.pprint :as pp]
            [clojure.set :as set]))
(use '[clojure.string :only [index-of]])

;file.txt -> tuple list
(def LISTA_P
  (first (list (read-string (slurp "L.txt")))))

(def to-bool {false 0 true 1})

;list-tuples -> arraymap [0 0 1 0 2 0 3 0...]
(defn get-variaveis
  [L]
  (let [ordenado (sort (vec (set (apply concat L))))
        lista (for [i ordenado]
                [i 0])]
    (vec (apply concat lista))))

(defn nand
  [a b]
  (if (and (= a b) (or (= a 1) (= a "1")))
      0
      1))

(defn inicializar-entradas
  [variaveis n]
  (loop [i 0 vals variaveis]
    (if (= i (count n))
      vals
      (let [pos (inc (* 2 i))
        value (subs n i (inc i))]
        (recur (inc i) (assoc vals pos value))))))

;L ((2 3 1) (0 8 4) (3 5 4) ...) / n "001110" -> str "01110"
(defn executa-nand
  "Executa o programa NAND representado em L conforme os bits de entrada de n."
  [n m L]
  (let [variaveis (get-variaveis L)
        inicializadas (inicializar-entradas variaveis n)
        saida (loop [lista L vals inicializadas]      ;inicializa as variaveis de entrada X[i] no vetor com os valores de n
                (if (= (count lista) 0)
                  vals
                  (let [item (first lista)
                        v1 (first item)
                        pos (inc (* 2 v1))
                        v2 (second item)
                        value-v2 (get vals (inc (* 2 v2)))
                        v3 (last item)
                        value-v3 (get vals (inc (* 2 v3)))
                        nand-value (nand value-v2 value-v3)                         ;(nand v2 v3)
                        atual (assoc vals pos nand-value)]
                        (recur (rest lista) atual))))]
    (apply str (take-nth 2 (rest (take-last (* 2 m) saida))))))         ;take-nth 2 somente os numeros dos indices pares da lista, por isso o rest antes

;TESTES
;(println LISTA_P)
;(println (map #(hash-map % 0) (get-variaveis LISTA_P)))
;(println (keys (first (get-variaveis LISTA_P))))
;(println (update (first (get-variaveis LISTA_P)) 0 1))
;(println (update (apply concat (get-variaveis LISTA_P)) (inc (* 2 2)) 1))
;(println (get-variaveis LISTA_P))
;(println (inicializar-entradas (get-variaveis LISTA_P) "00"))
(println (executa-nand "01" 2 LISTA_P))
