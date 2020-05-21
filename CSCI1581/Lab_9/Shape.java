/**
 * Defines a collection of points and gives basic functionality to the 
 * collection including adding and deleting points, etc. 
 *
 * Author: Franklin D. Worrell
 * Revised: 18 July 2016 at 9:35
 * Assignment: Lab 9 - Polymorphism
 * Class: Shape
 */ 
 
import java.util.LinkedList; 

public abstract class Shape implements Movable{
	private LinkedList<Point2D> points;	// LinkedList to hold points.
	
	
	/**
	 * Default constructor initializes an empty LinkedList of Point2D.
	 */ 
	public Shape() {
		this.points = new LinkedList<Point2D>(); 
	} // end default constructor
	
	
	/**
	 * Variable argument constructor will take a number of Point2Ds and 
	 * add each of them to the LinkedList of Point2D. 
	 *
	 * @param	initialPoints	array of Point2Ds to add to this
	 */ 
	public Shape(Point2D... initialPoints) {
		// Create the LinkedList with call to default constructor. 
		this(); 
		
		// Iterate through the Point2Ds and add them to the LinkedList. 
		for (int i = 0; i < initialPoints.length; i++) {
			this.points.add(initialPoints[i]); 
		} 
	} // end variable argument constructor 
	
	
	/**
	 * Returns the Point2D contained at the specified index in the 
	 * LinkedList
	 *
	 * @param	index	the index of the Point2D to return a reference to
	 * @return	a reference to the Point2D at the specified index
	 */ 
	public Point2D get(int index) {
		return this.points.get(index); 
	} // end method get

	public abstract double getArea();
	// end method getArea

	public void moveHorizontal(double ammount){

		for(Point2D point: points){

			point.moveHorizontal(ammount);
		}
	}

	public void moveVerticle(double ammount){

		for(Point2D point: points){

			point.moveVerticle(ammount);
		}
	}
	
	
	/**
	 * Returns a String representation of this. 
	 *
	 * @return	String representing this
	 */ 
	@Override 
	public String toString() {
		return this.points.toString(); 
	} // end method toString
} // end class SetOfPoints
