(ns exercicio1.aula3)

(defn valor-descontado
  "Retorna o valor com desconto de 10%"
  [valor-bruto]
  (let [desconto 0.1]
    (* valor-bruto (- 1 desconto))))

(valor-descontado 100)

(defn aplica-desconto?
  [valor-bruto]
  (if (> valor-bruto 100)
    true
    false))

(println (aplica-desconto? 800))