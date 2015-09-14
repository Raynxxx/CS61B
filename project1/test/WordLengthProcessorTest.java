package test;

import ngordnet.WordLengthProcessor;
import ngordnet.YearlyRecord;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * WordLengthProcessorTest
 * Created by rayn on 2015-9-14.
 */
public class WordLengthProcessorTest {

    @Test
    public void testBasic() {
        YearlyRecord yr = new YearlyRecord();
        yr.put("sheep", 100);
        yr.put("dog", 300);
        WordLengthProcessor wlp = new WordLengthProcessor();
        assertEquals(3.5, wlp.process(yr), 0.1);
    }


    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(WordLengthProcessorTest.class);
    }
}
