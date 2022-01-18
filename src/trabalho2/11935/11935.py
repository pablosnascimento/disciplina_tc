'''
11935 – Through the Desert
'''

import sys 
import os

__location__ = os.path.realpath(os.path.join(os.getcwd(), os.path.dirname(__file__)))

#ENV: 1 submissao; 2 - teste local
ENV = 2

class Car:
    def __init__(self):
        self.consumoAtual = 0
        self.qtdLeaks = 0
        self.kmPercorridos = 0
        self.litrosNecessarios = 0
        self.litrosConsumidos = 0
        self.kmPercorrer = 0

    def PrintGoal(self):
        '''
        Imprime qual a quantidade de litros necessária para atingir o objetivo.
        '''
        msg = "%.3f" % round(self.litrosNecessarios, 3)

        if(ENV == 1):
            sys.stdout.write(msg+"\n")
        else:
            print(msg)

    def Read(self, entrada):
        en = entrada.split()

        distancia = int(en[0])
        self.kmPercorrer = distancia

        self.Process()
        
        if (en[1] == "Goal"):
            self.PrintGoal()
            self.Zerar()

        elif (en[1] == "Gas"):
            #Quando da Gas station o que o tanque era capaz de armazenar já foi tratado e volta a contar do zero
            self.litrosConsumidos = 0

        elif (en[1] == "Fuel"):
            self.consumoAtual = int(en[-1])/100

        elif (en[1] == "Leak"):
            #a cada novo leak adiciona 1
            self.qtdLeaks = self.qtdLeaks + 1

        elif (en[1] == "Mechanic"):
            #mecanico conserta todos leaks
            self.qtdLeaks = 0

        self.kmPercorridos = self.kmPercorrer

        return 0

    def Zerar(self):
        '''
        A cada chamada do Goal zera os valores corrente, exceto o consumoAtual, pois precisa ser verificado na próxima rodada.
        '''
        self.qtdLeaks = 0
        self.kmPercorridos = 0
        self.litrosNecessarios = 0
        self.litrosConsumidos = 0
        self.kmPercorrer = 0

    def Process(self):
        '''
        Processa a entrada lida e atualiza kmPercorridos e litrosNecessarios conforme a situação do carro no momento.
        '''
        km = (self.kmPercorrer - self.kmPercorridos)

        #acumula os litros consumidos até um Gas station
        self.litrosConsumidos += (km * self.consumoAtual + km * self.qtdLeaks)

        #a litragem necessária será dada pelo maior acumulador.
        self.litrosNecessarios = max(self.litrosNecessarios, self.litrosConsumidos)

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

    carro = Car()
    
    #submissao stdin
    lines = LerEntradas()
    
    for i in range(len(lines)):
        line = lines[i]

        carro.Read(line)

        if (carro.consumoAtual == 0):
            return

if __name__ == "__main__":
    main()