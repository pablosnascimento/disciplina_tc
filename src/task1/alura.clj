(ns exercicio1.alura)

(defn aplica-desconto
  "Retorna o valor com desconto de 10%"
  [valor-bruto]
  (let [desconto 0.1]
    (* valor-bruto (- 1 desconto))))

(println (aplica-desconto 100))