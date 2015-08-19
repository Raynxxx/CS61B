
/**
 *  Tests getPlanet
 */
public class TestGetPlanet {

    /**
     *  Tests getPlanet.
     */
    public static void main(String[] args) {
        checkGetPlanet();
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
     *  Checks the Planet class to make sure getPlanet works.
     */
    private static void checkGetPlanet() {
        System.out.println("Checking getPlanet...");

        In in = new In("data/planets.txt");
        in.readInt();
        in.readDouble();

        Planet p = NBody.getPlanet(in);

        checkEquals(p.x, 1.496e11, "x");
        checkEquals(p.y, 0.0, "y");
        checkEquals(p.xVelocity, 0.0, "xVelocity");
        checkEquals(p.yVelocity, 29800.0, "yVelocity");
        checkEquals(p.xAccel, 0.0, "xAccel");
        checkEquals(p.yAccel, 0.0, "yAccel");
        checkStringEquals(p.img, "earth.gif", "image");
    }
}