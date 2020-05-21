/*

@author deven ronquillo
@timestamp oct 17 2016

creates dynamically resisable array

*/

import java.util.Arrays;

public class DynamicArray{

	private String[] array;
	private int numberOfElements;
	private int currentIndex;


	public DynamicArray(){

		this.array = new String[10];
		this.numberOfElements = 0;
		this.currentIndex = 0;


	}//end default constructor 

	public DynamicArray(int size){

		this.array = new String[size];
		this.numberOfElements = 0;
		this.currentIndex = 0;


	} //end user defined size constructor


	public DynamicArray(String[] array){

		this.array = Arrays.copyOf(array, array.length);
		this.numberOfElements = array.length;
		this.currentIndex = array.length;


	} //end transfer basic array to dynamic array constructor

	
	public void add(String passedString){

		if(this.array.length == this.currentIndex){

			this.expandArray();
		
		}//end if

		this.array[currentIndex] = passedString;
		this.numberOfElements++;
		this.currentIndex++;

	}//end add method


	private void expandArray(){

		int newLength = this.array.length * 3 / 2;
		String[] newArray = new  String[newLength];
		
		for(int i = 0; i < this.array.length; i++){

			newArray[i] = this.array[i];

		}//end for

		this.array = newArray;

	}//end expandArray method

	
	public String remove(int index){

		String temp = this.array[index];
		
		for(int i = index; i < this.numberOfElements-1; i++){

			this.array[i] = this.array[i + 1];
	
		}		

		this.currentIndex--;
		this.numberOfElements--;
		return temp;


	}//end remove method


	public String get(int index){


		return this.array[index];


	}// end get method

	
	public boolean isEmpty(){



		if(this.numberOfElements ==0){


			return true;
		}//end if


		else{

			return false;
		}//end else


	}//end is empty method


	public int sizeOf(){


		return this.numberOfElements;

	}//end sizeOf method

}// end class
