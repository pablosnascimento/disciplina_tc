(ns exercicio1.grade-school)

(defn rpad-sequence
  "Completa a sequencia seq passada com n valores x à direita"
  [seq n x]
  (vec (take n (concat seq (repeat x)))))

(defn lpad-sequence
  "Completa a sequencia seq passada com n valores x à esquerda"
  [seq n x]
  (vec (concat (take (- n (count seq)) (repeat x)) seq)))

(defn soma-vets
  "Recebe vetor v1 e soma com vetor par-ordenado v2 (numero e carry) e retorna o vetor soma"
  [v1 v2]
  (let [vetor1 (rpad-sequence v1 (count v2) 0)]
    (let [soma (+ (first vetor1) (first v2))]
      (if (< soma 10)
        (conj (conj (vec (butlast vetor1)) soma) (last v2))
        (do
          (let [somacoef (+ (quot soma 10) (last v2))]
            (if (< somacoef 10)
              (conj (conj (vec (butlast vetor1)) (mod soma 10) somacoef))
              (conj (conj (vec (butlast vetor1)) (mod soma 10) (mod somacoef 10) (quot somacoef 10))))))))))



(defn multiplica
  "Dados 2 vetores multiplica e retorna um vetor com os subvetores a serem somados"
  [v1 v2]
  (let [numeros-v1 (zipmap v1 (range))
        numeros-v2 (zipmap v2 (range))
        resultado (for [[x i] numeros-v1
                        [y j] numeros-v2
                        ;:when (= i (+ j 1))
                        ]
                    (let [mult (* x y)]
                      (lpad-sequence [mult] (+ 1 (+ i j)) 0))
                    )]
    resultado))

(println (soma-vets [3 0] [0 6]))

;(println (multiplica [3 4] [1 2]))
;(println (reduce soma-vets (multiplica [3 4] [1 2])))