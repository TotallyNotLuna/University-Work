set (homework_5
	MeshBuffer
	TriangleBuffer
	#insert cpp file name here
	)

set (GRAPHICS_PROGRAMS
	${homework_5} )

if (GRAPHICS_COMPLETE)
  foreach(program ${GRAPHICS_PROGRAMS})
    add_executable(${program} ${program}.cpp)
    target_link_libraries(${program} ${ALL_LIBS} )
  endforeach(program)
endif ()
