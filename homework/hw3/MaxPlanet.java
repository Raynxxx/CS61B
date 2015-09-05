import java.util.Comparator;
import java.util.Arrays;

/**
 * MaxPlanet.java
 */

public class MaxPlanet {

    /** Returns the Planet with the maximum value according to Comparator c. */
    public static Planet maxPlanet(Planet[] planets, Comparator<Planet> c) {
        // REPLACE THIS LINE WITH YOUR SOLUTION
        Arrays.sort(planets, c);
        return planets[planets.length-1];
    }
}
