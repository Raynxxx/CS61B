package ngordnet;

import java.util.*;

/**
 * TimeSeries
 * Created by rayn on 15-9-9.
 */
public class TimeSeries<T extends Number> extends TreeMap<Integer, T> {
    /** Constructs a new empty TimeSeries. */
    public TimeSeries() {
        super();
    }

    /** Returns the years in which this time series is valid. Doesn't really
     * need to be a NavigableSet. This is a private method and you don't have
     * to implement it if you don't want to. */
    private NavigableSet<Integer> validYears(int startYear, int endYear) {
        NavigableSet<Integer> ret = new TreeSet<>();
        for (Integer year : this.keySet()) {
            if (startYear <= year && year <= endYear) {
                ret.add(year);
            }
        }
        return ret;
    }

    /** Creates a copy of TS, but only between STARTYEAR and ENDYEAR.
     * inclusive of both end points. */
    public TimeSeries(TimeSeries<T> ts, int startYear, int endYear) {
        super();
        for (Integer year : ts.keySet()) {
            if (startYear <= year && year <= endYear) {
                this.put(year, ts.get(year));
            }
        }
    }

    /** Creates a copy of TS. */
    public TimeSeries(TimeSeries<T> ts) {
        this(ts, ts.firstKey(), ts.lastKey());
    }

    /** Returns the quotient of this time series divided by the relevant value in ts.
     * If ts is missing a key in this time series, return an IllegalArgumentException. */
    public TimeSeries<Double> dividedBy(TimeSeries<? extends Number> ts) {
        TimeSeries<Double> div = new TimeSeries<Double>();
        for (Integer year : this.keySet()) {
            if (!ts.containsKey(year)) {
                throw new IllegalArgumentException("Year mismatch");
            }
            double val = this.get(year).doubleValue() / ts.get(year).doubleValue();
            div.put(year, val);
        }
        return div;
    }

    /** Returns the sum of this time series with the given ts. The result is a
     * a Double time series (for simplicity). */
    public TimeSeries<Double> plus(TimeSeries<? extends Number> ts) {
        TimeSeries<Double> sum = new TimeSeries<Double>();
        Set<Integer> years = new HashSet<>(this.keySet());
        years.addAll(ts.keySet());
        for (Integer year : years) {
            double curSum = 0;
            if (this.containsKey(year)) {
                curSum += this.get(year).doubleValue();
            }
            if (ts.containsKey(year)) {
                curSum += ts.get(year).doubleValue();
            }
            sum.put(year, curSum);
        }
        return sum;
    }

    /** Returns all years for this time series (in any order). */
    public Collection<Number> years() {
        return new HashSet<Number>(this.keySet());
    }

    /** Returns all data for this time series.
     * Must be in the same order as years(). */
    public Collection<Number> data() {
        return new HashSet<Number>(this.values());
    }
}