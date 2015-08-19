import org.junit.Test;
import static org.junit.Assert.*;

/** In our testing lecture, we ran into the issue that testing
 *  private methods and private variables is a bit tricky. Our kludgy solution
 *  in lecture was to make things public, but that won't work in project 0.
 *
 *  An alternate approach is to create a nested test class. This file provides
 *  an example of this strategy. To run the tests, you'll need to run 
 *  TestGorpInternals.
 *
 *  You do not need to write unit tests for your private methods. In fact,
 *  some would argue that such testing is undesirable. This is a subject
 *  of not insignificant debate. 
 *
 *  If you're curious to read more, see: 
 *  http://www.artima.com/suiterunner/private.html
 *  http://stackoverflow.com/questions/34571/how-to-test-a-class-that-has-private-methods-fields-or-inner-classes
 *  
 *  @author Josh Hug
 */

public class TestableGorp {

    private int[][] gorpgorp;

    /** Initializes a new TestableGorp with values:
      * 0  1  2  3  4  5  6  7
      * 8  9 10 11 12 13 14 15 ...
      */ 
    public TestableGorp() {
        gorpgorp = new int[8][8];
        int count = 0;

        for (int i = 0; i < 8; i += 1) {
            for (int j = 0; j < 8; j += 1) {
                gorpgorp[i][j] = count;
                count += 1;
            }
        }
    }

    /* Your test class should be declared as below: public static class. 
     * We have not yet discussed nested classes nor the idea of static classes
     * so you'll just have to trust me. 
     */
    public static class TestableGorpTester {
        @Test
        public void testConstructor() {
            TestableGorp tg = new TestableGorp();
            assertEquals(6, tg.gorpgorp[0][6]);
        }

        public static void runTests() {
            jh61b.junit.textui.runClasses(TestableGorpTester.class);
        }
    }
} 