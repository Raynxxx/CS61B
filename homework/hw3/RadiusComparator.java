import java.util.Comparator;

/**
 * RadiusComparator.java
 */

public class RadiusComparator implements Comparator<Planet> {

    public RadiusComparator() {
    }

    /** Returns the difference in radius as an int.
     *  Round after calculating the difference. */
    public int compare(Planet planet1, Planet planet2) {
        // REPLACE THIS LINE WITH YOUR SOLUTION
        return (int) (planet1.getRadius() - planet2.getRadius());
    }
}
