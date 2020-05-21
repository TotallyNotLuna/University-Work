/**@author Deven Ronquillo
 * @version 6/2/2017
 */
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RealNumberTest {

    private RealNumber testNumOne;
    private RealNumber testNumTwo;
    private RealNumber testNumThree;
    private RealNumber testNumFour;
    private RealNumber testNumFive;

    @Before
    public void setUp(){

        testNumOne = new RealNumber(5);
        testNumTwo = new RealNumber(2);
        testNumThree = new RealNumber(0);
        testNumFour = new RealNumber(-4);
        testNumFive = new RealNumber(12);
    }//end set up

    @Test
    public void lessThanTest(){

        assertFalse(testNumOne.isLessThan(testNumTwo));
        assertTrue(testNumTwo.isLessThan(testNumFive));
        assertFalse(testNumThree.isLessThan(testNumFour));

    }///end lessThanTest

    @Test
    public void greaterThanTest(){

        assertTrue(testNumFive.isGreaterThan(testNumTwo));
        assertFalse(testNumFour.isGreaterThan(testNumFive));
        assertFalse(testNumThree.isGreaterThan(testNumOne));

    }///end greaterThanTest


}
