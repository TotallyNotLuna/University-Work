/*
Lab 5 ExerciseOne

Author@ Deven Ronquillo
Version@ Sep 19 2016

*/

public class ExerciseOne{

	public static void main(String[] args){

		int[] tenInts = new int[10];

		String[] months = {"Aug.", "Sep.", "Nov.", "Oct.", "Dec.", "Feb.", 					   "Jan.", "Mar.", "Apr.", "May.", "Jun.", "Jul."};

		for(int x = 0; x < tenInts.length; x++){

			tenInts[x] = (int)(Math.random() * ((10 - 1) + 1)) + 1;

			System.out.print(tenInts[x] + " ");

		}//end for loop

		System.out.println("\n\nRandom month: " + months[(int)(Math.random() * ((11 - 0) + 1)) + 0]);

	}//end method main

}//end class ExerciseOne
