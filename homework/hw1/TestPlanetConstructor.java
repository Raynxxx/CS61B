
/**
 *  Tests the Planet constructor.
 */
public class TestPlanetConstructor {

    /**
     *  Tests the Planet constructor to make sure it's working correctly.
     */
    public static void main(String[] args) {
        checkPlanetConstructor();
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
     *  Checks whether or not two Strings are equal and prints the result.
     *  @param  s1      String received
     *  @param  s2      Expected String
     *  @param  label   Label for the 'test' case
     */
    private static void checkStringEquals(String s1, String s2, String label) {

        if (s1.equals(s2)) {
            System.out.println("PASS: " + label + ": " + s1 + " equal to " + s2);
        } else {
            System.out.println("FAIL: " + label + ": " + s1 + " not equal to " + s2);
        }
    }

    /**
     *  Checks Planet constructor to make sure it's setting instance
     *  variables correctly.
     */
    private static void checkPlanetConstructor() {
        System.out.println("Checking Planet constructor...");

        double x = 1.0,
               y = 2.0,
               xVelocity = 3.0,
               yVelocity = 4.0,
               mass = 5.0;

        String img = "jupiter.gif";

        Planet p = new Planet(x, y, xVelocity, yVelocity, mass, img);

        checkEquals(x, p.x, "x");
        checkEquals(y, p.y, "y");
        checkEquals(xVelocity, p.xVelocity, "xVelocity");
        checkEquals(yVelocity, p.yVelocity, "yVelocity");
        checkEquals(mass, p.mass, "mass");
        checkStringEquals(img, p.img, "path to image");
    }
}