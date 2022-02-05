'''
12247 – Jollo
'''

from itertools import permutations
import sys
import os

__location__ = os.path.realpath(os.path.join(os.getcwd(), os.path.dirname(__file__)))

#ENV: 1 submissao; 2 - teste local
ENV = 2

class Round():
    def __init__(self):
        self.Cards1 = [0, 0, 0]
        self.Cards2 = [0, 0, 0]
        self.output = 0

    def Reset(self):
        '''
        Reinicia a rodada com valores zerados
        '''
        self.Cards1 = [0, 0, 0]
        self.Cards2 = [0, 0, 0]
        self.output = 0

    def EndGame(self):
        '''
        Verifica se é o fim do jogo.
        '''
        return sum(self.Cards1) + sum(self.Cards2) == 0
    
    def GivenCards(self, entrada):
        '''
        Distribui a entrada conforme as cartas.
        '''
        values = entrada.split()
        self.Cards1[0] = int(values[0])
        self.Cards1[1] = int(values[1])
        self.Cards1[2] = int(values[2])
        self.Cards2[0] = int(values[3])
        self.Cards2[1] = int(values[4])

    def GetNextCard(self, direcao):
        '''
        Encontrar qual a próxima menor carta válida maior que as duas menores que estão com a princesa.
        Parâmetro 'direcao': 1 se sobe, -1 se desce
        '''
        card = self.Cards2[2]
        s = sorted(self.Cards1)

        if (card == 0):
            next = s[1] + 1
        else:
            next = card + direcao

        while((next == s[0] or next == s[1] or next == s[2] or next == self.Cards2[0] or next == self.Cards2[1] or next == self.Cards2[2]) and next <= 52 and next > -1):
            next += direcao
        else:
            if next > 52 or next < 1:
                next = -1
        
        self.Cards2[2] = next
        return next
            
    def DefineLastCard(self):
        '''
        Verificar se a carta proposta é suficiente. Se for, busca qual a menor possível e principe vence. Se não, busca outras até encontrar. Caso encontre ele vence, senão ele perde.
        '''
        #apenas guardo qual a carta inicial
        self.GetNextCard(1)
        self.output = self.Cards2[2]
        cardVitoria = -1

        m = DefineMatriz(self)
        derrotas = ObterDerrotas(m)

        #enquanto o teste de todas possibilidades de uma carta der falha, sobe o valor da carta e continua tentando até chegar a 52
        while len(derrotas) > 0 and self.Cards2[2] > -1:
            self.GetNextCard(1)
            m = DefineMatriz(self)

            #obter todas possibilidades de derrota
            derrotas = ObterDerrotas(m)
            self.output = self.Cards2[2]

        else:
            #o contrario tambem precisa ocorrer, ou seja, se o teste der vitoria desce a carta até chegar ao mínimo valor possível em que a vitoria ainda ocorre.
            cardVitoria = self.output
            derrotas = []

            if(self.Cards2[2] > -1):
                while len(derrotas) == 0 and self.Cards2[2] > 0:
                    #guarda a ultima com vitoria
                    cardVitoria = self.Cards2[2]
                    
                    #agora desce
                    self.GetNextCard(-1)
                    m = DefineMatriz(self)

                    #obter todas possibilidades de derrota
                    derrotas = ObterDerrotas(m)
                else:
                    self.output = cardVitoria

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

def DefineMatriz(round):
    '''
    Cria uma matriz com todas combinações possíveis para as comparações.
    '''

    n = 6
    
    # a = round.Cards1[0]
    # b = round.Cards1[1]
    # c = round.Cards1[2]
    x = round.Cards2[0]
    y = round.Cards2[1]
    z = round.Cards2[2]

    #criando todas combinações de a, b e c
    permutacoes = list(permutations(round.Cards1))

    linha = [0] * n
    m = [linha] * 36

    for i in range(len(permutacoes)):
        p = permutacoes[i]
        idx = i*6
        m[0+idx] = [p[0], p[1], p[2], x, y, z]
        m[1+idx] = [p[0], p[1], p[2], x, z, y]
        m[2+idx] = [p[0], p[1], p[2], y, x, z]
        m[3+idx] = [p[0], p[1], p[2], y, z, x]
        m[4+idx] = [p[0], p[1], p[2], z, y, x]
        m[5+idx] = [p[0], p[1], p[2], z, x, y]

    return m

def ObterDerrotas(m):
    '''
    Obtem uma lista com as combinações em que o principe perde o round.
    '''
    perde = [x for x in m if (x[0] > x[3] and x[1] > x[4]) or (x[0] > x[3] and x[2] > x[5]) or (x[1] > x[4] and x[2] > x[5])]

    return perde

def main():

    #game é uma lista dos outputs
    game = []

    #Round é cada rodada do jogo
    round = Round()
    lines = LerEntradas()
    
    for i in range(len(lines)):
        line = lines[i]

        round.GivenCards(line)

        if (round.EndGame()):
            #Imprime todas as cartas-resposta conforme os rounds.
            for i in range(len(game)):
                PrintOutput(game[i])
            return

        else:
            round.DefineLastCard()
            game.append(round.output)
            round.Reset()


if __name__ == "__main__":
    main()