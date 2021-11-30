(ns exercicio1.aula4)

(def precos [3 5 4])

;acessa o indice direto no vetort
;(println (precos 0))

;retorna o valor do indice, mas se nao existir retorna nil. Posso usar else (mais um argumento no caso contrario)
;(println (get precos 5))

;nao altera o vetor, mas adiciona ao final e retorna o resultado
;(println (conj precos 5))


;(println (map-indexed (multip [idx item] [idx item]) [2 5 7 9]))
;(println (map-indexed (partial * 10) precos))

;multiplicacao entre os valores dos vetores - problema so pega enquanto ambos tem valores
(println (map * [1 9 3] [4 5 6]))

;--------------------------- multiplica todos elemtnso do vetor por 10 elevado ao indice da posicao
(defn multip
  ""
  [index, item]
  (* item (Math/pow 10 index)))

;(println (map-indexed (fn [idx itm] (* itm (Math/pow 10 idx))) [3 5 7 4]))
;(println (map-indexed multip [3 5 7 4]))
;(println (map-indexed multip precos))
;-------------------------------------------------------------------------------------------------

(defn lpad-sequence
  "Completa a sequencia seq passada com n valores x à direita"
  [seq n x]
  (vec (concat (take (- n (count seq)) (repeat x)) seq)))

(defn soma-pares
  [v-pares]
  (let [numeros-indice (zimap v-pares (range))
        indices (for [[x i] numeros-indice
                      [y j] numeros-indice
                      ;:while (= i (+ j 1))
                      ]
                  [(* x y)])]
    ;incompleto... testar depois do que isso pode ajudar
    indices))

(defn mmm
  "Recebe um inteiro e um vetor de inteiros e retorna um vetor de vetores par-ordenado da multiplicacao."
  [n vetor]
  (for [x vetor
        :let [primeiro (multiplica-int-gera-par-ordenado n x)
              segundo (multiplica-int-gera-par-ordenado n ())]]
    resultado))

(println (soma-pares [[1 2] [5 3] [9 4] [4 1]]))

;tentando definir uma funcao que retorne a multiplicacao dos dois vetores passados.
; completando os indices do menor vetor com 1 para multiplicacao
(defn produto
  [s1 s2]
  (for [x1 s1
        x2 s2]
    (* x1 x2)))

;(println (produto [3 7 1] (conj [2 4 5] 6)))
;---------------------------------------------------------------------------------------------

;funcionando ok
(defn m-numero-vetor
  [x a1 e1]
  (let [primeiro (first a1)]
    (if (nil? primeiro)
      []
      (let [resultado (* x primeiro)]
        (let [soma (+ resultado e1)]
        (println "soma=" (* 1 soma))
        (println "count a1=" (count a1))
        (println "exp=" (* 1 e1))
        (if (= (count a1) 1)
          (do
            (if (< soma 10)
               [soma]
               ;(conj (multiplica-4 x (rest a1) (quot resultado 10)) (+ (mod resultado 10) e1))
               [(quot soma 10) (mod soma 10)]
               ))
          (do
            (if (< soma 10)
              (conj (m-numero-vetor x (rest a1) (quot soma 10)) soma)
              (conj (m-numero-vetor x (rest a1) (quot soma 10)) (mod soma 10))
              ))))))))



;(println (m-numero-vetor 9 [7 7 5 4 3 5 6 6 7 7 8 8 5 4 6 6] 0))
;(println (conj [1, 2, 3] 4))


(defn resolve-sum
  "Recebe dois vetores de um ou dois digitos (numero e carry) e retorna o vetor soma"
  [v1 v2]
  (if (> (count v1) 1)
    (do
      (let [soma (+ (last v1) (first v2))]
        (if (< soma 10)
          (if (> (count v2) 1)
            (cons (first v1) [soma (last v2)])
            (cons (first v1) [soma])
            )
          (if (> (count v2) 1)
            (let [somafinal (+ (quot soma 10) (last v2))]
              (if (< somafinal 10)
                (cons (first v1) [(mod soma 10) somafinal])
                (cons (first v1) [(mod soma 10) (mod somafinal 10) (quot somafinal 10)])))
            (cons (first v1) [(mod soma 10) (quot soma 10)])))))
    (do
      (println (+ (first v1) (first v2)))
      (cons (first v1) v2))))



(defn soma
  "Multiplica dois numeros e retorna o resultado com os digitos separados em ordem inversa"
  [a b]
  (let [res (+ a b)]
    (if (> (quot res 10) 0)
      [(mod res 10) (quot res 10)]
      [(* res 1)])))

(defn ast
  "Multiplica dois numeros e retorna o resultado com os digitos separados em ordem inversa"
  [a b]
  (let [res (* a b)]
    (if (> (quot res 10) 0)
    [(mod res 10) (quot res 10)]
    [(* res 1)])))

(defn prod-dig [a b]
  "Product Digits: Write a function which multiples 2 numbers and returns the results as a sequence"
  (let [prod (* a b)
        len (count (str prod))]
    (map #(rem % 10) (reverse (take len (iterate #(quot % 10) prod))))))

(defn astv
  [vetor]
  (let [primeiro (first vetor)]
    (if (= (count primeiro) 1)
      (get primeiro 0)
      (recur (rest vetor)))))


;(println (ast 3 2))
;(println (map (partial soma 7) [3 2 1]))
;(println (map (partial ast 7) [3 2 1]))
;(println (map concat (map (partial ast 7) [3 2 1])))
;(println (flatten (map (partial ast 7) [3 2 1])))           ;remove todos parenteses ou colchetes e traz tudo a tona
;(println (to-array-2d (map (partial ast 7) [3 2 1])))
;(println (astv [(1 2) (4 1) (7)] 0))

