(ns task2.probl2.solucao1
  (:require [clojure.string :as str]
            [clojure.core.match :refer [match]]))
(require '[clojure.pprint :as pp])
(require 'clojure.walk)
(require '[clojure.string :as str])

(declare map-then)

;ast deve ser um vetor com caracteres [ e ] iniciando e finalizando o arquivo.
(def AST
  ;(read-string (slurp "teste-if-only2.txt"))
  (read-string (slurp "teste-if-else.txt"))
  ;(read-string (slurp "teste.txt"))
  )

(defn debug-ast
  [ast]
  (println (loop [line 0 ast-restantes ast]
             (if (seq ast-restantes)
               (do
                 (println line ast-restantes)
                 (recur (inc line) (next ast-restantes)))
               line))))

;ast -> string
(defn imprime-ast
  "Imprime a estrutura AST"
  [ast] (pp/pprint ast))
;(imprime-ast ast)

(defn has-value [key value]
  "Retorna o predicado que testa quando o mapa contém um valor especificado"
  (fn [m]
    (= value (m key))))

;(itens-por-chave-valor :expr :if AST) : todos itens que possuem a chave com o valor indicado
(defn itens-por-chave-valor
  [key value ast]
  (filter (has-value key value) ast))

;(contem-chave-valor :expr :if AST) : para AST vetor
(defn contem-chave-valor
  [key value ast]
  (> (count (filter (has-value key value) ast)) 0))

;(imprime-ast (= (get (last AST) :expr) :if))
;(imprime-ast (contem-chave-valor :expr :if AST))
;(println (filter (comp :if :expr) (last AST)))

;retorna a estrutura de IF resultante das entradas informadas
(defn add-assign-if
  "lhs = IF(arg0,arg1,arg2) - retorna IF no formato expr"
  [lhs-tipo lhs-nome-tipo lhs-valor,
   arg0-tipo arg0-nome-tipo arg0-valor,
   arg1-tipo arg1-nome-tipo arg1-valor,
   arg2-tipo arg2-nome-tipo arg2-valor]
  (conj [] (assoc {} :expr :assign
                     :lhs (assoc {} :expr lhs-tipo lhs-nome-tipo lhs-valor)
                     :rhs (assoc {} :expr :funcall :name "IF"
                                    :args [(assoc {} :expr arg0-tipo arg0-nome-tipo arg0-valor)
                                           (assoc {} :expr arg1-tipo arg1-nome-tipo arg1-valor)
                                           (assoc {} :expr arg2-tipo arg2-nome-tipo arg2-valor)]))))

(defn add-assign-func
  "lhs = function(arg0,arg1) - retorna a atribuição da função no formato expr"
  ([lhs-tipo lhs-nome-tipo lhs-valor
    arg0-tipo arg0-nome-tipo arg0-valor]
   (add-assign-func lhs-tipo lhs-nome-tipo lhs-valor,
                    "NOT",
                    arg0-tipo arg0-nome-tipo arg0-valor,
                    "" "" ""))
  ([lhs-tipo lhs-nome-tipo lhs-valor,
    arg0-tipo arg0-nome-tipo arg0-valor,
    arg1-tipo arg1-nome-tipo arg1-valor,
    arg2-tipo arg2-nome-tipo arg2-valor]
   (add-assign-if lhs-tipo lhs-nome-tipo lhs-valor,
                  arg0-tipo arg0-nome-tipo arg0-valor,
                  arg1-tipo arg1-nome-tipo arg1-valor,
                  arg2-tipo arg2-nome-tipo arg2-valor))
  ([lhs-tipo lhs-nome-tipo lhs-valor,
    rhs-function,
    arg0-tipo arg0-nome-tipo arg0-valor,
    arg1-tipo arg1-nome-tipo arg1-valor]
   (conj [] (assoc {} :expr :assign
                      :lhs (assoc {} :expr lhs-tipo lhs-nome-tipo lhs-valor)
                      :rhs (assoc {} :expr :funcall :name rhs-function
                                     :args (if (= rhs-function "NOT")
                                             [(assoc {} :expr arg0-tipo arg0-nome-tipo arg0-valor)]
                                             [(assoc {} :expr arg0-tipo arg0-nome-tipo arg0-valor)
                                              (assoc {} :expr arg1-tipo arg1-nome-tipo arg1-valor)]))))))

(defn is-if [ast] (= :if (get ast :expr)))
(defn is-assign [ast] (= :assign (get ast :expr)))
(defn is-var [ast] (= :var (get ast :expr)))
(defn is-funcall [ast] (= :funcall (get ast :expr)))
(defn is-input [ast] (= :input (get ast :expr)))
(defn is-output [ast] (= :output (get ast :expr)))
;(println (is-if (last AST)))

;{:expr :assign :lhs {} ... } -> {}
(defn lhs [assign] "Recebe :assign retorna o :lhs"
  (get assign :lhs))
;{:expr :assign :lhs {:expr :var, :name a}... -> :var
(defn lhs-tipo [assign] "Recebe :assign retorna o :tipo do :lhs"
  (get (lhs assign) :expr))
;{:expr :assign :lhs {:expr :var, :name a}... -> :name
(defn lhs-nome-tipo [assign] "Recebe :assign retorna o nome do tipo do :lhs"
  (last (keys (lhs assign))))
;{:expr :assign :lhs {:expr :var, :name a}... -> a
(defn lhs-valor-tipo [assign sufix] "Recebe :assign retorna o vaor do tipo do :lhs"
  (let [valor (last (vals (lhs assign)))]
    (if (or (str/includes? valor "X[") (str/includes? valor "Y["))
      valor
      (str valor sufix))))

;rhs
(defn rhs [assign] (get assign :rhs))
(defn rhs-tipo [assign] "Recebe :assign retorna o :tipo do :rhs"
  (get (rhs assign) :expr))
;{:expr :assign :rhs {:expr :var, :name a}... -> :name
(defn rhs-nome-tipo [assign] "Recebe :assign retorna o nome do tipo do :rhs"
  (let [tipo (rhs-tipo assign)]
    (case tipo
      :input :index
      :output :index
      :var :name
      :funcall :name)))
(defn rhs-valor-tipo [assign sufix] "Recebe :assign retorna o vaor do tipo do :lhs"
  (let [valor (get (rhs assign) (rhs-nome-tipo assign))
        tipo (rhs-tipo assign)]
    (if (or (= tipo :input) (= tipo :output) (= tipo :funcall))
      valor
      (str valor sufix))))

(defn argx [assign x] "Recebe :assign retorna o :args indicado"
  (get (get (rhs assign) :args) x))                         ;verificar se for vetor ok, se for lista ajustar get
(defn argx-tipo [assign x] "Recebe :assign retorna o :tipo do :rhs"
  (get (argx assign x) :expr))
(defn argx-nome-tipo [assign x] "Recebe :assign retorna o nome do tipo do :rhs"
  (let [tipo (argx-tipo assign x)]
    (case tipo
      :input :index
      :output :index
      :var :name
      :funcall :name)))
(defn argx-valor-tipo [assign x sufix] "Recebe :assign retorna o vaor do tipo do :lhs"
  (let [valor (get (argx assign x) (argx-nome-tipo assign x))
        tipo (rhs-tipo assign)]
    (if (or (= tipo :input) (= tipo :output) (= tipo :funcall))
      valor
      (str valor sufix))))

(defn map-if
  "Recebe um map :if e percorre tratando cada caso para o :then, :else e :test.
  Retorna a estrutura IF correspondente do AST interpretado."
  [comando-if if-number]
  (let [if-keys (select-keys comando-if [:then :else :test])
        lista-then (first (vals (select-keys if-keys [:then])))
        lista-else (first (vals (select-keys if-keys [:else])))
        test-value (vals (select-keys if-keys [:test]))

        teste (map-then lista-then if-number)
        ]
    (concat (concat teste lista-else) test-value)))
;into () conj une cada um como um novo elemento, que é o que eu quero. Cada comando numa linha

;lista com valor do :then -> ast interpretado
(defn map-then
  "Recebe um map :then e percorre tratando caso seja atribuição ou outro if interno
   Retorna a estrutura do then correspondente do AST interpretado."
  [comandos-then if-number]
  (loop [line 0 ast-restantes comandos-then resultado []]
    (println "------------ line" line)
    (if (> (count ast-restantes) 0)
      (let [comando (first ast-restantes)
            sufix (str "$then" if-number "_" line)]
        ;(imprime-ast comando)
        (if (is-assign comando)
          (add-assign-func (lhs-tipo comando) (lhs-nome-tipo comando) (lhs-valor-tipo comando sufix)
                           (rhs-valor-tipo comando sufix)
                           (argx-tipo comando 0) (argx-nome-tipo comando 0) (argx-valor-tipo comando 0 sufix)
                           (argx-tipo comando 1) (argx-nome-tipo comando 1) (argx-valor-tipo comando 1 sufix)
                           )
          ;verificar o valor do rhs-valor-tipo para os tipos diferentes com e sem variavel
          ;verificar casos em que tem os dois argumentos e os que nao tem e tratar no add-assign-func

          ;caso contrario, se for outro if
          (if (is-if comando)
            ;numerando o then devidamente
            (do
              (println "TESTE2")
              (recur (inc line) (next ast-restantes) (conj resultado (map-if comando (str if-number "_" line)))))
            resultado
            )
          )))))

;(contains? coll key) - retorna verdadeiro se a chave existe na coleção
(defn read-ast
  [ast]
  (loop [line 0 ast-restantes ast resultado []]
     (if (> (count ast-restantes) 0)
       (let [comando (first ast-restantes)]
         ;(println line ast-restantes)
         ;se é if, aplico as tratativas. Do contrario, continua o loop
         ;(imprime-ast comando)
         (if (is-if comando)
           (do
             ;(println "entrou no if")
             (recur (inc line) (next ast-restantes) (conj resultado (map-if comando line))))
           (recur (inc line) (next ast-restantes) (conj resultado comando)))                                      ;tratar o comando que não é if depois
       )
       resultado)))

;(imprime-ast (read-ast AST))
;(imprime-ast (vals (first (vals (select-keys (first AST) [:then])))))

;valores do then:
;(imprime-ast (vals (select-keys (first AST) [:then])))
; 1 - tipo do lhs do then
;(imprime-ast (first (vals (select-keys (first (first (vals (select-keys (first AST) [:then])))) [:lhs]))))
;(imprime-ast (select-keys (last AST) [:then]))