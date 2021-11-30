(ns exercicio1.solucao_ex1)
(require '[clojure.set :as set])

(defn rpad-sequence
  "Completa a sequencia seq passada com n valores x à direita"
  [seq n x]
  (vec (take n (concat seq (repeat x)))))

(defn lpad-sequence
  "Completa a sequencia seq passada com n valores x à esquerda"
  [seq n x]
  (vec (concat (take (- n (count seq)) (repeat x)) seq)))

;------------------------------------------- um escalar que multiplica todos valores internos multiplica
;1 - multiplicar o vetor todo por um escalar
;2 - separar cada elemento conforme apenas um digito
;3 - repetir os processos 1 e 2 para o proximo digito da multiplicacao do segundo vetor
;4 - somar os indices equivalentes jogando o expoente para frente
;talvez aplicar ua funcao no lugar de asterisco no partial ja fazendo as duas coisas
;(println (map (partial * 7) [3 2 1]))

(defn multiplica-vetor
  "Multiplica o numero inteiro x pelo vetor de inteiros iniciando com e1=0"
  [x a1 e1]
  (let [primeiro (first a1)]
    (if (nil? primeiro)
      []
      (let [resultado (* x primeiro)]
        (let [soma (+ resultado e1)]
          ;(println "soma=" (* 1 soma))
          ;(println "count a1=" (count a1))
          ;(println "exp=" (* 1 e1))
          (if (= (count a1) 1)
            (do
              (if (< soma 10)
                [soma]
                [(mod soma 10) (quot soma 10)]))
            (do
              (if (< soma 10)
                (into [soma] (multiplica-vetor x (rest a1) (quot soma 10)))
                (into [(mod soma 10)] (multiplica-vetor x (rest a1) (quot soma 10)))
                ))))))))

;preciso de uma funcao que eu passe dois vetores e retorne a soma como forma de vetor de inteiros
;OBS1: para esta soma, precisa-se garantir que os vetores a serem somados são do mesmo tamanho
(defn soma-vetores
  "Dados dois vetores de numeros inteiros v1 e v2 retorna a soma em um novo vetor"
  [vetor1 vetor2 exp]
  (let [i1 (first vetor1)]
    (let [i2 (first vetor2)]
      (if (nil? i1) (if (nil? i2) (+ exp 0)) (+ exp i2))    ;se i1 e i2 foram nulos retorna 0;
      (if (nil? i2) (+ exp i1))
      (let [resultado (+ i1 i2)]
        (let [soma (+ resultado exp)]
          (if (= (count vetor2) 1)                          ;OBS1
            (do
              (if (< soma 10)
                [soma]
                [(mod soma 10) (quot soma 10)]))
            (do
              (if (< soma 10)
                (into [soma] (soma-vetores (rest vetor1) (rest vetor2) (quot soma 10)))
                (into [(mod soma 10)] (soma-vetores (rest vetor1) (rest vetor2) (quot soma 10)))
                ))))))))


(defn standard-multiply
  "Dados dois vetores retorna a multiplicação inteira entre eles"
  [vetor1 vetor2]
  (if (> (count vetor1) (count vetor2))
     (standard-multiply vetor2 vetor1)

    (let [primeiro (first vetor1)]
      (if (nil? primeiro) [])
      (if (= (count vetor1) 1)
        (multiplica-vetor primeiro vetor2 0)
        (do
          (soma-vetores (rpad-sequence (multiplica-vetor primeiro vetor2 0) (* 2 (count vetor2)) 0) (into [0] (standard-multiply (rest vetor1) vetor2)) 0)
          )))))

;(println "Calculando...")
;(println (standard-multiply [9 0 9 8 5 5 1 9 3 1 3 2 7 3 0 7 7 7 4 6 2 8 4 3 5] [8 0 3 7 8 4 3 6 7 0 6 3 9 2 3 7 7 3 2 8 8 7 4 0 3]))
;(println "Finalizado!")


;-=============================================================================================================
;KARATSUBA
;para karatsuba
(defn soma-par-ordenado
  "Recebe vetor v1 e soma com vetor par-ordenado v2 (numero e carry) e retorna o vetor soma"
  [v1 v2]
  (let [soma (+ (last v1) (first v2))]
    (if (< soma 10)
      (conj (conj (vec (butlast v1)) soma) (last v2))
      (do
        (let [somacoef (+ (quot soma 10) (last v2))]
          (if (< somacoef 10)
            (conj (conj (vec (butlast v1)) (mod soma 10) somacoef))
            (conj (conj (vec (butlast v1)) (mod soma 10) (mod somacoef 10) (quot somacoef 10)))))))))


(defn soma-vetores-gera-par-ordenado
  "Soma dois elementos e retorna o resultado com carry em par ordenado"
  [n1 n2]
  (let [soma (+ n1 n2)]
    (if (< soma 10)
      [soma 0]
      [(mod soma 10) (quot soma 10)])))

(defn soma-vetores2
  [v1 v2]
  (let [m (max (count v1) (count v2))
        vetor1 (rpad-sequence v1 m 0)
        vetor2 (rpad-sequence v2 m 0)]
    (let [reduzido (reduce soma-par-ordenado (map soma-vetores-gera-par-ordenado vetor1 vetor2))]
      (if (= (last reduzido) 0)
        (vec (butlast reduzido))
        (vec reduzido)))))

(defn karatsuba2
  [x y n]
  (if (<= n 3)
    (standard-multiply x y)
    (let [q (int (Math/ceil (/ n 2)))
          m (int (Math/floor (/ n 2)))
          B (vec (first (split-at q x)))
          A (vec (last (split-at q x)))
          D (vec (first (split-at q y)))
          C (vec (last (split-at q y)))
          E (karatsuba2 A C m)
          F (karatsuba2 B D q)
          H (soma-vetores2 (standard-multiply A D) (standard-multiply B C))]
              (do
                (soma-vetores2 (soma-vetores2 (lpad-sequence E (+ n (count E)) 0) (lpad-sequence H (+ q (count H)) 0)) F))
              )))

(defn karatsuba
  [x y]
  (karatsuba2 x y (count x)))

(println "Calculando...")
(println (karatsuba [9 0 9 8 5 5 1 9 3 1 3 2 7 3 0 7 7 7 4 6 2 8 4 3 5] [8 0 3 7 8 4 3 6 7 0 6 3 9 2 3 7 7 3 2 8 8 7 4 0 3]))
(println (standard-multiply [9 0 9 8 5 5 1 9 3 1 3 2 7 3 0 7 7 7 4 6 2 8 4 3 5] [8 0 3 7 8 4 3 6 7 0 6 3 9 2 3 7 7 3 2 8 8 7 4 0 3]))
(println "Finalizado!")
