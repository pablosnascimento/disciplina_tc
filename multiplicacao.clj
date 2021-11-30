(ns exercicio1.multiplicacao)

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
      (if (nil? i1) (if (nil? i2) (+ exp 0)) (+ exp i2))        ;se i1 e i2 foram nulos retorna 0;
      (if (nil? i2) (+ exp i1))
      (let [resultado (+ i1 i2)]
        (let [soma (+ resultado exp)]
          (if (= (count vetor2) 1)                              ;OBS1
            (do
              (if (< soma 10)
                [soma]
                [(mod soma 10) (quot soma 10)]))
            (do
              (if (< soma 10)
                (into [soma] (soma-vetores (rest vetor1) (rest vetor2) (quot soma 10)))
                (into [(mod soma 10)] (soma-vetores (rest vetor1) (rest vetor2) (quot soma 10)))))))))))

;(println (soma-vetores [2 4 6 5 6 5 3 2] [1 9 2 4 6 8 9 3] 0))
;(println (soma-vetores [2 4 0] [1 9 2] 0))

(defn rpad-sequence
  "Completa a sequencia seq passada com n valores x à direita"
  [seq n x]
  (vec (take n (concat seq (repeat x)))))

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

(println (standard-multiply [9 0 9 8 5 5 1 9 3 1 3 2 7 3 0 7 7 7 4 6 2 8 4 3 5]
                            [8 0 3 7 8 4 3 6 7 0 6 3 9 2 3 7 7 3 2 8 8 7 4 0 3]))















