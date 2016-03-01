
HW #5
*****

##Exercises [from the textbook]:
 
###2.20:
Find the shortest sequence of MIPS instructions that extracts bits 16 down to 11 from register $t0 
and uses the value of this field to replace bits 31 down to 26 in register $t1 without changing 
the other 26 bits of register $t1

    srl $t2, $t0, 10 //Shift $t0 10 bits to the right (i.e. get the sequence of bits we want to keep at the beginning of the word)
    sll $t2, $t2, 26 //Shift  $t2 26 bits to the left; bits 0-25 will be zeros
    sll $t1, $t1, 6 //Shift $t1 6 bits to the left; cut off bits 26-31, which will be replaced by zeros
    srl $t1, $t1, 6 //Shift $t1 6 bits to the right, so that the last 6 bits contain zeros
    add $t1, $t2, $t1 //add $t1 and $t2; since bits 0-26 of $t2 are 0, and bits 26-31 of $t2 are 0, $t1 now contains the result
 
###2.25 
The following instruction is not included in the MIPS instruction set:
    rpt $t2, loop # if (R[rs]>0) R[rs] = R[rs]-1, PC = PC + 4 + BranchAddr

This is a 'repeat' instruction that tests a counter value, subtracts one from the counter and branches to the label 'loop'.

#####2.25.1:
If this instruction were to be implemented in the MIPS instruction set, what is the most appropriate instruction format?

The most appropriate instruction format is the I-type/I-format. 

#####2.25.2
What is the shortest sequence of MIPS instructions that performs the same operation?

    LOOP: slt $t3, $t2, $zero //Set temp var $t3 to 1 if $t2 < 0; else, set $t3 to zero  
          beq $t3, $zero, Exit //When the slt command results in zero, R[rs] is !> 0; terminate loop and go to EXIT
          sub $t2, $t2, 1 //if (R[rs]>0), decrement R[rs] by 1
          j LOOP //iterate again through LOOP if (R[rs]>0)
     EXIT:

###2.26  
(Note: there is no 'subi' instruction. The textbook has a 'typo'.)
Consider the following MIPS loop:
    LOOP: slt $t2, $0, $t1
        beq $t2, $0, DONE
        sub $t1, $t1, 1
        addi $s2, $s2, 2
        j    LOOP
    DONE:

#####2.26.1
Assume that the register $t1 is initialized to the value 10. What is the value in the register $s2 assuming $s2 is initially zero?

#####2.26.2
For each of the loops above, write the equivalent C code routine. Assume that the registers $s1, $s2, $t1, and $t2 are integers A, B, i, and temp, respectively.

#####2.26.3
For the loops written in MIPS assembly above, assume that the reigster $t1 is initialized to the value N. How many MIPS instructions are executed?

##Additional exercises:
#####For each of these pseudo-instructions, write the MIPS hardware instructions that the Assembler would use to implement them.  Do not destroy the values in the source registers.  ( Use only the minimum number of instructions needed.)
 
     abs  $s4, $s1      #  absolute value;  page A-51
 
     rol  $t7, $t3, 8   #  rotate left;  page A-56
 
     ld   $t5, 0($t8)   # load double;  page A-67
