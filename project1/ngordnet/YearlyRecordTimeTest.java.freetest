package ngordnet;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.List;
import java.util.ArrayList;
import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.Stopwatch;

/** Timing class to help you figure out if your YearlyRecord is too slow.
 *  @author Josh Hug
 */
public class YearlyRecordTimeTest {

    /** Stright from: http://www.baeldung.com/java-random-string. Returns
      * a random string. */
    public static String randomString() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 123; // letter 'z' + 1
        int targetStringLength = 10;
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = StdRandom.uniform(leftLimit, rightLimit);
            buffer.append((char) randomLimitedInt);
        }
        return buffer.toString();
    }

    /** Inserts N random Strings and dummy counts into a YearlyRecord. */
    private void testPutHelper(int N) {
        YearlyRecord yr = new YearlyRecord();

        for (int i = 0; i < N; i += 1) {
            String rs = randomString();
            /* Each random string has a random count of between 0 and 49. 
             * May include multiple strings with same count. */
            yr.put(rs, StdRandom.uniform(50));
        }        
    }


    /* Tests to see if you can insert 100000 strings in less than a second. 
     * Pass this on submit/proj1 for 0.1 bonus points. */
    @Test(timeout = 1000)
    public void testPut100000() {
        testPutHelper(100000);
    }

    /* Tests to see if you can insert 1000 strings in less than a second. */
    @Test(timeout = 1000)
    public void testPut1000() {

        testPutHelper(1000);
    }


    /** Returns the number of rank calls that can be made in MAXTIME on a
      * YearlyRecord with N entries. Prints result in addition to returning.*/
    private int countRankCalls(int N, int maxTime) {
        YearlyRecord yr = new YearlyRecord();

        List<String> words = new ArrayList<String>();
        for (int i = 0; i < N; i += 1) {
            String rs = randomString();
            yr.put(rs, i);
            words.add(rs);
        }

        int rankCount = 0;
        Stopwatch sw = new Stopwatch();
        while (sw.elapsedTime() < maxTime) {
            String retrievalString = words.get(StdRandom.uniform(0, N));
            yr.rank(retrievalString);
            rankCount += 1;
        }

        System.out.println("Completed " + rankCount + " rank() ops in " + maxTime
                           + " seconds on YearlyRecord with " + N + " entries.");
        return rankCount;
    }

    /** Tests to see if your rank() function is independent of YearlyRecord size. 
      * Must pass for full credit. */
    @Test(timeout = 10000)
    public void testRankCalls() {
        int maxTimeInSeconds = 2;
        int numWordsToPut = 1;
        int numRankCallsTinyYR = countRankCalls(numWordsToPut, maxTimeInSeconds);

        numWordsToPut = 100;
        int numRankCallsLargerYR = countRankCalls(numWordsToPut, maxTimeInSeconds);
    
        double ratio = ((double) numRankCallsTinyYR / (double) numRankCallsLargerYR);
        assertTrue("Expected ratio of number of rank calls to be no more than 2. "
                   + "Actual ratio was: " + ratio, ratio < 2);
    }

    public static void main(String... args) {
        System.out.println("This test suite has 3 tests:");
        System.out.println(" * TestRankCalls: Tests how many rank calls can be made");
        System.out.println("   in 5 seconds on YearlyRecords of varying sizes. Should not");
        System.out.println("   vary significantly (factor of 10x) when comparing between");
        System.out.println("   YearlyRecord of size 1 and YearlyRecord of size 100. Must");
        System.out.println("   pass on submit/proj1 for full credit.\n");

        System.out.println(" * TestPut100000: Can your code insert 100000 items into a");
        System.out.println("   yearly record in less than 1 second? Pass on submit/proj1");
        System.out.println("   for 0.1 bonus points.\n");        

        System.out.println(" * TestPut1000: Can your code insert 1000 items into a");
        System.out.println("   yearly record in less than 1 second? No bonus credit, but");
        System.out.println("   your code really ought to be able to do this! I hope");
        System.out.println("   the thirst for efficiency is sufficient to motivate you.\n");

        jh61b.junit.textui.runClasses(YearlyRecordTimeTest.class);
    }
}   


