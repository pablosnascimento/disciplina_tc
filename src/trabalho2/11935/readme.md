# Trabalho 2 -- Teoria da Computação -- 2021/2

**Projeto e Análise de Algoritmos**
**Autor:** Pablo S Nascimento

## Problema 11935 – Through the Desert

Este problema pede uma solução que calcule o menor volume de tanque possível (em litros) que garanta que o veículo complete o percurso do trajeto informado conforme as regras estabelecidas.

## Artefatos

Disponibilizei um arquivo chamado teste.txt contendo as entradas que se desejar testar.

**Análise de Complexidade**

Minha solução faz a leitura das entradas e as interpreta pelo for linha a linha, constrói cada caso de teste e o avalia. 

A solução implementada faz a leitura dos eventos à medida em que vão ocorrendo e os trata internamente na classe Car para que, quando solicitado sobre o custo atual, informe uma resposta.

Todas funções chamadas no programa são O(1), portanto, a classe de complexidade da solução neste problema é dada por O(n), pois serão chamadas n vezes, conforme a quantidade de eventos que o programa irá receber.

**Execução**
> Variável global ENV = 2 -> teste local; ENV = 1 -> submissão no UVA Judge.

> Com arquivo teste.txt preenchido, para executar a função no prompt execute, por exemplo:

python 11935.py > resultado.txt

