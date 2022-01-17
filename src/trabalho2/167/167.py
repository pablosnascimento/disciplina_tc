'''
167 – The Sultan Sucessor
'''

import sys
import os

#ENV: 1 submissao; 2 - teste local
ENV = 1
PROBLEM_ID = 167

def PrintOutput(output):
    '''
    Imprime o valor passado.
    '''
    msg = str(output)

    if(ENV == 1):
        sys.stdout.write(msg+"\n")
    else:
        print(msg)

def LerEntradas():
    '''
    Lê o arquivo de entrada com os comandos a serem interpretados.
    '''
    #submissao stdin
    if(ENV == 1):
        lines = sys.stdin.readlines()
        lines = [line.rstrip() for line in lines]
    else:
        #testes em arquivo local
        with open(os.getcwd()+'\\%s\\teste.txt' % str(PROBLEM_ID)) as file:
            lines = [line.strip() for line in file]

    return lines

def NewMatrix():
    linha = [0] * 8
    m = [linha] * 8
    return m

def Solution1():
    m = NewMatrix()
    m[0] = [0,0,0,1,0,0,0,0]
    m[1] = [0,0,0,0,0,0,1,0]
    m[2] = [0,0,1,0,0,0,0,0]
    m[3] = [0,0,0,0,0,0,0,1]
    m[4] = [0,1,0,0,0,0,0,0]
    m[5] = [0,0,0,0,1,0,0,0]
    m[6] = [1,0,0,0,0,0,0,0]
    m[7] = [0,0,0,0,0,1,0,0]
    return m

def Solution2():
    m = NewMatrix()
    m[0] = [0,0,0,0,1,0,0,0]
    m[1] = [0,1,0,0,0,0,0,0]
    m[2] = [0,0,0,1,0,0,0,0]
    m[3] = [0,0,0,0,0,0,1,0]
    m[4] = [0,0,1,0,0,0,0,0]
    m[5] = [0,0,0,0,0,0,0,1]
    m[6] = [0,0,0,0,0,1,0,0]
    m[7] = [1,0,0,0,0,0,0,0]
    return m
    
def Solution3():
    m = NewMatrix()
    m[0] = [0,0,0,1,0,0,0,0]
    m[1] = [0,1,0,0,0,0,0,0]
    m[2] = [0,0,0,0,0,0,1,0]
    m[3] = [0,0,1,0,0,0,0,0]
    m[4] = [0,0,0,0,0,1,0,0]
    m[5] = [0,0,0,0,0,0,0,1]
    m[6] = [0,0,0,0,1,0,0,0]
    m[7] = [1,0,0,0,0,0,0,0]
    return m

def Solution4():
    m = NewMatrix()
    m[0] = [0,0,0,1,0,0,0,0]
    m[1] = [0,0,0,0,0,1,0,0]
    m[2] = [0,0,0,0,0,0,0,1]
    m[3] = [0,0,1,0,0,0,0,0]
    m[4] = [1,0,0,0,0,0,0,0]
    m[5] = [0,0,0,0,0,0,1,0]
    m[6] = [0,0,0,0,1,0,0,0]
    m[7] = [0,1,0,0,0,0,0,0]
    return m

def Solution5():
    m = NewMatrix()
    m[0] = [0,0,1,0,0,0,0,0]
    m[1] = [0,0,0,0,0,1,0,0]
    m[2] = [0,0,0,0,0,0,0,1]
    m[3] = [1,0,0,0,0,0,0,0]
    m[4] = [0,0,0,1,0,0,0,0]
    m[5] = [0,0,0,0,0,0,1,0]
    m[6] = [0,0,0,0,1,0,0,0]
    m[7] = [0,1,0,0,0,0,0,0]
    return m

def Solution6():
    m = NewMatrix()
    m[0] = [0,0,0,0,1,0,0,0]
    m[1] = [0,0,1,0,0,0,0,0]
    m[2] = [0,0,0,0,0,0,0,1]
    m[3] = [0,0,0,1,0,0,0,0]
    m[4] = [0,0,0,0,0,0,1,0]
    m[5] = [1,0,0,0,0,0,0,0]
    m[6] = [0,0,0,0,0,1,0,0]
    m[7] = [0,1,0,0,0,0,0,0]
    return m

def Solution7():
    m = NewMatrix()
    m[0] = [0,0,0,0,1,0,0,0]
    m[1] = [0,0,0,0,0,0,1,0]
    m[2] = [0,0,0,1,0,0,0,0]
    m[3] = [1,0,0,0,0,0,0,0]
    m[4] = [0,0,1,0,0,0,0,0]
    m[5] = [0,0,0,0,0,0,0,1]
    m[6] = [0,0,0,0,0,1,0,0]
    m[7] = [0,1,0,0,0,0,0,0]
    return m

def Solution8():
    m = NewMatrix()
    m[0] = [0,0,0,1,0,0,0,0]
    m[1] = [1,0,0,0,0,0,0,0]
    m[2] = [0,0,0,0,1,0,0,0]
    m[3] = [0,0,0,0,0,0,0,1]
    m[4] = [0,0,0,0,0,1,0,0]
    m[5] = [0,0,1,0,0,0,0,0]
    m[6] = [0,0,0,0,0,0,1,0]
    m[7] = [0,1,0,0,0,0,0,0]
    return m

def Solution9():
    m = NewMatrix()
    m[0] = [0,0,1,0,0,0,0,0]
    m[1] = [0,0,0,0,0,1,0,0]
    m[2] = [0,0,0,1,0,0,0,0]
    m[3] = [1,0,0,0,0,0,0,0]
    m[4] = [0,0,0,0,0,0,0,1]
    m[5] = [0,0,0,0,1,0,0,0]
    m[6] = [0,0,0,0,0,0,1,0]
    m[7] = [0,1,0,0,0,0,0,0]
    return m

def Solution10():
    m = NewMatrix()
    m[0] = [0,0,0,0,0,1,0,0]
    m[1] = [0,1,0,0,0,0,0,0]
    m[2] = [0,0,0,0,0,0,1,0]
    m[3] = [1,0,0,0,0,0,0,0]
    m[4] = [0,0,0,1,0,0,0,0]
    m[5] = [0,0,0,0,0,0,0,1]
    m[6] = [0,0,0,0,1,0,0,0]
    m[7] = [0,0,1,0,0,0,0,0]
    return m

def Solution11():
    m = NewMatrix()
    m[0] = [0,0,0,1,0,0,0,0]
    m[1] = [0,0,0,0,0,0,1,0]
    m[2] = [1,0,0,0,0,0,0,0]
    m[3] = [0,0,0,0,0,0,0,1]
    m[4] = [0,0,0,0,1,0,0,0]
    m[5] = [0,1,0,0,0,0,0,0]
    m[6] = [0,0,0,0,0,1,0,0]
    m[7] = [0,0,1,0,0,0,0,0]
    return m

def Solution12():
    m = NewMatrix()
    m[0] = [0,0,0,0,0,1,0,0]
    m[1] = [0,0,0,1,0,0,0,0]
    m[2] = [0,0,0,0,0,0,1,0]
    m[3] = [1,0,0,0,0,0,0,0]
    m[4] = [0,0,0,0,0,0,0,1]
    m[5] = [0,1,0,0,0,0,0,0]
    m[6] = [0,0,0,0,1,0,0,0]
    m[7] = [0,0,1,0,0,0,0,0]
    return m

class Solucoes():
    def __init__(self):
        self.S1 = Solution1()
        self.S2 = Solution2()
        self.S3 = Solution3()
        self.S4 = Solution4()
        self.S5 = Solution5()
        self.S6 = Solution6()
        self.S7 = Solution7()
        self.S8 = Solution8()
        self.S9 = Solution9()
        self.S10 = Solution10()
        self.S11 = Solution11()
        self.S12 = Solution12()

def Soma(m1, m2):
    '''
    Dadas duas matrizes de tamanho 8x8 retorna a multiplicação entre cada posição em uma nova matriz.
    '''
    m = NewMatrix()
    for i in range(8):
        m[i] = [m1[i][0]*m2[i][0], m1[i][1]*m2[i][1], m1[i][2]*m2[i][2], m1[i][3]*m2[i][3], m1[i][4]*m2[i][4], m1[i][5]*m2[i][5], m1[i][6]*m2[i][6], m1[i][7]*m2[i][7]]
    
    soma = sum(map(sum, m))
    return soma

def MaiorSomaVariacoes(solution, tabuleiro):
    '''
    Constroi todas variações possíveis da mesma solução, calcula a soma e retona a maior soma encontrada.
    '''
    rotate1 = list(zip(*solution[::-1]))
    rotate2 = list(zip(*rotate1[::-1]))
    rotate3 = list(zip(*rotate2[::-1]))
    
    reverse = list(reversed(solution[:]))
    rev1 = list(zip(*reverse[::-1]))
    rev2 = list(zip(*rev1[::-1]))
    rev3 = list(zip(*rev2[::-1]))

    final = []
    final.append(Soma(solution, tabuleiro))
    final.append(Soma(rotate1, tabuleiro))
    final.append(Soma(rotate2, tabuleiro))
    final.append(Soma(rotate3, tabuleiro))
    final.append(Soma(reverse, tabuleiro))
    final.append(Soma(rev1, tabuleiro))
    final.append(Soma(rev2, tabuleiro))
    final.append(Soma(rev3, tabuleiro))

    return max(final)

class Board():
    def __init__(self):
        self.tabuleiro = []
        self.output = 0

    def Calculate(self):
        '''
        Calcula a soma final do tabuleiro atual conforme todas soluções possíveis e retorna o maior valor.
        '''
        #manter somente os numeros conforme a disposição da solucao das peças e depois somar
        solucoes = Solucoes()

        final = []
        final.append(MaiorSomaVariacoes(solucoes.S1, self.tabuleiro))
        final.append(MaiorSomaVariacoes(solucoes.S2, self.tabuleiro))
        final.append(MaiorSomaVariacoes(solucoes.S3, self.tabuleiro))
        final.append(MaiorSomaVariacoes(solucoes.S4, self.tabuleiro))
        final.append(MaiorSomaVariacoes(solucoes.S5, self.tabuleiro))
        final.append(MaiorSomaVariacoes(solucoes.S6, self.tabuleiro))
        final.append(MaiorSomaVariacoes(solucoes.S7, self.tabuleiro))
        final.append(MaiorSomaVariacoes(solucoes.S8, self.tabuleiro))
        final.append(MaiorSomaVariacoes(solucoes.S9, self.tabuleiro))
        final.append(MaiorSomaVariacoes(solucoes.S10, self.tabuleiro))
        final.append(MaiorSomaVariacoes(solucoes.S11, self.tabuleiro))
        final.append(MaiorSomaVariacoes(solucoes.S12, self.tabuleiro))

        maiorSoma = max(final)

        self.output = maiorSoma
        return maiorSoma

    def Reset(self):
        self.tabuleiro = []
        self.output = 0
    

def main():

    lines = LerEntradas()
    saidas = []

    board = Board()
    k = int(lines[0])

    solucoes = Solucoes()

    indexMatriz = 0
    indexGeral = 0
    while k > 0:
        
        while indexMatriz % 8 > 0 or indexMatriz == 0:
            linha = [int(i) for i in lines[indexGeral+1].split()]
            board.tabuleiro.append(linha)
            indexMatriz += 1
            indexGeral += 1

        board.Calculate()
        saidas.append(board.output)
        board.Reset()

        k -= 1
        indexMatriz = 0

    for i in range(len(saidas)):
        PrintOutput("%5d" % saidas[i])


if __name__ == "__main__":
    main()