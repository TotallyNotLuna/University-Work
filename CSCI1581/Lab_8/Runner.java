public class Runner{

	public static void main(String[]args){

		Point2D firstPoint = new Point2D(1,4);
		Point2D secondPoint = new Point2D(1,4);
		
		System.out.println(firstPoint.equals(secondPoint));
		System.out.println(firstPoint);
		System.out.println(secondPoint);

		System.out.println(((Object) firstPoint).toString());
	}//end main method
}//end class
