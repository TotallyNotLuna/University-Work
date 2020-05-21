/*

@author Deven Ronquillo
@timestamp oct 16 2016

a basic fraction class for object introduction

*/





public class Fraction{

	private int numerator;
	private int denominator;

	public Fraction(int num, int den){
	
		this.numerator = num;
		this.denominator = den;
	
	}//end constructor
	
	public Fraction add(Fraction otherFrac){
	
		int denom = (this.denominator * otherFrac.denominator);
		int numer = (this.numerator * otherFrac.denominator) + (this.numerator * otherFrac.denominator);
		
		return new Fraction(numer, denom);
	
	}//end add method
	
	public Fraction subtract(Fraction otherFrac){
	
		int denom = (this.denominator * otherFrac.denominator);
		int numer = (this.numerator * otherFrac.denominator) - (this.numerator * otherFrac.denominator);
		
		return new Fraction(numer, denom);
	
	}//end subtract method
	
	public Fraction multiply(Fraction otherFrac){
	
		int denom = (this.denominator * otherFrac.denominator);
		int numer = (this.numerator * otherFrac.numerator);
		
		return new Fraction(numer, denom);
	
	}//end multiply method
	
	public Fraction divide(Fraction otherFrac){
	
		int denom = (this.denominator * otherFrac.numerator);
		int numer = (this.numerator * otherFrac.denominator);
		
		return new Fraction(numer, denom);
	
	}//end divide method
	
	
	
	public String toString(){
	
		return this.numerator + "/" + this.denominator;
	
	}//end toString Method

}//end class