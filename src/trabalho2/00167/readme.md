# Trabalho 2 -- Teoria da Computação -- 2021/2

**Projeto e Análise de Algoritmos**
**Autor:** Pablo S Nascimento

## Problema 00167 - The Sultan Sucessor

Este problema é uma variação do conhecido problema das 8 rainhas. Neste caso, cada posição do tabuleiro é numerada com valores de 1 a 100 e pede-se qual a maior pontuação possível dentre todas soluções.

**Artefatos**

Disponibilizei um arquivo chamado teste.txt contendo as entradas que se desejar testar.

**Solução**

Minha solução se vale de que a solução para o problema das 8 rainhas é conhecido, ou seja, as 92 soluções são conhecidas. Tais soluções podem ser resumidas em apenas 12 a partir das quais giramos o tabuleiro e temos mais novas configurações. 4 giros temos 4x12 = 48 soluções; para cada uma delas, podemos também espelhar o tabuleiro na vertical, o que nos fornece mais 48 soluções, totalizando as 92 conhecidas.

Assim, construí a classe Solucoes que prepara as 12 soluções principais. Para cada novo tabuleiro proposto, calculo todos giros e espelhamento do tabuleiro para cada solução dentre as 12 conhecidas. A cada avaliação dessa, retorna qual forneceu a maior soma; ao final, avalia-se qual dentre todas somas resultantes é a maior.

**Análise de Complexidade**

Minha solução faz a leitura das entradas e preenche a matriz do tabuleiro e chama a função Calculate que fornece a soma final. Assim, vamos analisar esta função:

solucoes = Solucoes()												# O(n), constrói matrizes fixas 8x8 (extrapolando seria nxn)
																	
final = []                                                          # 1
final.append(MaiorSomaVariacoes(solucoes.S1, self.tabuleiro))       # O(n³), custo da função avaliado abaixo!
final.append(MaiorSomaVariacoes(solucoes.S2, self.tabuleiro))       # O(n³)
final.append(MaiorSomaVariacoes(solucoes.S3, self.tabuleiro))       # O(n³)
final.append(MaiorSomaVariacoes(solucoes.S4, self.tabuleiro))       # O(n³)
final.append(MaiorSomaVariacoes(solucoes.S5, self.tabuleiro))       # O(n³)
final.append(MaiorSomaVariacoes(solucoes.S6, self.tabuleiro))       # O(n³)
final.append(MaiorSomaVariacoes(solucoes.S7, self.tabuleiro))       # O(n³)
final.append(MaiorSomaVariacoes(solucoes.S8, self.tabuleiro))       # O(n³)
final.append(MaiorSomaVariacoes(solucoes.S9, self.tabuleiro))       # O(n³)
final.append(MaiorSomaVariacoes(solucoes.S10, self.tabuleiro))      # O(n³)
final.append(MaiorSomaVariacoes(solucoes.S11, self.tabuleiro))      # O(n³)
final.append(MaiorSomaVariacoes(solucoes.S12, self.tabuleiro))      # O(n³)
																	
maiorSoma = max(final)                                              # O(n), a princípio n, mas não está claro como irá variar, vai depender de quantas soluções fundamentais cada n irá possuir
																	
self.output = maiorSoma                                             # 1

---------------
Custo de MaiorSomaVariacoes():
rotate1 = list(zip(*solution[::-1]))			# n(n²)
rotate2 = list(zip(*rotate1[::-1]))             # n(n²)
rotate3 = list(zip(*rotate2[::-1]))             # n(n²)
												
reverse = list(reversed(solution[:]))           # n(n²)
rev1 = list(zip(*reverse[::-1]))                # n(n²)
rev2 = list(zip(*rev1[::-1]))                   # n(n²)
rev3 = list(zip(*rev2[::-1]))                   # n(n²)
												
final = []                                      #
final.append(Soma(solution, tabuleiro))         # n, custo da função Soma avaliado abaixo!
final.append(Soma(rotate1, tabuleiro))          # n
final.append(Soma(rotate2, tabuleiro))          # n 
final.append(Soma(rotate3, tabuleiro))          # n
final.append(Soma(reverse, tabuleiro))          # n
final.append(Soma(rev1, tabuleiro))             # n
final.append(Soma(rev2, tabuleiro))             # n
final.append(Soma(rev3, tabuleiro))             # n

return max(final)								# n, a princípio n, mas não está claro como irá variar, vai depender de quantas soluções cada n irá possuir

T(n) = O(n³)
---------------
Custo de Soma():
m = NewMatrix()									# n
for i in range(8):								# n
	m[i] = [m1[i][0]*m2[i][0], m1[i][1]*m2[i][1], m1[i][2]*m2[i][2], m1[i][3]*m2[i][3], m1[i][4]*m2[i][4], m1[i][5]*m2[i][5], m1[i][6]*m2[i][6], m1[i][7]*m2[i][7]]

soma = sum(map(sum, m))							# n
return soma

T(n) = O(n)
---------------

Logo, a complexidade da solução final é dada por O(n³).

**Execução**
> Variável global ENV = 2 -> teste local; ENV = 1 -> submissão no UVA Judge.

> Com arquivo teste.txt preenchido, para executar a função no prompt execute, por exemplo:

python 00167.py > resultado.txt