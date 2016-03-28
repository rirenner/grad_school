
#####################################
#   Christine Herlihy               #
#   3/23/16 EN.605.204.81.SP16      #
#   Module 8 Assignment             #
#   From Rome to Arabia Lab  	    #
#####################################

#	This program runs in MIPS, and performs the following functions:
#
#	(1)	Reads input Roman Numerals as ASCII text characters
#
#	(2)	Calculates the equivalent decimal value
#
#	(3)	Writes the calculated value as Arabic numerals to the SPIM
#		console, with appropriate commentary text
#
#	(4)	Tests by calculating the values of these Roman numerals:
#		{I:1, V:5, X:10, L:50, C:100, D:500, M:1000}
#
#	(5)	Calculates and displays the results of the following Roman numerals:
#		-> LXXIX: 79
#		-> MMXVI: 2016
#		-> MCMXLVIII: 1948
#		-> MDCCCCVIIII: 1909
#
#	NOTE: There is no check for "valid" Roman numerals (i.e., VIIII is not "valid" but will be computed as = 9) 
#	However, subtraction logic is used (i.e. numbers that decrease from L to R = addition; a smaller number to the left
#	of a larger number represents subtraction, such that IX == VIIII == 9.


# To clarify: Should we prompt the user to enter in each string one at a time? Should we just run it for these values?
# Loop to allow testing? 


.data 
        # Lookup tables
        #roman:		.asciiz "IVXLCDMivxlcdm"
        roman:		.asciiz "IVXLCDM"
	#values:		.byte 1, 5, 10, 50, 100, 500, 1000, 1, 5, 10, 50, 100, 500, 1000
	values:		.byte 1, 5, 10, 50, 100, 500, 1000	
	
	# Input
	input1:		.word 4 
	input2:		.asciiz "\n "
	input3:		.asciiz "\n "
	input4:		.asciiz "\n"
	
	# Conversion 
	sum:		.word 0
	counter:	.word 0
	temp:		.word 0
	
	# I/O and program control
	convertAgain:	.word 2
        prompt1:	.asciiz "\nPlease enter the Roman Numeral to convert in all uppercase OR all lowercase letters (i.e. VII or vii): "
        outRecap:	.asciiz "\nYou entered: "
        outFinal:	.asciiz "\n The equivalent decimal value is: "
        loopdriver:	.asciiz "\nWould you like to convert another number? Enter 1 for YES and 2 for NO: "  
        stop:		.asciiz "\n\nProgram complete.\n"
        
        # Exception handling
        error1:		.asciiz "\nThe Roman numeral you have entered contains invalid character(s). Please try again.\n"
        
 
######################################################################################################################################################
.text

.globl main

main:

        #Prompt user to enter the Roman Numeral to convert
        li $v0, 4 # print prompt1
        la $a0, prompt1 # load address
        syscall
        
        # Load the word reserved for input, and read in the Roman Numeral string input by the user         
        la $a0, input1 # load contents of input1 (i.e. space allocated to save the input string) into $a0
        la $a1, input1 # load the length allocated for input1 (i.e. 4 bytes or 32 bits) into $a1 to prevent overflow
        li $v0, 8 # read string from user 
        syscall 
        
        
        ### EDIT HERE: CONVERT / SHOW ALL THE TEST CASES BY DEFAULT, THEN ALLOW USER TO ENTER!
        
       jal init
        
        
        # Load output string 1 to show the user their original input string
        la $a0, outRecap # load contents of outRecap into $a0
        li $v0, 4 # print string 
        syscall
        
        # Load the string the user entered 
        la $a0, input1 # load contents of input1 into $a0
        li $v0, 4 # print string
        syscall
        
        # Ask the user whether they want to convert another Roman Numeral (1 if YES; 2 if NO; default is NO)
        la $a0, loopdriver
        li $v0, 4
        syscall 
        
        # Get user input from keyboard (1 = they want to convert another Roman Numeral; 2 = Exit; default = 2)
        li $v0, 5 # read integer input
        syscall
        
        # Save the user's input and jump to "Exit" 
        sw $v0, convertAgain
        lw $t0, convertAgain
        
        bne $t0, 1, Exit # if the user enters a number != 1, go to Exit
        j main	# Go back to start if the user enters 1
        
######################################################################################################################################################      

# Take one char at a time. 
# Compare it to every element in the roman "table"
# get the offset
# save the offset in a temp var

# get the next value
#compare them
# if cur > = next, add next to the sum



# Convert Roman Numeral string to integer decimal value   
init: 
	
	sw $ra, 4($sp)
	sw $a1, 0($sp)
	addi $sp, $sp, -8 # move stack pointer	
	la $t2, input1	#load the address of the string (see by looking at starting index of each the length of each string 
	la $t3, roman
	la $t4, values
	

loop1:	lb $a0, ($t2) # This gets the FIRST byte of the string (from L to R); change the offset to get others (i.e. lb $a0, 3($t0) = I
	beqz $a0, return # If the byte == 0, we have reached the end of the input string; jump to "return"
	
	li $v0, 11	# print byte to console 
	syscall
	
	# While string[i] != null:
	jal index

	addi $t2, $t2, 1 # Move to next character in input string 
	jal loop1 # iterate through loop1 again
	
index:	lb $t5, ($t3) # load the first byte of romans
	beqz $t5, invalid # if we reach the end of romans and the char isn't found, it's not a valid Roman numeral
	#beq $a0, $t5, getVal # once we find the index we want, where the romans value matches our char, need to look it up in values
	addi $t3, $t3, 1 # if we haven't found a match, increment $t3 and iterate through the loop again
	jal index # loop
	

getVal:	la $t6, roman # Get the address of the array containing Roman numeral characters
	la $t7, values
	
	sub $t8, $t3, $t6 # Get the index value of the element that matches the byte we are working with [1:7]
	add $t7, $t7, $t8 
	lb $t9, ($t7) 
	sw $t9, sum

	

	#li $v0, 1	# print int
       # lw $a0, sum 	# load address for stop (a string)
       # syscall
	

	jr $s1
     



compare: 
	
	


	
	
return:	
	addi $sp, $sp, 8 # reset the stack pointer
	lw $a0, 0($sp) # fetch saved (n-1)
        lw $ra, 4($sp) # fetch return address
	jr $ra	# go back to main so that results can be displayed
	
#Exception handling: User input string contains invalid character(s) 
invalid: 
        li $v0, 4	# print string
        la $a0, error1	# load address for error1 (a string)
        syscall

	j main 






#System Exit
Exit: 

	# Display exit message
        li $v0, 4	# print string
        la $a0, stop	# load address for stop (a string)
        syscall

        li $v0, 10
        syscall
        
######################################################################################################################################################      

        
 
        # Store user input in the global variable numPool
       #	sw $v0, input1
       	

#	la $a0, Tell_Output #load address Tell_Output from memory and store it into arguement register 0
#	li $v0, 4 #loads the value 4 into register $v0 which is the op code for print string
#	syscall #reads register $v0 for op code, sees 4 and prints the string located in $a0

#	la $a0, insert_into #load address insert_into from memory and store it into arguement register 0
#	li $v0, 4 #loads the value 4 into register $v0 which is the op code for print string
#	syscall #reads register $v0 for op code, sees 4 and prints the string located in $a0

#	li $v0, 10 #loads op code into $v0 to exit program
#	syscall #reads $v0 and exits program
        
        
        
#Then write a loop that compares the character you just read from your input to a character in all_numerals.
# Once you find the index, get the value at the same index in all_values. 
 #For example, "X" is the third element in all_numerals and has an index of 2.
 # Go to index 2 of all_values and you get 10. If you hit null before you find a match, 
 # that means that the character you read is invalid, and you can handle it accordingly. 

 
 	la $t0, input1	#load the address of the string (see by looking at starting index of each the length of each string 
	lb $a0, 3($t0) #This gets the FIRST byte of the string (from L to R); change the offset to get others (i.e. lb $a0, 3($t0) = I
	li $v0, 11	# print byte to console 
	syscall



	
	

