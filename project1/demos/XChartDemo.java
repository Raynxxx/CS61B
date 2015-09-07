/** Demo of using the XChart class. Will compile 
 *  and run straight out of the box. 
 *  @author Josh Hug
 */

import java.util.ArrayList;
import com.xeiam.xchart.Chart;
import com.xeiam.xchart.QuickChart;
import com.xeiam.xchart.SwingWrapper;
import com.xeiam.xchart.StyleManager.ChartTheme;
import com.xeiam.xchart.ChartBuilder;

public class XChartDemo {

    /** Creates a plot of y = x^2 vs. x */
    private static void plotOne() {
        ArrayList<Number> xValues = new ArrayList<Number>();
        ArrayList<Number> yValues = new ArrayList<Number>();

        for (int i = 1; i < 30; i += 1) {
            xValues.add(i);
            yValues.add(i*i);
        }        
    
        // Create Chart
        String title = "x^2 vs. x";
        String ylabel = "y";
        String xlabel = "x";
        String legend = "x^2";
        Chart chart = QuickChart.getChart(title, ylabel, xlabel, legend, xValues, yValues);
     
        // Show it
        new SwingWrapper(chart).displayChart();        
    }

    /** Creates a plot of x^2 vs. x on a loglog plot. */
    private static void loglogPlot() {
        ArrayList<Number> xValues = new ArrayList<Number>();
        ArrayList<Number> yValues = new ArrayList<Number>();

        for (int i = 1; i < 30; i += 1) {
            xValues.add(i);
            yValues.add(i*i);
        }        

        // Create Chart
        String title = "x^2 vs. x";
        String ylabel = "y (log)";
        String xlabel = "x (log)";
        String legend = "x^2";
        Chart chart = new ChartBuilder().width(800).height(600).xAxisTitle(ylabel).yAxisTitle(xlabel).build();
        chart.getStyleManager().setYAxisLogarithmic(true);
        chart.getStyleManager().setXAxisLogarithmic(true);
        chart.addSeries(legend, xValues, yValues);

        // Show it
        new SwingWrapper(chart).displayChart();
    }

    /** Creates overlaid plots of x^2 and x^3 vs. x */
    private static void plotTwo() {
        ArrayList<Number> xValues = new ArrayList<Number>();
        ArrayList<Number> yValues1 = new ArrayList<Number>();
        ArrayList<Number> yValues2 = new ArrayList<Number>();

        for (int i = 1; i < 30; i += 1) {
            xValues.add(i);
            yValues1.add(i*i);
            yValues2.add(i*i*i / 20);
        } 

        // Create Chart
        String title = "x^2 vs. x";
        String ylabel = "y (log)";
        String xlabel = "x (log)";
        String legend1 = "x^2";
        String legend2 = "x^3 / 20";

        Chart chart = new ChartBuilder().width(800).height(600).xAxisTitle(ylabel).yAxisTitle(xlabel).build();
        chart.addSeries(legend1, xValues, yValues1);
        chart.addSeries(legend2, xValues, yValues2);

        // Show it
        new SwingWrapper(chart).displayChart();
    }

    public static void main(String[] args) {
        plotOne();
        loglogPlot();
        plotTwo();
    }
} 