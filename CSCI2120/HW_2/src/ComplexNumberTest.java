/**@author deven ronquillo
 * @version 6/2/2017
 */

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class ComplexNumberTest {

    private final float delta = 0.001f;

    private ComplexNumber testNumOne;
    private ComplexNumber testNumTwo;
    private ComplexNumber testNumThree;
    private ComplexNumber testNumFour;
    private ComplexNumber testNumFive;

    @Before
    public void setUp(){

        testNumOne = new ComplexNumber(5,10);
        testNumTwo = new ComplexNumber(2,1);
        testNumThree = new ComplexNumber(0,5);
        testNumFour = new ComplexNumber(-4,6);
        testNumFive = new ComplexNumber(12,-8);
    }//end set up

    @Test
    public void addTest(){

        assertEquals(7.0f, testNumOne.add(testNumTwo).getA(), delta);
        assertEquals(11.0f, testNumOne.add(testNumTwo).getB(), delta);

        assertEquals(12.0f, testNumThree.add(testNumFive).getA(), delta);
        assertEquals(-3.0f, testNumThree.add(testNumFive).getB(), delta);

        assertEquals(-2.0f, testNumFour.add(testNumTwo).getA(), delta);
        assertEquals(7.0f, testNumFour.add(testNumTwo).getB(), delta);

    }///end addTest

    @Test
    public void subtractTest(){

        assertEquals(3.0f, testNumOne.subtract(testNumTwo).getA(), delta);
        assertEquals(9.0f, testNumOne.subtract(testNumTwo).getB(), delta);

        assertEquals(-12.0f, testNumThree.subtract(testNumFive).getA(), delta);
        assertEquals(13.0f, testNumThree.subtract(testNumFive).getB(), delta);

        assertEquals(-6.0f, testNumFour.subtract(testNumTwo).getA(), delta);
        assertEquals(5.0f, testNumFour.subtract(testNumTwo).getB(), delta);

    }///end subtractTest

    @Test
    public void multiplyTest(){

        assertEquals(0.0f, testNumOne.multiply(testNumTwo).getA(), delta);
        assertEquals(25.0f, testNumOne.multiply(testNumTwo).getB(), delta);

        assertEquals(40.0f, testNumThree.multiply(testNumFive).getA(), delta);
        assertEquals(60.0f, testNumThree.multiply(testNumFive).getB(), delta);

        assertEquals(-14.0f, testNumFour.multiply(testNumTwo).getA(), delta);
        assertEquals(8.0f, testNumFour.multiply(testNumTwo).getB(), delta);

    }///end multiplyTest

    @Test
    public void divideTest(){

        assertEquals(3.333f, testNumOne.divide(testNumTwo).getA(), delta);
        assertEquals(2.5f, testNumOne.divide(testNumTwo).getB(), delta);

        assertEquals(-5.0f, testNumThree.divide(testNumFive).getA(), delta);
        assertEquals(7.5f, testNumThree.divide(testNumFive).getB(), delta);

        assertEquals(-0.333f, testNumFour.divide(testNumTwo).getA(), delta);
        assertEquals(2.666f, testNumFour.divide(testNumTwo).getB(), delta);

    }///end divideTest

    public void equalsTest(){

        assertFalse(testNumOne.equals(testNumTwo));
        assertTrue(testNumFour.equals(new ComplexNumber(-4, 6)));
    }///end divideTest


}
