'''
10602 – Nottoobad
'''

import sys
import os

#ENV: 1 submissao; 2 - teste local
ENV = 2
PROBLEM_ID = 10602

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

class Case:
    def __init__(self):
        self.n = 0
        self.words = []
        self.n_presses = 0
        self.outputs = []

    def Reset(self):
        self.n = 0
        self.words = []
        self.n_presses = 0
        self.outputs = []

    def Calculate(self):
        '''
        Define a quantidade de digitações e qual a ordem das palavras e adiciona nos outputs.
        solução: pegar cada palavra do vetor ordenado dar comando de REPEAT, adiciona-la, verificar ate onde ela é igual a primeira, DELET nos simbolos finais ate chegar ate essa raiz
        chamar o comando de Press tantas vezes quantas forem os caracteres faltantes para completar a palavra. Repetir o processo para a proxima palavra."
        '''
        palavra = self.words[0]
        self.outputs.append(palavra)
        self.n_presses = len(palavra)
        participantes = self.words[1:]

        #Vamos monstar um vetor ordenado senod a junção de outros dois vetores formados da seguinte forma:
        #O primeiro contendo as palavras que iniciem com a mesma letra da primeira palavra para economizar comandos;
        #O segundo, unimos o restante das palavras não existentes no primeiro vetor de forma ordenada
        iguais = [item for item in participantes if item == palavra]
        size = len(palavra)

        mesmoComeco = []
        while size > 0:
            raiz = palavra[:size]
            vetor = [item for item in participantes if item.startswith(raiz) and item != palavra and item not in mesmoComeco]
            vetor = sorted(vetor)
            mesmoComeco = mesmoComeco + vetor
            size -= 1
        
        mesmoComeco = iguais + mesmoComeco

        restantes = [item for item in participantes if item not in mesmoComeco]
        restantes = sorted(restantes)
        
        ordenado = mesmoComeco + restantes #ordenado apenas com as palavras futuras
        n = len(ordenado)
        index = 0

        while n > 0:
            
            next_word = ordenado[index]

            self.DefinirQtdDigitacoes(next_word, palavra)

            index += 1
            palavra = next_word

            n -= 1

    def DefinirQtdDigitacoes(self, next_word, palavra):
        '''
        Dadas duas palavras, compara e define a quantidade de "presses"
        '''
        caracteres = len(min(next_word, palavra))
        index = 0

        while caracteres > 0 and next_word[index] == palavra[index]:
            caracteres -= 1
            index += 1
        
        self.outputs.append(next_word)

        #se caracateres = 0 as palavras possuem todas letras em comum no início
        #neste caso, retornar a quantidade de letras da palavra como sendo a quantidade de "presses" e adicionar a palavra no output
        # if caracteres == 0 and next_word[index] == palavra[index]:
        #         self.n_presses += len(next_word)
        # #
        # else:
        parteRestante = next_word[index:]
        self.n_presses += len(parteRestante)

    def Print(self):
        '''
        Imprime o Case conforme saída especificada.
        '''
        PrintOutput(self.n_presses)
        for i in range(len(self.outputs)):
            PrintOutput(self.outputs[i])

def main():

    lines = LerEntradas()

    saidas = []
    
    n_cases = int(lines[0])
    index = 1

    while n_cases > 0:
        case = Case()
        case.n = int(lines[index])
        nbegin = index + 1
        case.words = lines[nbegin: nbegin + case.n]
            
        case.Calculate()
        saidas.append(case)
        
        index = index + case.n + 1
        n_cases -= 1
    
    #imprime as saidas
    for i in range(len(saidas)):
        case = saidas[i]
        case.Print()

if __name__ == "__main__":
    main()