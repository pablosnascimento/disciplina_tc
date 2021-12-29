# Tarefa 3 -- Teoria da Computação -- 2021/2

**Funções com Domínios Infinitos, Autômatose Expressões Regulares & Loops e Infinitude & Modelos Equivalentes de Computação**
**Autor:** Pablo S Nascimento

## Problema 3

Este problema pede a construção de máquinas de turing que resolvam 2 problemas: incremento de um e soma.
Na parte a, pede-se uma máquina de Turing que dado um numero compute seu incremento de um. 
Na parte b, dados dois números, pede-se uma máquina de Turing que some os dois.

**Solução parte a**
A solução dada foi implementar primeiro uma cópia do número à direita para depois, voltar ao número inicial, e efetuar a soma.

**Execução**
Para executar o programa, copiar o código fonte do arquivo "solucao3.1.txt" e aplicar no simulador http://morphett.info/turing/turing.html.


**Solução parte b**
A solução dada foi implementar primeiro uma cópia do número à direita do # posicionando-o no início e separando com carcter '='. 
Em seguida, o último número passa a ser ignorado e apenas os dois primeiros serão lidos e considerados. 
Da direita para a esquerda, vamos lendo os números e analisando a possibilidade de haver ou não carry (1 + 1).

De maneira geral, a Máquina proposta possui 2 partes principais:
1 - Trata da cópia do ultimo número no começo seguido de um sinal =;
2 - Feita a cópia, inicia-se a soma. Esta soma ignora o último número após o sinal #. 

A execução da soma também pode ser compreendida como composta de 3 partes:
1 - Quando é lido um 1 preparamos o cenário para o caso de um carry;
2 - Quando é lido um 0 segue o fluxo conforme a soma com 0 ou 1 normalmente;
3 - Quando finaliza a leitura, os caracteres de tratamento são substituídos novamente para 0s e 1s e para.

**Execução**
Para executar o programa, copiar o código fonte do arquivo "solucao3.2.txt" e aplicar no simulador http://morphett.info/turing/turing.html.
