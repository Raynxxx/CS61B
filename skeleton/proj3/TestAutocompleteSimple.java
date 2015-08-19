import org.junit.Test;
import static org.junit.Assert.*;
import java.util.TreeSet;
import java.util.ArrayList;
import java.util.Iterator;

/** Example Junit tests. Expand these instead of beating the autograder to death.
 *  @author Josh Hug
 */

public class TestAutocompleteSimple {
    /* Compares student's answer with expected answer. If your weightOf is broken, this test will be broken, too. */
    private static boolean compare(Iterable<String> student, Iterable<String> reference, Autocomplete studentAutocomplete) {
        Iterator<String> studentIterator  = student.iterator();
        Iterator<String> referenceIterator = reference.iterator();
        int N = 0;
        TreeSet<String> set = new TreeSet<String>();
        while (studentIterator.hasNext() && referenceIterator.hasNext()) {
            N++;
            String studentTerm   = studentIterator.next();
            String referenceTerm = referenceIterator.next();
            if (studentTerm == null) {
                System.out.println("      -  student entry " + N + " is null");
                return false;
            }
            if (set.contains(studentTerm)) {
                System.out.println("      -  student entry " + N + " + repeats the term \"" + studentTerm + "\"");
                return false;
            }
            set.add(studentTerm);
            double studentWeight   = studentAutocomplete.weightOf(studentTerm);
            double referenceWeight = studentAutocomplete.weightOf(referenceTerm);
            if (studentWeight != referenceWeight) {
                if ((referenceTerm.length() == 0 ) && (studentTerm.length() > 0)) {
                    System.out.println("      -  Warning: Reference term was the empty string, and student term was not.\n");
                    System.out.println("      -  Student term was of length: " + studentTerm.length() + ", may include unprintable characters.");
                }

                System.out.println(String.format("      -  reference entry %d = \"%s\" has weight %.1f\n", N, referenceTerm, referenceWeight));
                System.out.println(String.format("      -  student   entry %d = \"%s\" has weight %.1f\n", N, studentTerm,   studentWeight));
                return false;
            }
        }

        // different number of entries
        if (studentIterator.hasNext()) {
            String studentTerm = studentIterator.next();
            System.out.println("      -  student solution has extra entry or entries, including \"" + studentTerm + "\"");
            return false;
        }
        if (referenceIterator.hasNext()) {
            String referenceTerm = referenceIterator.next();
            System.out.println("      -  student solution is missing an entry or entries, including \"" + referenceTerm + "\"");
            return false;
        }

        return true;
    }

    /** Runs a test using inputFile as the input file, k as the number of matches, prefix as the 
      * prefix to attempt to match, and expectedAnswers as the expected results. */
    private static void conductTest(String inputFile, int k, String prefix, ArrayList<String> expectedAnswers) {
        In in = new In(inputFile); 

        int N = Integer.parseInt(in.readLine());
        String[] terms = new String[N];
        double[] weights = new double[N];
        for (int i = 0; i < N; i++) {
            weights[i] = in.readDouble();   // read the next weight
            in.readChar();                  // scan past the tab
            terms[i] = in.readLine();       // read the next term
        }

        Autocomplete    student   = new Autocomplete(terms, weights);

        assertTrue(compare(student.topMatches(prefix, k), expectedAnswers, student));
    }

    @Test
    public void testTiny1() {
        ArrayList<String> expectedAnswers = new ArrayList<String>();
        String filename = "tiny.txt";
        int k = 3;
        String prefix = "s";

        expectedAnswers.add("spite");
        expectedAnswers.add("spit");
        expectedAnswers.add("sad");
        conductTest(filename, k, prefix, expectedAnswers);
    }

    @Test
    public void testCities() {
        ArrayList<String> expectedAnswers = new ArrayList<String>();
        String filename = "cities.txt";
        int k = 5;
        String prefix = "Hug";
        
        expectedAnswers.add("Hugli, India");
        expectedAnswers.add("Hugo, Minnesota, United States");
        expectedAnswers.add("Hughson, California, United States");
        expectedAnswers.add("Hugo, Oklahoma, United States");
        expectedAnswers.add("Hugoton, Kansas, United States");
        conductTest(filename, k, prefix, expectedAnswers);
    }

    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestAutocompleteSimple.class);
    }
} 