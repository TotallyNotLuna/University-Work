/**
 * Models a line segment as two Point2Ds. Implements a length method.
 *
 * Author: Franklin D. Worrell
 * Revised: 27 July 2016, 17:22
 * Assignment: Lab 8 - Inheritance
 * Class: LineSegment
 */ 

public class LineSegment extends SetOfPoints { 
	/**
	 * Default constructor initializes without any endpoints.
	 */ 
	public LineSegment() {
		/* Stubbed */ 
		super();
	} // end default constructor


	/**
	 * Constructor takes two Point2Ds--the endpoints of this. 
	 *
	 * @param	begin	the first Point2D endpoint
	 * @param	end		the second Point2D endpoint
	 */ 
	public LineSegment(Point2D begin, Point2D end) {
		/* Stubbed */ 
		super(begin, end);
	} // end Constructor
	
	
	/**
	 * Returns the length of this line segment after calculating it. 
	 * 
	 * @throws	ArithmeticException
	 * @return	the length of this
	 */ 
	public double length() {
		// this does not contain appropriate number of points. 
		if (super.size() != 2) {
			throw new ArithmeticException("Line does not contain two endpoints."); 
		} 
		
		// this contains two endpoints, so calculate the distance.
		else {
			return super.get(0).distance(super.get(1)); 
		}  
	} // end method length
} // end method LineSegment
