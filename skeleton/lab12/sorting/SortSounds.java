/* SortSounds.java */

package sorting;


import util.StdAudio;
import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdDraw;
import java.awt.Color;
import java.util.List;
import java.util.Arrays;
import edu.princeton.cs.algs4.Queue;
import synthesizer.GuitarString;
import synthesizer.EnvelopedSineWave;
import synthesizer.SoundGenerator;

/**
 * "Audibilization" and visualization of various sort algorithms.
 * Inspired by <a href="http://panthema.net/2013/sound-of-sorting/">The Sounds of Sorting </a>
 * @author Daniel Nguyen
 */

class SortSounds {

    /**
     * elements is the number of elements to be sorted, ideally between 16 and 256.
     *          Any larger than 256 and visualization will not work
     * halfWidth is half of the width of a rectangle in the visualization
     * scaledHeight is the half of the height of the smallest rectangle in the visualization
     *               All other rectangles will be scaled accordingly based to the scaledHeight
     * step is the step between two frequencies in the audibilization
     * sort is the sort to be visualized
     * order is the order of the array
     * SORTS is an array of all sorts that can be visualized
     * ORDERS are the orders of the array to be sorted
     * HEIGHT is the height of the visualization window
     * WIDTH is the width of the visualization window
     * LOW_TONE is the lowest tone that will be played in the audibilization
     * HIGH_TONE is the highest tone that will be played in the audibilization
     */
    private int elements;
    private static int halfWidth;
    private static int scaledHeight;
    private static int step;
    private String sort = "all";
    private String order = "random";
    private static final String[] SORTS = {"all", "insertion", "selection",
    "shell", "heap", "merge", "quick"};
    private static final String[] ORDERS = {"random", "inorder", "reverse"};
    private static final int HEIGHT = 512;
    private static final int WIDTH = 1024;

    /** Lowest and highest frequency sounds that will be played. */
    private static final int LOW_TONE = 400;
    private static final int HIGH_TONE = 800;

    /** Number of samples to play for each play() operation. Program pauses
      * while samples are played. As you decrease this number, the animation
      * will run faster, but eventually you'll start getting ugly sound
      * artifacts. */
    private static final int SAMPLES_TO_PLAY = 1000;

    /** Number of samples before an instrument is killed. The number of
     * tones audible at once is MAX_SOUND_AGE / SAMPLES_TO_PLAY.  */
    private static final int MAX_SOUND_AGE = 1000;

    /** Default number of items in the array. */
    private static final int NUMBER_OF_ITEMS = 64;
    private static int[] toSort;

    /** Contains all currently active sounds. */
    private static Queue<SoundGenerator> soundQueue = new Queue<SoundGenerator>();

    /**
     * Default constructor. Intiallizes visualization with 32 item
     */
    public SortSounds() {
        this(NUMBER_OF_ITEMS);
    }

    /**
     * Constructor for SortSounds.
     * @param  N number of elements that will be sorted
     */
    public SortSounds(int N) {
        elements = N;
        halfWidth = WIDTH / (elements * 2);
        scaledHeight = HEIGHT /(elements * 2);
        step = (HIGH_TONE - LOW_TONE) / elements;

        toSort = new int[elements];
        for (int i = 0; i < elements; i++) {
            toSort[i] = i;
        }
    } 

    /**
     * Draws a rectangle for the visualization. Rectangle is based on the
     * element of the toSort array at index i
     * @param c Color of the rectangle
     * @param i index of the array that is being sorted
     */
    static void drawRectangle(Color c, int i) {
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.rectangle(halfWidth + halfWidth * 2 * i,
            toSort[i] * scaledHeight, halfWidth,
            scaledHeight * (toSort[i] + 1));
        StdDraw.setPenColor(c);
        StdDraw.filledRectangle(halfWidth + halfWidth * 2 * i, 
            toSort[i] * scaledHeight, halfWidth - 0.5, 
            scaledHeight * (toSort[i] + 1) - 0.5);
    }

    /**
     * Clears the rectangle on the visualization. Rectangle is based on the
     * element of the toSort array at index i
     * @param i index of the array that is being sorted
     */
    static void clearRectangle(int i) {
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.rectangle(halfWidth + halfWidth * 2 * i,
            toSort[i] * scaledHeight, halfWidth,
            scaledHeight * (toSort[i] + 1));
        StdDraw.filledRectangle(halfWidth + halfWidth * 2 * i,
            toSort[i] * scaledHeight, halfWidth + 0.5,
            scaledHeight * (toSort[i] + 1) + 0.5);
    }

    /**
     * Adds a new SoundGenerator to the sound queue with frequency given 
     * by the element in position i. Also plays SAMPLES_TO_PLAY of all
     * sounds in the sound queue.
     * @param i index of the array
     */
    static void play(int i) {

        double frequency = LOW_TONE + toSort[i] * step;

        SoundGenerator x = new EnvelopedSineWave(0.1, 
                              frequency, 
                              MAX_SOUND_AGE);

        soundQueue.enqueue(x);

        for (int j = 0; j < SAMPLES_TO_PLAY; j += 1) {
            double sample = 0;
            int numberToRemove = 0; 
            for (SoundGenerator gs : soundQueue) {
                if (gs.age() > MAX_SOUND_AGE) {
                    numberToRemove += 1;
                }
                sample += gs.sample();
                gs.tic();
            }

            StdAudio.play(sample);
            for (int k = 0; k < numberToRemove; k += 1) {
                soundQueue.dequeue();
            }
        }
    }

    /**
     * Initializes the visualization. Sets the order of the elements in the toSort array
     * Draws the intial rectangles and plays their sounds. Write the current sort on the
     * top of the screen.
     */
    private void initialize() {
        StdDraw.clear();

        switch (order) {
            case "random":
            StdRandom.shuffle(toSort);
            break;
            case "reverse":
            for (int i = 0; i < elements; i++) {
                toSort[i] = elements - i;
            }
            break;
            case "inOrder":
            break;
        }

        StdDraw.setPenColor(StdDraw.BLACK);
        if (!sort.equals("all")) {
            StdDraw.text(WIDTH/2, HEIGHT, sort);
        } else {
            StdDraw.text(WIDTH/2, HEIGHT, "insertion");
        }
        StdDraw.show(1000);

        for (int i = 0; i < elements; i++) {
            drawRectangle(StdDraw.CYAN, i);
            StdDraw.show(5); 
            play(i);
        }
    }

    /**
     * Finishes the visualization by drawing a rainbow of rectangles
     * and plays their sounds
     */
    private void finish() {
        for (int i = 0; i < elements; i++) {
            if (i % 4 == 3) {
                drawRectangle(StdDraw.BLUE, i); 
            } else if (i % 4 == 2) {
                drawRectangle(StdDraw.GREEN, i); 
            } else if (i % 4 == 1) {
                drawRectangle(StdDraw.YELLOW, i); 
            } else {
                drawRectangle(StdDraw.RED, i);
            }
            play(i);
            StdDraw.show(5);
        }
    }

    /**
     * Visualization and audiblization of sort algorithms
     * Runs the program
     */
    public static void main(String[] args) {
        String sort = null;
        SortSounds ss = null;
        if (args.length == 0) {
            ss = new SortSounds();
        } else if (args.length % 2 != 0) {
            System.out.println("Usage: java sorting/SortSounds [-n <number>] [-s <sortName>] [-o <order>]");
            System.exit(0);
        } else {
            List<String> commandArgs = Arrays.asList(args);
            int numberIndex = commandArgs.indexOf("-n");
            int sortIndex = commandArgs.indexOf("-s");
            int orderIndex = commandArgs.indexOf("-o");
            if (numberIndex != -1) {
                try {
                    ss = new SortSounds(Integer.valueOf(args[numberIndex + 1]));
                } catch (NumberFormatException e) {
                    System.out.println("Did not input a valid number");
                    System.exit(0);
                } 
            } else {
                ss = new SortSounds();
            }
            if (sortIndex != -1) {
                if (Arrays.asList(SORTS).contains(args[sortIndex + 1])) {
                    ss.sort = args[sortIndex + 1];
                } else {
                    System.out.println("Did not input a valid sort");
                    System.exit(0);
                }
            }
            if (orderIndex != -1) {
                if (Arrays.asList(ORDERS).contains(args[orderIndex + 1])) {
                    ss.order = args[orderIndex + 1];
                } else {
                    System.out.println("Did not input a valid order");
                    System.exit(0);
                }
            }
        }

        StdDraw.setCanvasSize(WIDTH, HEIGHT);
        StdDraw.setXscale(0, WIDTH);
        StdDraw.setYscale(0, HEIGHT);

        StdDraw.show(1);

        ss.initialize();

        switch (ss.sort) {
            case "insertion":
                Sort.insertionSort(ss.toSort);
                break;
            case "selection":
                Sort.selectionSort(ss.toSort);
                break;
            case "shell":
                Sort.shellsort(ss.toSort);
                break;
            case "heap":
                Sort.heapsort(ss.toSort);
                break;
            case "merge":
                Sort.mergeSort(ss.toSort);
                break;
            case "quick":
                Sort.quicksort(ss.toSort);
                break;
            /* Do all sorts. */
            default: 
                ss.sort = "insertion";
                Sort.insertionSort(ss.toSort);
                ss.finish();
                ss.sort = "selection";
                ss.initialize();
                Sort.selectionSort(ss.toSort);
                ss.finish();
                ss.sort = "shell";
                ss.initialize();
                Sort.shellsort(ss.toSort);
                ss.finish();
                ss.sort = "heap";
                ss.initialize();
                Sort.heapsort(ss.toSort);
                ss.finish();
                ss.sort = "merge";
                ss.initialize();
                Sort.mergeSort(ss.toSort);
                ss.finish();
                ss.sort = "quick";
                ss.initialize();
                Sort.quicksort(ss.toSort);
                break;
        }

        ss.finish();    
    }
    
}