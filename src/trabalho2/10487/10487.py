'''
10487 – Closest Sum
'''

import sys
import os

#ENV: 1 submissao; 2 - teste local
ENV = 2
PROBLEM_ID = 10487

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
        lines = [int(line.rstrip()) for line in lines]
    else:
        #testes em arquivo local
        with open(os.getcwd()+'\\%s\\teste.txt' % str(PROBLEM_ID)) as file:
            lines = [int(line.strip()) for line in file]

    return lines

def Imprimir(saidas):
    '''
    Imprime a matriz de saídas de cada Case.
    '''
    count = 0
    for i in range(len(saidas)):
        case = saidas[i]
        count += 1

        PrintOutput("Case %d:" % count)
        for j in range(len(case)):
            resultado = case[j]

            PrintOutput(resultado)

class Case:
    def __init__(self):
        self.set = []
        self.numbers = []
        self.outputs = []

    def Calculate(self):
        '''
        Encontra a soma mais próxima adiciona nos outputs.
        '''
        ordenado = sorted(self.set)
        min_idx = 0
        max_idx = len(ordenado) - 1
        soma = 0
        soma_final = 0

        for n in self.numbers:
            
            #para cada n calcular a soma mais proxima: 
            # 1) pega o primeiro e o ultimo e soma;
            # 2) salva em soma_final
            # 3) compara soma com o valor de n e anda o vetor pra cima ou pra baixo
            # 4) quando tiver somado todos valores do vetor ou encontrar soma igual a n para
            while min_idx < max_idx:
            
                soma = ordenado[min_idx] + ordenado[max_idx]

                #verifica qual o melhor valor de soma ate o momento
                if (abs(soma - n) < abs(soma_final - n)) or soma_final == 0:
                    soma_final = soma
                #caso da igualdade manter o menor valor de soma encontrado
                elif abs(soma - n) == abs(soma_final - n):
                    if soma < soma_final:
                        soma_final = soma

                #se a soma é menor que n avança no vetor para valores mais altos
                if soma < n:
                    min_idx += 1
                #se a soma é maior que n desce no vetor para valores mais baixos
                elif soma > n:
                    max_idx -= 1
                else:
                    break

            self.outputs.append("Closest sum to %d is %d." % (n, soma_final))

            #reinicia os parâmetros para o próximo n
            min_idx = 0
            max_idx = len(ordenado) - 1
            soma = 0
            soma_final = 0
        

    def Reset(self):
        '''
        Reinicia os valores para vazio
        '''
        self.set = []
        self.numbers = []
        self.outputs = []

def main():

    lines = LerEntradas()

    saidas = []
    case = Case()
    n = -1
    m = -1
    index = 0

    for i in range(len(lines)):
        number = lines[index]

        #inicio de cada novo case
        if n == -1:
            n = number

        if n > 0:
            nbegin = index + 1
            case.set = lines[nbegin: nbegin + n]
            
            m = lines[nbegin + n]
            case.numbers = lines[nbegin + n + 1:nbegin + n + 1 + m]
            
            case.Calculate()
            saidas.append(case.outputs)
            case.Reset()
            
        else:
            Imprimir(saidas)
            return

        index = index + n + m + 2
        
        #fim de cada case
        n = -1
        m = -1

if __name__ == "__main__":
    main()