import sys
import os

__location__ = os.path.realpath(os.path.join(os.getcwd(), os.path.dirname(__file__)))

#https://medium.com/swlh/problems-with-advanced-ds-binary-search-and-the-russian-doll-5b64cb5ee67b
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


def bsearch(arr, min_m, max_m, k):
    left, right = min_m, max_m
    pos = -1

    while left <= right:
        mid = int((left + right)/2)

        if arr[mid] < k:
            pos = mid
            left = mid+1
        else:
            right = mid-1

    return pos

def maxEnvelopes(envelopes):
    if len(envelopes) <= 1:
        return len(envelopes)

    envelopes = sorted(envelopes, key=lambda k:(k[0], -k[1]))

    f = [0]*len(envelopes)
    num_envelopes = [float("Inf")]*(len(envelopes)+1)

    min_m, max_m = float("Inf"), -float("Inf")

    for i in range(len(envelopes)):
        if i == 0:
            f[i] = 1
            num_envelopes[1] = envelopes[0][1]
            min_m, max_m = 1, 1

        else:
            j = bsearch(num_envelopes, min_m, max_m, envelopes[i][1])

            if j == -1:
                num_envelopes[1] = min(num_envelopes[1], envelopes[i][1])
                f[i] = 1
            else:
                num_envelopes[j+1] = min(num_envelopes[j+1], envelopes[i][1])
                f[i] = j+1

                min_m = min(min_m, j+1)
                max_m = max(max_m, j+1)

    return max(f)

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
        
        self.Lista = sorted(self.Lista)
    
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

        # while (i < len(self.Lista)):
        #     doll = self.Lista[i]
            
        #     i+=1
        #     for j in range(i, len(self.Lista)):
        #         doll_encaixar = self.Lista[j]

        #         if DollEncaixa(doll, doll_encaixar):
                    
        #             #so encaixa na mesma linha se encaixar nele tmb, do contrario adiciona linha
        #             if len(encaixados) > 0:
        #                 ultimoEncaixado = encaixados[-1]
                        
        #                 if DollEncaixa(ultimoEncaixado, doll_encaixar):
        #                     encaixados.remove(ultimoEncaixado)
        #                     encaixados.append(doll_encaixar)
        #             else:
        #                 encaixados.append(doll_encaixar)

        #             self.Lista.remove(doll)
        #             i = 0
        #             break

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
                    proximoMelhor = self.MelhorEncaixe(doll, j)
                    doll_encaixar = proximoMelhor
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
        
        result = maxEnvelopes(doll.Lista)

        saidas.append(result)
        doll.Reset()

    for i in range(len(saidas)):
        PrintOutput(saidas[i])
    return


if __name__ == "__main__":
    main()