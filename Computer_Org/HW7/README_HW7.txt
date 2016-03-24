
#####################################
#   3/22/16                         #
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
