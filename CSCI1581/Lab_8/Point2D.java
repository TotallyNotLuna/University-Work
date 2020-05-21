/**
 * Models a two dimensional point for use with the SetOfPoints class. 
 *
 * Author: Franklin D. Worrell
 * Revised: 27 July 2016, 17:15
 * Assignment: Lab 8 - Inheritance
 * Class: Point2D
 */ 

public class Point2D {
	private double x; 	// the x coordinate of the point
	private double y; 	// the y coordinate of the point 
	
	
	/**
	 * Constructor initializes the x and y coordinates of this.
	 * WHY IS THERE NO DEFAULT, NO-ARGUMENT CONSTRUCTOR??? 
	 *
	 * @param	x	the x coordinate of the new Point2D
	 * @param	y	the y coordinate of the new Point2D
	 */ 
	public Point2D(double x, double y) {
		this.x = x; 
		this.y = y; 
	} // end Constructor
	
	
	/**
	 * Return the x-coordinate. 
	 */ 
	public double getX() {
		return this.x; 
	} // end method getX
	
	
	/**
	 * Return the y-coordinate. 
	 *
	 * return	this.y
	 */ 
	public double getY() {
		return this.y; 
	} // end method getY
	
	
	/**
	 * Returns the distance between this and another Point2D. 
	 *
	 * @return	the distance between the points
	 */ 


	public double distance(Point2D otherPoint) {
		return Math.sqrt((otherPoint.getX() - this.x) * (otherPoint.getX() - this.x) + 
		                 (otherPoint.getY() - this.y) * (otherPoint.getY() - this.y)); 
	} // end method distance

	public boolean equals(Point2D point){

		if(Math.abs(this.getX() - point.getX()) < 0.001 && Math.abs(this.getY() - point.getY()) < 0.001){

			return true;
		}
		else{

			return false;
		}
	}

	@Override
	public String toString(){

		String str = "(" + this.getX() + ", " + this.getY() + ")";
		return str;


	}
} // end class Point2D
