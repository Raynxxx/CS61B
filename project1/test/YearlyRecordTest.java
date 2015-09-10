package test;

import ngordnet.YearlyRecord;
import org.junit.Test;
import static org.junit.Assert.*;
/**
 * YearlyRecordTest
 * Created by rayn on 2015-9-10.
 */
public class YearlyRecordTest {

    @Test
    public void testBasic() {
        YearlyRecord yr = new YearlyRecord();
        yr.put("quayside", 95);
        yr.put("surrogate", 340);
        yr.put("merchantman", 181);
        assertEquals(1, yr.rank("surrogate"));
        assertEquals(3, yr.rank("quayside"));
    }

    @Test
    public void testSize() {
        YearlyRecord yr = new YearlyRecord();
        yr.put("rayn", 20);
        yr.put("plus7", 22);
        yr.put("rayn", 21);
        yr.put("plus7", 25);
        assertEquals(2, yr.size());
    }

    public static void main(String... args) {
        jh61b.junit.textui.runClasses(YearlyRecordTimeTest.class);
    }
}
