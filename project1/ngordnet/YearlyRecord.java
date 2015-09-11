package ngordnet;

import java.util.*;

/**
 * YearlyRecord
 * Created by rayn on 2015-9-9.
 */
public class YearlyRecord {

    private TreeMap<String, Integer> records;
    private HashMap<String, Integer> recordCount;
    private HashMap<String, Integer> recordRank;
    private int size;
    private boolean frozen;

    /** Creates a new empty YearlyRecord. */
    public YearlyRecord() {
        records = new TreeMap<String, Integer>(new RecordComparator());
        recordCount = new HashMap<String, Integer>();
        recordRank = new HashMap<String, Integer>();
        size = 0;
        frozen = true;
    }

    /** Creates a YearlyRecord using the given data. */
    public YearlyRecord(HashMap<String, Integer> otherCountMap) {
        records = new TreeMap<String, Integer>(new RecordComparator());
        records.putAll(otherCountMap);
        recordCount = new HashMap<String, Integer>(otherCountMap);
        size = recordCount.size();
        buildRankMap();
    }

    /** Returns the number of times WORD appeared in this year. */
    public int count(String word) {
        if (recordCount.containsKey(word)) {
            return recordCount.get(word);
        }
        return 0;
    }

    /** Records that WORD occurred COUNT times in this year. */
    public void put(String word, int count) {
        if (!recordCount.containsKey(word)) {
            size++;
        }
        recordCount.put(word, count);
        records.put(word, count);
        frozen = false;
    }

    /** Returns the number of words recorded this year. */
    public int size() {
        return size;
    }

    /** Returns all words in ascending order of count. */
    public Collection<String> words() {
        return records.descendingKeySet();
    }

    /** Returns all counts in ascending order of count. */
    public Collection<Number> counts() {
        return new TreeSet<Number>(records.values());
    }

    /** Returns rank of WORD. Most common word is rank 1.
     * If two words have the same rank, break ties arbitrarily.
     * No two words should have the same rank.
     */
    public int rank(String word) {
        if (frozen) {
            return recordRank.get(word);
        } else {
            buildRankMap();
            return recordRank.get(word);
        }
    }

    private void buildRankMap() {
        recordRank = new HashMap<String, Integer>();
        int rank = 1;
        for (String key : records.keySet()) {
            recordRank.put(key, rank);
            rank++;
        }
        frozen = true;
    }

    private class RecordComparator implements Comparator<String> {

        @Override
        public int compare(String o1, String o2) {
            if (recordCount.get(o1).compareTo(recordCount.get(o2)) < 0) {
                return 1;
            } else {
                return -1;
            }
        }
    }

}