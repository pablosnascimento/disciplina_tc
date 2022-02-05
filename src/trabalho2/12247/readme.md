# Trabalho 2 -- Teoria da Computação -- 2021/2

**Projeto e Desenvolvimento de Algoritmos**
**Autor:** Pablo S Nascimento

## Problema 12247 - Jollo

Este problema pede a identificação da última carta que pode ser distribuída para uma partida de um jogo de modo que o segundo jogador sempre vença, caso a vitória possa ser garantida.

Dadas 6 cartas, 3 para cada jogador, o jogo consiste em as cartas serem apresentadas duas a duas, uma de cada jogador: a carta de maior valor vence. São 3 rodadas na partida confrontando 2 cartas de cada vez. Na dinamica do jogo, as cartas são distribuídas em ordem: 3 para o primeiro jogador e duas para o segundo. A definição de qual a menor carta que pode garantir a vitoria ao segundo jogador é o problema a ser resolvido.

## Artefatos

Disponibilizei um arquivo chamado teste.txt contendo as entradas que se desejar testar.

**Solução**
A solução dada foi identificar um valor de carta inicial e testar todas possibilidades de comparação entre as 3 cartas dos dois jogadores e verificar se em todas o principe vence. 
a) Caso não haja alguma combinação na distribuição das cartas em que ele perca, a carta aumenta para o próximo valor possível e testa todas possibilidades novamente. Repete-se o processo até que encontre um cenário em que vença e retorna o valor da carta ou até que estoure 52 e retorna -1. 
b) Caso o principe vença com o valor da carta inicial proposta, busca-se o próximo valor possível de carta para baixo e testa todas combinações com esta carta. Repete-se o processo enquanto o principe vencer em todas possibilidades até encontrar uma carta em que ele perca ou estoure a carta para 0. Neste caso a menor carta é a que ofereceu a última vitoria testada.

**Análise de Complexidade**

Minha solução faz a leitura das entradas e as interpreta pelo for linha a linha, constrói cada Round de jogada e o avalia pela função DefineLastCard(), função que iremos analisar:

self.GetNextCard(1)														# O(n), no pior caso percorre todas cartas
self.output = self.Cards2[2]                                            # 1
cardVitoria = -1                                                        # 1
																		
m = DefineMatriz(self)                                                  # O(1), apenas operações de custo 1 sem dependência de n
derrotas = ObterDerrotas(m)                                             # O(n), Analisada logo abaixo!
																		
while len(derrotas) > 0 and self.Cards2[2] > -1:                        # n, pode começar com a carta mais alta e ter que descer até o limite inferior
	self.GetNextCard(1)                                                 # n(n)
	m = DefineMatriz(self)                                              # n
																		
	derrotas = ObterDerrotas(m)                                         # n(n)
	self.output = self.Cards2[2]                                        # n
																		
else:                                                                   
	cardVitoria = self.output                                           # 1
	derrotas = []                                                       # 1
																		
	if(self.Cards2[2] > -1):                                            # 1
		while len(derrotas) == 0 and self.Cards2[2] > 0:                # n, na verdade, n - o número de repetições do while anterior. Ou seja, não se multiplicam, se complementam executando para a lista de n cartas
																		
			cardVitoria = self.Cards2[2]                                # n
																		
			self.GetNextCard(-1)                                        # n
			m = DefineMatriz(self)                                      # n
																		
			derrotas = ObterDerrotas(m)                                 # n
		else:                                                           
			self.output = cardVitoria                                   # 1
			
			
-----------------
Complexidade da função ObterDerrotas(m):

perde = [x for x in m if (x[0] > x[3] and x[1] > x[4]) or (x[0] > x[3] and x[2] > x[5]) or (x[1] > x[4] and x[2] > x[5])]

para cada elemento x em m executa as comparações logicas que tem custo 1, portanto, é O(n).
-----------------

Logo, temos a complexidade O(n²) para nossa solução principalmente devido ao custo de ObterDerrotas (que é O(n)) para cada vez que avaliarmos uma nova carta (O(n) também).

**Execução**
> Variável global ENV = 2 -> teste local; ENV = 1 -> submissão no UVA Judge.

> Com arquivo teste.txt preenchido, para executar a função no prompt execute, por exemplo:

python 12247.py > resultado.txt