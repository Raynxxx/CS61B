/* Homework 7, Problem 1. 
* Questions are in comments at the bottom of the file @ line 165.
* (Ctrl+G in Sublime will quickly take you to a specified line number)
* (Ctrl+L in Eclipse will also do the same)
*/
public class Nana {

    public static void main(String[] args) {
        System.out.println("Oh nana, what's myNum?");
        // The below is potentially useless test code.
                
        /*
          Nana weeyana = new Nana(83);
          System.out.println("A: " + weeyana.hashCodeA());
          System.out.println("B: " + weeyana.hashCodeB());
          System.out.println("C: " + weeyana.hashCodeC());
          System.out.println("D: " + weeyana.hashCodeD());
          System.out.println("E: " + weeyana.hashCodeE());
        */
    }

    // All instance variables
    private int myNum;

    // Simple constructor
    public Nana(int n) {
        this.myNum = n;
    }

    /** This @Override tells the compiler, "Hey compiler, make sure this
     * function overrides something!" If the function doesn't override
     * something, then the compiler will notify you right then and there!
     */
    @Override 
        public boolean equals(Object o) {
        // First check to make sure the other object is a Nana. Otherwise
        // return false.
        if (o != null && o instanceof Nana) {
            Nana other = (Nana) o;
            return this.myNum == other.myNum;
            // In other words, two Nana's are equal iff their myNums
            // are the same value.
        }
        return false;
    }

    public int hashCodeA() {
        /* Math.random() returns a pseudo-random double between 0.0 and 1.0 */
        return (int) (Math.random() * 100);
    }

    public int hashCodeB() {
        return myNum;
    }

    public int hashCodeC() {
        return myNum * myNum * 101 + 17;
    }

    public int hashCodeD() {
        return 17;
    }

    /* final, so once created it can't be changed. The problem was changed
     * to have the keyword static removed. The AG is always backwards
     * compatible, if applicable. */
    public final double MY_CONST = Math.random();

    public int hashCodeE() {
        return (int) (MY_CONST * 100);
    }

    public int hashCodeF() {
        return Math.abs(myNum);
    }

    /** This marks the end of the actual implementation of Nana. **/

    /**************************************************************/
    /****************** Questions 1.1.A to 1.1.F ******************/

    // Is hashCodeA() a perfect hash for Nana? Return "yes" or "no"
    public static final String isPerfectHashA() {
        return "maybe"; // REPLACE THIS LINE WITH YOUR ANSWER 
    }

    // Is hashCodeB() a perfect hash for Nana? Return "yes" or "no"
    public static final String isPerfectHashB() {
        return "maybe"; // REPLACE THIS LINE WITH YOUR ANSWER 
    }

    // Is hashCodeC() a perfect hash for Nana? Return "yes" or "no"
    public static final String isPerfectHashC() {
        return "maybe"; // REPLACE THIS LINE WITH YOUR ANSWER 
    }

    // Is hashCodeD() a perfect hash for Nana? Return "yes" or "no"
    public static final String isPerfectHashD() {
        return "maybe"; // REPLACE THIS LINE WITH YOUR ANSWER 
    }

    // Is hashCodeE() a perfect hash for Nana? Return "yes" or "no"
    public static final String isPerfectHashE() {
        return "maybe"; // REPLACE THIS LINE WITH YOUR ANSWER 
    }

    // Is hashCodeF() a perfect hash for Nana? Return "yes" or "no"
    public static final String isPerfectHashF() {
        return "maybe"; // REPLACE THIS LINE WITH YOUR ANSWER 
    }

    /**************************************************************/
    /****************** Questions 1.2.A to 1.2.F ******************/

    // Is hashCodeA() a valid hash for Nana? Return "yes" or "no"
    public static final String isValidHashA() {
        return "maybe"; // REPLACE THIS LINE WITH YOUR ANSWER 
    }

    // Is hashCodeB() a valid hash for Nana? Return "yes" or "no"
    public static final String isValidHashB() {
        return "maybe"; // REPLACE THIS LINE WITH YOUR ANSWER 
    }

    // Is hashCodeC() a valid hash for Nana? Return "yes" or "no"
    public static final String isValidHashC() {
        return "maybe"; // REPLACE THIS LINE WITH YOUR ANSWER 
    }

    // Is hashCodeD() a valid hash for Nana? Return "yes" or "no"
    public static final String isValidHashD() {
        return "maybe"; // REPLACE THIS LINE WITH YOUR ANSWER 
    }

    // Is hashCodeE() a valid hash for Nana? Return "yes" or "no"
    public static final String isValidHashE() {
        return "maybe"; // REPLACE THIS LINE WITH YOUR ANSWER 
    }

    // Is hashCodeF() a valid hash for Nana? Return "yes" or "no"
    public static final String isValidHashF() {
        return "maybe"; // REPLACE THIS LINE WITH YOUR ANSWER 
    }

    /**************************************************************/
    /************************ Question #1.3 ***********************/

    public static final String worst2Best() {
        return "A, B, C, D"; // REPLACE THIS LINE WITH YOUR ANSWER 
    }

    /**************************************************************/
    /************************ Question #1.4 ************************/

    public static final String thereWasCollision() {
        return "maybe"; // REPLACE THIS LINE WITH YOUR ANSWER 
    }

    public static final String canGetDrake() {
        return "maybe"; // REPLACE THIS LINE WITH YOUR ANSWER 
    }

} // End Nana.java

/*********************************************************************

HOMEWORK QUESTIONS:

#1.1.A
Is hashCodeA() a perfect hash for Nana? Give your answer "yes" or "no" 
as a hard-coded return value in isPerfectHashA().

#1.1.B - 1.1.F)
Same question as 1.1.A, but for hashCodeB, hashCodeC, ..., hashCodeF

#1.2.A) 
Could hashCodeA() serve as a valid hash function? A valid hash has the
following property: if two Nana's are .equals, their hash codes are always the
same.

#1.2.B - 1.2.F)
Same question as 1.2.A, but for hashCodeB, hashCodeC, ..., hashCodeF

#1.3)
List hashCode functions A-D (don't consider E or F) in order from worst
to best. Consider invalid hashCode functions to be worse than all valid
functions. Example answer: "A, B, C, D". Write your answer in the 
given method worst2best()
(Hint: Remember that poor hash codes have lots of collisions)

#1.4.i - 1.4.ii)
Suppose hashCodeF were to be used as the hashCode of Nana. Consider the
following code:

...
HashMap<Nana, String> myHash = new HashMap<Nana, String>(17);
myHash.put(new Nana(15), "Drake");
myHash.put(new Nana(-15), "Rihanna"); // second put
System.out.println(myHash.get(new Nana(-15))); // Prints "Rihanna"
...

1.4.i)  
Was there a collision in the second put? ("yes" or "no" or 
"compile error"). Write your answer in thereWasCollision().

1.4.ii) 
Is it possible to get the mapping to Drake after adding the mapping to
Rihanna? ("yes" or "no" or "compile error"). Write your answer in
canGetDrake().

*********************************************************************/
