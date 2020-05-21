/**
 * A runner to test the DynamicArray class. 
 *
 * @author	Franklin D. Worrell
 * @version 29 June 2016
 */ 
public class Runner {
	public static void main(String[] args) {
		// Create a DynamicArray. 
		String[] array = {"Me", "Myself", "and", "I"};
		DynamicArray testDS = new DynamicArray(array); 
		
		// Resize the array twice to make sure it works. 
		testDS.add("maybe"); 
		testDS.add("not"); 
		testDS.add("you"); 
		
		// Test the get method. 
		System.out.println("Size should be 7: " + testDS.sizeOf()); 
		System.out.println("Me should follow: " + testDS.get(0)); 
		System.out.println("you should follow: " + testDS.get(6)); 
		System.out.println("maybe should follow: " + testDS.get(4)); 
		System.out.println("I should follow: " + testDS.get(3)); 
		System.out.println(); 
		
		// Test the remove method. 
		System.out.println("Myself removed: " + testDS.remove(1)); 
		System.out.println("Me should follow: " + testDS.get(0)); 
		System.out.println("and should follow: " + testDS.get(1)); 
		System.out.println("you should follow: " + testDS.get(5)); 
		System.out.println("Size should be 6: " + testDS.sizeOf()); 
		System.out.println(); 
		System.out.println("not removed: " + testDS.remove(4)); 
		System.out.println("you should follow: " + testDS.get(4)); 
		System.out.println("Size should be 5: " + testDS.sizeOf()); 
		System.out.println();
		testDS.remove(0); 
		testDS.remove(3); 
		if (!testDS.isEmpty()) {
			System.out.println("isEmpty is working."); 
		} 
		else {
			System.out.println("isEmpty is failing."); 
		} 
		testDS.remove(2); 
		testDS.remove(0); 
		testDS.remove(0); 
		if (testDS.isEmpty()) {
			System.out.println("isEmpty is working."); 
		} 
		else {
			System.out.println("isEmpty is failing."); 
		} 
	} // end method main
} // end class Runner