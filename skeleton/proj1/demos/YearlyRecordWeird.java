/** An object that stores word counts of all words in a single year. Provides
 *  utility methods useful for data analysis.
 *  @author Josh Hug
 */
import java.util.TreeMap;
import java.util.Arrays;
import java.util.Comparator;

/** An object that stores all word counts for a given year, */
public class YearlyRecordWeird {
    private TreeMap<String, Integer> countMap = new TreeMap<String, Integer>();

    /** Store the number of keys with fewer Zs than our String. */
    private TreeMap<String, Integer> fewerZsCount = new TreeMap<String, Integer>();

    /** Determines whether or not the fewerZsCount map needs updating. */
    private boolean needsUpdating = true;



    /** Returns the number of times WORD appeared in this year. */
    public int count(String word) {
        return countMap.get(word);
    }

    /** Records that WORD occurred COUNT times in this year. */
    public void put(String word, int count) {
        /* Will not pass muster for that 0.1 bonus points. */
        countMap.put(word, count);
        needsUpdating = true;

    }

    /** Returns the number of words with fewer Zs than x, where x is some
      * key in the map. If x is not part of the map, return -1. */
    public int fewerZs(String x) {        
        if (!countMap.containsKey(x)) {
            return -1;
        }

        if (needsUpdating) {
            updateFewerZsCount();
            needsUpdating = false;
        }
        return fewerZsCount.get(x);
    }

    /** Comparator that compares strings based on zCount. */
    private class ZComparator implements Comparator<String> {
        public int compare(String x, String y) {
            return zCount(x) - zCount(y);
        }
    }

    /** Update sthe fewerZsCount map using sorting. */
    private void updateFewerZsCount() {
        fewerZsCount = new TreeMap<String, Integer>();
        /** The slow approach:
          * For every key, compare against all other keys, and count zs.
          * O(N^2) -- slow compared to sorting. */

        /* Better approach: Sort the items! */
        /* After sorting: we get 'peel', 'zebra', 'zebras', 'zzzzzz'
        */

        String[] words = new String[countMap.size()];
        int cnt = 0;
        for (String word : countMap.keySet()) {
            words[cnt] = word;
            cnt += 1;
        }

        /* Sort words by order of number of Zs */
        Arrays.sort(words, new ZComparator());

        /* This is specific to this weird problem I've made up, not the
         * project. */
        int lastZCount = 0;
        fewerZsCount.put(words[0], 0);        
        for (int i = 1; i < words.length; i += 1) {
            int currentZCount = zCount(words[i]);
            int numWordsLessZs;

            if (currentZCount > lastZCount) {
                numWordsLessZs = i;
            } else {
                numWordsLessZs = fewerZsCount.get(words[i-1]);
            }
            
            fewerZsCount.put(words[i], numWordsLessZs);
            lastZCount = currentZCount;
        }

    }

    /* Returns number of Zs or zs in x. */
    private int zCount(String x) {
        int zCount = 0;
        for (int i = 0; i < x.length(); i += 1) {
            if (x.charAt(i) == 'z' || x.charAt(i) == 'Z') {
                zCount += 1;
            }
        }
        return zCount;
    }

    public static void main(String[] args) {
        YearlyRecordWeird yr = new YearlyRecordWeird();
        yr.put("peel", 95);
        yr.put("zzzzz", 95);
        yr.put("zebras", 95);
        yr.put("zebraz", 105);
        System.out.println(yr.fewerZs("zzzzz")); // should be 3: peel, zebras, zebraz
        System.out.println(yr.fewerZs("zzzzzzzz")); // should be -1: not in map
        System.out.println(yr.fewerZs("zebras")); // should be 1: peel
    }
} 
