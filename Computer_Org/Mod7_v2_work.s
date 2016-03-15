

.data
        prompt1:	.asciiz "\nEnter the number of balls in pool: "
        prompt2:	.asciiz "\nEnter the number of balls to select: "  #ERROR handling! make sure this val is < 12 AND < the number of balls in the pool!!!
	error1:		.asciiz "\nError: the number of balls selected must be an integer between 1 and 11. Please start over."
	error2:         .asciiz "\nError: the number of balls selected must be <= the number of balls in the pool. Please start over."
	resultMessage:	.ascii "\nThe odds are 1 in "
	numPool:	.word 0
	numSelect: 	.word 0
	numRedBall:	.word 26 # The red ball is C(26, 1); = 26!/(1!*25!) = 26; hard-coded because there is only ONE red ball and range is 1:26 
	numDiff:	.word 0	# To store numPool- numSelect (i.e. n-k in combinatorics)
	factorialA:    	.word 0
	factorialB: 	.word 0
	answer:		.word 0
	
############################################################################################################	
.text

.globl main

main:   
	#Prompt user to enter number of balls in pool 
	li $v0, 4 # print prompt1
        la $a0, prompt1 # load address
        syscall

        # Get user input from keyboard re: number of balls in pool 
        li $v0, 5 # read integer input
        syscall
        
        # Store user input in the global variable numPool
       	sw $v0, numPool   	

	# Prompt user to enter number of balls to select
        li $v0, 4 # print prompt2
        la $a0, prompt2 # load address
        syscall

        # Get user input from keyboard re: number of balls to select from pool 
        li $v0, 5 # read integer input
        syscall
        
        # Store user input in the global variable numSelect
        sw $v0, numSelect
        
        # Check to make sure the number of balls selected is 1 < x < 11 && <= numPool
        sltiu  $t3, $v0, 12 # if numSelect < 12, $t3 = 1; else, $t3 = 0
        bne $t3, 1, inRange # if numSelect is not in range, branch to exception handling
        addi $t4, $v0, 1 # add 1 to numPool and store in $t4 (to make sure numSelect <= numPool)  
        lw $t5, numPool # load word numPool into register $t5 
        slt  $t6, $v0, $t5 # if numSelect < numPool + 1 , $t4 = 1; else, $t4 = 0
        bne $t6, 1, tooBig # if numSelect is not in range, branch to exception handling

        # Calculate (n-k) and then call factorial function [ i.e. get (n:n-k)! ]
        lw $t7, numPool	# load word numPool into register $t7 
        lw $t8, numSelect # load word numSelect into register $t8
        sub $s0, $t7, $t8 # calculate (numPool - numSelect) and store in $a0 (later, will use this value to simplify factorial calculations)
        addi $s0, $s0, 1
        
        sw $s0, numDiff # store contents of $s0 in word numDiff
   	lw $a0, numPool 
        jal pfctrlNum	# jump and link to factorial function
        sw $v0, factorialA	# store result of factorial function in the global variable "answer"
        
        # Calculate k! using the factorial function (this will be the denominator)
        lw $a0, numSelect # store contents of $a0 in word numSelect
        jal pfctrlDenom	# jump and link to factorial function
        sw $v0, factorialB	# store result of factorial function in the global variable "answer"
        
        lw $t9, factorialA
        lw $s1, factorialB
        
        # Divide (n-k)!/k!    ## what you want is to factor out what num and denom have in common (LOOP, n-- each time while n > n-k
        div $v0, $t9, $s1
        sw $v0, answer
        
   
        # Display the results (string) 
        li $v0, 4	# print string
        la $a0, resultMessage	# load address for resultMessage (a string) 
        syscall 
       
        # Display the results (int)  
        li $v0, 1 # print integer
        lw $a0, answer # load word "factorial" 
        syscall 

	#System Exit 
	li $v0, 10
	syscall
	
############################################################################################################
	
# Input exception handling

# Check to make sure numSelect is <= 12
inRange:li $v0, 4	# print string
        la $a0, error1	# load address for error1(a string) 
        syscall 
        
        j main	#restart program
        
# Check to make sure numSelect is <= numPool
tooBig:	li $v0, 4	# print string
        la $a0, error2	# load address for error2 (a string) 
        syscall 
        
        j main	#restart program    



############################################################################################################	
	
# Factorial subroutine

pfctrlNum: sw $ra, 4($sp) # save the return address
        sw $a0, 0($sp) # save the current value of n
        addi $sp, $sp, -8 # move stack pointer
        sle $t0, $a0, $s0 # save 1 iteration, n=0 or n=1; n!=1   #CHANGE THIS TO MODIFY ALGEBRA!
        beq $t0, $zero, L1 # not, calculate n(n-1)!
        addi $v0, $zero, 1 # n=1; n!=1
        jr $ra # now multiply

L1:     addi $a0, $a0, -1 # n := n-1

        jal pfctrlNum # now (n-1)!

        addi $sp, $sp, 8 # reset the stack pointer
        lw $a0, 0($sp) # fetch saved (n-1)
        lw $ra, 4($sp) # fetch return address
        mul $v0, $a0, $v0 # multiply (n)*(n-1)
        jr $ra	# go back to main so that results can be displayed 


pfctrlDenom: sw $ra, 4($sp) # save the return address
        sw $a0, 0($sp) # save the current value of n
        addi $sp, $sp, -8 # move stack pointer
        slti $t0, $a0, 2 # save 1 iteration, n=0 or n=1; n!=1   
        beq $t0, $zero, L2 # not, calculate n(n-1)!
        addi $v0, $zero, 1 # n=1; n!=1
        jr $ra # now multiply

L2:     addi $a0, $a0, -1 # n := n-1

        jal pfctrlDenom # now (n-1)!

        addi $sp, $sp, 8 # reset the stack pointer
        lw $a0, 0($sp) # fetch saved (n-1)
        lw $ra, 4($sp) # fetch return address
        mul $v0, $a0, $v0 # multiply (n)*(n-1)
        jr $ra	# go back to main so that results can be displayed 



