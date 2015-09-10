package ngordnet;

import edu.princeton.cs.introcs.In;

import java.sql.Time;
import java.util.Collection;
import java.util.HashMap;

/**
 * NGramMap
 * Created by rayn on 15-9-9.
 */
public class NGramMap {

    private HashMap<Integer, YearlyRecord> yearRecords;
    private HashMap<String, TimeSeries<Integer>> wordsTimeSeries;
    private TimeSeries<Long> yearTotalCount;

    /** Constructs an NGramMap from WORDSFILENAME and COUNTSFILENAME. */
    public NGramMap(String wordsFilename, String countsFilename) {
        In wordsIn = new In(wordsFilename);
        In countsIn = new In(countsFilename);
        yearRecords = new HashMap<>();
        wordsTimeSeries = new HashMap<>();
        while (!wordsIn.isEmpty()) {
            String[] row = wordsIn.readLine().split("\\t");
            String word = row[0];
            int year = Integer.parseInt(row[1]);
            int appear = Integer.parseInt(row[2]);
            if (!yearRecords.containsKey(year)) {
                yearRecords.put(year, new YearlyRecord());
            }
            yearRecords.get(year).put(word, appear);

            if (!wordsTimeSeries.containsKey(word)) {
                wordsTimeSeries.put(word, new TimeSeries<Integer>());
            }
            wordsTimeSeries.get(word).put(year, appear);
        }
        yearTotalCount = new TimeSeries<Long>();
        while (!countsIn.isEmpty()) {
            String[] row = countsIn.readLine().split(",");
            int year = Integer.parseInt(row[0]);
            long total = Long.parseLong(row[1]);
            yearTotalCount.put(year, total);
        }
    }

    /** Returns the absolute count of WORD in the given YEAR. If the word
     * did not appear in the given year, return 0. */
    public int countInYear(String word, int year) {
        if (yearRecords.containsKey(year)) {
            return yearRecords.get(year).count(word);
        }
        return 0;
    }

    /** Returns a defensive copy of the YearlyRecord of YEAR. */
    public YearlyRecord getRecord(int year) {
        YearlyRecord yr = yearRecords.get(year);
        YearlyRecord ret = new YearlyRecord();
        Collection<String> words = yr.words();
        for (String word : words) {
            ret.put(word, yr.count(word));
        }
        return ret;
    }

    /** Returns the total number of words recorded in all volumes. */
    public TimeSeries<Long> totalCountHistory() {
        return yearTotalCount;
    }

    /** Provides the history of WORD between STARTYEAR and ENDYEAR. */
    public TimeSeries<Integer> countHistory(String word, int startYear, int endYear) {
        TimeSeries<Integer> whole = countHistory(word);
        if (whole != null) {
            return new TimeSeries<Integer>(whole, startYear, endYear);
        }
        return null;
    }

    /** Provides a defensive copy of the history of WORD. */
    public TimeSeries<Integer> countHistory(String word) {
        if (wordsTimeSeries.containsKey(word)) {
            return wordsTimeSeries.get(word);
        }
        return null;
    }

    /** Provides the relative frequency of WORD between STARTYEAR and ENDYEAR. */
    public TimeSeries<Double> weightHistory(String word, int startYear, int endYear) {
        TimeSeries<Double> whole = weightHistory(word);
        if (whole != null) {
            return new TimeSeries<Double>(whole, startYear, endYear);
        }
        return null;
    }

    /** Provides the relative frequency of WORD. */
    public TimeSeries<Double> weightHistory(String word) {
        TimeSeries<Integer> whole = countHistory(word);
        return whole.dividedBy(yearTotalCount);
    }

    /** Provides the summed relative frequency of all WORDS between
     * STARTYEAR and ENDYEAR. If a word does not exist, ignore it rather
     * than throwing an exception. */
    public TimeSeries<Double> summedWeightHistory(Collection<String> words,
                                                  int startYear, int endYear) {
        TimeSeries<Double> whole = summedWeightHistory(words);
        if (whole != null) {
            return new TimeSeries<Double>(whole, startYear, endYear);
        }
        return null;
    }

    /** Returns the summed relative frequency of all WORDS. */
    public TimeSeries<Double> summedWeightHistory(Collection<String> words) {
        TimeSeries<Double> sum = new TimeSeries<Double>();
        for (String word : words) {
            if (countHistory(word) != null) {
                if (sum.size() == 0) {
                    sum = weightHistory(word);
                } else {
                    sum = sum.plus(weightHistory(word));
                }
            }
        }
        return sum;
    }

    /** Provides processed history of all words between STARTYEAR and ENDYEAR as processed
     * by YRP. */
    public TimeSeries<Double> processedHistory(int startYear, int endYear,
                                               YearlyRecordProcessor yrp) {
        return null;
    }

    /** Provides processed history of all words ever as processed by YRP. */
    public TimeSeries<Double> processedHistory(YearlyRecordProcessor yrp) {
        return null;
    }
}