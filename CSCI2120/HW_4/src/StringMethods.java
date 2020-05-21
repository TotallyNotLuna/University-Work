/**
 * The main part of HW4. Implement both of these methods.
 */
public class StringMethods {

    /** No reason to instantiate this class, so the constructor is private. */
    private StringMethods(){ }

    /*
     * TODO: Fill in the areEqual and isPalindrome methods below with recursive implementations.
     *
     * These methods are recursive, so they either solve the problem right away (the base case),
     * or reduce the input to a simpler problem before calling themselves again (the recursive case).
     */

    ///////////////////////////////////////////////////////////
    // PART ONE
    ///////////////////////////////////////////////////////////

    /**
     * Returns whether two NCStrings are equal. The comparison respects case (so 'test' != 'Test').
     *
     * @return Whether two NCStrings are equal.
     */
    public static boolean areEqual(final NCString a, final NCString b){

        if(a.getLength() != b.getLength()){

            return false;
        }
        else if(a.getLength() > 0 && b.getLength() > 0 && a.getFirstChar() != b.getFirstChar()){

            return false;
        }
        else if(a.getLength() > 0 && b.getLength() > 0 && a.getLastChar() != b.getLastChar()){

            return false;
        }
        else{

            if(a.getLength() <= 2 && b.getLength() <=2){

                return true;
            }
            else{

                return areEqual(a.getMiddleChars(), b.getMiddleChars());//if all previous statement fail a shorter string is
                //passed on for reevaluation until a statement passes.
            }
        }
    }

    ///////////////////////////////////////////////////////////
    // PART TWO
    ///////////////////////////////////////////////////////////

    /**
     * Returns whether two NCStrings are palindromes. A String is a palindrome if it reads the
     * same forwards and backwards, respecting case and whitespace. E.g.:
     *
     * "racecar"
     * "level"
     * "a"
     * "aa"
     * "mom"
     * "radar radar"
     * ""           <-- the empty string.
     *
     * The following would not be considered palindromes:
     *
     * "example"
     * "top spot"   <-- because of the space
     * "Mom"        <-- because of the capitalization
     *
     * @param s The NCString to examine. Can be assumed to be non-null.
     * @return Whether the argument is a palindrome, as defined above.
     */
    public static boolean isPalindrome(final NCString s){

        if(s.getLength() > 0 && s.getFirstChar() != s.getLastChar()){

            return false;
        }
        else{

            if(s.getLength() <= 2){

                return true;
            }
            else{

                return isPalindrome(s.getMiddleChars());//if the prestatments fail it passes a shortened string in for
                //evaluation until a statement passes.
            }
        }
    }



}
