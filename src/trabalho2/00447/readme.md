# Trabalho 2 -- Teoria da Computação -- 2021/2

**Projeto e Análise de Algoritmos**
**Autor:** Pablo S Nascimento

## Problema 00447 - Population Explosion

Este problema pede uma interpretação das regras do crescimento populacional em uma determinada região ao longo de um número de anos indicado.

## Artefatos

Disponibilizei um arquivo chamado teste.txt contendo as entradas que se desejar testar.

**Análise de Complexidade**

Minha solução faz a leitura das entradas e as interpreta pelo for linha a linha, constrói cada caso de teste e o avalia. 

Para cada ano em n indicado na entrada implica em uma chamada de avaliação da população pela função BuildPopulationNextYear(). Neste caso, temos n vezes a execução desta função; além desta, apenas funções aritméticas e de impressão que não depdendem de n, portanto são O(1).

Analisemos, portanto, a função BuildPopulationNextYear().

pop = [[0 for i in range(SIZE)] for j in range(SIZE)]   # 20*20=O(1), não depende de n
														#
if self.years == 0:                                     # 1
    return []                                           # 1
														#
for i in range(SIZE):                                   # 1*20=O(1)
    for j in range(SIZE):                               # 1*40=O(1)
        survive = self.NextYear(i,j)                    # Custo de NextYear()
        pop[i][j] = survive                             # 1
														#
self.location = pop                                     # 1

Portanto O(1) + Custo de NextYear(). Vamos avaliá-la:
NextYear() chama Neighbors() que tem custo O(1) preparando a matriz independente de n. As funções Rule1, Rule2, e Rule3 chamam a função Living() que é O(1) e não acrescentam mais nada além de O(1). Portanto, a função NextYear() é O(1). 

Logo, a complexidade da solução apresentada é O(n), sempre dependendo do número de anos que se deseja trabalhar a projeção da próxima população.

**Execução**
> Variável global ENV = 2 -> teste local; ENV = 1 -> submissão no UVA Judge.

> Com arquivo teste.txt preenchido, para executar a função no prompt execute, por exemplo:

python 00447.py > resultado.txt