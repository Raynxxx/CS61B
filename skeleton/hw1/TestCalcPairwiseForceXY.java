import java.math.*;

/**
 *  Tests calcPairwiseForce
 */
public class TestCalcPairwiseForceXY {

    /**
     *  Tests calcPairwiseForceXY.
     */
    public static void main(String[] args) {
        checkCalcPairwiseForceXY();
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
     *  Checks the Planet class to make sure calcPairwiseForceXY works.
     */
    private static void checkCalcPairwiseForceXY() {
        System.out.println("Checking calcPairwiseForceX and calcPairwiseForceY...");

        Planet p1 = new Planet(1.0, 1.0, 3.0, 4.0, 5.0, "jupiter.gif");
        Planet p2 = new Planet(2.0, 1.0, 3.0, 4.0, 4e11, "jupiter.gif");
        Planet p3 = new Planet(4.0, 5.0, 3.0, 4.0, 5.0, "jupiter.gif");

        checkEquals(p1.calcPairwiseForceX(p2), 133.4, "calcPairwiseForceX()", 0.01);
        checkEquals(p1.calcPairwiseForceX(p3), 4.002e-11, "calcPairwiseForceX()", 0.01);
        checkEquals(p1.calcPairwiseForceY(p2), 0.0, "calcPairwiseForceY()", 0.01);
        checkEquals(p1.calcPairwiseForceY(p3), 5.336e-11, "calcPairwiseForceY()", 0.01);
    }
}