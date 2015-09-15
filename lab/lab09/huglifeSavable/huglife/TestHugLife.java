package huglife;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/** Performs basic tests of huglife package.
 *  @author Josh Hug
 */

public class TestHugLife {
    /** Performs basic tests of huglife package.
     */

    @Test
    public void populateAndDraw() {
        Grid g = new Grid(20);
        g.placeOccupant(0, 10, new SampleCreature());
        g.placeOccupant(5, 5, new SampleCreature());
        g.drawWorld();
        StdDraw.show(20);
        g.doMove(0, 10, 0, 11);
        g.drawWorld();
        StdDraw.show(20);

    }

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(TestHugLife.class);
        for (Failure failure : result.getFailures()) {
          System.out.println(failure.toString());
        }   
    }
} 