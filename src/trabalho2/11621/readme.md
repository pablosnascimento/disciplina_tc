# Trabalho 2 -- Teoria da Computação -- 2021/2

**Projeto e Análise de Algoritmos**
**Autor:** Pablo S Nascimento

## Problema 11621 – Smallest Factor

Este problema fornece um conjunto de numeros inteiros e, para cada um número n pertencente ao conjunto, pede que seja encontrado o próximo número inteiro maior que n tal que seja expressado como um produto dos fatores primos 2 e 3 de n.

## Artefatos

Disponibilizei um arquivo chamado teste.txt contendo as entradas que se desejar testar.

**Análise de Complexidade**

Minha solução faz a leitura das entradas e as interpreta pelo for linha a linha, constrói cada caso de teste e o avalia. 

A solução implementada se valeu do fato de o problema limitar o máximo número utilizado a 2^31, mas consideraremos a análise de complexidade como sendo ilimitado.

Primeiro, calculamos todos números formados pelas multiplicações de 2 e 3  com a função CalculateAll().

while i < 32:						#n, generalizando para n
	while j < 32:                   #n(n)
		pow2i = pow(2,i)            #n(n)
		pow3j = pow(3,j)            #n(n)
		menor = pow2i * pow3j       #n(n)
									#
		self.F.append(menor)        #n(n)
		j += 1                      #n(n)
									#
	j = 0                           #n
	i += 1                          #n
									#
self.F = sorted(self.F)             #n log n

T(n) = n + 6*n(n) + 2n + n(logn)
T(n) = 6n² + 3n + n(logn)
T(n) = n²

Portanto, a complexidade de CalculateAll() é O(n²). Em seguida, é chamada a função SmallestFactor(n) que efetivamente traz a resposta do problema. O custo desta função é dado por:

 for i in range(len(self.F)):		#n², o custo seria n=linear, mas como o tamanho de F já é n² (pelo resultado na função anterior), o custo se torna n²
            value = self.F[i]       #n²
            if (value >= m):        #n²
                return value        #

Poranto, a classe de complexidade da solução neste problema é dada por O(n²).

**Execução**
> Variável global ENV = 2 -> teste local; ENV = 1 -> submissão no UVA Judge.

> Com arquivo teste.txt preenchido, para executar a função no prompt execute, por exemplo:

python 11621.py > resultado.txt

