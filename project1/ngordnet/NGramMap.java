package ngordnet;

import java.util.Collection;

/**
 * NGramMap
 * Created by rayn on 15-9-9.
 */
public class NGramMap {
    /** Constructs an NGramMap from WORDSFILENAME and COUNTSFILENAME. */
    public NGramMap(String wordsFilename, String countsFilename) {}

    /** Returns the absolute count of WORD in the given YEAR. If the word
     * did not appear in the given year, return 0. */
    public int countInYear(String word, int year) {
        return 0;
    }

    /** Returns a defensive copy of the YearlyRecord of YEAR. */
    public YearlyRecord getRecord(int year) {
        return null;
    }

    /** Returns the total number of words recorded in all volumes. */
    public TimeSeries<Long> totalCountHistory() {
        return null;
    }

    /** Provides the history of WORD between STARTYEAR and ENDYEAR. */
    public TimeSeries<Integer> countHistory(String word, int startYear, int endYear) {
        return null;
    }

    /** Provides a defensive copy of the history of WORD. */
    public TimeSeries<Integer> countHistory(String word) {
        return null;
    }

    /** Provides the relative frequency of WORD between STARTYEAR and ENDYEAR. */
    public TimeSeries<Double> weightHistory(String word, int startYear, int endYear) {
        return null;
    }

    /** Provides the relative frequency of WORD. */
    public TimeSeries<Double> weightHistory(String word) {
        return null;
    }

    /** Provides the summed relative frequency of all WORDS between
     * STARTYEAR and ENDYEAR. If a word does not exist, ignore it rather
     * than throwing an exception. */
    public TimeSeries<Double> summedWeightHistory(Collection<String> words,
                                                  int startYear, int endYear) {
        return null;
    }

    /** Returns the summed relative frequency of all WORDS. */
    public TimeSeries<Double> summedWeightHistory(Collection<String> words) {
        return null;
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