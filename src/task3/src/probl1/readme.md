# Tarefa 3 -- Teoria da Computação -- 2021/2

**Funções com Domínios Infinitos, Autômatose Expressões Regulares & Loops e Infinitude & Modelos Equivalentes de Computação**
**Autor:** Pablo S Nascimento

## Problema 1 - Parte 2

Este problema pede uma implementação em Clojure da construção do autômato resultante do produto assíncrono dos autômatos M1 e M2.

**Solução**
A solução foi criar funções em clojure responsáveis por construir cada parte do autômato e, por fim, chamar todas em uma função principal e construir o autômato final.

Cada função define sua respectiva parte: 
a) new-alphabet: recebe dois autômatos e retorna o novo alfabeto. Como o problema especifica que ambos autômatos terão o mesmo alfabeto, basta pegar o alfabeto do primeiro autômato.
b) new-states: recebe dois autômatos e retorna o conjunto de estados do produto cartesiano entre eles.
c) new-initial: recebe dois autômatos e retorna o par-ordenado que define o estado inicial do novo automato assíncrono.
d) new-accepting: recebe dois autômatos e retorna o conjunto de estados aceitáveis do novo automato.
e) new-transitions: recebe dois autômatos e retorna o conjunto de transições do novo automato.
f) assinc-aut: recebe dois autômatos e define o autômato assíncrono entre eles chamando as definições anteriores.
g) renomear-estados: recebe um prefixo que será adicionado ao nome dos estados em todo o automato.

Além disso, foi necessário garantir que não possuam os mesmos nomes nos estados, portanto utilizei funções que renomeiam os estados adicionando um prefixo escolhido no nome de cada estado em todo o autômato. Para renomear os estados de um automato, chamar a função "renomear-estados" informando um prefixo a ser concatenado no nome dos estados.

Caso não seja necessário renomear os estados, pois já possuam nomes diferentes, não é necessário chamar a função de "renomear-estados", apenas assinc-aut passando os automatos.

**Execução**
Para executar a função basta chamar no clojure:
(assinc-aut (renomear-estados automato1 "a_") (renomear-estados automato2 "b_"))

onde "automato1" e "automato2" são lidos do arquivo "automatos.txt" contendo um array com dois automatos no formato AST, conforme exemplo contido no arquivo "automatos.txt"



