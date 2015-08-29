import java.math.*;

/**
 *  Tests update
 */
public class TestUpdate {

    /**
     *  Tests update.
     */
    public static void main(String[] args) {
        checkUpdate();
    }

    /**
     *  Checks whether or not two Doubles are equal and prints the result.
     *
     *  @param  d1      Double received
     *  @param  d2      Expected double
     *  @param  label   Label for the 'test' case
     *  @param  eps     Tolerance for the double comparison.
     */
    private static void checkEquals(double d1, double d2, String label, double eps) {
        if (Math.abs(d1 - d2) <= eps * Math.max(d1, d2)) {
            System.out.println("PASS: " + label + ": " + d1 + " equal to " + d2);
        } else {
            System.out.println("FAIL: " + label + ": " + d1 + " not equal to " + d2);
        }
    }


    /**
     *  Checks the Planet class to make sure update works.
     */
    private static void checkUpdate() {
        System.out.println("Checking update...");

        Planet p1 = new Planet(1.0, 1.0, 3.0, 4.0, 5.0, "jupiter.gif");

        p1.update(2.0);

        checkEquals(p1.x, 7.0, "update()", 0.01);
        checkEquals(p1.y, 9.0, "update()", 0.01);
        checkEquals(p1.xAccel, 0.0, "update()", 0.01);
        checkEquals(p1.yAccel, 0.0, "update()", 0.01);
        checkEquals(p1.xVelocity, 3.0, "update()", 0.01);
        checkEquals(p1.yVelocity, 4.0, "update()", 0.01);
    }
}