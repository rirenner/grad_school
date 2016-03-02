

**HW #5**
*****

###Exercises [from the textbook]:
 
###2.20:
Find the shortest sequence of MIPS instructions that extracts bits 16 down to 11 from register $t0 
and uses the value of this field to replace bits 31 down to 26 in register $t1 without changing 
the other 26 bits of register $t1

    srl $t2, $t0, 10 # Shift $t0 10 bits to the right (i.e. get the sequence of bits we want to keep at the beginning of the word)
    sll $t2, $t2, 26 # Shift  $t2 26 bits to the left; bits 0-25 will be zeros
    sll $t1, $t1, 6 # Shift $t1 6 bits to the left; cut off bits 26-31, which will be replaced by zeros
    srl $t1, $t1, 6 # Shift $t1 6 bits to the right, so that the last 6 bits contain zeros
    add $t1, $t2, $t1 # add $t1 and $t2; since bits 0-26 of $t2 are 0, and bits 26-31 of $t2 are 0, $t1 now contains the result
 
###2.25 
The following instruction is not included in the MIPS instruction set:

    rpt $t2, loop # if (R[rs]>0) R[rs] = R[rs]-1, PC = PC + 4 + BranchAddr

This is a 'repeat' instruction that tests a counter value, subtracts one from the counter and branches to the label 'loop'.

#####2.25.1:
If this instruction were to be implemented in the MIPS instruction set, what is the most appropriate instruction format?

The most appropriate instruction format is the **I-type/I-format**. 

#####2.25.2
What is the shortest sequence of MIPS instructions that performs the same operation?

    LOOP: slt $t3, $zero, $t2 # Set temp var $t3 to 1 if 0< $t2; else, set $t3 to zero  
          beq $t3, $zero, DONE # When the slt command results in zero, R[rs] is !> 0; terminate loop and go to DONE
          sub $t2, $t2, 1 # if (R[rs]>0), decrement R[rs] by 1
          j LOOP # iterate again through LOOP if (R[rs]>0)
     DONE:

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

First iteration:

    LOOP: slt $t2, $0, $t1 # 0 < 10; here, $t2 = 1
        beq $t2, $0, DONE # 1 != 0; go to next instruction
        sub $t1, $t1, 1 # $t1 = 10 - 1= 9
        addi $s2, $s2, 2 # $s2 = 0 + 2= 2
        j    LOOP # iterate again
    DONE:

Second iteration:

    LOOP: slt $t2, $0, $t1 # 0 < 9; here, $t2 = 1
        beq $t2, $0, DONE # 1 != 0; go to next instruction
        sub $t1, $t1, 1 # $t1 = 9-1= 8
        addi $s2, $s2, 2 # $s2 = $s2 + 2 = 4
        j    LOOP # iterate again
    DONE:

Third iteration:

    LOOP: slt $t2, $0, $t1 # 0 < 8; here, $t2 = 1
        beq $t2, $0, DONE # 1 != 0; go to next instruction
        sub $t1, $t1, 1 # $t1 = 8-1=7
        addi $s2, $s2, 2 # $s2 = $s2 + 2 = 6
        j    LOOP # iterate again
    DONE:
    
Fourth iteration:

    LOOP: slt $t2, $0, $t1 #  0 < 7; here, $t2 = 1
        beq $t2, $0, DONE # 1 != 0; go to next instruction
        sub $t1, $t1, 1 # $t1 = 7-1= 6
        addi $s2, $s2, 2 # $s2 = $s2 + 2 = 8
        j    LOOP # iterate again
    DONE: 
    
 Fifth iteration:

    LOOP: slt $t2, $0, $t1 #  0 < 6; here, $t2 = 1
        beq $t2, $0, DONE # 1 != 0; go to next instruction
        sub $t1, $t1, 1 # $t1 = 6-1=5
        addi $s2, $s2, 2 # $s2 = $s2 + 2 = 10
        j    LOOP # iterate again
    DONE: 

Sixth iteration:

    LOOP: slt $t2, $0, $t1 #  0 < 5; here, $t2 = 1
        beq $t2, $0, DONE # 1 != 0; go to next instruction
        sub $t1, $t1, 1 # $t1 = 5-1 =4
        addi $s2, $s2, 2 # $s2 = $s2 + 2 = 12
        j    LOOP # iterate again
    DONE: 

Seventh iteration:

    LOOP: slt $t2, $0, $t1 #  0 < 4; here, $t2 = 1
        beq $t2, $0, DONE # 1 != 0; go to next instruction
        sub $t1, $t1, 1 # $t1 = 4-1=3
        addi $s2, $s2, 2 # $s2 = $s2 + 2 = 14
        j    LOOP # iterate again
    DONE: 

Eigth iteration:

    LOOP: slt $t2, $0, $t1 #  0 < 3; here, $t2 = 1
        beq $t2, $0, DONE # 1 != 0; go to next instruction
        sub $t1, $t1, 1 # $t1 = 3-1=2
        addi $s2, $s2, 2 # $s2 = $s2 + 2 = 16
        j    LOOP # iterate again
    DONE: 

Ninth iteration:

    LOOP: slt $t2, $0, $t1 #  0 < 2; here, $t2 = 1
        beq $t2, $0, DONE # 1 != 0; go to next instruction
        sub $t1, $t1, 1 # $t1 = 2-1=1
        addi $s2, $s2, 2 # $s2 = $s2 + 2 = 18
        j    LOOP # iterate again
    DONE: 

Tenth iteration:

    LOOP: slt $t2, $0, $t1 #  0 < 1; here, $t2 = 1
        beq $t2, $0, DONE # 1 != 0; go to next instruction
        sub $t1, $t1, 1 # $t1 = 1-1=0
        addi $s2, $s2, 2 # $s2 = $s2 + 2 = 20
        j    LOOP # iterate again
    DONE: 

Eleventh iteration:

    LOOP: slt $t2, $0, $t1 #  0 == 0; here, $t2 = 0
        beq $t2, $0, DONE # 0 == 0; go to DONE
        sub $t1, $t1, 1 
        addi $s2, $s2, 2 
        j    LOOP 
    DONE: 

The value in the register $s2 is **20**

#####2.26.2
For each of the loops above, write the equivalent C code routine. Assume that the registers $s1, $s2, $t1, and $t2 are integers A, B, i, and temp, respectively.

     while(i > 0){
         i= i-1 //Decrement i by one during each iteration of the loop (sub $t1, $t1, 1)
         B = B + 2 //Increment B by 2 each time (addi $s2, $s2, 2)
      } //exit loop 

#####2.26.3
For the loops written in MIPS assembly above, assume that the reigster $t1 is initialized to the value N. How many MIPS instructions are executed?

Base case: N= 0. If N=0, only 2 MIPS instructions are executed:

        LOOP: slt $t2, $0, $t1 #  0 == 0; here, $t2 = 0  [#1]
        beq $t2, $0, DONE # 0 == 0; go to DONE           [#2]
        sub $t1, $t1, 1 
        addi $s2, $s2, 2 
        j    LOOP 
    DONE: 

For any value of N > 0, (N full loops + 2 lines) of MIPS instructions must be executed; each full loop contains 5 MIPS instructions.
Therefore, if the register $t1 is initialized to the value N, a total of (5*N)+2 MIPS instructions are executed. 

###Additional exercises:
#####For each of these pseudo-instructions, write the MIPS hardware instructions that the Assembler would use to implement them.  Do not destroy the values in the source registers.  ( Use only the minimum number of instructions needed.)

     abs  $s4, $s1      #  absolute value;  page A-51

Answer:

         bgez $s1, POS #if the integer in register $s1 is >=0, then go to POS (no need to invert bits)
         nor $s4, $s1, $zero #if the integer in register $s1 < 0, $s1 NOR $zero will invert each bit
         addi $s4, $s4, 1 #add one to get the two's complement of the original (negative) integer
     POS: add $s4, $s1, $zero #the integer in $s1 >=0; adding $s1 + $zero lets us "move" the value into the destination register, $s4


     rol  $t7, $t3, 8   #  rotate left;  page A-56

Answer:

https://www.utdallas.edu/~dodge/EE2310/lec14.pdf gives an explanation of rotate left: "For a left rotate (rol), bits shifted off the left end of a data word fill the vacated positions on the right"

Thus, here, we want to rotate the contents of $t3 8 bits to the left, and store the result in $t7

    sll $t0, $t3, 8 #shift the contents of $t3 8 bits to the left, and store the result in $t0
    srl $t1, $t3, 24 #shift the contents of $t3 24 bits to the right, and store the result in $t1
    or $t7, $t0, $t1 #perform bitwise OR $t0 | $t1, and store the results in $t7
 
     ld   $t5, 0($t8)   # load double;  page A-67
 
 

Answer:
