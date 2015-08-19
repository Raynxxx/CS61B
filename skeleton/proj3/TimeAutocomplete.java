import java.util.Arrays;

public class TimeAutocomplete {
    // total score of topMatches. Used to avoid strangeness with compiler optimizing away our calls to topMatches.
    private static double score = 0.0;

    // hack - save terms and weights in instance variables to avoid reparsing (slows autograder)
    private static String[] terms;
    private static String[] sortedTerms;
    private static double[] weights;
    private static Autocomplete student;

    public static void initTermsWeights(String filename) {
        In in = new In(filename);
        int N = Integer.parseInt(in.readLine());
        terms = new String[N];
        weights = new double[N];
        sortedTerms = new String[N];
        for (int i = 0; i < N; i++) {
            weights[i] = in.readDouble();   // read the next weight
            in.readChar();                  // scan past the tab
            terms[i] = in.readLine();       // read the next term
            sortedTerms[i] = terms[i];
        }
        Arrays.sort(sortedTerms);
    }

    /** Constructs a student autocomplete object. Returns time to do so. */
    private static double constructStudentAutocomplete()  {
        Stopwatch timer = new Stopwatch();
        student = new Autocomplete(terms, weights);
        return timer.elapsedTime() * 1000;
    }

    /** Returns a random valid prefix of given size. */
    private static String randomPrefix(int prefixSize) {
        while (true) {
            int r = StdRandom.uniform(terms.length);
            if (terms[r].length() >= prefixSize)
                return terms[r].substring(0, prefixSize);
        }
    }

    /** Measures calls per second on an Autocomplete object. */
    public static double callsPerSecond(Autocomplete student, int k, double maxTime, int prefixSize) {
        Stopwatch timer = new Stopwatch();
        int calls = 0;
        while (timer.elapsedTime() <  maxTime) {
            String prefix = randomPrefix(prefixSize);
            for (String word : student.topMatches(prefix, k))
                score += student.weightOf(word);
            calls++;
        }
        return ((double) calls) / maxTime;
    }

    // student callsPerSecond tester that uses a preset list of prefixes
    public static double callsPerSecond(Autocomplete student, int k, double maxTime, Iterable<String> prefixes) {
        Stopwatch timer = new Stopwatch();
        int total = 0;
        int calls = 0;
        for (String queryPrefix : prefixes) {
            if (timer.elapsedTime() > maxTime)
                break;

            for (String word : student.topMatches(queryPrefix, k))
                score += student.weightOf(word);                    

            calls++;        
        }        

        return ((double) calls) / ((double) timer.elapsedTime());
    }


    // top level method for timing constructor
    private static void timeConstructor(String filename) {
        System.out.println(" *  " + filename);
        initTermsWeights(filename);
        int iter = 3; 

        double constructorTime = 0.0;
        for (int i = 0; i < iter; i++) {
            try {
                constructorTime += constructStudentAutocomplete();
            }
            catch (Throwable e) {
                e.printStackTrace();
                return;
            }
        }
        constructorTime /= iter;

        System.out.println((String.format ("      - student   solution time (in milliseconds): %.2f\n", constructorTime)));
    }

    // top level method timing tests, based on prefix size
    private static void testTimeVsPrefixSize(int k, double seconds, int prefixSize) {
        System.out.println(String.format("  *  timing for %.1f seconds.\n", seconds)); 

        double studentCPS = 0.0;
        try {
            studentCPS = callsPerSecond(student, k, seconds, prefixSize);
        }
        catch (Throwable e) {
            e.printStackTrace();
            return;
        }

        System.out.println(String.format ("      - student   solution calls per second: %.2f\n", studentCPS));
    }

    public static void test1() {
        System.out.println("Test 1: timing constructor");
        timeConstructor("cities.txt");
        timeConstructor("baby-names.txt");
        timeConstructor("wiktionary.txt");
    }

    public static void test2() {
        int k = 5;
        int prefixLength = 2;
        String filename = "cities.txt";
        System.out.println((String.format("Calling topMatches() with k = %d and random %d-letter queries using %s\n", k, prefixLength, filename)));
        initTermsWeights(filename);
        constructStudentAutocomplete();

        testTimeVsPrefixSize(k, 2.0, prefixLength);
    }

    public static void test3() {
        int k = 10;
        int prefixLength = 6;
        String filename = "cities.txt";
        System.out.println((String.format("Calling topMatches() with k = %d and random %d-letter queries using %s\n", k, prefixLength, filename)));
        initTermsWeights(filename);
        constructStudentAutocomplete();

        testTimeVsPrefixSize(k, 2.0, prefixLength);
    }

    public static void test4() {
        double ratio = 10;
        int k = 10;
        int prefixLength = 3;
        String filename = "baby-names.txt";
        System.out.println(String.format("Calling topMatches() with k = %d and random %d-letter queries using %s\n", k, prefixLength, filename));
        initTermsWeights(filename);
        constructStudentAutocomplete();

        testTimeVsPrefixSize(k, 2.0, prefixLength);
        System.out.println("Now testing with prefix length 1");
        testTimeVsPrefixSize(k, 2.0, 1);
        System.out.println("Now testing with prefix length 0");
        testTimeVsPrefixSize(k, 2.0, 0);
    }
    
    public static void test5() {
        double ratio = 10;
        int k = 3;
        int prefixLength = 2;
        String filename = "baby-names.txt";
        System.out.println(String.format("Calling topMatches() with k = %d and random %d-letter queries using %s\n", k, prefixLength, filename));
        initTermsWeights(filename);
        constructStudentAutocomplete();

        testTimeVsPrefixSize(k, 2.0, prefixLength);
    }

    public static void main(String... args) {
        test1();
        test2();
        test3();
        test4();
        test5();
    }
}
