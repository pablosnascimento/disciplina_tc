# Trabalho 2 -- Teoria da Computação -- 2021/2

**Projeto e Análise de Algoritmos**
**Autor:** Pablo S Nascimento

## Problema 11269 – Setting Problem

Este problema apresenta dois personagens que precisam executar algumas tarefas durante o dia que custam horas bem definidas. Porém, o segundo só pode iniciar uma determinada tarefa quando o primeiro a finalizar. Pede-se, então, qual a sequência de tarefas deverá ser realizada cujo custo de tempo seja o menor possível.

## Artefatos

Disponibilizei um arquivo chamado teste.txt contendo as entradas que se desejar testar.

**Análise de Complexidade**

Minha solução faz a leitura das entradas e as interpreta pelo for linha a linha, constrói cada caso de teste e o avalia. 

A solução implementada partiu de um princípio: dadas duas tarefas nós conseguimos avaliar e dizer qual a ordem tem o menor custo. Sabendo disso, construímos uma função que calcula o custo de realizar a tarefa 'a' seguida de 'b'. Uma vez definida esta função, temos a base para aplicar esta função a uma ordenação da lista de tarefas inicial conforme a regra deste custo. Ou seja, no fim, teremos uma lista com a ordem correta das tarefas a ser executada e precisamos apenas de calcular o valor do custo total.

Assim, para cada Work devidamente preenchido pelas entradas calcula o resultado pela função Calculate().

ordenado = sorted(self.Jobs, key=functools.cmp_to_key(OrdenaJob))      #n log n

tempoSultan = 0														   #1
tempoDescontado = 0													   #1
for i in range(len(ordenado)):                                         #n
    task = ordenado[i]                                                 #n
    tempoSultan += task[0]                                             #n
																	   
    tempoDescontado -= task[0]                                         #n
    if (tempoDescontado < 0):                                          #n
        tempoDescontado = 0                                            #menos que n vezes
																	   
    tempoDescontado += task[1]                                         #n
																	   
return tempoSultan + tempoDescontado                                   #1

A função OrdenaJob tem custo O(1), logo, a complexidade da solução apresentada no pior caso tende a O(n log n) devido às execução de *ordenação*.

**Execução**
> Variável global ENV = 2 -> teste local; ENV = 1 -> submissão no UVA Judge.

> Com arquivo teste.txt preenchido, para executar a função no prompt execute, por exemplo:

python 11269.py > resultado.txt

