# Trabalho 2 -- Teoria da Computação -- 2021/2

**Projeto e Análise de Algoritmos**
**Autor:** Pablo S Nascimento

## Problema 10602 - Editor Nottoobad

Este problema fornece como entrada um conjunto de palavras e pede-se qual o menor custo para digitá-las todas.

## Artefatos

Disponibilizei um arquivo chamado teste.txt contendo as entradas que se desejar testar.

**Análise de Complexidade**

Minha solução faz a leitura das entradas e as interpreta pelo for linha a linha, constrói cada caso de teste e o avalia. 

Para cada Case devidamente preenchido pelas entradas calcula o resultado pela função Calculate().

participantes = self.words[1:]                                  # O(n)
iguais = [item for item in participantes if item == palavra]    # O(n), percorre todos elelementos uma vez e adiciona no vetor (compara O(1) e append O(1) em cada n)
...
while size > 0:                                                 # O(1), pois será executado uma vez para cada letra da palavra, ou seja, no máximo a quantidade de letras de uma palavra, independe de n.
    vetor = [item for item in participantes if item.startswith(raiz) and item != palavra and item not in mesmoComeco]                     # O(n)
    vetor = sorted(vetor)                                       # O(n log n)
    ...

restantes = [item for item in participantes if item not in mesmoComeco]     # O(n), cada item em participantes + O(m) not in mesmo Conexao
restantes = sorted(restantes)                                               # O(n log n)
...
while n > 0:                                                    # O(n)
    next_word = ordenado[index]                                 # O(1)
    self.DefinirQtdDigitacoes(next_word, palavra)               # O(1), apenas chamadas relacionadas ao tamanho da palavra, indepdende de n.
    ...                                                         # O(1)

Logo, a complexidade da solução apresentada no pior caso tende a O(n log n) devido às execuções de *ordenação*. Embora faça while ordenando novamente subvetores, a quantidade dessas chamadas de ordenação depende diretamente do tamanho das palavras que é limitado, portanto, podemos considerar constante.

**Execução**
> Variável global ENV = 2 -> teste local; ENV = 1 -> submissão no UVA Judge.

> Com arquivo teste.txt preenchido, para executar a função no prompt execute, por exemplo:

python 10602.py > resultado.txt