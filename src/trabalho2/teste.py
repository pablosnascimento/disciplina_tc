'''
PROBLEM_ID – Problem Name
'''

import sys
import os

__location__ = os.path.realpath(os.path.join(os.getcwd(), os.path.dirname(__file__)))

#ENV: 1 submissao; 2 - teste local
ENV = 2

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

def main():

    lines = LerEntradas()
    
    for i in range(len(lines)):
        line = lines[i]

        # round.GivenCards(line)

        # if (round.EndGame()):
        #     #Imprime todas as cartas-resposta conforme os rounds.
        #     for i in range(len(game)):
        #         PrintOutput(game[i])
        #     return

        # else:
        #     round.DefineLastCard()
        #     game.append(round.output)
        #     round.Reset()


if __name__ == "__main__":
    main()