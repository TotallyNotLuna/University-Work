#@author Deven Ronquillo
#@version 18/3/19
#
#parses a file for frequency of word length.

#---------------------IMPORTS------------------------
from time import time, sleep
from threading import Thread, Lock
from random import uniform

import os

#---------------------FUNCTIONS----------------------

class PhilosopherThread(Thread):

	def __init__(self, leftFork, rightFork):

		Thread.__init__(self)

		self.leftFork = leftFork
		self.rightFork = rightFork

		self.hungryTime = 0.0
		self.hungryTimeStart = 0.0
		self.eatingTime = 0.0
		self.thinkingTime = 0.0

		self.hungry = True

	def run(self):

		self.hungryTimeStart = time()

		while (int(round(time() * 1000)) - gloabalTimer) < (60 * 1000):

			if self.hungry:

				self.Eating()
			else:

				self.Thinking()
		self.CalcAvg()


	def Thinking(self):

		
		SaveState(self.name, "Entering Thinking state")
		sleep(0.010)

		self.thinkingTime = self.thinkingTime + 0.010
		self.hungry = True
		self.hungryTimeStart = time()


	def Eating(self):

		SaveState(self.name, "Entering hungry state")

		while self.hungry:

			if forks[self.leftFork].acquire(False):


				SaveState(self.name, "Picked up fork " + str(self.leftFork))

				rightForkTries = 0
				
				while self.hungry:

					if forks[self.rightFork].acquire(False):

						self.hungryTime = self.hungryTime + (time() - self.hungryTimeStart)

						SaveState(self.name, "Picked up fork " + str(self.rightFork))

						eatTime = uniform(0.010, 0.040)
						SaveState(self.name, "Entering Eating State. Will eat for: " + str(eatTime*1000) + "ms")
						
						sleep(eatTime)

						self.eatingTime = self.eatingTime + eatTime
						self.hungry = False
						
						forks[self.leftFork].release()
						forks[self.rightFork].release()

						return 1
					else:

						rightForkTries = rightForkTries + 1

						SaveState(self.name, "Tried picking up fork " + str(self.rightFork) + " and failed")
				
						if rightForkTries >= 2:

							forks[self.leftFork].release()

							return 0
			else:

				
				SaveState(self.name, "Tried picking up fork " + str(self.leftFork) + " and failed")
				self.Sleep()
				


	def Sleep(self):

		sleep(uniform(0.050, 0.10))

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

def SaveState(name, state):

	writeMutex.acquire()

	try:
		currTime = int(round(time() * 1000)) - gloabalTimer
		file.write("%s: Time: %dms, entering state : %s\n" %(name, currTime, state))
	finally:

		writeMutex.release()

#-----------------------MAIN--------------------

#VARS

gloabalTimer = 0.0

if os.path.exists("q2output"):

	os.remove("q2output")

file = open("q2output", "a+")
writeMutex = Lock()

forks = [Lock(), Lock(), Lock(), Lock()]
phils = [PhilosopherThread(0, 1), PhilosopherThread(1, 2), PhilosopherThread(2, 3), PhilosopherThread(3, 0)]

gloabalTimer = int(round(time() * 1000))

for p in phils:

	p.start()

for p in phils:

	p.join()