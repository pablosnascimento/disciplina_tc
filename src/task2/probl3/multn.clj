(ns task2.probl3.multn
  (:require [clojure.string :as str]))
(require '[clojure.pprint :as pp])

;decimal -> binario
(defn dec-to-bin
  "Converte um número decimal para binário"
  [n]
  (Long/toString n 2))
;(println (dec-to-bin 4))

;for @i = 0 to 2:
;   x@i = AND(X[2], X[5-i])
;   y@{i+1} = AND(X[1], X[4-i])
;   z@{i+2} = AND(X[0], X[3-i])
;next

;tem que adicionar um ZERO=X[0] à esquerda do ultimo numero gerado
(defn for-fatores-soma
  "Recebe o primeiro valor binario que será somado e retorna a construção das multiplicações."
  [x]
  (let [size (count x)]
  (doseq [slinha (range size)]
    ;(println i)
    (doseq [posi (range (+ size slinha))]
      (if (> slinha posi)
        ;(doseq [idx (range (+ slinha posi))]
          (println (format "s%d@{i+%d} = ZERO(X[0])" slinha posi))
        (println (format "s%d@{i+%d} = AND(X[%d],X[%d])" slinha posi slinha (- (+ size posi) slinha)))))
    )))

(for-fatores-soma "111" "111")
;(println (doseq [posi (range 200)] (println posi)))