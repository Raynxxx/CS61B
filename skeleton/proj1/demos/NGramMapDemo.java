/** Provides examples of using the NGramMap class.
 *  @author Josh Hug
 */
import ngordnet.NGramMap;
import ngordnet.YearlyRecord;
import ngordnet.TimeSeries;
import java.util.ArrayList;

public class NGramMapDemo {
    public static void main(String[] args) {
        NGramMap ngm = new NGramMap("./ngrams/words_that_start_with_q.csv", 
                                    "./ngrams/total_counts.csv");


        System.out.println(ngm.countInYear("quantity", 1736)); // should print 139
        YearlyRecord yr = ngm.getRecord(1736);
        System.out.println(yr.count("quantity")); // should print 139

        TimeSeries<Integer> countHistory = ngm.countHistory("quantity");
        System.out.println(countHistory.get(1736)); // should print 139

        TimeSeries<Long> totalCountHistory = ngm.totalCountHistory();
        /* In 1736, there were 8049773 recorded words. Note we are not counting
         * distinct words, but rather the total number of words printed that year. */
        System.out.println(totalCountHistory.get(1736)); // should print 8049773

        TimeSeries<Double> weightHistory = ngm.weightHistory("quantity");
        System.out.println(weightHistory.get(1736));  // should print roughly 1.7267E-5
    
        System.out.println((double) countHistory.get(1736) 
                           / (double) totalCountHistory.get(1736)); 

        ArrayList<String> words = new ArrayList<String>();
        words.add("quantity");
        words.add("quality");        

        TimeSeries<Double> sum = ngm.summedWeightHistory(words);
        System.out.println(sum.get(1736)); // should print roughly 3.875E-5
    }
}
