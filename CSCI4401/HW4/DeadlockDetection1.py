#@author Deven Ronquillo
#@version 12.5.19
#
#A program to parse a hypothetical sytem resource request log to detirmine if a deadlock will ocour

#----------IMPORTS
import sys

#----------GLOBALS
nodeList = {}
queuedRequest = {}

deadlocked = False
deadlockProcesses = []
deadlockResources = []

#@desccription A function that formats text and removes white space
#@arguments text text to format
#@return
def ParseText(text):

	arg = 0

	process = ""
	action = ""
	resource = ""

	text = ' '.join(text.split())

	for char in text:

		if char != " ":

			if arg == 0:

				process += char

			if arg == 1:

				action += char

			if arg == 2:

				resource += char
		else:

			arg = arg + 1

	#print("Parsed: " + process + action + resource)
	CommitLine(process, action, resource)
#@description Takes formated text and determines what the issued command is and what to do with the provided information
#@arguments process the requesting process
#@arguments action the action to perform
#@arguments the resource to perform said action on
def CommitLine(process, action, resource):


	if action == "W":#performs aquisition action on resource

		key = resource+"r"

		if key in nodeList:

			if not nodeList[key]:

				nodeList[key].append(process+"p")
				print("Process %s wants resouce %s - Resource %s is allocated to process %s" %(process, resource, resource, process))

				key = process+"p"

				if key not in nodeList:

					nodeList[key] = []
			else:

				if key in queuedRequest:

					queuedRequest[key].append(process+"p")
				else:

					queuedRequest[key] = [process+"p"]

				key = process+"p"

				if key in nodeList:

					nodeList[key].append(resource+"r")
					print("Process %s wants resouce %s - Process %s must wait" %(process, resource, process))
				else:

					nodeList[key] = [resource+"r"]
					print("Process %s wants resouce %s - Process %s must wait" %(process, resource, process))
		else:

			nodeList[key] = [process+"p"]
			print("Process %s wants resouce %s - Resource %s is allocated to process %s" %(process, resource, resource, process))

			key = process+"p"

			if key not in nodeList:

				nodeList[key] = []



	if action == "R":#performs release on resource

		key = resource+"r"

		if key in nodeList:

			nodeList[key] = []
			print("Process %s released resource %s - Resource %s is now free" %(process, resource, resource))
			
			if key in queuedRequest:

				if not queuedRequest[key]:

					print("Resource %s currently has no queued process" %resource)

				else:

					process = queuedRequest[key].pop()
					nodeList[key].append(process)

					key = process
					for i in range(0, len(nodeList[key])):

						if nodeList[key][i] == resource+"r":

							nodeList[key].pop(i)
							break

					print("Process %s claimed resource %s - Process %s is no longer in queue" %(process, resource, process))

	#print("Current Graph----------")
	#for i in nodeList:
	#	print i, nodeList[i]

	#print("Current Queue----------")
	#for i in queuedRequest:
	#	print i, queuedRequest[i]

	#print("Runing DFS----------")
	#print(DFS(nodeList,resource+"r", []))
	DFS(nodeList,resource+"r", [])

#@description Depth first search algorithm designed to find a loop
#@arguments graph the graph to iterate through
#@arguments node starting node
#@arguments visited a list of found nodes
#@return a list of found nodes
def DFS(graph, node, visited):

	#print("Visiting node: " + node)
	#print(visited)

	if node not in visited:

		visited.append(node)

		try:

			for n in graph[node]:

				DFS(graph, n, visited)
		except:

			print("end of branch, returning.")
	else:

		#print("Deadlock Detected!")

		global deadlocked
		deadlocked = True

		for n in visited:

			builtString = ""

			for char in n:

				if char.isdigit():

					builtString += char
				if char == "p":

					global deadlockProcesses
					deadlockProcesses.append(builtString)
				if char == "r":

					global deadlockResources
					deadlockResources.append(builtString)
	return visited

#----------MAIN
if len(sys.argv) >= 2:


	file = open(sys.argv[1], "r")
	lineCount = sum(1 for line in file)

	file.seek(0)

	print("File Name: %s" %file.name )
	print("Total Steps: %d\n" %lineCount)

	for index in range(0,lineCount):#Execution starts here

		if deadlocked == False:

			ParseText(file.readline())
		else:

			processesString = ""
			for n in deadlockProcesses:

				processesString += n + ", "

			resourcesString = ""

			for n in deadlockResources:

				resourcesString += n + ", "

			print("Deadlock detected: processes " + processesString + "and resources " + resourcesString + "found in cycle.")
			break

		if index == lineCount-1:

			print("Execution completed: No deadlock detected.")
else:

	print("Not enough arguments provided.")