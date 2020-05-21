# Intiger sorter Ft. MIPS
# Sorts an array of given integers into acending order.

# ------------------------------------------------------------------
	
	.text

	.globl	main

main:

	li $v0, 4#loads title
	la $a0, title
	syscall

	arrayinput:	li$v0, 4#loads prompt
				la $a0, arrayprompt
				syscall

				li $v0, 5#waits for user input
				syscall

				ble $v0, $zero, error

				addi $t0, $zero, -1
				addi $t1, $zero, 4

				move $t2, $v0

				mul $t2, $t2, $t1#negates our input
				mul $t2, $t2, $t0#calc how much stack to allocate

				add $sp, $sp, $t2#moves stack by allocation size

				move $t0, $zero#loop int
				move $t1, $v0#stores user input in $t1 to for counting
				move $t2, $sp#array index
				move $t3, $sp#array begining

	elementinput:	bge $t0, $t1, sort

					li $v0, 1#loads index number
					move $a0, $t0
					syscall

					li $v0, 4#loads prompt
					la $a0, elementprompt
					syscall

					li $v0, 5#waits for user input
					syscall

					sw $v0, 0($t2)
					addi $t2, $t2, 4

					addi $t0, $t0, 1

					j elementinput




	sortoutput:			move $t0, $zero#loop int
						move $t2, $t3#sets array index to 0

						li $v0, 4#loads string
						la $a0, finaloutput
						syscall

	sortoutputloop: 		bge $t0, $t1, exitsortoutputloop

							lw $t4, 0($t2)

							li $v0, 1#prints out int at array index
							move $a0, $t4
							syscall

							li $v0, 4#loads string
							la $a0, outputspacer
							syscall

							addi $t2, $t2, 4#move down stack
							addi $t0, $t0, 1


							j sortoutputloop

	exitsortoutputloop:	sub $t2, $t2, $t3# pop array
						add $sp, $sp, $t2

						j arrayinput
	

	error:	li $v0, 4#loads error
			la $a0, erroroutput
			syscall

			j arrayinput
		

# ------------------------------------------------------------------
	
	# FUNCTION: void sort(int a)
	# Arguments null
	# Return null
	# Return_address sortoutput

sort:	move $t0, $zero#loop int
		#$t1 array size
		move $t2, $t3#sets array pointer to index 0
		#$t3 array begining
		#t4 array item
		#t5 array item
		#t6 check/temp val

		addi $t7, $zero, 4#array end pointer
		mul $t7, $t7, $t1
		add $t7, $t7, $t3
		add $t7, $t7, -4

arrayloop: 	move $t0, $zero
			move $t2, $t3

arrayindexloop:	lw $t4 0($t2)
				lw $t5 4($t2)

				sgt $t6, $t4, $t5
				beq $t6, $zero, skipswap

				addi $t0, $zero, 1
				sw $t4, 4($t2)
				sw $t5, 0($t2)	

skipswap:		addi $t2, $t2, 4
				bne $t2, $t7, arrayindexloop
				bne $t0, $0, arrayloop

	
				j sortoutput

	
	

# ------------------------------------------------------------------
	
	#.data segment
	.data

		title: .asciiz "--Bubble Sort--(Ft. MIPS)--\n"
		arrayprompt: .asciiz "\nArray Size (int): "
		elementprompt: .asciiz " Element: "
		finaloutput: .asciiz "--Sorted Input--\n"
		outputspacer: .asciiz ", "

		erroroutput: .asciiz "input invalid!\n"