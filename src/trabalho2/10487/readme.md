# Trabalho 2 -- Teoria da Computação -- 2021/2

**Projeto e Análise de Algoritmos**
**Autor:** Pablo S Nascimento

## Problema 10487 – Closest Sum

Este problema fornece como entrada um conjunto de numeros inteiros e uma sequencia de queries, que são casos de teste. Cada query fornece um número e pede que se encontre qual a soma de dois números existentes no cojunto tem o valor mais próximo do número indicado.

## Artefatos

Disponibilizei um arquivo chamado teste.txt contendo as entradas que se desejar testar.

**Análise de Complexidade**

Minha solução faz a leitura das entradas e as interpreta pelo for linha a linha, constrói cada caso de teste e o avalia. 

Para cada Case devidamente preenchido pelas entradas calcula o resultado pela função Calculate(). Neste caso, temos a execução desta função para cada novo Case definido. Analisemos, portanto, a função Calculate().

ordenado = sorted(self.set)                             # n log n
...
for n in self.numbers:                                  # m, depende da quantidade de elementos de self.numbers
    ...
    while min_idx < max_idx:                            # m*n, vai executar uma vez para cada elemento do conjunto self.set
        soma = ordenado[min_idx] + ordenado[max_idx]    # m*n + (n/2) + (n/2),  executará o slice em ordenado n/2 vezes para min_idx e max_idx

        ...                                             # demais funções O(1)

Logo, a complexidade da solução apresentada no pior caso tende a O(n*m), pois o maior custo é o while dentro do for com slice em O(n) sendo executado a cada vez. Se a quantidade de m e n forem iguais, tende a O(n²).

**Execução**
> Variável global ENV = 2 -> teste local; ENV = 1 -> submissão no UVA Judge.

> Com arquivo teste.txt preenchido, para executar a função no prompt execute, por exemplo:

python 10487.py > resultado.txt