package ngordnet;

import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.In;

/** Provides a simple user interface for exploring WordNet and NGram data.
 *  @author Rayn
 */
public class NgordnetUI {
    public static void main(String[] args) {
        In in = new In("ngordnet/ngordnetui.config");
        System.out.println("Reading ngordnetui.config...");

        String wordFile = in.readString();
        String countFile = in.readString();
        String synsetFile = in.readString();
        String hyponymFile = in.readString();
        System.out.println("\nBased on ngordnetui.config, using the following: \n"
                           + wordFile + "\n" + countFile + "\n" + synsetFile +
                           "\n" + hyponymFile);

        NGramMap nGramMap = new NGramMap(wordFile, countFile);
        WordNet wordNet = new WordNet(synsetFile, hyponymFile);
        YearlyRecordProcessor yrp = new WordLengthProcessor();

        int startDate = 0, endDate = Integer.MAX_VALUE;
        while (true) {
            System.out.print("> ");
            String line = StdIn.readLine();
            String[] rawTokens = line.split(" ");
            String command = rawTokens[0];
            String[] tokens = new String[rawTokens.length - 1];
            System.arraycopy(rawTokens, 1, tokens, 0, rawTokens.length - 1);
            switch (command) {
                case "quit":
                    return;
                case "help":
                    String helpStr = new In("./ngordnet/help.txt").readAll();
                    System.out.println(helpStr);
                    break;
                case "range":
                    startDate = Integer.parseInt(tokens[0]);
                    endDate = Integer.parseInt(tokens[1]);
                    System.out.println("Start date: " + startDate);
                    System.out.println("End date: " + endDate);
                    break;
                case "count":
                    String word = tokens[0];
                    int year = Integer.parseInt(tokens[1]);
                    System.out.println(nGramMap.countInYear(word, year) + "\n");
                    break;
                case "hyponyms":
                    System.out.println(wordNet.hyponyms(tokens[0]));
                    break;
                case "history":
                    Plotter.plotAllWords(nGramMap, tokens, startDate, endDate);
                    break;
                case "hypohist":
                    Plotter.plotCategoryWeights(nGramMap, wordNet, tokens, startDate, endDate);
                    break;
                case "wordlength":
                    Plotter.plotProcessedHistory(nGramMap, startDate, endDate, yrp);
                    break;
                case "zipf":
                    Plotter.plotZipfsLaw(nGramMap, Integer.parseInt(tokens[0]));
                    break;
                default:
                    System.out.println("Invalid command.");
                    break;
            }
        }

    }
} 
