/*

@author Deven Ronquillo
@timestamp oct 16 2016

a runner to test the fraction class

*/



public class Runner{

	public static void main(String[] args){
	
		Fraction one = new Fraction(2,4);//test fraction creation
		Fraction two = new Fraction(1,2);
	
		System.out.println("Fraction 1: " + one);//test toString()
		System.out.println("Fraction 2: " + two);
	
		System.out.println("The sum of fraction one and two is: " + one.add(two));//test add()
	
		System.out.println("The difference of fraction one and two is: " + one.subtract(two));//test subtract()
	
		System.out.println("The product of fraction one and two is: " + one.multiply(two));//test multiply()
	
		System.out.println("The quotient of fraction one and two is: " + one.divide(two));//test divide()
		
	}//end main method

}//end class runner