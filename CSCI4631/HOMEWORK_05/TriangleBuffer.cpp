/*
@desc Mesh Data Structure for storing triangles

@author Deven Ronquillo
@version 28/11/2018
*/

//-----INCLUDES
#include <angel.h>
#include <vector>
#include <tuple>

class TriangleBuffer {

	public:

	//-----STRUCTS
	struct _triangle {//struct for a triangle

		vec3* start;
		vec3* middle;
		vec3* end;
		vec3* normal;

		bool operator ==(const _triangle& rhs) {//comparator for triangles

			return std::tie(*this->start, *this->middle, *this->end) == std::tie(*rhs.start, *rhs.middle, *rhs.end);
		}
	};


	//-----GLOBS

	std::vector<_triangle> triangleList;//vector of triangles
	std::vector<_triangle>::iterator triangleIter;//iterator for triangles

	std::vector<vec3> vertexList;//vector of verticies
	std::vector<vec3>::iterator vertexIter;//vertex iterator

	//-----FUNCIONS
	void Add(vec3 start, vec3 middle, vec3 end);
	void Modify(vec3 oldVert, vec3 newVertex);
	void CalculateNormal(_triangle tri);
	std::vector<vec3> GetMesh();
	
};

/*
@param starting vertex of an triangle
@param middle vertex of a triangle
@param ending vertex of an triangle

@desc adds a triangle to the mesh

*/
void TriangleBuffer::Add(vec3 start, vec3 middle, vec3 end) {

	//-----CREATE OUR POTENTIAL EDGE
	_triangle tempTriangle;

	//-----CHECK FOR THE EXISTANCE OF OUR START VERT
	vertexIter = std::find(vertexList.begin(), vertexList.end(), start);

	if (vertexIter == vertexList.end()) {

		vertexList.push_back(start);
		tempTriangle.start = &(*vertexList.end());
	}
	else {

		tempTriangle.start = &(*vertexIter);
	}

	//-----CHECK FOR THE EXISTANCE OF OUR MIDDLE VERT
	vertexIter = std::find(vertexList.begin(), vertexList.end(), middle);

	if (vertexIter == vertexList.end()) {

		vertexList.push_back(start);
		tempTriangle.middle = &(*vertexList.end());
	}
	else {

		tempTriangle.middle = &(*vertexIter);
	}

	//-----CHECK FOR THE EXISTANCE OF OUR END VERT
	vertexIter = std::find(vertexList.begin(), vertexList.end(), end);

	if (vertexIter == vertexList.end()) {

		vertexList.push_back(end);
		tempTriangle.end = &(*vertexList.end());
	}
	else {

		tempTriangle.end = &(*vertexIter);
	}

	//-----CHECK FOR ANY TRIANGLES THAT ARE THE SAME AS OUR POTENTIAL TRIANGLE
	triangleIter = find(triangleList.begin(), triangleList.end(), tempTriangle);

	if (triangleIter == triangleList.end()) {

		CalculateNormal(tempTriangle);

		triangleList.push_back(tempTriangle);
	}
}

/*
@param vertex to search for
@param vertex to replace with

@desc allows for the modification of a vertex across all triangles

*/
void TriangleBuffer::Modify(vec3 oldVert, vec3 newVertex) {

	vertexIter = find(vertexList.begin(), vertexList.end(), oldVert);

	if (vertexIter != vertexList.end()) {

		*vertexIter = newVertex;

		triangleIter = triangleList.begin();

		//RUNS THROUGH ALL TRIANGLE TO RECALCULATE NORMALS FOR ANY THAT SHARE OUR NEW VERTEX
		while (triangleIter != triangleList.end()) {

			if (*((*triangleIter).start) == newVertex || *((*triangleIter).middle) == newVertex || *((*triangleIter).end) == newVertex) {

				CalculateNormal(*triangleIter);
			}
		}
	}
}

/*
@desc Caltulates the normal for a given triangle.

*/
void TriangleBuffer::CalculateNormal(_triangle tri) {

	vec3 u = tri.middle - tri.start;
	vec3 v = tri.end - tri.start;

	vec3 normal;

	normal.x = (u.y*v.z) - (u.z*v.y);
	normal.y = (u.z*v.x) - (u.x*v.z);
	normal.z = (u.x*v.y) - (u.y*v.x);

	*tri.normal = normal;
}

/*
@desc Compiles a vector of all vec3 quads.

@return vector<vec3>

*/
std::vector<vec3> TriangleBuffer::GetMesh() {

	std::vector<vec3> meshVerticies;

	while (triangleIter != triangleList.end()) {

		meshVerticies.push_back(*(*triangleIter).start);
		meshVerticies.push_back(*(*triangleIter).middle);
		meshVerticies.push_back(*(*triangleIter).end);
		meshVerticies.push_back(*(*triangleIter).normal);

		std::advance(triangleIter, 1);
	}

	return meshVerticies;
}

/*
@desc Empty main, required.

*/
int main() {};