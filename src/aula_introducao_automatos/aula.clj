(defn xor [x]
  (mod (apply + x) 2))

;apply = aceita n argumentos e a operação é feita sobre todos dois a dois
;(apply + [1 2 3] === (+ 1 2 3)
;reduce = aceita dois argumentos e a operação é feita
;(reduce + [1 2 3] === (+ (+ 1 2) 3)

(println (xor [1 2 3]))

;exercicios de clojure:
; função: verificar se string é palindromo
;PALINDROMO(x) = 1, se para todo [|x|], ou seja, {0, 1, 2... n-1}, então Xi = X_size(x)-1