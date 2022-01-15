# Trabalho 2 -- Teoria da Computação -- 2021/2

**Projeto e Desenvolvimento de Algoritmos**
**Autor:** Pablo S Nascimento

## Problema 12247 - Jollo

Este problema pede a identificação da última carta que pode ser distribuída para uma partida de um jogo de modo que o segundo jogador sempre vença, caso a vitória possa ser garantida.

Dadas 6 cartas, 3 para cada jogador, o jogo consiste em as cartas serem apresentadas duas a duas, uma de cada jogador: a carta de maior valor vence. São 3 rodadas na partida confrontando 2 cartas de cada vez. Na dinamica do jogo, as cartas são distribuídas em ordem: 3 para o primeiro jogador e duas para o segundo. A definição de qual a menor carta que pode garantir a vitoria ao segundo jogador é o problema a ser resolvido.

**Solução**
A solução dada foi identificar um valor de carta inicial e testar todas possibilidades de comparação entre as 3 cartas dos dois jogadores e verificar se em todas o principe vence. 
a) Caso não haja alguma combinação na distribuição das cartas em que ele perca, a carta aumenta para o próximo valor possível e testa todas possibilidades novamente. Repete-se o processo até que encontre um cenário em que vença e retorna o valor da carta ou até que estoure 52 e retorna -1. 
b) Caso o principe vença com o valor da carta inicial proposta, busca-se o próximo valor possível de carta para baixo e testa todas combinações com esta carta. Repete-se o processo enquanto o principe vencer em todas possibilidades até encontrar uma carta em que ele perca ou estoure a carta para 0. Neste caso a menor carta é a que ofereceu a última vitoria testada.

**Execução**
É possível executar o código preenchendo o arquivo teste.txt com as entradas possíveis, uma por linha, finalizando com a linha 0 0 0 0 0
Alterar o valor da variável ENV = 2 para executar lendo do arquivo e ENV = 1 para submeter o código no Online Judge.