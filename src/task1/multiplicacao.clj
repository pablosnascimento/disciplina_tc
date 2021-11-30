(ns exercicio1.multiplicacao)
(require '[clojure.set :as set])

(defn rpad-sequence
  "Completa a sequencia seq passada com n valores x à direita"
  [seq n x]
  (vec (take n (concat seq (repeat x)))))

(defn lpad-sequence
  "Completa a sequencia seq passada com n valores x à esquerda"
  [seq n x]
  (vec (concat (take (- n (count seq)) (repeat x)) seq)))

;(println (lpad-sequence [2 4 5] 10 0))
;(println (lpad-sequence [2 4 5] 10 0))

(defn soma-vetores-gera-par-ordenado
  "Soma dois elementos e retorna o resultado com carry em par ordenado"
  [n1 n2]
  (let [soma (+ n1 n2)]
    (if (< soma 10)
      [soma 0]
      [(mod soma 10) (quot soma 10)])))

;(println (map soma-vetores-gera-par-ordenado [4 6 3 8 6 1 0 0 8 7] [5 3 8 7 1 9 0 5 4 8]))
;==> ([9 0] [9 0] [1 1] [5 1] [7 0] [0 1] [0 0] [5 0] [2 1] [5 1])

(defn multiplica-int-gera-par-ordenado
  "Soma dois elementos e retorna o resultado com carry em par ordenado"
  [n1 n2]
  (let [soma (* n1 n2)]
    (if (< soma 10)
      [soma 0]
      [(mod soma 10) (quot soma 10)])))



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

;(println (next [[9 2] [6 8] [7 3]]))
;(println (soma-pares [[9 2] [6 8] [7 3]]))

(defn soma-vetores2
  [v1 v2]
  (let [m (max (count v1) (count v2))
        vetor1 (rpad-sequence v1 m 0)
        vetor2 (rpad-sequence v2 m 0)]
    ;(println vetor1)
    ;(println vetor2)
    (let [reduzido (reduce soma-par-ordenado (map soma-vetores-gera-par-ordenado vetor1 vetor2))]
      (if (= (last reduzido) 0)
        (butlast reduzido)
        reduzido))))

(println "soma-vetores2")
(println (soma-vetores2 [8 1] [9 0 9]))

;(println (soma-par-ordenado [4 6 9 8 7] [5 3]))
;(println (map soma-vetores-gera-par-ordenado [3 0] [0 6]))

;(println (reduce soma-par-ordenado (map soma2-par-ordenado [4 6 3 8 6 1 0 0 8 7] [5 3 8 7 1 9 0 5 4 8])))
;(println (map soma-vetores-gera-par-ordenado [4 6 3 8 6 0 0 0 0 0] [5 3 8 7 1 9 0 5 4 8]))
;(println (soma-vetores2 [4 6 3 8 6] [5 3 8 7 1 9 0 5 4 8]))
;(println (rpad-sequence [7 6] 4 0))


;verificar
(defn subtrai-vetores
  "Dados dois vetores de numeros inteiros v1 e v2 retorna a soma em um novo vetor"
  [vetor1 vetor2 exp]
  (let [i1 (first vetor1)]
    (let [i2 (first vetor2)]
      (if (nil? i1) (if (nil? i2) (+ exp 0)) (+ exp i2))    ;se i1 e i2 foram nulos retorna 0;
      (if (nil? i2) (+ exp i1))
      (let [resultado (- i1 i2)]
        (let [subtracao (- resultado exp)]
          (if (= (count vetor2) 1)                          ;OBS1
            (do
              (if (< subtracao 10)
                [subtracao]
                [(mod subtracao 10) (quot subtracao 10)]))
            (do
              (if (< subtracao 10)
                (into [subtracao] (subtrai-vetores (rest vetor1) (rest vetor2) (quot subtracao 10)))
                (into [(mod subtracao 10)] (subtrai-vetores (rest vetor1) (rest vetor2) (quot subtracao 10)))
                ))))))))

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

;(println (multiplica-vetor 7 [7 4 1 4 5 9 3] 0))
;(println (multiplica-vetor 3 [7 1 3 2 4] 0))

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

;(println (soma-vetores [2 4 6 5 6 5 3 2] [1 9 2 4 6 8 9 3] 0))
;(println (soma-vetores [2 4 0] [1 9 2] 0))

(defn standard-multiply
  "Dados dois vetores retorna a multiplicação inteira entre eles"
  [vetor1 vetor2]
  (let [primeiro (first vetor1)]
    (if (nil? primeiro) [])
    ;(println primeiro)
    (if (= (count vetor1) 1)
      (multiplica-vetor primeiro vetor2 0)
      (do
        ;(println (multiplica-vetor primeiro vetor2 0))
        (soma-vetores (rpad-sequence (multiplica-vetor primeiro vetor2 0) (* 2 (count vetor2)) 0) (into [0] (standard-multiply (rest vetor1) vetor2)) 0)
        ))))

;(println (standard-multiply [9 0 9 8 5 5 1 9 3 1 3 2 7 3 0 7 7 7 4 6 2 8 4 3 5] [8 0 3 7 8 4 3 6 7 0 6 3 9 2 3 7 7 3 2 8 8 7 4 0 3]))
;(println (standard-multiply [7 1] [4 3]))

(defn gera-vetor
  "Gera um vetor de inteiros de tamanho n com digitos aleatorios entre 0 e 9"
  [n]
  (for [x (take n (range))]
    (rand-int 10)))

;(println (vec (gera-vetor 50)))
;(println "Calculando...")
;(println (standard-multiply (vec (gera-vetor 1200)) (vec (gera-vetor 1200))))
;(println (standard-multiply [9 0 9 8 5 5 1 9 3 1 3 2 7 3 0 7 7 7 4 6 2 8 4 3 5] [8 0 3 7 8 4 3 6 7 0 6 3 9 2 3 7 7 3 2 8 8 7 4 0 3]))
;(println "Finalizado!")



;====================== teste de nova funcao ==========================
(defn m5
  "FUNCIONANDO - Dados 2 vetores multiplica e retorna um vetor com os subvetores a serem somados"
  [v1 v2]
  (let [numeros-v1 (map-indexed vector v1)
        numeros-v2 (map-indexed vector v2)
        resultado (for [[i x] numeros-v1]
                        (let [res (for [[j y] numeros-v2]
                                    (let [mult (* x y)
                                          shift10n (+ 1 (+ i j))]
                                      (do
                                        ;(println "i=" i "j=" j)
                                        (if (< mult 10)
                                          (lpad-sequence [mult] shift10n 0)
                                          (lpad-sequence [(mod mult 10) (quot mult 10)] (+ 1 shift10n) 0)))
                                      ))]
                          (do
                            ;(println res)
                            ;(println (reduce soma-vetores2 [] res))
                            (reduce soma-vetores2 [] res))))]
    resultado))

;(println (soma-par-ordenado [2 7] [5 1]))
;(println(map soma-vetores-gera-par-ordenado [0 2 7] [0 0 8]))
;(println (reduce soma-par-ordenado (map soma-vetores-gera-par-ordenado [0 2 7] [0 0 8])))
;(println (soma-vetores2 [0 2 7] [0 0 8]))
;(println (m5 [9 1 9] [8 1 3]))
;(println (standard-multiply [9 1 9] [8 1 3]))
;(println (reduce soma-vetores2 (m3 [8 9] [9 7])))
;(println (reduce soma-vetores2 (m5 [9 0 9 8 5 5 1 9 3 1 3 2 7 3 0 7 7 7 4 6 2 8 4 3 5] [8 0 3 7 8 4 3 6 7 0 6 3 9 2 3 7 7 3 2 8 8 7 4 0 3])))

;(println (standard-multiply (gera-vetor 1200) (gera-vetor 1200)))
;abaixo com reduce e m5 funcionou, mas demora demais, inviavel
;(println (reduce soma-vetores2 (m5 (gera-vetor 150) (gera-vetor 150))))


;2 7 9 6 2 8 5 5 3 5 4 6 2 8 1 6 4 5 6 4 9 0 2 9 4 4 8 0 0 5 1 0 4 0 2 1 3 2 2 3 6 9 1 8 8 0 0 3 6 1 correto
;2 7 9 6 2 8 5 5 3 5 4 6 2 8 1 6 4 5 6 4 9 0 2 9 4 4 8 0 0 5 1 0 4 0 2 1 3 2 2 3 6 9 1 8 8 0 0 3 6 1





;-=============================================================================================================
;;procedure karatsuba(num1, num2)
;;if (num1 < 10) or (num2 < 10)
;;return num1*num2

;;*calculates the size of the numbers*
;;m = max(size_base10(num1), size_base10(num2))
;;m2 = m/2

;;*split the digit sequences about the middle*
;;high1, low1 = split_at(num1, m2)
;;high2, low2 = split_at(num2, m2)

;;*3 calls made to numbers approximately half the size*
;;z0 = karatsuba(low1, low2)
;;z1 = karatsuba((low1 + high1), (low2 + high2))
;;z2 = karatsuba(high1, high2)
;;return (z2 * 10 ^ (2 * m2)) + ((z1 - z2 - z0) * 10 ^ (m2)) + (z0)

;;equivalencia
;;low1 - x1
;;low2 - y1
;;
;;z1 - z2 - z0 = (low1 x high2) + (high2 x low2)

;KARATSUBA
(defn karatsuba
  [x y]
  (if (or (<= (count x) 4) (<= (count y) 4))
    (standard-multiply x y)
    (let [m (int (/ (max (count x) (count y)) 2))
          high1 (vec (first (split-at m x)))
          low1 (vec (last (split-at m x)))
          high2 (vec (first (split-at m y)))
          low2 (vec (last (split-at m y)))

          z0 (karatsuba low1 low2)
          z1 (karatsuba (soma-vetores low1 high1 0) (soma-vetores low2 high2 0))
          z2 (karatsuba high1 high2)

          fator1 (lpad-sequence z2 (1 + (* 2 m)) 0)
          fator2 (soma-vetores (standard-multiply low1 high2) (standard-multiply low2 high1) 0)]                                   ;definir

      (soma-vetores (soma-vetores fator1 fator2 0) z0 0)
      ;finalizacao da formula
      )))

;(println (int (/ (max (count [9 0 9 8 5 5 1 9 3 1 3 2 7 3 0 7 7 7 4 6 2 8 4 3 5]) (count [8 0 3 7 8 4 3 6 7 0 6 3 9 2 3 7 7 3 2 8 8 7 4 0 3])) 2)))
(println (karatsuba [9 0 9 8 5 5] [8 0 3 7 8 4]))
;(println (vec (first (split-at 2 [9 0 9 8 5 5]))))
;(println (vec (last (split-at 2 [9 0 9 8 5 5]))))