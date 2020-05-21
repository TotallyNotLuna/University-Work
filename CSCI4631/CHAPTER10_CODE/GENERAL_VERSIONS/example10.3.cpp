
#include "Angel.h"

const int NumVertices = 500;

	// Spline control points
	const vec2 p[4] = {
	    vec2( -1.00, -1.00 ),
	    vec2( -0.33,  1.00 ),
	    vec2(  0.33,  1.00 ),
	    vec2(  1.00, -1.00 )
	};

void
init()
{
    // Generate spline data
    vec2  points[NumVertices+4];
    
    for ( int i = 0; i < NumVertices; ++i ) {
	const float d = 1.0 / ( NumVertices - 1.0 );

        float u = i * d;
        float v = 1.0 - u;
	
	points[i] =
	    (u*u*u)*p[0] + 3*(u*u*v)*p[1] + 3*(u*v*v)*p[2] + (v*v*v)*p[3];
    }
    
    for(int i =0; i<4; i++) points[NumVertices+i] = p[i];

    // Create a vertex array object
    GLuint vao;
#ifdef __APPLE__
    glGenVertexArraysAPPLE( 1, &vao );
    glBindVertexArrayAPPLE( vao );
#else
    glGenVertexArrays( 1, &vao );
    glBindVertexArray( vao );
#endif

    // Create and initialize a buffer object
    GLuint buffer;
    glGenBuffers( 1, &buffer );
    glBindBuffer( GL_ARRAY_BUFFER, buffer );
    glBufferData( GL_ARRAY_BUFFER, sizeof(points), points, GL_STATIC_DRAW );

    // Load shaders and use the resulting shader program
    std::string evname = "ANGELDIR";
    std::string path = getEnvironmentVariable(evname);
    path += "/shaders/";
#ifdef __APPLE__
    path += "/MAC_VERSIONS/";
#else
    path += "/WINDOWS_VERSIONS/";
#endif
    std::string vshader = path + "vshader102.glsl";
    std::string fshader = path + "fshader102.glsl";
    GLuint program = InitShader( vshader.c_str(), fshader.c_str() );
    glUseProgram( program );

    // set up vertex arrays
    GLuint vPosition = glGetAttribLocation( program, "vPosition" );
    glEnableVertexAttribArray( vPosition );
    glVertexAttribPointer( vPosition, 2, GL_FLOAT, GL_FALSE, 0,
			   BUFFER_OFFSET(0) );

    glPointSize(5.0);

    glClearColor( 1.0, 1.0, 1.0, 1.0 );
}

//----------------------------------------------------------------------------

void
display( void )
{
    glClear( GL_COLOR_BUFFER_BIT );
    glDrawArrays( GL_LINE_STRIP, 0, NumVertices );
    glDrawArrays( GL_POINTS, NumVertices, 4 );
    glutSwapBuffers();
}

//----------------------------------------------------------------------------

void
keyboard( unsigned char key, int x, int y )
{
    switch( key ) {
	case 033:  // Escape key
	case 'q': case 'Q':
	    exit( EXIT_SUCCESS );
	    break;
    }
}

//----------------------------------------------------------------------------

int
main( int argc, char **argv )
{
    glutInit( &argc, argv );
    glutInitDisplayMode( GLUT_RGBA | GLUT_DOUBLE );
    glutInitWindowSize( 512, 512 );
#ifndef __APPLE__
    glutInitContextVersion(3, 2);
    glutInitContextProfile(GLUT_CORE_PROFILE);
#endif
    glutCreateWindow( "Simple GLSL example" );
#ifdef GLEW_EXPERIMENTAL
    glewExperimental = GL_TRUE;
#endif
#ifndef __APPLE__
    glewInit();
#endif

    init();

    glutDisplayFunc( display );
    glutKeyboardFunc( keyboard );
    
    glutMainLoop();
    return 0;
}
