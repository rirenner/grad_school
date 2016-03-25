
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
        I:	.word 1
        V:	.word 5
        X:	.word 10
        L:	.word 50
        C:	.word 100
        D:	.word 500
        M:	.word 1000
        input1:	.asciiz "LXXIX"
        input2: .asciiz "MMXVI"
        input3: .asciiz 
 
######################################################################################################################################################
.text


.globl main

main:

 
 	la $t0, input1	#load the address of the string (see by looking at starting index of each the length of each string 
	lb $a0, 3($t0) #This gets the FIRST byte of the string (from L to R); change the offset to get others (i.e. lb $a0, 3($t0) = I
	li $v0, 11	# print byte to console 
	syscall
	
	

