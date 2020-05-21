# Factorial Calculator Ft. MIPS
# Recursively calculates the factorial of a given positive intiger.

# ------------------------------------------------------------------
	
	.text

	.globl	main

main:

	li $v0, 4#loads title
	la $a0, title
	syscall

	input:	li$v0, 4#loads prompt
			la $a0, userprompt
			syscall

			li $v0, 5#waits for user input
			syscall

	slt $t0, $v0, $zero

	bne $t0, $zero, error#checkes - case

	addi $t1, $zero, 13#checkes for >= case
	bge $v0, $t1, error

	move $a0, $v0#stores user input in arg 0
	jal fact

	move $t0, $v0#moves our answer temporarily

	li $v0, 4#loads output
	la $a0, factoutput
	syscall

	li $v0, 1
	move $a0, $t0
	syscall

	j input#reloads input

	error:	li $v0, 4#loads error
			la $a0, erroroutput
			syscall

			j input
		

# ------------------------------------------------------------------
	
	# FUNCTION: int fact(int a)
	# Arguments $a0
	# Return $v0
	# Return_address $ra

fact:

	addi $sp, $sp, -8#make room on stack

	sw $a0, 4($sp)#store our int an return adress
	sw $ra, 0($sp)

	slti $t0, $a0, 1#check for base case
	beq $t0, $zero, else

	addi $v0, $zero, 1#handle base case
	addi $sp, $sp, 8

	jr $ra

	else:	addi $a0, $a0, -1#handle recursive case
			jal fact

			lw $a0, 4($sp)
			lw $ra, 0($sp)

			addi $sp, $sp, 8
			mul $v0, $a0, $v0

			jr $ra

	
	

# ------------------------------------------------------------------
	
	#.data segment
	.data

		title: .asciiz "--FACTORIAL CALCULATOR--(Ft. MIPS)--\n"
		userprompt: .asciiz "\nInput (int): "
		factoutput: .asciiz "Fact(x) = "

		erroroutput: .asciiz "input invalid! Please give a positive integer less than 13.\n"