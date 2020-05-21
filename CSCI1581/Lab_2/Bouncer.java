/*

age checker.

@author Deven Ronquillo
@version sept 4 2016

*/

import java.util.*;

public class Bouncer{

	public static void main(String[] args){
		
		int age;//age input var
		int queue;//people in line
		
		Scanner keyB = new Scanner(System.in);//keyboard input

		System.out.print("How many people are in the queue? ");

		queue = keyB.nextInt();//store keyboard input
		
		while(queue > 0){

			System.out.print("\nwhat is your age: ");
		
			age = keyB.nextInt();//stores keyboard input
		
			if(age < 18){
		
				System.out.println("\nSorry kid, you're not old enough.");
			
			}//end if 
		
			else if(age >= 18 && age < 21){
		
				System.out.println("\nCome on it, but NO DRINKS!");
		
			}//end else if
		
		 	else{
		
				System.out.println("\nHelp yourself to booze!");
		
			}//end else
		
			System.out.println("\nNonetheless, have a good night!");

			queue = queue - 1;

		}// while queue > 0 end

		System.out.println("\nWell, I guess I'll go take a nap!");
		
	}//end main

}//end class Bouncer
