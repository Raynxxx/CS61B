package test;

import ngordnet.TimeSeries;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * TimeSeriesTest
 * Created by rayn on 2015-9-9.
 */
public class TimeSeriesTest {

    @Test
    public void testPlus() {
        TimeSeries<Double> ts = new TimeSeries<Double>();
        ts.put(1992, 3.6);
        ts.put(1993, 9.2);
        ts.put(1994, 15.2);
        TimeSeries<Integer> ts2 = new TimeSeries<Integer>();
        ts2.put(1991, 10);
        ts2.put(1992, -5);
        ts2.put(1993, 1);
        TimeSeries<Double> tSum = ts.plus(ts2);
        assertEquals(10.0, tSum.get(1991), 0.01);
    }

    @Test
    public void testDivision() {
        TimeSeries<Integer> ts = new TimeSeries<Integer>();
        ts.put(1991, 10);
        ts.put(1992, -5);
        ts.put(1993, 1);
        TimeSeries<Double> ts2 = new TimeSeries<Double>();
        ts2.put(1991, 5.0);
        ts2.put(1992, 1.0);
        ts2.put(1993, 100.0);
        TimeSeries<Double> tQuotient = ts.dividedBy(ts2);
        assertEquals(2.0, tQuotient.get(1991), 0.01);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testYearMismatch() {
        TimeSeries<Double> ts = new TimeSeries<Double>();
        ts.put(1992, 3.6);
        ts.put(1993, 9.2);
        ts.put(1994, 15.2);
        TimeSeries<Integer> ts2 = new TimeSeries<Integer>();
        ts2.put(1991, 10);
        ts2.put(1992, -5);
        ts2.put(1993, 1);
        TimeSeries<Double> tQuotient = ts.dividedBy(ts2);
    }

    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TimeSeriesTest.class);
    }
}
