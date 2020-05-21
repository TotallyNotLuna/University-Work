/* 

@author Deven Ronqillo

Runner to test various classes

Note: was told to ignore circle class on day of this lab

*/
public class Runner{

	public static void main(String[] args){

		Point2D p1 = new Point2D(0,0);
		Point2D p2 = new Point2D(5,0);
		Point2D p3 = new Point2D(5,5);
		

		Triangle tri1 = new Triangle(p1,p2,p3);

		System.out.println("Area of tri: "+tri1.getArea());

		Shape[] arrayShape = new Shape[1];

		arrayShape[0] = tri1;

		for(int i = 0; i < arrayShape.length; i++){

			System.out.println(arrayShape[i].toString());

			System.out.println("Area of current object in array: "+arrayShape[i].getArea());


		}

		tri1.moveHorizontal(2.00);
		tri1.moveVerticle(2.00);

		System.out.println("Area of tri after move: "+tri1.getArea());


	}//end main method


}//end class
