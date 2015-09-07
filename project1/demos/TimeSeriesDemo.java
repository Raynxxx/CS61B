import ngordnet.TimeSeries;
import java.util.Collection;

/** Class that demonstrates the use of a TimeSeries.
 *  This file is not intended to be compiled or executed.
 *  Though you're welcome to do so after you finish the TimeSeries class.
 *  @author Josh Hug
 */

public class TimeSeriesDemo {
    public static void main(String[] args) {
        //Create a new time series that maps to Double 
        TimeSeries<Double> ts = new TimeSeries<Double>();

        /* You will not need to implement the put method, since your
           TimeSeries class should extend the TreeMap class. */
        ts.put(1992, 3.6);
        ts.put(1993, 9.2);
        ts.put(1994, 15.2);
        ts.put(1995, 16.1);
        ts.put(1996, -15.7);

        /* Gets the years and data of this TimeSeries. 
         * Note, you should never cast these to another type, even
         * if you happen to know how the Collection<Number> is implemented. */
        Collection<Number> years = ts.years();
        Collection<Number> data = ts.data();

        for (Number yearNumber : years) {
            /* This awkward conversion is necessary since you cannot
             * do yearNumber.get(yearNumber), since get expects as
             * Integer since TimeSeries always require an integer
             * key. 
             *
             * Your output may be in any order. */
            int year = yearNumber.intValue();
            double value = ts.get(year);
            System.out.println("In the year " + year + " the value was " + value);
        }

        for (Number dataNumber : data) {
             /* Your dataNumber values must print out in the same order as the
              * they did in the previous for loop. */
            double datum = dataNumber.doubleValue();
            System.out.println("In some year, the value was " + datum);
        }        

        TimeSeries<Integer> ts2 = new TimeSeries<Integer>();
        ts2.put(1991, 10);
        ts2.put(1992, -5);
        ts2.put(1993, 1);

        TimeSeries<Double> tSum = ts.plus(ts2);
        System.out.println(tSum.get(1991)); // should print 10
        System.out.println(tSum.get(1992)); // should print -1.4

        TimeSeries<Double> ts3 = new TimeSeries<Double>();
        ts3.put(1991, 5.0);
        ts3.put(1992, 1.0);
        ts3.put(1993, 100.0);

        TimeSeries<Double> tQuotient = ts2.dividedBy(ts3);

        System.out.println(tQuotient.get(1991)); // should print 2.0

        /* The following would cause an IllegalArgumentException since ts2
         * does not include all years from ts, which is tantamount to a
         * divide by zero error. 
        
        TimeSeries<Double> quotient = ts.dividedBy(ts2);
        
        */


        /* The following code would fail at compile time, because a 
         * TimeSeries's formal type parameter is defined as having
         * a type upper bound of Number, and String is-not-a Number.
        
        TimeSeries<String> tsS = new TimeSeries<String>();

        */

    }
} 
