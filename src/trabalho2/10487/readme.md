# Trabalho 2 -- Teoria da Computação -- 2021/2

**Projeto e Desenvolvimento de Algoritmos**
**Autor:** Pablo S Nascimento

## Problema 10487 – Closest Sum

Dado um certo número 'n' e um conjunto de números, qual é a soma de dois números pertencentes ao conjunto mais próxima do número n.

**Solução**
A solução dada foi ordenar o vetor dos números a serem somados e para cada n informado calcular a soma mais proxima da seguinte forma: 
1) pega o primeiro e o ultimo e soma;
2) guarda na variavel 'soma_final' o valor mais próximo encontrado até o momento
3) compara 'soma' com o valor de n e anda o vetor pra cima ou pra baixo conforme resultado
4) quando tiver somado todos valores do vetor ou encontrar soma igual a n para.

**Execução**
É possível executar o código preenchendo o arquivo teste.txt com as entradas possíveis, uma por linha, finalizando com a linha 0
Alterar o valor da variável ENV = 2 para executar lendo do arquivo e ENV = 1 para submeter o código no Online Judge.