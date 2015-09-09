package ngordnet;

import java.util.Collection;
import java.util.HashMap;

/**
 * YearlyRecord
 * Created by rayn on 15-9-9.
 */
public class YearlyRecord {
    /** Creates a new empty YearlyRecord. */
    public YearlyRecord() {}

    /** Creates a YearlyRecord using the given data. */
    public YearlyRecord(HashMap<String, Integer> otherCountMap) {}

    /** Returns the number of times WORD appeared in this year. */
    public int count(String word) {
        return 0;
    }

    /** Records that WORD occurred COUNT times in this year. */
    public void put(String word, int count) {

    }

    /** Returns the number of words recorded this year. */
    public int size() {
        return 0;
    }

    /** Returns all words in ascending order of count. */
    public Collection<String> words() {
        return null;
    }

    /** Returns all counts in ascending order of count. */
    public Collection<Number> counts() {
        return null;
    }

    /** Returns rank of WORD. Most common word is rank 1.
     * If two words have the same rank, break ties arbitrarily.
     * No two words should have the same rank.
     */
    public int rank(String word) {
        return 0;
    }
}