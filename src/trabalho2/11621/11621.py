'''
11621 – Smallest Factor
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
        #with open(os.getcwd()+'\\teste.txt') as file:
            lines = [line.strip() for line in file]

    return lines

class Factor():
    def __init__(self):
        self.F = []

    def CalculateAll(self):
        '''
        Calcula todos valores para os inteiros com produto 2i*3i e poe num vetor até atingir o limite enunciado de 2^31.
        '''
        i = 0
        j = 0
        menor = 0
        
        while i < 32:
            while j < 32:
                pow2i = pow(2,i)
                pow3j = pow(3,j)
                menor = pow2i * pow3j
                    
                self.F.append(menor)
                j += 1

            j = 0
            i += 1

        self.F = sorted(self.F)
            

    def SmallestFactor(self, m):
        '''
        '''
        for i in range(len(self.F)):
            value = self.F[i]
            if (value >= m):
                return value

        return 0

def main():

    saidas = []
    lines = LerEntradas()

    allF = Factor()
    allF.CalculateAll()

    for i in range(len(lines)):
        number = int(lines[i])
        
        if number == 0:
            break

        saidas.append(allF.SmallestFactor((int(number))))

    for i in range(len(saidas)):
        value = saidas[i]
        PrintOutput(value)

if __name__ == "__main__":
    main()