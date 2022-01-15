'''
PROBLEM_ID – Problem Name
'''

import sys
import os

#ENV: 1 submissao; 2 - teste local
ENV = 2
PROBLEM_ID = 0

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