import java.util.LinkedList;
import java.util.ArrayList;

public class Sorts {

    public static void main(String[] args) {
        if (args.length > 1) {
            try {
                ArrayList<Double> vals = Asymptotics2.randomVals(Integer.parseInt(args[1]));
                Stopwatch timer = new Stopwatch();
                switch (args[0]) {
                case "sort1": insertionSort(vals);
                    break;
                case "sort2": insertionSort2(vals);
                    break;
                }
                System.out.println(timer.elapsedTime() + " seconds elapsed");
            } catch (NumberFormatException e) {
                return;
            }
        }
    }

    public static ArrayList<Double> insertionSort(ArrayList<Double> vals) {
        for (int i = 1; i < vals.size(); i += 1) {
            double temp = vals.get(i);
            int j;
            for (j = i - 1; j >= 0 && vals.get(j) > temp; j -= 1) {
                vals.set(j + 1, vals.get(j));
            }
            vals.set(j + 1, temp);
        }
        return vals;
    }

    public static LinkedList<Double> insertionSort2(ArrayList<Double> vals) {
        LinkedList<Double> valsList = new LinkedList<Double>(vals);
        for (int i = 1; i < vals.size(); i += 1) {
            double temp = valsList.get(i);
            int j;
            for (j = i - 1; j >= 0 && valsList.get(j) > temp; j -= 1) {
                valsList.set(j + 1, valsList.get(j));
            }
            valsList.set(j + 1, temp);
        }
        return valsList;
    }

}