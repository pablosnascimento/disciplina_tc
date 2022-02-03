'''
00447 – Population Explosion
'''

import sys
import os

__location__ = os.path.realpath(os.path.join(os.getcwd(), os.path.dirname(__file__)))

#ENV: 1 submissao; 2 - teste local
ENV = 1
SIZE = 20
ASTERISCOS = "********************"

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
        with open(__location__+'\\teste.txt') as file:
            lines = [line.strip() for line in file]

    return lines

class Case():
    def __init__(self):
        self.years = 0
        self.initialCoordinates = []
        self.location = [] #matriz com a situação atual de todo o espaço para alojamento
    
    def Reset(self):
        '''
        Reinicia os valores do Case.
        '''
        self.years = 0
        self.initialCoordinates = []
        self.location = []

    def Neighbors(self, x, y):
        '''
        Dadas as posições de um alojamento, obtém quantos alojamentos vizinhos ele possui no Case indicado.
        '''
        n = 0
        li = x - 1
        lf = x + 1
        ci = y - 1
        cf = y + 1
        linhas = 3
        colunas = 3

        #tratando os limites
        if li < 0:
            li = 0
            linhas = 2
        if lf > 19:
            lf = 19
            linhas = 2
        
        if ci < 0:
            ci = 0
            colunas = 2
        if cf > 19:
            cf = 19
            colunas = 2
        
        subM = []

        #matriz de vizinhos
        for i in range(linhas):
            for j in range(colunas):
                living = self.location[li][ci]

                #não considerar se estiver em cima do próprio alojamento que se quer avaliar
                if (li != x or ci != y) and living == 1:
                    n += 1

                ci += 1
            
            li += 1
            ci -= colunas
        
        return n

    def Living(self, x, y):
        '''
        Informa se tem alojamento na posição indicada ou não.
        '''
        return self.location[x][y] == 1

    def Rule1(self, x, y, n):
        '''
        Retorna verdadeiro se alguem habita e possui 2 ou 3 vizinhos.
        '''
        return self.Living(x,y) and (n == 2 or n == 3)

    def Rule2(self, x, y, n):
        '''
        Retorna verdadeiro se alguem habita  epossui 4 ou mais vizinhos ou ainda um ou menos.
        '''
        return self.Living(x, y) and (n >= 4 or n <= 1)

    def Rule3(self, x, y, n):
        '''
        Retorna verdadeiro se ninguem habita e possui 3 vizinhos.
        '''
        return (not self.Living(x, y)) and n == 3

    def NextYear(self, x, y):
        '''
        Indica o que ocorrerá com a posicao indicada no proximo ano.
        '''
        n = self.Neighbors(x,y)

        if self.Rule1(x,y,n) or self.Rule3(x,y,n):
            return 1
        
        if self.Rule2(x,y,n) or not self.Living(x, y):
            return 0

    def BuildPopulationNextYear(self):
        '''
        Avalia as regras para cada alojamento se sobrevive ou não no próximo ano, ou ainda, se cria novo alojamento e atualiza o Location.
        '''
        pop = [[0 for i in range(SIZE)] for j in range(SIZE)]

        if self.years == 0:
            return []

        for i in range(SIZE):
            for j in range(SIZE):
                survive = self.NextYear(i,j)
                pop[i][j] = survive

        self.location = pop

        return

    def FillLocation(self):
        '''
        Baseado nas coordenadas, preenche as posições dos alojamentos na localização atual.
        '''

        self.location = [[0 for i in range(SIZE)] for j in range(SIZE)]

        for i in range(len(self.initialCoordinates)):
            coord = self.initialCoordinates[i]
            linha = coord[0]
            coluna = coord[1]
            
            self.location[linha][coluna] = 1

        return self.location
    
    def PrintLocation(self):
        '''
        Retorna uma matriz dos caracteres conforme serão tratados na saída.
        '''
        m = []

        for i in range(len(self.location)):
            coord = self.location[i]
            linha = ["O" if i == 1 else " " for i in coord]
            m.append(linha)

        out = ["".join(l) for l in m]
        return out

def main():

    lines = LerEntradas()
    lines.append('') #garante que vai avaliar o último case
    nCases = int(lines[0])
    
    saidas = []
    saidas.append(ASTERISCOS) #no inicio

    case = Case()

    for i in range(2, len(lines)):
        
        line = lines[i]
        entrada = line.split()

        # se for linha vazia, inicia novo teste case
        if len(entrada) == 0:
            case.FillLocation()

            for i in range(case.years):
                if i == 0:
                    firstPop = case.PrintLocation()
                    saidas.append(firstPop)
                else:
                    case.BuildPopulationNextYear()
                    nextPop = case.PrintLocation()
                    saidas.append(nextPop)

                #separar cada ano com asteriscos
                saidas.append(ASTERISCOS)

            case.Reset()
            saidas.append('')
            saidas.append(ASTERISCOS)

        #número de anos do Case
        elif len(entrada) == 1:
            case.years = int(entrada[0])


        else:
            coordinates = [int(x)-1 for x in entrada]
            case.initialCoordinates.append(coordinates)

    saidas = saidas[:-3] #remove os ultimos asteriscos inseridos

    for i in range(len(saidas)):
        population = saidas[i]

        if population == ASTERISCOS or population == '':
            PrintOutput(population)
            continue

        for j in range(len(population)):
            PrintOutput(population[j])
    
    PrintOutput(ASTERISCOS) #no final

if __name__ == "__main__":
    main()