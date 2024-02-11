
/**
 * Task 15 question 1 - The class PolyNode
 * This class represents a PolyNode object, a single element of polynom  
 *
 * @Author Netanel Dahan 313565871
 * @Version (2020a)
 */
public class PolyNode {
    // instance variables 
    private int _power ;
    private double _coefficient ;
    private PolyNode _next ;

    // default value
    private static final int DEFAULT_VALUE = 0 ;

    // constractors definitions
    /**
     * Constructor for objects of class PolyNode .
     * 
     * Time complexity- O(1)
     * The constractor performs a fixed number of set posts (3)
     * Space complexity - O(1)
     * The method uses a fixed number of variables (3)
     * 
     * @param power  the power of the new PolyNode 
     * @param coefficient the coefficient of the new PolyNode 
     * 
     */
    public PolyNode(int power,double coefficient){
        if (validPower(power)){ 
            _power = power;
            _coefficient = coefficient;
        }
        else{ // power < 0 - Invalid power to polyNode
            _power = DEFAULT_VALUE ;
            _coefficient = DEFAULT_VALUE;
        }
        _next = null ; // the last (or only) in the list
    }// end of the constractor

    /**
     * Constructor for objects of class PolyNode .
     * 
     * Time complexity- O(1)
     * The constractor performs a fixed number of set posts (3)
     * Space complexity - O(1)
     * The method uses a fixed number of variables (3)
     * 
     * @param power  the power of the new PolyNode 
     * @param coefficient the coefficient of the new PolyNode 
     * @param p the next PolyNode that follows it on the list
     * 
     */
    public PolyNode(int power,double coefficient,PolyNode p){
        if (validPower(power)) {
            _power = power;
            _coefficient = coefficient;
        }
        else{ // power < 0 - Invalid power to PolyNode
            _power = DEFAULT_VALUE ;
            _coefficient = DEFAULT_VALUE;
        }
        _next = p ; //aliasing
    }// end of the constractor

    /**
     * Copy constructor for PolyNode.
     * Construct a PolyNode with the same variables as another PolyNode.
     * 
     * Time complexity- O(1)
     * The constractor performs a fixed number of set posts (3)
     * Space complexity - O(1)
     * The method uses a fixed number of variables (3)
     * 
     * @param p The PolyNode object from which to construct the new PolyNode
     */
    public PolyNode(PolyNode p)
    {
        _power = p._power;
        _coefficient = p._coefficient;
        _next = p._next ; // aliasing

    }// end of the copy constractor

    // method definitions
    /**
     * Returns the power of the PolyNode.
     * 
     * Time complexity- O(1)
     * The constractor performs a fixed number of get posts (1)
     * Space complexity - O(1)
     * The method uses a fixed number of variables (1)
     * 
     * @return The  power of the PolyNode
     */
    public  int getPower() {
        return _power ;
    }

    /**
     * Returns the coefficient of the PolyNode.
     * 
     * Time complexity- O(1)
     * The constractor performs a fixed number of get posts (1)
     * Space complexity - O(1)
     * The method uses a fixed number of variables (1)
     * 
     * @return The coefficient of the PolyNode
     */
    public double getCoefficient(){
        return _coefficient ; 
    }

    /**
     * Returns the next PolyNode that follows this PolyNode on the list.
     * 
     * Time complexity- O(1)
     * The constractor performs a fixed number of get posts (1)
     * Space complexity - O(1)
     * The method uses a fixed number of variables (1)
     * 
     * @return the next PolyNode that follows the PolyNode on the list
     */
    public PolyNode getNext(){
        return _next ; // Return the address - aliasing
    }

    /**
     * Changes the power of the PolyNode. (only if not negative)
     * 
     * Time complexity- O(1)
     * The constractor performs a fixed number of set posts (0||1)
     * Space complexity - O(1)
     * The method uses a fixed number of variables (1)
     * 
     * @param newPower - the power value to be set
     */
    public void setPower (int newPower){
        if (validPower(newPower)) 
            _power =newPower ;
        //else - does not do anything
    }

    /**
     * Changes the Coefficient of the PolyNode.
     * 
     * Time complexity- O(1)
     * The constractor performs a fixed number of set posts (1)
     * Space complexity - O(1)
     * The method uses a fixed number of variables (1)
     * 
     * @param newCoefficient - coefficient value to be set
     */
    public void setCoefficient (double newCoefficient) {
        _coefficient= newCoefficient ;
    }

    /**
     * Changes the next PolyNode that follows this PolyNode on the list.
     * 
     * Time complexity- O(1)
     * The constractor performs a fixed number of set posts (1)
     * Space complexity - O(1)
     * The method uses a fixed number of variables (1)
     * 
     * @param p - next PolyNode to be set
     */
    public void setNext (PolyNode next) {
        _next= next ; // Changes the address - aliasing
    }

    /**
     * Returns a String that represents this PolyNode.
     * 
     * Time complexity- O(n)
     * The method performs a limited number of operations per possible single input
     * Space complexity - O(n)
     * The length of the string depends on the length of the PolyNode.
     * 
     * @return String that represents this food item in the following format:
     *   "ax^n" - for example "-5.8x^3"
     */
    public String toString() {
        String p=""; // default string

        //to string - coefficient
        if (_coefficient==0) // 0*x^n = 0 
            return p ; //  Empty string
        //else
        if (_coefficient==1){
            if (_power==0) // 1x^0 =1*1 =1
                return p+_coefficient ; // "1"
            //else - does not do anything, 1*x^n = x^n
        }
        else if (_coefficient==-1){
            if (_power==0) // -1x^0 = -1*1= -1
                return p+_coefficient ; // "-1"
            // else - (n>0)
            p+= "-"; // -1x^n = -x^n 
        }
        else // a != (1,-1,0)
            p+= _coefficient ; // "a"(x^n)

        // to string - power
        if (_power==0) // ax^0 = a*1= a 
            ; // does not do anything
        else if (_power==1) // x^1 = x
            p+= "x" ; // "(a)x"
        else // x^n n>1
            p+= "x^"+_power ; // "(a)x^n" 

        return p ; // The completed string of the PolyNode
    }// end of method toString

    //Checking if power is valid for PolyNode
    //  Time complexity- O(1)
    // The constractor performs a fixed number of steps (1)
    //  Space complexity - O(1)
    //  The method uses a fixed number of variables (1)
    private boolean validPower (int power) {
        if (power >=0)
            return true ;
        // else - Negative value
        return false ;
    }// end of method
}// end of class PolyNode