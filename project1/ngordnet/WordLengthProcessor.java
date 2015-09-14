package ngordnet;

import java.util.*;

/**
 * WordLengthProcessor
 * Created by rayn on 15-9-9.
 */
public class WordLengthProcessor implements YearlyRecordProcessor {
    @Override
    public double process(YearlyRecord yearlyRecord) {
        Collection<String> wordSet = yearlyRecord.words();
        //TreeSet<String> wordSet = new TreeSet<>(yearlyRecord.words());
        double totalCount = 0, totalLength = 0;
        for (String word : wordSet) {
            System.out.print(word + "  ");
            int curCount = yearlyRecord.count(word);
            int curLength = word.length();
            totalLength += curLength * curCount;
            totalCount += curCount;
        }
        return totalLength / totalCount;
    }
}
