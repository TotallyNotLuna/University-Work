/*
@desc Mesh Data Structure for storing edges

@author Deven Ronquillo
@version 26/11/2018
*/



//-----INCLUDES
#include <angel.h>
#include <vector>
#include <tuple>

class MeshBuffer {

	public:

	//-----STRUCTS

	struct _edge {//struct for a edge

		vec3* start;
		vec3* end;

		bool operator ==(const _edge& rhs) {//comparator for edges

			return std::tie(*this->start, *this->end) == std::tie(*rhs.start, *rhs.end);
		};
	};
	
	//-----GLOBS

	std::vector<_edge> edgeList;//vector of edges
	std::vector<_edge>::iterator edgeIter;//iterator for edges

	std::vector<vec3> vertexList;//vector of verticies
	std::vector<vec3>::iterator vertexIter;//vertex iterator

	//-----FUNCTIONS
	void Add(vec3 start, vec3 end);
	void Modify(vec3 oldVert, vec3 newVertex);
	std::vector<vec3> GetMesh();
};



/*
@param starting vertex of an edge
@param ending vertex of an edge

@desc adds an edge to the mesh

*/

void MeshBuffer::Add(vec3 start, vec3 end) {

	//-----CREATE OUR POTENTIAL EDGE
	_edge tempEdge;

	//-----CHECK FOR THE EXISTANCE OF OUR START VERT
	vertexIter = std::find(vertexList.begin(), vertexList.end(), start);

	if (vertexIter == vertexList.end()) {

		vertexList.push_back(start);
		tempEdge.start = &(*vertexList.end());
	}
	else {

		tempEdge.start = &(*vertexIter);
	}

	//-----CHECK FOR THE EXISTANCE OF OUR END VERT
	vertexIter = std::find(vertexList.begin(), vertexList.end(), end);

	if (vertexIter == vertexList.end()) {

		vertexList.push_back(end);
		tempEdge.end = &(*vertexList.end());
	}
	else {

		tempEdge.end = &(*vertexIter);
	}

	//-----CHECK FOR ANY EDGES THAT ARE THE SAME AS OUR POTENTIAL EDGE
	edgeIter = find(edgeList.begin(), edgeList.end(), tempEdge);

	if (edgeIter == edgeList.end()) {

		edgeList.push_back(tempEdge);
	}
}

/*
@param vertex to search for
@param vertex to replace with

@desc allows for the modification of a vertex across all edges

*/

void MeshBuffer::Modify(vec3 oldVert, vec3 newVertex) {

	vertexIter = find(vertexList.begin(), vertexList.end(), oldVert);

	if (vertexIter != vertexList.end()) {

		*vertexIter = newVertex;
	}
}

/*
@desc Compiles a vector of all vec3 pairs.

@return vector<vec3>

*/

std::vector<vec3> MeshBuffer::GetMesh() {

	std::vector<vec3> meshVerticies;

	edgeIter = edgeList.begin();

	while (edgeIter != edgeList.end()) {

		meshVerticies.push_back(*(*edgeIter).start);
		meshVerticies.push_back(*(*edgeIter).end);

		std::advance(edgeIter, 1);
	}

	return meshVerticies;
}
/*
@desc Empty main, required.

*/
int main() {};