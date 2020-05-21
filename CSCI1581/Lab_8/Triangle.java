/**
 * Models a triangle as a set of three Point2Ds. Adds an appropriate
 * method for calculating the area of the triangle. 
 *
 * Author: Franklin D. Worrell
 * Revised: 27 July 2016, 17:20
 * Assignment: Lab 8 - Inheritance
 * Class: Triangle
 */ 

public class Triangle extends SetOfPoints{
	/**
	 * Default constructor initializes this with no vertices. 
	 */ 
	public Triangle() {
		/* Stubbed */ 
	} // end default constructor
	
	
	/**
	 * Constructor takes three Point2Ds--the vertices of the triangle. 
	 *
	 * @param	vertOne		the triangle's first vertex
	 * @param	vertTwo 	the triangle's second vertex
	 * @param	vertThree	the triangle's third vertex
	 */ 
	public Triangle(Point2D vertOne, Point2D vertTwo, Point2D vertThree) {
		/* Stubbed */ 
	} // end Constructor
	
	
	/**
	 * Returns the area of the space bound by the three Point2Ds.
	 * 
	 * @throws 	ArithmeticException
	 * @return	the area of this
	 */ 
	public double area() {
		// this does not contain three vertices, so area can't be found. 
		if (super.size() != 3) {
			throw new ArithmeticException("Inappropriate number of vertices.");
		} 
		
		// this contains three vertices, so calculate area. 
		else {
			double area = ((super.get(1).getX() - super.get(0).getX()) * 
						   (super.get(2).getY() - super.get(0).getY()) - 
						   (super.get(2).getX() - super.get(0).getX()) * 
						   (super.get(1).getY() - super.get(0).getY())) / 2.0; 
			return area; 
		} 
	} // end method area
} // end class Triangle
