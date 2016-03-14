

.data
        prompt1:	.asciiz "Enter the number of balls in pool: "
        prompt2:	.asciiz "Enter the number of balls to select: "  #ERROR handling! make sure this val is < 12 AND < the number of balls in the pool!!!
	resultMessage:	.ascii "The odds are 1 in "
	numPool:	.word 0
	numSelect: 	.word 0
	answer:    	.word 0
.text

.globl main

main:   li $v0, 4 # print prompt1
        la $a0, prompt1 # load address
        syscall

        # Get user input from keyboard re: number of balls in pool 
        li $v0, 5	# read integer input
        syscall
        
        # Store user input in the global variable numPool
       	sw $v0, numPool 

	# Get user input from keyboard re: number of balls to select from pool 
        li $v0, 4 # print prompt2
        la $a0, prompt2 # load address
        syscall

        #Get user input from keyboard
        li $v0, 5 # read integer input
        syscall
        
        # Store user input in the global variable numSelect
        sw $v0, numSelect

        # Call factorial function
        lw $a0, numPool	# load word numPool into register $a0 
        jal pfctrl	# jump and link to factorial function
        sw $v0, answer	# store result of factorial function in the global variable "answer"
        
        # Display the results (string) 
        li $v0, 4	# print string
        la $a0, resultMessage	# load address for resultMessage (a string) 
        syscall 
       
        # Display the results (int)  
        li $v0, 1 # print integer
        lw $a0, answer # load word "answer" 
        syscall 

	#System Exit 
	li $v0, 10
	syscall
	
# Input error handling
	
	
	
# Factorial subroutine
pfctrl: sw $ra, 4($sp) # save the return address
        sw $a0, 0($sp) # save the current value of n
        addi $sp, $sp, -8 # move stack pointer
        slti $t0, $a0, 2 # save 1 iteration, n=0 or n=1; n!=1   #CHANGE THIS TO MODIFY ALGEBRA!
        beq $t0, $zero, L1 # not, calculate n(n-1)!
        addi $v0, $zero, 1 # n=1; n!=1
        jr $ra # now multiply

L1:     addi $a0, $a0, -1 # n := n-1

        jal pfctrl # now (n-1)!

        addi $sp, $sp, 8 # reset the stack pointer
        lw $a0, 0($sp) # fetch saved (n-1)
        lw $ra, 4($sp) # fetch return address
        mul $v0, $a0, $v0 # multiply (n)*(n-1)
        jr $ra	# go back to main so that results can be displayed 



