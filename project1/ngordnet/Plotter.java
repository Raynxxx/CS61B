package ngordnet;

import java.util.*;
import com.xeiam.xchart.*;

/**
 * Plotter
 * Created by rayn on 2015-9-10.
 * Utility class for generating plots.
 */
public class Plotter {
    /** Creates a plot of the TimeSeries TS. Labels the graph with the
     * given TITLE, XLABEL, YLABEL, and LEGEND. */
    public static void plotTS(TimeSeries<? extends Number> ts, String title,
                              String xlabel, String ylabel, String legend) {
        Collection<Number> xValues = ts.years();
        Collection<Number> yValues = ts.data();
        Chart chart = QuickChart.getChart(title, xlabel, ylabel, legend,
                xValues, yValues);
        new SwingWrapper(chart).displayChart();
    }

    /** Creates a plot of the absolute word counts for WORD from STARTYEAR
     * to ENDYEAR, using NGM as a data source. */
    public static void plotCountHistory(NGramMap ngm, String word,
                                        int startYear, int endYear) {
        TimeSeries<Integer> ts = ngm.countHistory(word, startYear, endYear);
        Plotter.plotTS(ts, "Absolute Count of " + word + " vs. Year",
                "Year", "Absolute Count", word);
    }

    /** Creates a plot of the normalized weight counts for WORD from STARTYEAR
     * to ENDYEAR, using NGM as a data source. */
    public static void plotWeightHistory(NGramMap ngm, String word,
                                         int startYear, int endYear) {
        TimeSeries<Double> ts = ngm.weightHistory(word, startYear, endYear);
        Plotter.plotTS(ts, "Normalized Weight Count of " + word + " vs. Year",
                "Year", "Normalized Weight Count of", word);
    }

    /** Creates a plot of the processed history from STARTYEAR to ENDYEAR, using
     * NGM as a data source, and the YRP as a yearly record processor. */
    public static void plotProcessedHistory(NGramMap ngm, int startYear, int endYear,
                                            YearlyRecordProcessor yrp) {
        TimeSeries<Double> ts = ngm.processedHistory(startYear, endYear, yrp);
        Plotter.plotTS(ts, "Length of Average Word vs. Year", "Year",
                "Length of Average Word", "Length of Average Word");
    }

    /** Creates a plot of the total normalized count of WN.hyponyms(CATEGORYLABEL)
     * from STARTYEAR to ENDYEAR using NGM and WN as data sources. */
    public static void plotCategoryWeights(NGramMap ngm, WordNet wn, String categoryLabel,
                                           int startYear, int endYear) {
        Set<String> hyponyms = wn.hyponyms(categoryLabel);
        TimeSeries<Double> ts = ngm.summedWeightHistory(hyponyms, startYear, endYear);
        Plotter.plotTS(ts, "Total Normalized Weight Count of Hyponyms of " + categoryLabel + " vs. Year",
                "Year", categoryLabel + "'s Hyponyms Total Normalized Weight",
                categoryLabel);

    }

    /** Creates overlaid category weight plots for each category label in CATEGORYLABELS
     * from STARTYEAR to ENDYEAR using NGM and WN as data sources. */
    public static void plotCategoryWeights(NGramMap ngm, WordNet wn, String[] categoryLabels,
                                           int startYear, int endYear) {
        Chart chart = new ChartBuilder()
                .width(800).height(600)
                .xAxisTitle("Year")
                .yAxisTitle("Hyponyms' Total Normalized Weight").build();
        for (String categoryLabel : categoryLabels) {
            Set<String> hyponyms = wn.hyponyms(categoryLabel);
            TimeSeries<Double> ts = ngm.summedWeightHistory(hyponyms, startYear, endYear);
            Collection<Number> xValues = ts.years();
            Collection<Number> yValues = ts.data();
            chart.addSeries(categoryLabel, xValues, yValues);
        }
        new SwingWrapper(chart).displayChart();
    }

    /** Makes a plot showing overlaid individual normalized count for every word in WORDS
     * from STARTYEAR to ENDYEAR using NGM as a data source. */
    public static void plotAllWords(NGramMap ngm, String[] words, int startYear, int endYear) {
        Chart chart = new ChartBuilder()
                .width(800).height(600)
                .xAxisTitle("Year")
                .yAxisTitle("Normalized Count").build();
        for (String word : words) {
            TimeSeries<Double> ts = ngm.weightHistory(word, startYear, endYear);
            Collection<Number> xValues = ts.years();
            Collection<Number> yValues = ts.data();
            chart.addSeries(word, xValues, yValues);
        }
        new SwingWrapper(chart).displayChart();
    }

    /** Returns the numbers from max to 1, inclusive in decreasing order.
     * Private, so you don't have to implement if you don't want to. */
    private static Collection<Number> downRange(int max) {
        ArrayList<Number> ret = new ArrayList<>();
        for (int i = max; i > 0; --i) {
            ret.add(i);
        }
        return ret;
    }

    /** Plots the count (or weight) of every word against the rank of every word on a
     * log-log plot. Uses data from YEAR, using NGM as a data source. */
    public static void plotZipfsLaw(NGramMap ngm, int year) {
        YearlyRecord yr = ngm.getRecord(year);
        Collection<Number> yearCounts = yr.counts();
        Collection<Number> yearRank = downRange(yearCounts.size());

        Chart chart = new ChartBuilder()
                .width(800).height(600)
                .xAxisTitle("Rank").yAxisTitle("Count").build();
        chart.getStyleManager().setYAxisLogarithmic(true);
        chart.getStyleManager().setXAxisLogarithmic(true);
        chart.addSeries("Zipf's Law", yearRank, yearCounts);
        new SwingWrapper(chart).displayChart();
    }
}