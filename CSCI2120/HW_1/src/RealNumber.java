/**Allows for the creation of a sub type complex number, real number.
 * @author devenRonquillo
 * @version 1/29/2017.
 */
public class RealNumber extends ComplexNumber {

    /**constructs a real number
     *
     * @param a, real number
     */
    public RealNumber(float a){

        super(a, 0.0f);
    }

    /**test to check if one real number is less than another
     *
     * @param other, Real number
     * @return boolean based on the given param
     */
    public boolean isLessThan(RealNumber other) {

        if (getA() < other.getA()) {

            return true;
        } else {

            return false;
        }
    }

        /**test to check if one real number is less than another
         *
         * @param other, real number
         * @return boolean based on the given param
         */

        public boolean isGreaterThan(RealNumber other){

            if(getA() > other.getA()){

                return true;
            }

            else{

                return false;
            }
        }
}
