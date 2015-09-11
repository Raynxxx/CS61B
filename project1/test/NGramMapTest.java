package test;

import ngordnet.NGramMap;
import ngordnet.TimeSeries;
import ngordnet.YearlyRecord;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * NGramMapTest
 * Created by rayn on 2015-9-10.
 */
public class NGramMapTest {

    @Test
    public void testBasic() {
        NGramMap ngm = new NGramMap("./p1data/ngrams/words_that_start_with_q.csv",
                                    "./p1data/ngrams/total_counts.csv");
        assertEquals(139, ngm.countInYear("quantity", 1736));
        YearlyRecord yr = ngm.getRecord(1736);
        assertEquals(139, yr.count("quantity"));

        TimeSeries<Integer> countHistory = ngm.countHistory("quantity");
        assertEquals(Integer.valueOf(139), countHistory.get(1736));

        TimeSeries<Long> totalCountHistory = ngm.totalCountHistory();
        /* In 1736, there were 8049773 recorded words. Note we are not counting
         * distinct words, but rather the total number of words printed that year. */
        assertEquals(Long.valueOf(8049773), totalCountHistory.get(1736)); // should print 8049773

        TimeSeries<Double> weightHistory = ngm.weightHistory("quantity");
        assertEquals(1.7267E-5, weightHistory.get(1736), 1E-5);

        ArrayList<String> words = new ArrayList<String>();
        words.add("quantity");
        words.add("quality");

        TimeSeries<Double> sum = ngm.summedWeightHistory(words);
        assertEquals(3.875E-5, sum.get(1736), 1E-5);
    }

    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(NGramMapTest.class);
    }
}
