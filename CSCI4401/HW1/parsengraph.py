#Question 2, 3, 4

#IMPORTS

from graphviz import Digraph
from collections import namedtuple

#GLOBAL STRUCTURES

node = namedtuple("node", ["pid", "ppid", "depth"])
parsedNodes = set()

#HELPER METHODS

def findDepth(pid):

	for currentNode in parsedNodes:

		if currentNode.pid == pid:

			return 1 + findDepth(currentNode.ppid)

	return -1

#MAIN

file = open("procdata.txt", "r+")

for line in file:#file parser

	readNode = line.split("|")#split node pids
	del readNode[-1]#delete empty element

	print(readNode)#print for sanity

	newNode = node(readNode[0], readNode[1], 0)#create node object

	parsedNodes.add(newNode)#add node to our set, will prevent

file.truncate(0)
file.close() 

print(parsedNodes)#print for sanity

g = Digraph('G', filename='pidgraph.gv')#instantiate graph

for currentNode in parsedNodes:#create graph nodes

	updatedNode = currentNode._replace(depth = findDepth(currentNode.pid))#generates node depth info

	if updatedNode.depth - 1 > -1:

		g.edge(updatedNode.ppid + "\nLevel %d"%(updatedNode.depth - 1), updatedNode.pid + "\nLevel %d"%(updatedNode.depth) )

g.view()#display