'''
11368 - Nested Dolls
'''

import sys
import os

#https://www.youtube.com/watch?v=cjWnW0hdF1Y tutorial LIS
#https://stackoverflow.com/questions/40813044/nested-boxes-algorithm-based-on-nested-dolls-but-fundamentally-different

#https://www.youtube.com/watch?v=SSVFIVkfEPY video em portugues
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

class Doll():
    def __init__(self):
        self.Lista = []
        self.n_dolls = 0
    
    def Fill(self, n, line):
        '''
        Faz a leitura da linha e preenche a lista com pares [width, high] para cada doll.
        '''
        self.n_dolls = n

        entrada = line.split()
        for i in range(0, len(entrada), 2):
            w = int(entrada[i])
            h = int(entrada[i+1])
            self.Lista.append([w, h])
        
        self.Lista = sorted(self.Lista, key=lambda k:(k[0], -k[1]))
    
    def MelhorEncaixe(self, doll, index):
        '''
        Busca o doll com melhor encaixe para o doll indicado na lista disponivel.
        '''
        proximo = self.Lista[index]
        soma = (proximo[0] - doll[0]) + (proximo[1] - doll[1])
        eleito = proximo
        idxEleito = index

        #segue a lista buscando encaixe melhor
        for i in range(index+1, len(self.Lista)):
            proximo = self.Lista[i]
            s = (proximo[0] - doll[0]) + (proximo[1] - doll[1])
            
            if DollEncaixa(doll, proximo):
                if s < soma:
                    soma = s
                    eleito = proximo
                    idxEleito = i
            else:
                break
                
        return eleito

    def Calculate(self):
        '''
        Calcula a quantidade de dolls resultado do problema.
        '''
        n_dolls = 0
        w = 0
        h = 0

        i = 0
        encaixados = []

        while (i < len(self.Lista)):
            doll = self.Lista[i]
            
            if len(encaixados) > 0:
                ultimoEncaixado = encaixados[-1]
                
                #se encaixa em quem já foi encaixado, encaixa sobre ele e continua pro proximo
                if DollEncaixa(ultimoEncaixado, doll):
                    encaixados.remove(ultimoEncaixado)
                    encaixados.append(doll)
                    self.Lista.remove(doll)
                    continue
                #se nao se encaixa, mas é o ultimo da lista a ser classificado, adiciona na fila e termina
                elif 1 == len(self.Lista):
                    encaixados.append(doll)
                    self.Lista.remove(doll)
                    continue

            i+=1
            for j in range(i, len(self.Lista)):
                doll_encaixar = self.Lista[j]

                if DollEncaixa(doll, doll_encaixar):
                    #proximoMelhor = self.MelhorEncaixe(doll, j)
                    #doll_encaixar = proximoMelhor
                    encaixados.append(doll_encaixar)
                    self.Lista.remove(doll_encaixar)
                    self.Lista.remove(doll)
                    i = 0
                    break
                elif j == len(self.Lista) - 1:
                    #percorreu toda a lista e não encaixou ninguem, repassa pra lista de encaixe sozinho
                    encaixados.append(doll)
                    self.Lista.remove(doll)
                    i = 0

        n_dolls = len(encaixados)

        return n_dolls

    def Calculate2(self):
        '''
        '''
        alturas = [x[1] for x in self.Lista]

        aux = alturas[0]
        count = 0
        seqDecrescentes = []
        maiorSeq = 0

        for i in range(len(alturas)):
            
            h = alturas[i]
            #count += 1

            #fim da sequencia onde os valores de altura vinham decrementando em sequencia
            if h <= aux:
                maiorSeq += 1
            
            if h > aux or i == len(alturas) - 1:
                seqDecrescentes.append(maiorSeq)
                maiorSeq = 1
            
            aux = h
        
        return max(seqDecrescentes)



    def Reset(self):
        '''
        Reinicia os valores dos atributos da classe.
        '''
        self.Lista = []
        self.n_dolls = 0

def DollEncaixa(d1, d2):
    return d1[0] < d2[0] and d1[1] < d2[1]

def DollsIguais(d1, d2):
    return d1[0] == d2[0] and d1[1] == d2[1]

def main():

    lines = LerEntradas()

    saidas = []
    testes = int(lines[0])

    doll = Doll()

    for i in range(1, len(lines), 2):
        n_dolls = int(lines[i])
        line = lines[i+1]

        doll.Fill(n_dolls, line)
        #result = doll.Calculate()
        result = doll.Calculate2()

        saidas.append(result)
        doll.Reset()

    for i in range(len(saidas)):
        PrintOutput(saidas[i])
    return


if __name__ == "__main__":
    main()