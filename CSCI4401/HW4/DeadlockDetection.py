#from collections import namedtuple
from collections import defaultdict 


class Graph: 
  
    # Constructor 
    def __init__(self): 
  
        # default dictionary to store graph 
        self.graph = defaultdict(list) 
  
    # function to add an edge to graph 
    def addEdge(self,u,v): 
        self.graph[u].append(v) 
  
    # A function used by DFS 
    def DFSUtil(self,v,visited): 
  
        # Mark the current node as visited and print it 
        visited[v]= True
        print v, 
  
        # Recur for all the vertices adjacent to this vertex 
        for i in self.graph[v]: 
            if visited[i] == False: 
                self.DFSUtil(i, visited) 
  
  
    # The function to do DFS traversal. It uses 
    # recursive DFSUtil() 
    def DFS(self,v): 
  
        # Mark all the vertices as not visited 
        visited = [False]*(len(self.graph)) 
  
        # Call the recursive helper function to print 
        # DFS traversal 
        self.DFSUtil(v,visited) 


#{def MakeNode(text):

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

	print(process + action + resource)

	if action == "W":

		arg = 1

		if len(nodeList) == 0:

			nodeList.append(Node(resource, process, "resource"))

		for node in nodeList:

			print(len(nodeList))

			if node.id == resource and node.type == "resource" and node.nextnode != process:

				print("Process %s wants resouce %s - Process %s must wait" %(process, resource, process))

				nodeList.append(Node(process, resource, "process"))
				break

			if arg == len(nodeList):

				print("Process %s wants resouce %s - Resource %s is allocated to process %s" %(process, resource, resource, process))

				nodeList.append(Node(resource, process, "resource"))
				break

			arg = arg + 1

		#DFS()

	if action == "R":

		for node in nodeList:

			if node.id == resource and node.type == "resource" and node.nextnode == process:

				print("Process %s released resource %s - Resource %s is now free" %(process, resource, resource))
				nodeList.remove(node)

		for node in nodeList:

			if node.nextnode == resource and node.type == "process":

				print("Process %s claimed resource %s - Process %s is no longer in queue" %(node.id, resource, node.id))

				nodeList.append(Node(resource, node.id, "resource"))
				nodeList.remove(node)




file = open("input1.txt", "r")
lineCount = sum(1 for line in file)

file.seek(0)

print("File Name: %s" %file.name )
print("Total Steps: %d\n" %lineCount)

for index in range(1,lineCount):

	MakeNode(file.readline())











