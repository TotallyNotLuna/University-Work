#@author Deven Ronquillo
#@version 18/3/19
#
#parses a file for frequency of word length.

#---------------------IMPORTS------------------------
from time import time

#---------------------FUNCTIONS----------------------

def GetWordFrequency(start, end):

	for start in range(end):

		ParseText( FormatText( file.readline() ) )

		#percent = ( float(start)/float(end) )* 100
		#print("Completion: %.2f%% : %d / %d\n" %(percent, start, end) )



def FormatText(text):

	#print("Converting: " + text + "\n")

	for char in text:

		if char.isalpha() == False and char.isdigit() == False and char != "-" and char != "_":

			text = text.replace(char, " ")

	#print("Into: " + text + "\n")

	return text
			


def ParseText(text):

	#print("Parsing: " + text + "\n")

	size = 0

	for char in text:

		if char != " ":

			size = size + 1
		else:

			if size >= 8:

				Count[8] = Count[8] + 1
			else:

				Count[size] = Count[size] + 1

			size = 0

	#print("Done!\n")

def PrintCount():

	total = 0

	for index in range(1,len(Count)):

		total = total + Count[index]

	print("Total word count: %d\n" %total)

	for index in range(1,len(Count)):

		freq = (float(Count[index])/float(total))*100

		print("Words of length %d found: %d : %.2f%%\n" %(index, Count[index ], freq) )



#-----------------------MAIN--------------------



file = open("enwik9", "r")
lineCount = sum(1 for line in file)

Count = [0, 0, 0, 0, 0, 0, 0 , 0, 0]

file.seek(0)

print("File Name: %s" %file.name )
print("Total line count: %d\n" %lineCount)

startTime = time()

GetWordFrequency(1, lineCount)

startTime = time() - startTime

print("The operation took: %d's" %startTime)
PrintCount()





