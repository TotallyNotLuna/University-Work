#@author Deven Ronquillo
#@version 18/3/19
#
#parses a file for frequency of word length.

#---------------------IMPORTS------------------------
from time import time
from threading import Thread, Lock

import linecache

#---------------------FUNCTIONS----------------------

class ParserThread(Thread):

	def __init__(self, startLine, endLine):

		Thread.__init__(self)

		self.startLine = startLine
		self.endLine = endLine

		self.Count = [0, 0, 0, 0, 0, 0, 0 , 0, 0]

	def run(self):

		self.GetWordFrequency()
		SaveCount(self.Count)


	def GetWordFrequency(self):

		

		for currLine in range(self.startLine, self.endLine + 1):
			self.ParseText( self.FormatText( linecache.getline("enwik9", currLine) ) )

			#totalLines = self.endLine - self.startLine
			#currLineOffset = currLine -self.startLine

			#percent = ( float(currLineOffset)/float(totalLines) )* 100

			#print("Completion: %.2f%% : %d / %d\n" %(percent, currLine, self.endLine) )



	def FormatText(self, text):

		#print("Converting: " + text + "\n")

		for char in text:

			if char.isalpha() == False and char.isdigit() == False and char != "-" and char != "_":

				text = text.replace(char, " ")

		#print("Into: " + text + "\n")

		return text
				


	def ParseText(self, text):

		#print("Parsing: " + text + "\n")

		size = 0

		for char in text:

			if char != " ":

				size = size + 1
			else:

				if size >= 8:

					self.Count[8] = self.Count[8] + 1
				else:

					self.Count[size] = self.Count[size] + 1

				size = 0

		#print("Done!\n")







#---------------------FUNCTIONS----------------------

def SaveCount(localCount):

	MUTEX.acquire()

	try:

		for index in range(len(Count)):

			Count[index] = Count[index] + localCount[index]
	finally:

		MUTEX.release()




def PrintCount():

	total = 0

	for index in range(1,len(Count)):

		total = total + Count[index]

	print("Total word count: %d\n" %total)

	for index in range(1,len(Count)):

		freq = (float(Count[index])/float(total))*100

		print("Words of length %d found: %d : %.2f%%\n" %(index, Count[index ], freq) )








#-----------------------MAIN--------------------

#VARS
MUTEX = Lock()
THREADCOUNT = 2

threadList =[]

file = open("enwik9", "r")
lineCount = sum(1 for line in file)

Count = [0, 0, 0, 0, 0, 0, 0 , 0, 0]

#PRINTING
file.seek(0)

print("File Name: %s" %file.name )
print("Total line count: %d\n" %lineCount)

file.close()





#THREAT CREATION
for i in range(1, THREADCOUNT+1):

	startLine = (i*(lineCount/THREADCOUNT)) - (lineCount/THREADCOUNT) + 1
	endLine = (i*(lineCount/THREADCOUNT))

	print("creating thread for lines %d to %d" %(startLine, endLine))

	newThread = ParserThread( startLine, endLine )
	threadList.append(newThread)




startTime = time()





for t in threadList:

	t.start()

for t in threadList:

	t.join()





startTime = time() - startTime

#final stats
print("The operation took: %d's" %startTime)
PrintCount()