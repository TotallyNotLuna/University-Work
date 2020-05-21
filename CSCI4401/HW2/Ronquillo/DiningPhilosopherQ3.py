#@author Deven Ronquillo
#@version 18/3/19
#
#parses a file for frequency of word length.

#---------------------IMPORTS------------------------
from time import time, sleep
from threading import Thread, Lock, Semaphore
from random import uniform

import os

#---------------------FUNCTIONS----------------------

class PhilosopherThread(Thread):

	def __init__(self, index):

		Thread.__init__(self)

		self.index = index

		self.hungryTime = 0.0
		self.hungryTimeStart = 0.0
		self.eatingTime = 0.0
		self.thinkingTime = 0.0

		self.hungry = True

	def run(self):

		while (int(round(time() * 1000)) - gloabalTimer) < (60 * 1000):

			self.Thinking()
			self.GetForks()
			self.Eating()
			self.SetForks()
		self.CalcAvg()


	def Thinking(self):

		
		SavephilState(self.name, "Entering Thinking state")
		sleep(0.010)

		self.thinkingTime = self.thinkingTime + 0.010


	def Eating(self):

		eatTime = uniform(0.010, 0.040)
		self.eatingTime = self.eatingTime + eatTime

		self.hungryTime = self.hungryTime + (time() - self.hungryTimeStart)

		SavephilState(self.name, "Entering Eating state. Will eat for " + str(eatTime*1000) + " ms")

		sleep(eatTime)





	def GetForks(self):

		philStateMutex.acquire()

		self.hungryTimeStart = time()

		SavephilState(self.name, "Entering hungry state")

		philState[self.index] = 1
		self.checkSides(self.index)

		philStateMutex.release()
		philSem[self.index].release()

	def SetForks(self):

		philStateMutex.acquire()

		SavephilState(self.name, "Put down forks")

		philState[self.index] = 0

		self.checkSides(self.GetLeftPhil(self.index))
		self.checkSides(self.GetRightPhil(self.index))

		philStateMutex.release()

	def checkSides(self, p):

		if philState[p] == 1 and philState[self.GetLeftPhil(p)] != 2 and philState[self.GetRightPhil(p)] != 2:

			philState[p] = 2

			SavephilState(self.name, "Picked up forks")

			philSem[p].acquire()

	def Sleep(self):

		sleep(uniform(0.050, 0.10))







	def GetLeftPhil(self, p):

		pMinus = p - 1

		if pMinus < 0:

			pMinus = 0

		return pMinus

	def GetRightPhil(self, p):

		pPlus = p + 1

		if pPlus > 3:

			pPlus = 0

		return pPlus

	def CalcAvg(self):

		self.hungryTime = self.hungryTime*1000
		self.thinkingTime = self.thinkingTime*1000
		self.eatingTime = self.eatingTime*1000

		hungryTimeP = (float(self.hungryTime)/float(60*1000))*100

		print("%s: Time spent in hungry state: %dms : %.2F%%\n" %(self.name, self.hungryTime, hungryTimeP))

		thinkingTimeP = (float(self.thinkingTime)/float(60*1000))*100

		print("%s: Time spent in thinking state: %dms : %.2F%%\n" %(self.name, self.thinkingTime, thinkingTimeP))

		eatingTimeP = (float(self.eatingTime)/float(60*1000))*100

		print("%s: Time spent in eating state: %dms : %.2F%%\n" %(self.name, self.eatingTime, eatingTimeP))








#---------------------FUNCTIONS----------------------

def SavephilState(name, philState):

	writeMutex.acquire()

	try:
		currTime = int(round(time() * 1000)) - gloabalTimer
		file.write("%s: Time: %dms, entering state : %s\n" %(name, currTime, philState))
	finally:

		writeMutex.release()

#-----------------------MAIN--------------------

#VARS

gloabalTimer = 0.0

if os.path.exists("q3output"):

	os.remove("q3output")

file = open("q3output", "a+")
writeMutex = Lock()



forks = [Lock(), Lock(), Lock(), Lock()]

philState = [0, 0, 0, 0]
philStateMutex = Lock()

philSem = [Semaphore(), Semaphore(), Semaphore(), Semaphore()]

phils = [PhilosopherThread(0), PhilosopherThread(1), PhilosopherThread(2), PhilosopherThread(3)]

gloabalTimer = int(round(time() * 1000))

for p in phils:

	p.start()

for p in phils:

	p.join()