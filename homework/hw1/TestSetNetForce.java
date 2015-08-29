import java.math.*;

/**
 *  Tests setNetForce
 */
public class TestSetNetForce {

    /**
     *  Tests setNetForce.
     */
    public static void main(String[] args) {
        checkSetNetForce();
    }

    /**
     *  Checks whether or not two Doubles are equal and prints the result.
     *
     *  @param  d1      Double received
     *  @param  d2      Expected double
     *  @param  label   Label for the 'test' case
     */
    private static void checkEquals(double d1, double d2, String label) {
        if (d1 == d2) {
            System.out.println("PASS: " + label + ": " + d1 + " equal to " + d2);
        } else {
            System.out.println("FAIL: " + label + ": " + d1 + " not equal to " + d2);
        }
    }

    /**
     *  Rounds a double value to a number of decimal places.
     *
     *  @param  value   Double to be rounded.
     *  @param  places  Integer number of places to round VALUE to.
     */
    private static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    /**
     *  Checks the Planet class to make sure setNetForce works.
     */
    private static void checkSetNetForce() {
        System.out.println("Checking setNetForce...");

        Planet p1 = new Planet(1.0, 1.0, 3.0, 4.0, 5.0, "jupiter.gif");
        Planet p2 = new Planet(2.0, 1.0, 3.0, 4.0, 4e11, "jupiter.gif");
        Planet p3 = new Planet(4.0, 5.0, 3.0, 4.0, 5.0, "jupiter.gif");
        Planet p4 = new Planet(3.0, 2.0, 3.0, 4.0, 5.0, "jupiter.gif");

        Planet[] planets = {p2, p3, p4};

        p1.setNetForce(planets);

        checkEquals(round(p1.xNetForce, 2), 133.4, "setNetForce()");
        checkEquals(round(p1.yNetForce, 2), 0.0, "setNetForce()");
    }
}