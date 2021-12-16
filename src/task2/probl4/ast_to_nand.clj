(ns task2.probl4.ast-to-nand
  (:require [clojure.string :as str]
            [clojure.core.match :refer [match]]
            [clojure.pprint :as pp]
            [clojure.set :as set]))
(use '[clojure.string :only [index-of]])

(def AST
  (read-string (slurp "nand.txt")))

;ast -> string
(defn imprime-ast
  "Imprime a estrutura AST"
  [ast] (pp/pprint ast))

(defn match-var
  [x]
  (match [x]
         [{:expr :input :index in}] {:X (format "X[%d]" in)}
         [{:expr :output :index out}] {:Y (format "Y[%d]" out)}
         [{:expr :var :name var}] {:variable var}))

;:lhs {:expr :var :name "a"} -> {:variable a}
(defn lhs-variavel
  [x]
  (match-var x))

(defn rhs-variaveis
  [x]
  (match [x]
         [{:expr :funcall :name "NAND" :args args}] [(match-var (first args)) (match-var (second args))]))

;AST -> lista com trios com as chaves das variaveis na ordem em que aparecem
(defn get-variaveis
  [ast]
  (loop [ast-restante ast result []]
    (if (= (count ast-restante) 0)
      result
      (let [lhs (lhs-variavel (first (vals (select-keys (first ast-restante) [:lhs]))))
            rhs (rhs-variaveis (first (vals (select-keys (first ast-restante) [:rhs]))))
            ]
        (recur (rest ast-restante) (conj result (conj rhs lhs)))))))

;ast -> list, conjunto de variaveis unicas
(defn set-variaveis
  [ast]
  (vec (set (apply concat (get-variaveis ast)))))

;(AST) -> numero de variáveis de entrada do programa
;type: :X, :Y ou :variable
(defn variable-values
  [set type]
  (filter (fn [x] (= (first x) type)) (vec (apply concat set))))

(defn n-entradas
  [ast]
  (count (variable-values (set-variaveis ast) :X)))

(defn m-saidas
  [ast]
  (count (variable-values (set-variaveis ast) :Y)))

(defn v-variaveis-internas
  [ast]
  (count (variable-values (set-variaveis ast) :variable)))

(defn ordem-das-variaveis
  [set]
  (let [lx (sort-by second (variable-values set :X))
        li (variable-values set :variable)
        ly (sort-by second (variable-values set :Y))]
    (vec (concat lx li ly))))

(defn enumera-variaveis
  [set-ordenado]
  (for [i (range (count set-ordenado))
          :let [item (second (get set-ordenado i))]]
    [item i]))

;lista-variaveis-enumeradas variavel -> numeração que a variáve recebeu
;list number -> number
(defn ordem-da-variavel
  [lista-enumerada var]
  (let [result (filter (fn [x] (= (first x) var)) lista-enumerada)]
    (if (> (count result))
      (second (first (doall result)))
      result)))




;fase 2
(defn match-var-pura
  ;[x n-entradas v-internas]
  [x lista-enumerada]
  (match [x]
         [{:expr :input :index index-in}] (ordem-da-variavel lista-enumerada (format "X[%d]" index-in))
         [{:expr :output :index index-out}] (ordem-da-variavel lista-enumerada (format "Y[%d]" index-out))
         [{:expr :var :name var}] (ordem-da-variavel lista-enumerada var)))

;:lhs {:expr :var :name "a"} -> {:variable a}
(defn lhs-variavel-pura
  [x lista-enumerada]
  (match-var-pura x lista-enumerada))

(defn rhs-variaveis-pura
  [x lista-enumerada]
  (match [x]
         [{:expr :funcall :name "NAND" :args args}] [(match-var-pura (first args) lista-enumerada) (match-var-pura (second args) lista-enumerada)]))

;AST -> lista com trios com as chaves das variaveis na ordem em que aparecem
(defn get-variaveis-puras
  [ast]
  (let [lista-enumerada (enumera-variaveis (ordem-das-variaveis (set-variaveis ast)))]
  (loop [ast-restante ast result []]
    (if (= (count ast-restante) 0)
      result
      (let [lhs (lhs-variavel-pura (first (vals (select-keys (first ast-restante) [:lhs]))) lista-enumerada)
            rhs (rhs-variaveis-pura (first (vals (select-keys (first ast-restante) [:rhs]))) lista-enumerada)]
        (recur (rest ast-restante) (conj result (cons lhs rhs))))))))

(defn representacao-lista-tuplas
  [ast]
  (let [L (apply list (get-variaveis-puras ast))
        n (n-entradas ast)
        m (m-saidas ast)]
    (apply list [n m L])))

;TESTES
;(imprime-ast AST)
;(println (set (apply concat (get-variaveis AST))))

;(println (n-entradas AST))
;(println (m-saidas AST))
;(println (v-variaveis-internas AST))
;(println (get-variaveis AST))
;(println (set-variaveis AST))
;(println (ordem-das-variaveis (set-variaveis AST)))
;(println (enumera-variaveis (ordem-das-variaveis (set-variaveis AST))))
;testando filtros
;(println (filter (fn [x] (= (first x) "X[2]")) (enumera-variaveis (ordem-das-variaveis (set-variaveis AST)))))
;(println (ordem-da-variavel (enumera-variaveis (ordem-das-variaveis (set-variaveis AST))) "temp2"))
;(println (get-variaveis-puras AST))
(println (representacao-lista-tuplas AST))
