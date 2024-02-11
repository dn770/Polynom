
/**
 * Task 15 question 2 - The class Polynom
 * This class represents a Polynom object, a list of PolyNodes.
 *
 * @Author Netanel Dahan 313565871
 * @Version (2020a)
 */
public class Polynom
{
    // instance variables 
    private PolyNode _head; //Points to the first PolyNode in the list

    // constractors definitions
    /**
     * Null constructor for objects of class Polynom
     * Time complexity- O(1)
     * The constractor performs a fixed number of set posts (1)
     * Space complexity - O(1)
     * The method uses a fixed number of variables (1)
     */
    public Polynom() {
        _head = null; // empty list
    }

    /**
     * Constructor for objects of class Polynom that 
     * Time complexity- O(1)
     * The constractor performs a fixed number of set posts (1)
     * Space complexity - O(1)
     * The method uses a fixed number of variables (1)
     * 
     * @param p the PolyNode that to set to be the first in the Polynom 
     */
    public Polynom(PolyNode p) {
        _head = p; //set the address - aliasing
    }

    // method definitions
    /**
     * Returns the PolyNode at the head of the Polynom
     * 
     * Time complexity- O(1)
     * The constractor performs a fixed number of get posts (1)
     * Space complexity - O(1)
     * The method uses a fixed number of variables (1)
     * 
     * @return the PolyNode at the head of the Polynom
     */
    public PolyNode getHead() {
        return _head ; // aliasing
    }

    /**
     * set PolyNode to the head of the Polynom
     * 
     * Time complexity- O(1)
     * The constractor performs a fixed number of set posts (1)
     * Space complexity - O(1)
     * The method uses a fixed number of variables (1)
     * 
     * @param p - the PolyNode to be placed at the head
     */
    public void setHead( PolyNode p ) {
        // p.setNext(_head);// The first in the list becomes second in the list
        _head=p;   // aliasing- The new PolyNode is placed at the head of the polynom
    }

    /**
     * Add a new PolyNode to its appropriate place in the sorted Polynom
     * 
     * Time complexity- O(n)
     * Searching the location of the organ in the list-
     *  can cause a list-length loop operation
     * Space complexity - O(1)
     *The method defines at most one pointer and one new polynode.
     * (the  polynom is given as a parameter.)
     * 
     * (the set & get methods -  Time complexity- O(1) Space complexity - O(1) )
     * the isEmpty method - Time complexity- O(1) Space complexity - O(1) )
     * 
     * @param p - the PolyNode to be added to the polynom
     * @return the new polynom after the adds  
     */
    public Polynom addNode (PolyNode p) {
        if (p==null)
            return this ;
        if (isEmpty()) 
            _head = p; //Placing the PolyNode at the head of an empty list   
        else 
        if (p.getPower()>_head.getPower()) { 
            addToHead(p) ;  //   New maximal power found
        }   
        else if (p.getPower()==_head.getPower()) 
        //For identical powers- summerize the coefficients
            _head.setCoefficient(p.getCoefficient()+_head.getCoefficient());

        else { //p.getPower()<_head.getPower()

            //Find the right place in the polynom by power
            PolyNode current=_head;  
            while (current.getNext()!=null &&
            current.getNext().getPower()>p.getPower())   
                current=current.getNext();

            if( current.getNext()==null )   // the end of polynom
                current.setNext(p); //A new minimum power has been found
            else if (current.getNext().getPower()== p.getPower()){
                //For identical powers- summerize the coefficients
                current=current.getNext(); 
                current.setCoefficient(p.getCoefficient()+current.getCoefficient());
            }
            else {//Placing the new polynode between its greater and smaller holding
                p.setNext(current.getNext()); 
                current.setNext(p); 
            }
        }// end of outter "else" block
        return this ;// the new polynom after the adds
    }// end of method addNode

    /**
     * Calculate the multiplication of polynom by scalar.
     * 
     * Time complexity- O(n)
     * Loop goes through the entire list according to number of polyNodes (2n)
     * Space complexity - O(1)
     * The method does not define new variables,
     * the loop operates on a given polynom.
     * 
     * (the set & get methods -  Time complexity- O(1) Space complexity - O(1) )
     * 
     * @param num - The scalar it has is multiplied by a polynom
     * @return The new polynom after the multiplication operation
     */
    public Polynom multByScalar (int num) {
        for (PolyNode ptr=_head ;ptr!=null; ptr=ptr.getNext()){
            //Multiplying the scalar in each of the coefficients
            ptr.setCoefficient (ptr.getCoefficient() * num ) ;
        }
        return this ;//the new multiplicative polynom
    }

    /**
     * Calculate the sum of a given polynom and other polynom that obtained as a parameter.
     * 
     * Time complexity- O(n)
     * The method passes the given polynom twice at most,
     * and the parameter polynom once. 
     * Space complexity - O(n)
     * The method can add new polynodes up to the maximum number of organs in the parameter polynom
     * (in addition to three fixed pointers)
     * 
     * (the set & get methods -  Time complexity- O(1) Space complexity - O(1)
     * the isEmpty method - Time complexity- O(1) Space complexity - O(1) )
     * 
     * @param other - the polynom has to be adds to the given polynom
     * @return The new polynom after adding operation.
     */
    public Polynom addPol (Polynom other) {
        if (other.isEmpty()) 
            return this ; //No polynodes to be added
        PolyNode ptrBack = this._head ;
        PolyNode ptrFr = null ; // to ptrBack.getNext if valid (front)
        PolyNode ptrOthr=other._head ; //other is NOT Empty

        if (this.isEmpty()) {
            //Copy the values of the entire other polynom in order
            while  (ptrOthr != null ){
                PolyNode p = new PolyNode (ptrOthr.getPower(),ptrOthr.getCoefficient());
                this.addNode (p) ;

                ptrOthr=ptrOthr.getNext() ;
            }
            return this ; //Copy of the other polynom values
        }
        //else - At least one polynode in every polynom
        if ( ptrBack.getNext() == null ){ // only polynode in this polynom
            while (ptrBack.getNext() == null  && ptrOthr!= null ){
                //Comparing organs between the two polynoms and updating the polynom
                // until finding a second organ for the polynom
                if (ptrOthr.getPower()>ptrBack.getPower()) {// only for first check
                    //  New maximal power found for this polynom
                    PolyNode p = new PolyNode (ptrOthr.getPower(),ptrOthr.getCoefficient(), ptrBack) ;
                    this.addToHead(p) ;
                    // Update pointers
                    ptrBack= this.getHead() ; // back to the new head
                    ptrOthr= ptrOthr.getNext() ; // next polynode to check
                    ptrFr= ptrBack.getNext() ; // found second polynode to this polynom
                }
                else if (ptrOthr.getPower()<ptrBack.getPower()) {
                    // found second polynode to this polynom
                    PolyNode p = new PolyNode (ptrOthr.getPower(),ptrOthr.getCoefficient()) ;
                    ptrBack.setNext(p) ;
                    // Update pointers
                    ptrOthr= ptrOthr.getNext() ;// next polynode to check
                    // ptrBack stay in its place
                    ptrFr= ptrBack.getNext() ;// found second polynode to this polynom
                }
                else if (ptrOthr.getPower()==ptrBack.getPower()) {
                    //For identical powers- summerize the coefficients
                    ptrBack.setCoefficient(ptrBack.getCoefficient()+ptrOthr.getCoefficient()) ;
                    // Update pointers
                    ptrOthr=ptrOthr.getNext();// next polynode to check
                    // ptrBack stay in its place
                    //Meanwhile,no found second polynode to this polynom
                }
            }// end of loop block
        } // end of "if" block
        else // ptrBack.getNext() != null
            ptrFr = ptrBack.getNext() ; // != null

        while ( ptrOthr != null ) {
            //Adding all polynodes of other to their respective positions in this polynom
            // by comparison to the two polynom pointers.
            if (ptrOthr.getPower()>ptrBack.getPower()){ // only for first check
                //  New maximal power found for this polynom
                PolyNode p = new PolyNode (ptrOthr.getPower(),ptrOthr.getCoefficient(), ptrBack) ;
                addToHead(p) ;
                // Update pointers
                ptrBack= this.getHead() ; // back to the new head
                ptrFr= ptrBack.getNext() ;// step backward because the new head
                ptrOthr= ptrOthr.getNext() ;// next polynode to check
            }
            else if ( ptrOthr.getPower()<ptrFr.getPower()) { //&& << ptrBack.getPower
                if (ptrFr.getNext()==null){ // edge case
                    //  New minimal power found for this polynom
                    PolyNode p = new PolyNode (ptrOthr.getPower(),ptrOthr.getCoefficient()) ;
                    ptrFr.setNext(p) ;
                    // Update pointers
                    ptrBack=ptrFr ; // step forward
                    ptrFr= ptrFr.getNext(); // the new polynode
                    ptrOthr= ptrOthr.getNext() ;// next polynode to check
                }
                else { //ptrFr.getNext()!=null
                    // does not do anything
                    // Update pointers
                    ptrBack=ptrFr ; // now- ptrOthr.getPower < ptrBack.getPower
                    ptrFr= ptrFr.getNext();// step forward (!= null)
                    //ptrOthr stay in its place to another check  
                }
            }            
            else // ptrBack <= ptrOthr <= ptrFr
            if (ptrBack.getPower()>ptrOthr.getPower()&&ptrOthr.getPower()>ptrFr.getPower()){
                //adds between the two pointers
                PolyNode p = new PolyNode (ptrOthr.getPower(),ptrOthr.getCoefficient(), ptrFr);
                ptrBack.setNext (p) ;
                // Update pointers
                ptrBack= ptrBack.getNext();// the new polynode
                //ptrFr stay in its place becuase the new polynode backward him
                ptrOthr= ptrOthr.getNext();// next polynode to check
            }            
            else // ptrBack == ptrOthr || ptrOthr == ptrFr
            if (ptrBack.getPower()==ptrOthr.getPower()) {
                ptrBack.setCoefficient(ptrBack.getCoefficient()+ptrOthr.getCoefficient()) ;
                // Update pointers
                ptrOthr=ptrOthr.getNext();// next polynode to check
                // the two pointers stay in they places becuase that no adds any new polynode
            }
            else if (ptrFr.getPower()==ptrOthr.getPower()) 
                if (ptrFr.getNext()==null){//edge case - ptrback can't reach to last polynode in the polynom
                    //For identical powers- summerize the coefficients
                    ptrFr.setCoefficient(ptrFr.getCoefficient()+ptrOthr.getCoefficient()) ;
                    // Update pointers
                    ptrOthr=ptrOthr.getNext();// next polynode to check
                    // the two pointers stay in they places becuase that no adds any new polynode
                }
                else {//ptrFr.getNext()!=null
                    // does not do anything
                    // Update pointers
                    ptrBack=ptrFr ; // now- ptrOthr.getPower = ptrBack.getPower
                    ptrFr= ptrFr.getNext();// step forward (!= null)
                    //ptrOthr stay in its place to another check 
                }
        } // end of "while" loop
        return this ; //The new sum polynom
    }// end of method addNode

    /**
     * Multiplying a polynom into a polynom that is obtained as a parameter
     * 
     * @param other - the polynom has to be multiplied by the given polynom
     * @return The new polynom after the multiplication operation
     */
    public Polynom multPol (Polynom other) {
        if (other.isEmpty()||this.isEmpty()) 
            return this ; //No polynodes were found to multiply them

        //Copy the original polynom to a reference list,
        // to keep its values from adding the new organs after the multiplication
        Polynom copyPol = new Polynom () ;
        for (PolyNode ptr=this._head ;ptr!=null; ptr=ptr.getNext()){
            PolyNode node = new PolyNode (ptr.getPower(), ptr.getCoefficient());
            copyPol.addNode(node);
        }
        //Multiplying each polynode in the copied polynom by each polynode in the parameter polynom,
        // and adding the multiplier to the original polynom
        for (PolyNode ptrCp=copyPol.getHead() ;ptrCp!=null; ptrCp=ptrCp.getNext()){

            for (PolyNode ptrOthr=other.getHead() ;ptrOthr!=null; ptrOthr=ptrOthr.getNext()){
                double coefficient = 0 ;
                int power = 0 ;
                // ax^n * bx^k = (a*b)x^(n+k)
                coefficient= ptrCp.getCoefficient() * ptrOthr.getCoefficient() ;
                power= ptrCp.getPower() + ptrOthr.getPower() ;
                // adding the multiplier to the original polynom
                PolyNode node= new PolyNode(power,coefficient);
                this.addNode(node) ;

            }// end of inner loop
        }// end of outter loop
        //Missing the first  original (copied) polynom values
        // from the new multiplicative polynom because they are no longer needed
        copyPol.multByScalar(-1) ;
        this.addPol(copyPol) ;

        return this ; //the new multiplicative polynom
    }// end of method addNode

    /**
     * Performs Derivative on the polynom
     * 
     * Time complexity- O(n)
     * Loop goes through the entire list according to number of polyNodes (2n)
     * Space complexity - O(1)
     * The method does not define new variables,
     * the loop operates on a given polynom.
     * 
     * (the set & get methods -  Time complexity- O(1) Space complexity - O(1) 
     *
     *@return The new polynom after the derivation operation
     */
    public Polynom differential () {
        for (PolyNode ptr=_head ;ptr!=null; ptr=ptr.getNext()){ 
            // ax^b -> (a*b)x^(b-1) to every polynode in the polynom
            ptr.setCoefficient (ptr.getCoefficient() * ptr.getPower() ) ;
            ptr.setPower (ptr.getPower()-1) ;
        }
        return this ; //The new derivative polynom
    }

    /**
     * Returns a String that represents this Polynom.
     * 
     * Time complexity- O(n)
     * Loop goes through the entire list according to number of polyNodes
     *( method polynode.toString O(1) )
     * Space complexity - O(n)
     * The length of the string depends on the number of polynodes in the list
     * (The length of each polynode is negligible relative to the number of polynodes)
     * 
     * @return String that represents this polynom in the following format:
     *   "ax^n+bx^k-..." - for example "-5.8x^3+3x^2..."
     */
    public String toString() {
        String p=""; // default string
        if (isEmpty()) // null polynom
            return p; // null string

        for (PolyNode ptr=_head ; ptr!=null ;ptr= ptr.getNext() ){  
            if (ptr.getCoefficient() <=0)// negative PolyNode (-ax^n) || PolyNode== 0 (null)
                p+= ptr.toString() ;  
            else // positive polynode
            if (!p.equals("")) // not first PolyNode of the polynom
                p+= "+" + ptr.toString() ;
            else //edge case - first PolyNode that is no 0 - no need "+" before it
                p+= ptr.toString() ;
        }
        return p ; // the complete string of polynom
    }// end of method toString

    // Checking if polynom is empty list
    //  Time complexity- O(1)
    // The constractor performs a fixed number of steps (1)
    //  Space complexity - O(1)
    //  The method uses a fixed number of variables (1)
    private boolean isEmpty() {
        return _head == null; 
    }
    // add PolyNode to the head of the Polynom 
    // Time complexity- O(1)
    // The constractor performs a fixed number of set posts (1)
    // Space complexity - O(1)
    // The method uses a fixed number of variables (1)
    private void addToHead( PolyNode p ) {
        p.setNext(_head);// The first in the list becomes second in the list
        _head=p; // aliasing- The new PolyNode is placed at the head of the polynom
    }
} //end of class Polynom