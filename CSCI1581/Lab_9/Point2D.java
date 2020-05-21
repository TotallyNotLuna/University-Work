/**
 * Models a two dimensional point for use with the Shape abstract class. 
 *
 * Author: Franklin D. Worrell
 * Revised: 27 July 2016 at 11:07
 * Assignment: Lab 9 - Polymorphism
 * Class: Point2D
 */ 

public class Point2D implements Movable{
	private double x; 	// the x coordinate of the point
	private double y; 	// the y coordinate of the point 
	
	
	/**
	 * Constructor initializes the x and y coordinates of this.
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

	public void moveVerticle(double ammount){

		this.y = this.y + ammount;
	}
	
	public void moveHorizontal(double ammount){

		this.x = this.x + ammount;
	}
	
	/**
	 * Returns a String representation of this.
	 *
	 * @return	String representation of this
	 */ 
	@Override
	public String toString() {
		return "(" + this.x + ", " + this.y + ")";  
	} // end method toString
} // end class Point2D