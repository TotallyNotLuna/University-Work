import org.junit.*;
import org.junit.Test;

import static com.sun.xml.internal.ws.dump.LoggingDumpTube.Position.Before;
import static org.junit.Assert.*;

/**
 * A test class for StringMethods.
 */
public class StringMethodsTest {

    private NCString empty, a, aa, aba, abba, ab, aabb, A, Aa,  AA, ABA, AABB, ABBA, aba_aba, nathan, aab, baa;

    @Before
    public void setupFixtures(){
        empty = new NCString("");
        a = new NCString("a");
        aa = new NCString("aa");
        aba = new NCString("aba");
        aabb = new NCString("aabb");
        abba = new NCString("abba");
        Aa = new NCString("Aa");
        ab = new NCString("abba");
        ab = new NCString("aabb");
        A = new NCString("A");
        AA = new NCString("AA");
        ABA = new NCString("ABA");
        AABB = new NCString("AABB");
        ABBA = new NCString("ABBA");
        aba_aba = new NCString("aba aba");
        nathan = new NCString("nathan");
        aab = new NCString("aab");
        baa = new NCString("baa");
    }

    /**
     * Tests whether the areEquals() method works.
     */
    @Test
    public void testAreEquals(){
        // ARE EQUAL
        assertTrue(StringMethods.areEqual(empty,empty));
        assertTrue(StringMethods.areEqual(a,new NCString("a")));
        assertTrue(StringMethods.areEqual(aa,new NCString("aa")));
        assertTrue(StringMethods.areEqual(aba,new NCString("aba")));
        assertTrue(StringMethods.areEqual(aabb,new NCString("aabb")));
        assertTrue(StringMethods.areEqual(nathan,new NCString("nathan")));
        assertTrue(StringMethods.areEqual(aab,new NCString("aab")));
        assertTrue(StringMethods.areEqual(baa,new NCString("baa")));

        // ARE NOT EQUAL
        assertFalse(StringMethods.areEqual(empty,a));
        assertFalse(StringMethods.areEqual(empty,A));
        assertFalse(StringMethods.areEqual(a,A));
        assertFalse(StringMethods.areEqual(a,aa));
        assertFalse(StringMethods.areEqual(aa,Aa));
        assertFalse(StringMethods.areEqual(Aa,aa));
        assertFalse(StringMethods.areEqual(a,empty));
        assertFalse(StringMethods.areEqual(empty,a));
        assertFalse(StringMethods.areEqual(aabb,AABB));
        assertFalse(StringMethods.areEqual(aba,aa));
        assertFalse(StringMethods.areEqual(aab,baa));
        assertFalse(StringMethods.areEqual(baa,aab));
    }

    /**
     * Tests whether the isPalindrome() method works.
     */
    @Test
    public void testIsPalindrome(){
        // ARE PALINDROMES
        assertTrue(StringMethods.isPalindrome(empty));
        assertTrue(StringMethods.isPalindrome(a));
        assertTrue(StringMethods.isPalindrome(aa));
        assertTrue(StringMethods.isPalindrome(aba));
        assertTrue(StringMethods.isPalindrome(AA));
        assertTrue(StringMethods.isPalindrome(abba));
        assertTrue(StringMethods.isPalindrome(ABA));
        assertTrue(StringMethods.isPalindrome(ABBA));
        assertTrue(StringMethods.isPalindrome(aba_aba));

        // ARE NOT PALINDROMES
        assertFalse(StringMethods.isPalindrome(aabb));
        assertFalse(StringMethods.isPalindrome(ab));
        assertFalse(StringMethods.isPalindrome(nathan));
        assertFalse(StringMethods.isPalindrome(Aa));
        assertFalse(StringMethods.isPalindrome(aab));
        assertFalse(StringMethods.isPalindrome(baa));
    }

}
