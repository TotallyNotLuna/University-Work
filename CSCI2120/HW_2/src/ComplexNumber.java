/**
 * A class to construct ComplexNumber data types.
 *
 * @author  deven ronquillo
 * @version 1/29/2017.
 */
public class ComplexNumber {

    /** The real part of a complex number.*/
    private float a;

    /** the imaginary part of a complex number.*/
    private float b;

    /** Constructs a complex number via the given parameters.
     *
     * @param a, Real Number
     * @param b, Imaginary number
     */
    public ComplexNumber(float a,float b){

        this.a = a;
        this.b =b;

    }

    /** Returns the sum of two complex numbers.
     *
     * @param other, ComplexNumber
     * @return the sum of two complex numbers
     */
    public ComplexNumber add(ComplexNumber other){

        float floatA = this.a + other.a;
        float floatB = this.b + other.b;

        return new ComplexNumber(floatA, floatB);
    }

    /** Returns the difference of two complex numbers.
     *
     * @param other, ComplexNumber
     * @return the difference of two complex numbers
     */
    public ComplexNumber subtract(ComplexNumber other){

        float floatA = this.a - other.a;
        float floatB = this.b - other.b;

        return new ComplexNumber(floatA, floatB);
    }

    /** Returns the product of two complex numbers.
     *
     * @param other, ComplexNumber
     * @return the product of two complex numbers
     */
    public ComplexNumber multiply(ComplexNumber other){

        float floatA = (this.a * other.a) - (this.b * other.b);
        float floatB = (this.b * other.a) + (this.a * other.b);

        return new ComplexNumber(floatA, floatB);
    }

    /** Returns the quotient of two complex numbers.
     *
     * @param other, ComplexNumber
     * @return the quotient of two complex numbers
     */
    public ComplexNumber divide(ComplexNumber other){

        if (((2*other.a) + (2 * other.b)) == 0){

            throw new ArithmeticException();
        }

        else {


            float floatA = ((this.a * other.a) + (this.b * other.b)) / ((2 * other.a) + (2 * other.b));
            float floatB = ((this.b * other.a) - (this.a * other.b)) / ((2 * other.a) + (2 * other.b));

            return new ComplexNumber(floatA, floatB);
        }
    }

    /** Returns reall number of a complex number
     *
     * @return real number of a complex number
     */
    public float getA(){

        return this.a;
    }

    /**returns imaginary number of a complex number
     *
     * @return imaginary number of a complex number
     */
    public float getB(){

        return this.b;
    }

    /**checks if two imaginary numbers are equal
     *
     * @param other, ComplexNumber
     * @return boolean true or false based on the param given
     */
    @Override
    public boolean equals(Object other){

        if(other instanceof ComplexNumber){

            ComplexNumber otherCasted = (ComplexNumber) other;

            if(this.a == otherCasted.a && this.b == otherCasted.b){

                return true;
            }

            else{

                return false;
            }
        }

        else{

            return false;
        }
    }

    /** prints to string the complex number in a user friendly maner
     *
     * @return String bearing useful information on the complex number
     */

    @Override
    public String toString(){

        return this.a + " + " + this.b + "i";
    }

}
