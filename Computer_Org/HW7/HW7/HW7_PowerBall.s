
#####################################
#   Christine Herlihy               #
#   3/22/16 EN.605.204.81.SP16      #
#   Module 7 Assignment             #
#   Powerball Lottery Jackpot Lab   #
#####################################


#   This program runs in MIPS, and performs the following functions:
#
#   (1) Prompts the user if they would like to calculate simple odds, or odds of winning PowerBall jackpot grand prize
#       Win = match 5 white balls pulled from bin with range [1:69] (any order; no replacement)
#       & 1 red "power ball" from bucket with range [1:29]
#
#   (2) Prompts the user for two input values: (1) an integer representing the large pool of possible numbers
#       and (2) a second integer representing the count of numbers to be selected from the large pool
#           (implicit constraint): 0 < number selected < 12 && <= number of balls in the pool (exception handling is included)
#
#   (3) Displays the calculated value (i.e. odds) on the SPIM console screen
#
#   (4) Displays a message when the program stops
#
#   TO RUN: load program; click "Run", and (when prompted), enter appropriate values for number of balls in pool & number selected
#
#   NOTE: This program uses single precision integer arithmetic instructions, and as such, will not produce correct results if odds values >= 1 in 2 billion
#           In such cases, an exception is triggered, an overflow error message is displayed, and the user is forced to enter new values.
#
#   Several screenshots of sample program output are included in the directory ~/HW_outputImages


.data
        prompt1:        .asciiz "\nEnter the number of balls in pool: "
        prompt2:        .asciiz "\nEnter the number of balls to select: "  #Exception: make sure this val is < 12 AND < the number of balls in the pool
        prompt3:        .asciiz "\nEnter 1 to calculate normal odds; enter 2 to calculate odds of winning the PowerBall jackpot grand prize: "
        error1:         .asciiz "\nError: the number of balls selected must be an integer between 1 and 11. Please start over.\n"
        error2:         .asciiz "\nError: the number of balls selected must be <= the number of balls in the pool. Please start over.\n"
        error3:         .asciiz "\nError: Overflow has occurred; odds cannot be accurately calculated. Please start over.\n"
        resultMessage:	.asciiz "\nThe odds are 1 in "
        stop:           .asciiz "\n\nProgram complete.\n"
        numPool:        .word 0
        numSelect:      .word 0
        powerballBool:	.word 1 # The red ball is C(26, 1); = 26!/(1!*25!) = 26; there is only ONE red ball; init to 1; will change pending user input
        numDiff:        .word 0	# To store numPool- numSelect (i.e. n-k in combinatorics)
        factorialA:    	.word 0
        factorialB: 	.word 0
        answer:         .word 0

######################################################################################################################################################
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

        # Check to make sure the number of balls selected is 1 < x < 12 && <= numPool
        sltiu  $t3, $v0, 11 # if numSelect < 12, $t3 = 1; else, $t3 = 0
        bne $t3, 1, inRange # if numSelect is not in range, branch to exception handling
        addi $t4, $v0, 1 # add 1 to numPool and store in $t4 (to make sure numSelect <= numPool)
        lw $t5, numPool # load word numPool into register $t5
        slt  $t6, $v0, $t5 # if numSelect < numPool + 1 , $t4 = 1; else, $t4 = 0
        bne $t6, 1, tooBig # if numSelect is not in range, branch to exception handling

        # Prompt user re: Powerball (should the odds of matching the powerball be factored in? 1 red ball; range= [1,26])
        li $v0, 4 # print prompt3
        la $a0, prompt3 # load address
        syscall

        # Get user input from keyboard re: normal odds (powerballBool = 1) or powerball jackpot odds (powerballBool=26)
        li $v0, 5 # read integer input
        syscall

        # Store user input in the global variable powerballBool
       	sw $v0, powerballBool # store user input in variable powerballBool
       	jal powerball # sub-routine to handle user input re: powerball

        # Calculate (n-k) and then call factorial function [ i.e. get (n:n-k)! ]
        lw $t7, numPool	# load word numPool into register $t7
        lw $t8, numSelect # load word numSelect into register $t8
        sub $s0, $t7, $t8 # calculate (numPool - numSelect) and store in $a0 (later, will use this value to simplify factorial calculations)
        addi $s0, $s0, 1 # add numDiff + 1 (for controlling the sle loop in the pfctrlNum sub-routine

        sw $s0, numDiff # store contents of $s0 in word numDiff
        lw $a0, numPool # load numPool into $a0; use this as the argument for pfctrlNum
        jal pfctrlNum	# jump and link to factorial function
        sw $v0, factorialA	# store result of factorial function in the global variable "answer"

        # Calculate k! using the pfctrlDenom function (this will be the denominator)
        lw $a0, numSelect # store contents of $a0 in word numSelect
        jal pfctrlDenom	# jump and link to factorial function
        sw $v0, factorialB	# store result of factorial function in the global variable "answer"

        #Load numberator and denominator into temporary variables
        lw $t9, factorialA  # load word factorialA into register $t9
        lw $s1, factorialB  # load word factorialB into register $s1

        # Divide [n:n-k)!/k!
        divu $v0, $t9, $s1
        mfhi $s3 # store upper bits in register $s3
        bne $s3, $zero, ovrflw # check to make sure overflow has NOT occurred; if it has, branch to exception handling

        # Multiply the result of [n:n-k)!/k! by the powerball variable (26 if powerball is included; 1 otherwise)
        mult  $v0, $s2  # get the product of $v0 * $s2
      	mflo $v0    # store lower bits in $v0
        mfhi $s4    # store upper bits in $s3
        bne $s4, $zero, ovrflw # check to make sure overflow has NOT occurred; if it has, branch to exception handling
        sw $v0, answer

        # Display the results (string)
        li $v0, 4	# print string
        la $a0, resultMessage	# load address for resultMessage (a string)
        syscall

        # Display the results (int)
        li $v0, 1 # print integer
        lw $a0, answer # load word "answer"
        syscall

        # Display exit message
        li $v0, 4	# print string
        la $a0, stop	# load address for stop (a string)
        syscall

        #System Exit
        li $v0, 10
        syscall

######################################################################################################################################################

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

# Check to see if loss of precision has occurred; if so, show error message and return to start
ovrflw: li $v0, 4	# print string
        la $a0, error3	# load address for error3 (a string)
        syscall

        j main	#restart program


#######################################################################################################################################################

# Set powerball [boolean; if selected, = 26, to represent C(26, 1)= 26!/(1!*25!) = 26. If NOT selected, set equal to 1]

powerball:	sw $ra, 0($sp) # save the return address
            addi $sp, $sp, -4 # move the stack pointer
            lw $s2, powerballBool # load word powerballBool into register $s2
            bne $s2, 1, init # if $s2 != 1, goto init (change from 1 to 26)
            jr $ra

init:       mult $s2, $zero # in case user has entered a value != 1 & !=2
            mflo $s2
            addi $s2, $s2, 26   # init (powerball -> 26)

            addi $sp, $sp, 4 # reset the stack pointer
        	lw $ra, 0($sp) # fetch saved (n-1)
            jr $ra  # jump to return address

#######################################################################################################################################################

# Factorial subroutines

# Numerator [n:n-k)!

pfctrlNum:  sw $ra, 4($sp) # save the return address
            sw $a0, 0($sp) # save the current value of n
            addi $sp, $sp, -8 # move stack pointer
            sle $t0, $a0, $s0 # save 1 iteration, n=0 or n=1; n!=1   #CHANGE THIS TO MODIFY ALGEBRA!
            beq $t0, $zero, L1 # not, calculate n(n-1)!
            addi $v0, $zero, 1 # n=1; n!=1
            jr $ra # now multiply

L1:         addi $a0, $a0, -1 # n := n-1

            jal pfctrlNum # now (n-1)!

            addi $sp, $sp, 8 # reset the stack pointer
            lw $a0, 0($sp) # fetch saved (n-1)
            lw $ra, 4($sp) # fetch return address
            mul $v0, $a0, $v0 # multiply (n)*(n-1)
            jr $ra	# go back to main so that results can be displayed

# Denominator (k!)

pfctrlDenom: sw $ra, 4($sp) # save the return address
            sw $a0, 0($sp) # save the current value of n
            addi $sp, $sp, -8 # move stack pointer
            slti $t0, $a0, 2 # save 1 iteration, n=0 or n=1; n!=1   #CHANGE THIS TO MODIFY ALGEBRA!
            beq $t0, $zero, L2 # not, calculate n(n-1)!
            addi $v0, $zero, 1 # n=1; n!=1
            jr $ra # now multiply

L2:         addi $a0, $a0, -1 # n := n-1

            jal pfctrlDenom # now (n-1)!

            addi $sp, $sp, 8 # reset the stack pointer
            lw $a0, 0($sp) # fetch saved (n-1)
            lw $ra, 4($sp) # fetch return address
            mul $v0, $a0, $v0 # multiply (n)*(n-1)
            jr $ra	# go back to main so that results can be displayed
