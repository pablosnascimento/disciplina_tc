import numpy as np

def AND(a,b): 
    return a*b

def OR(a,b): 
    return 1 if a+b else 0

def NOT(a): 
    return 1-a

def MAJ(X0,X1,X2):
    firstpair  = AND(X0,X1)
    secondpair = AND(X1,X2)
    thirdpair  = AND(X0,X2)
    temp       = OR(secondpair,thirdpair)
    return OR(firstpair,temp)
    
#print(MAJ(0,1,1))

def CMP(X0, X1, X2, X3):
    notc = NOT(X2)
    and1 = AND(X0,notc)
    and2 = AND(X0,X2)
    nota = NOT(X0)
    and3 = AND(nota,notc)
    or1 = OR(and2,and3)
    notd = NOT(X3)
    and4 = AND(X1,notd)
    and5 = AND(or1,and4)
    return OR(and1,and5)

#print("CMP: ", CMP(1,1,1,0))

def GT6(a2, a1, a0, b2, b1, b0):
    nota2 = NOT(a2)
    nota1 = NOT(a1)
    nota0 = NOT(a0)
    notb2 = NOT(b2)
    notb1 = NOT(b1)
    notb0 = NOT(b0)
    and1 = AND(a2,notb2)

    and2 = AND(nota2,notb2)
    and3 = AND(a2,b2)
    and4 = AND(a1,notb1)
    or1 = OR(and2,and3)
    and5 = AND(or1,and4)
    
    and6 = AND(nota1,notb1)
    and7 = AND(a1,b1)
    and8 = AND(a0,notb0)
    or2 = OR(and6,and7)
    and9 = AND(or2,and8)
    and10 = AND(or1,and9)
    
    or3 = OR(and1,and5)
    or4 = OR(or3,and10)
    
    return or4

print("GT6:", GT6(0,0,0,0,0,0))

def printbin(n):

    lines = 2^n
    columns = n
    m = np.zeros((lines,columns), np.int)
    for i in range(lines):
        for j in range(columns):
            print(m[i][j])

def TesteGT6():
    a = [0,0,0]
    b = [0,0,0]

    pos = 1
    for i in range(3):
        for j in range(3):
            print(pos,"-",a[0],a[1],a[2],b[0],b[1],b[2])
            pos+=1


# Driver Code
if __name__ == "__main__":

	n = 6
	printbin(6)
