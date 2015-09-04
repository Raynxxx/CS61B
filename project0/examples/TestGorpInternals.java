/** Gives an example of how to run tests that are wrapped in a nested class.
 *  @author Josh Hug
 */

public class TestGorpInternals {
    /** Runs the tests provided by the TestableGorpTester class that
     *  is nested inside TestableGorp.
     */

    public static void main(String[] args) {
        TestableGorp.TestableGorpTester.runTests();
    }
} 