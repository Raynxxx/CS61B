/** 
 *  @author Josh Hug
 */

public class ExceptionDemo {
    public static void printPositiveNumber(int x) {
        if (x <= 0) {
            throw new RuntimeException("Number should be positive.");
        }
        System.out.println(x);
    }

    public static void main(String[] args) {
        printPositiveNumber(5);
        printPositiveNumber(-2);
        
        // Note: the line below will not execute since the code
        // crashes before it is reached.
        printPositiveNumber(1);
    }
}