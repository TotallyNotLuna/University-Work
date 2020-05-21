/*
@desc Koch Snowflake Fractal visualizer

@author Deven Ronquillo
@version 9/26/2018

takes an optional command line argument to set the depth of the fractal to be created
*/



//----------------Includes:
#define _USE_MATH_DEFINES//defines math constants

#include <GL/glut.h>
#include <cmath>
#include <iostream>
#include <vector>

using namespace std;









//--------------------Global vars and struct stuff:
GLint depth = 0;
GLfloat length = 1;

GLint methodControl = 0;

typedef struct _point {//struct for a basic 2d point

	GLfloat x;
	GLfloat y;
};

ostream& operator<<(ostream& os, const _point& point) {//overide for std out of our _point struct

	return os << "X: " << point.x << " Y: " << point.y << "\n";
}

_point startingPoint { (- length/2) , (length/2) };//initial point, 1/2 initial length to center it



const int ESCKEY = 27;
const int SPACEBAR = 32;





//---------------------Fractal methods:

/*
@param dir direction in degrees
@param len length of initial fractal
@param iter depth of our fractal

@desc generates one side of our fractal given a direction

*/
void DrawTriangleRecursive(GLfloat dir, GLfloat len, GLint iter) {

	GLdouble rads = dir*(M_PI/180); //convert deg to rads 

	GLfloat newX = startingPoint.x + len * cos(rads);
	GLfloat newY = startingPoint.y + len * sin(rads);

	if (iter==0) {//base case

		glVertex2f(startingPoint.x, startingPoint.y);
		glVertex2f(newX, newY);

		startingPoint.x = newX;
		startingPoint.y = newY;
	}
	else {//recursive

		iter--;// - depth

		//draw four new parts
		DrawTriangleRecursive(dir, len/3, iter);
		dir += 60.0;
		DrawTriangleRecursive(dir, len/3, iter);
		dir -= 120.0;
		DrawTriangleRecursive(dir, len/3, iter);
		dir += 60.0;
		DrawTriangleRecursive(dir, len/3, iter);
	}
}


/*
@param len length of initial fractal
@param iter depth of our fractal

@desc generates a koch snowflake bassed off of the given info

*/
void DrawTriangleIterative(GLfloat len, GLint iter) {

	std::vector<_point> points;//vector of points

	std::cout << "------------Generating main triangle:\n";//generating initial triangle

	_point tempPoint;//holder point

	tempPoint.x = startingPoint.x + len * cos(0 * (M_PI / 180));
	tempPoint.y = startingPoint.y + len * sin(0 * (M_PI / 180));

	startingPoint.x = tempPoint.x;
	startingPoint.y = tempPoint.y;

	points.insert(points.begin(), tempPoint);

	std::cout << "Main triangle point at: " << tempPoint.x << " , " << tempPoint.y << "\n";

	tempPoint.x = startingPoint.x + len * cos(-120*(M_PI/180));
	tempPoint.y = startingPoint.y + len * sin(-120*(M_PI/180));

	startingPoint.x = tempPoint.x;
	startingPoint.y = tempPoint.y;
	
	points.insert(points.begin() + 1, tempPoint);

	std::cout << "Main triangle point at: " << tempPoint.x << " , " << tempPoint.y << "\n";

	tempPoint.x = startingPoint.x + len * cos(120 * (M_PI / 180));
	tempPoint.y = startingPoint.y + len * sin(120 * (M_PI / 180));

	startingPoint.x = tempPoint.x;
	startingPoint.y = tempPoint.y;

	points.insert(points.begin() + 2, tempPoint);

	std::cout << "Main triangle point at: " << tempPoint.x << " , " << tempPoint.y << "\n\n";
	points.insert(points.begin() + 3, points[0]);//adds first point to end of array to wrap triangle

	std::cout << "------------Generating sub triangles:\n\n";
	while (iter > 0) {

		GLint i = 0;
		GLint vectorDepthSize = 4 * (points.size() - 1);
		len = len / 3;

		while (i < vectorDepthSize) {

			std::cout << "generating new points. Vector size:" << points.size() << "\n";
			std::cout << "our current index is:" << i << "\n";
			std::cout << "working from point: " << points[i].x << " , " << points[i].y << "to point: " << points[i+1].x << " , " << points[i+1].y << "\n";

			GLfloat tempDeg = (atan2((points[i].y - points[i + 1].y), (points[i].x - points[i + 1].x)) * (180/M_PI)) - 180;
			std::cout << "degrees between points: " << tempDeg << "\n\n";

			_point tempPointA;//temporary holders so we can reorder our points at the end
			_point tempPointB;
			_point tempPointC;

			startingPoint = points[i];//sets starting point to current working segment

			tempPoint.x = startingPoint.x + len * cos(tempDeg * (M_PI / 180));
			tempPoint.y = startingPoint.y + len * sin(tempDeg * (M_PI / 180));

			startingPoint.x = tempPoint.x;
			startingPoint.y = tempPoint.y;

			tempPointA = tempPoint;

			tempDeg += 60;

			tempPoint.x = startingPoint.x + len * cos(tempDeg * (M_PI / 180));
			tempPoint.y = startingPoint.y + len * sin(tempDeg * (M_PI / 180));

			startingPoint.x = tempPoint.x;
			startingPoint.y = tempPoint.y;

			tempPointB = tempPoint;

			tempDeg -= 120;

			tempPoint.x = startingPoint.x + len * cos(tempDeg * (M_PI / 180));
			tempPoint.y = startingPoint.y + len * sin(tempDeg * (M_PI / 180));

			startingPoint.x = tempPoint.x;
			startingPoint.y = tempPoint.y;

			tempPointC = tempPoint;

			points.insert(points.begin() + i + 1, tempPointC);
			points.insert(points.begin() + i + 1, tempPointB);
			points.insert(points.begin() + i + 1, tempPointA);

			i = i + 4;	
		}

		iter -= 1;
	}

	std::cout << "----------------Built Vector: \n\n";
	for (int i = 0; i < points.size(); i++) {

		std::cout << points[i];
	}

	std::cout << "----------------Drawing points: \n\n";
	for (int i = 0; i < points.size() - 1; i++) {

		std::cout << "drawing from point: " << points[i].x << " , " << points[i].y << "to point: " << points[i + 1].x << " , " << points[i + 1].y << "\n";

		glVertex2f(points[i].x, points[i].y);
		glVertex2f(points[i + 1].x, points[i + 1].y);

	}
}








//-------------------GL specific functions:
void display(){

	

	 glClear( GL_COLOR_BUFFER_BIT );
	 glBegin(GL_LINES);

	 if (methodControl == 0) {

		 glColor3f(1.0, 0.5, 0.0);

		 DrawTriangleIterative(length, depth);

	 }
	 else {

		 glColor3f(1.0, 1.0, 0.0);

		 DrawTriangleRecursive(0.0,length,depth);
		DrawTriangleRecursive(-120.0, length, depth);
		DrawTriangleRecursive(120.0,length,depth);

	 }
	  
	 glEnd();
	 glFlush(); 
}

void keyboard(unsigned char key, int x, int y) {

	switch (key) {

	case ESCKEY:

		exit(0);
		break;

	case SPACEBAR:

		if (methodControl == 0) {

			methodControl = 1;
		}
		else {

			methodControl = 0;
		}

		startingPoint.x = -(length / 2);
		startingPoint.y = (length / 2);

		glutPostRedisplay();

		break;
	}
}

void init() {

	glClearColor(0.2, 0.2, 0.2, 0.0);
}

int main(int argc, char** argv){

	if (argc == 2) {//checks for command arg

		depth = (int)(*argv[1] - '0');
	}

	glutInit(&argc, argv); 
	glutInitDisplayMode(GLUT_SINGLE | GLUT_RGB);   


	glutInitWindowSize(800, 800);    	
	glutInitWindowPosition(0, 0); 
	glutCreateWindow("HW3 - Koch Snowflake Fractal Visualizer");  

	init();
	glutDisplayFunc(display);
	glutKeyboardFunc(keyboard);
	glutMainLoop();
}