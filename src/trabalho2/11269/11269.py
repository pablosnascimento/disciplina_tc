'''
11269 – Setting Problem
'''

import sys
import os
import functools

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

def CustoJob1ParaJob2(job1, job2):
    '''
    Função que calcula o custo entre duas tarefas na ordem em que são passadas.
    a b -> custo = a + (caso igual | caso maior | caso menor) + d
    c d
    '''
    s = 0
    g = 1

    custo = job1[s]
    
    if (job1[g] == job2[s]):
        custo += job1[g] 
    elif (job1[g] > job2[s]):
        custo += job1[g]
    elif (job1[g] < job2[s]):
        custo += job2[s]
    
    custo += job2[g] 

    return custo
 

def OrdenaJob(job2, job1):
    '''
    Calcula o custo de executar duas tarefas nas duas ordens e aceita a menor, ou seja, faz ordenação
    '''
    custo1 = CustoJob1ParaJob2(job1, job2)
    custo2 = CustoJob1ParaJob2(job2, job1)
    
    #a ordenação será dada por quem tiver o menor custo primeiro
    if custo1 < custo2:
        return 1
    else:
        return -1

class Work():
    def __init__(self):
        self.Jobs = []
        self.n = 0

    def FillDay(self, n, s, g):
        '''
        Preenche a jornada diária com as tarefas e as quantidades. 
        '''

        self.n = n
        entrada1 = s.split()
        entrada2 = g.split()

        for i in range(len(entrada1)):
            s = int(entrada1[i])
            g = int(entrada2[i])
            self.Jobs.append([s, g])

    def Calculate(self):
        '''
        Calcula o tempo total da execução dos jobs.
        '''

        #ordena os tempos conforme a regra especificada
        ordenado = sorted(self.Jobs, key=functools.cmp_to_key(OrdenaJob))

        tempoSultan = 0
        tempoDescontado = 0
        for i in range(len(ordenado)):
            task = ordenado[i]
            tempoSultan += task[0]

            tempoDescontado -= task[0]
            if (tempoDescontado < 0):
                tempoDescontado = 0

            tempoDescontado += task[1]
        
        return tempoSultan + tempoDescontado

    def Reset(self):
        '''
        Reinicia os valores dos atributos.
        '''
        self.Jobs = []
        self.n = 0

def main():

    lines = LerEntradas()
    
    work = Work()
    saidas = []

    for i in range(0, len(lines), 3):
        work.FillDay(int(lines[i]), lines[i+1], lines[i+2])

        result = work.Calculate()
        saidas.append(result)
        work.Reset()

    for i in range(len(saidas)):
        PrintOutput(saidas[i])

if __name__ == "__main__":
    main()