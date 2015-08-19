import java.util.Random;
import java.util.Collections;
import java.util.HashSet;
import java.util.ArrayList;

/** Class for testing the running times of inserting and removing
 *  from linked lists and hash tables. Usage:
 *      java Asymptotics2 DataStructure n
 *  (where n is a positive integer and DataStructure is either ArrayList or
 *   HashSet)
 *  This prints the number of seconds that it takes to insert and remove
 *  n random values between 0 and 1 from the appropriate data structure. */
public class Asymptotics2 {

    public static void main(String[] args) {
        if (args.length > 1) {
            try {
                int n = Integer.parseInt(args[1]);
                if (n <= 0) {
                    usage();
                    return;
                }
                ArrayList<Double> vals = randomVals(n);
                ArrayList<Double> shuffledVals = new ArrayList<Double>(vals);
                Collections.shuffle(shuffledVals);
                Stopwatch timer = new Stopwatch();
                switch (args[0]) {
                case "ArrayList":
                    ArrayListRemoval(shuffledVals, new ArrayList<Double>(vals));
                    break;
                case "HashSet":
                    HashSetRemoval(shuffledVals, new HashSet<Double>(vals));
                    break;
                default: usage();
                    return;
                }
                System.out.println(timer.elapsedTime() + " seconds elapsed");
            } catch (NumberFormatException e) {
                usage();
            }
        } else {
            usage();
        }
    }

    /** Returns a list of N values between 0 and 1 chosen uniformly
     *  at random. */
    public static ArrayList<Double> randomVals(int n) {
        Random R = new Random();
        ArrayList<Double> vals = new ArrayList<Double>();
        for (int i = 0; i < n; i++) {
            vals.add(R.nextDouble());
        }
        return vals;
    }

    /** Removes the elements of VALS from LIST. */
    public static void ArrayListRemoval(ArrayList<Double> vals, ArrayList<Double> list) {
        for (double d : vals) {
            list.remove(d);
        }
    }

    /** Removes the elements of VALS from the hash table SET. */
    public static void HashSetRemoval(ArrayList<Double> vals, HashSet<Double> set) {
        for (double d : vals) {
            set.remove(d);
        }
    }

    public static void usage() {
        System.out.println("Use commands of the form 'java Asymptotics2 " +
                           "DataStructure n' where n is a positive integer and " +
                           "DataStructure is either ArrayList or HashSet. " +
                           "This will print the number of seconds that it took to " +
                           "insert and then remove n random values between 0" +
                           " and 1 from the specified data structure.");
    }
    

}