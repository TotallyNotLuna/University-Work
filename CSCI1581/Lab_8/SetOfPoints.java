/**
 * Defines a collection of points and gives basic functionality to the 
 * collection including adding and deleting points, etc. 
 *
 * Author: Franklin D. Worrell
 * Revised: 10 July 2016 at 20:56
 * Assignment: Lab 8 - Inheritance
 * Class: SetOfPoints
 */ 
 
import java.util.LinkedList; 

public class SetOfPoints {
	private LinkedList<Point2D> points;	// LinkedList to hold set's points 
	
	
	/**
	 * Default constructor initializes an empty LinkedList of Point2D.
	 */ 
	public SetOfPoints() {
		this.points = new LinkedList<Point2D>(); 
	} // end default constructor
	
	
	/**
	 * Variable argument constructor will take a number of Point2Ds and 
	 * add each of them to the LinkedList of Point2D. 
	 *
	 * @param	initialPoints	array of Point2Ds to add to this
	 */ 
	public SetOfPoints(Point2D... initialPoints) {
		// Create the LinkedList with call to default constructor. 
		this(); 
		
		// Iterate through the Point2Ds and add them to the LinkedList. 
		for (int i = 0; i < initialPoints.length; i++) {
			this.points.add(initialPoints[i]); 
		} 
	} // end variable argument constructor 
	
	
	/**
	 * Returns the number of Point2Ds in this.
	 * 
	 * @return	the number of Point2Ds this contains
	 */ 
	public int size() {
		return this.points.size(); 
	} // end method size
	
	
	/**
	 * Adds a new point to this. 
	 *
	 * @param	newPoint	the Point2D to be added
	 */ 
	public void add(Point2D newPoint) {
		this.points.add(newPoint); 
	} // end method add
	
	
	/**
	 * Removes the specified point from this.
	 * 
	 * @param	removePoint	the Point2D to remove from this
	 */ 
	public void remove(Point2D removePoint) {
		this.points.remove(removePoint); 
	} // end method remove 
	
	
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
	
	
	/**
	 * Returns a String representation of this. 
	 *
	 * @return	String representing this
	 */ 
	@Override 
	public final String toString() {
		return this.points.toString(); 
	} // end method toString
} // end class SetOfPoints