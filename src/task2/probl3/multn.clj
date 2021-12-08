(ns task2.probl3.multn
  (:require [clojure.string :as str]))
(use 'clojure.java.io)
(:gen-class :main true)

;comandos NOT, AND, OR, XOR, ONE e XERO definidos em NAND-CIRC.
(def definicoes
  "def NOT(a):\n\treturn NAND(a,a)\nend\n\ndef OR(a,b):\n\tnaa = NAND(a,a)\n\tnbb = NAND(b,b)\n\treturn NAND(naa,nbb)\nend\n\ndef AND(a,b):\n\tnab = NAND(a,b)\n\treturn NAND(nab,nab)\nend\n\ndef XOR(a, b):\n\tor1 = OR(a, b)\n\tand1 = AND(a, b)\n\tnand1 = NOT(and1)\n\treturn AND(or1, nand1)\nend\n\ndef ONE(a):\n\tna = NAND(a,a)\n\treturn NAND(a,na)\nend\n\ndef ZERO(a):\n\tnota = ONE(a)\n\treturn NOT(nota)\nend\n\n")

;number -> list
(defn fatores-multn
  "Lista os comandos em NAND-CIRC para multiplicar dois números de size bits."
  [size]
  (for [slinha (range size)
        posi (range (inc (+ size slinha)))
        ;atribui ZERO no bit menos significativo ou
        ;ou ZERO para o bit de mais alta ordem da soma anterior
        :let [result (if (or (> slinha posi) (= posi (+ size slinha)))
                       (format "s%d_%d = ZERO(X[0])\n" slinha posi)
                       (format "s%d_%d = AND(X[%d],X[%d])\n" slinha posi (dec (- size slinha)) (+ (- (dec (* 2 size)) posi) slinha)))]]
    result))

;number string string -> list
(defn for-soma
  "Lista os comandos para somar dois numeros de size bits."
  [size f1 f2]
    (for [i (range (inc size))                              ;aumenta 1 devido ao bit que é acrescentado na soma com o fator de baixo
          :let [nome1 (format "%s_%d" f1 i)
                nome2 (format "%s_%d" f2 i)
                zerar_carry (format "\nCarry%d = ZERO(X[0])\n" i)
                soma (format "Soma%s_%d" f2 i)
                soma-f (format "Soma%s_%d = AND(Carry%d, Carry%d)" f2 (inc i) (inc i) (inc i))
                result (format "%stemp%d = XOR(%s, %s)\n%s = XOR(temp%d, Carry%d)\na%d = AND(%s, %s)\nb%d = AND(temp%d, Carry%d)\nCarry%d = OR(a%d, b%d)\n%s" (if (zero? i) zerar_carry "\n") i nome1 nome2 soma i i i nome1 nome2 i i i (inc i) i i (if (= i size) soma-f ""))]]
      result))

;number -> list
(defn somas-dos-fatores
  "Lista os 'for' de cada soma dos fatores resultantes da multiplicação para um número de size bits."
  [size]
  (for [i (range (dec size))
          :let [f (for-soma (+ size i) (if (zero? i) (format "s%d" i) (format "Somas%d" i)) (format "s%d" (inc i)))]]
    f))

;number string -> list
(defn saidas-y
  "Lista as saídas de Y[i] do programa NAND-CIRC."
  [size nome-soma]
  (for [i (range (* 2 size))
        :let [nome (format "%s_%d" nome-soma (- (dec (* 2 size)) i))
              f (format "\nY[%d] = AND(%s, %s)" i nome nome)]]
    f))

(defn multn
  "Prepara um codigo NAND-CIRC com plus para execução da multiplicação de dois numeros de n bits."
  [n]
  (let [fatores (fatores-multn n)
        somas (apply concat (somas-dos-fatores n))
        saidas (saidas-y n (format "Somas%d" (dec n)))]        ;o nome da ultima variavel de soma será Somas(n-1)
    (concat fatores somas saidas)))

;TESTES
;(pp/pprint (fatores-multn 3))
;(println (fatores-multn 3))
;(println (for-soma 3 "s0" "s1"))
;(println (somas-dos-fatores 3))
;(println (saidas-y 3 "Somaf"))
;(println (multn 4))

(defn imprime-multn
  [n]
  (with-open [wrtr (writer (format "mult%d.txt" n))]
    (.write wrtr (clojure.string/join "" (cons definicoes (multn 4))))))

;(imprime-multn 4)

;testando chamar por linha de comando
(defn -main [n]
  (imprime-multn n))