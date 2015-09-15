package huglife;
import creatures.*;
import edu.princeton.cs.introcs.StdRandom;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.FileNotFoundException;

/** World facing class for HugLife simulator.
 *  @author Josh Hug
 */
public class HugLife {
    /** Maximum number of cycles to simulate by default. */
    public static final int MAX_CYCLES = 1000;

    /** Creates a new world grid of size N for this HugLife simulation. */
    public HugLife(int N) {
        g = new Grid(N);
    }

    /** Adds a creature C to the HugLife universe at X, Y. */
    public void addCreature(int x, int y, Creature c) {
        g.createCreature(x, y, c);
    }

    /** Simulates the world for CYCLES cycles, simulation
     *  one entire cycle between 
     */
    public void simulate() {
        int cycleCount = 0;
        while(true) {
            boolean cycleCompleted = g.tic();
            if (cycleCompleted) {
                g.drawWorld();
                StdDraw.show(pauseTimePerSimstep);
                writeWorldStateFile("savedStates/quicksave.ser");
                cycleCount += 1;                
            }
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                if (key == 's') { 
                    String s = generateRandomFilename();
                    writeWorldStateFile("savedStates/" + s + ".ser"); 
                    StdDraw.save("savedStates/" + s + ".png");
                    System.out.println("Saving: " + s);
                }
                if (key == '+') { 
                    /* should actually be -= 1, since + should speed up the simulation */
                    pauseTimePerSimstep = Math.max(0, pauseTimePerSimstep - 5);
                    Out out = new Out(CONFIG_FILENAME);
                    out.print(pauseTimePerSimstep);
                    out.close();
                }                
                if (key == '-') { 
                    pauseTimePerSimstep += 5;
                    Out out = new Out(CONFIG_FILENAME);
                    out.print(pauseTimePerSimstep);
                    out.close();
                }
                if (key == 'q') {
                    break;
                }
            }          
        }  
    }

    /** Generates a random filename. */
    private String generateRandomFilename() {
        int N = filenameWords.length;
        String w1 = filenameWords[StdRandom.uniform(0, N)];
        String w2 = filenameWords[StdRandom.uniform(0, N)];
        w2 = w2.substring(0, 1).toUpperCase() + w2.substring(1);
        return w1 + w2;
    }

    /** A set of precanned hard-coded worlds. This is terrible
     *  style, but hey it's very easy to write this way.
     */
    public void initialize(String worldName) {
        if (worldName.equals("samplesolo")) {
            addCreature(11, 1, new SampleCreature());
        }

        else if (worldName.equals("sampleplip")) {
            addCreature(11, 1, new SampleCreature());
            addCreature(12, 12, new Plip());
            addCreature(4, 3, new Plip());
        }

        else if (worldName.equals("strugggz")) {
            System.out.println("You need to uncomment the strugggz test!");
            /*addCreature(11, 1, new SampleCreature());
            addCreature(12, 12, new Plip());
            addCreature(3, 3, new Plip());
            addCreature(4, 3, new Plip());

            addCreature(2, 2, new Clorus(1));*/
        } else {
            System.out.println("World name not recognized!");
        }
    }

    /**
     * Determines whether to use a .world or a .ser file. Scans
     * ./huglife directory for a .world file, and if none is found,
     * checks the ./savedStates directory for a .ser file.
     * @param  worldName 
     * @return filename of world
     */
    private static String getWorldFilename(String worldName) {
        String putativeWorldFile = "huglife/" + worldName + ".world";
        boolean worldFileExists = new File(putativeWorldFile).exists();
        if (worldFileExists) {
            return putativeWorldFile;
        }

        String putativeStateFile = "savedStates/" + worldName + ".ser";
        boolean stateFileExists = new File(putativeStateFile).exists();
        if (stateFileExists) {
            return putativeStateFile;
        }

        return null;
    }

    /**
     * Reads the world from a file with name as specified and initializes
     * a HugLife with the contents of the file.
     * @param  worldName name of the file to read from
     * @return a newly initialized HugLife
     */
    public static HugLife readWorld(String worldName, String configFile) {
        String worldFilename = getWorldFilename(worldName);
        if (worldFilename == null) {
            throw new IllegalArgumentException("No world file found!");
        }
        HugLife h;

        if (worldFilename.endsWith(".world")) {
            h = readWorldFile(worldFilename);
        } else if (worldFilename.endsWith(".ser")) {
            h = readWorldStateFile(worldFilename);
        } else {
            throw new IllegalArgumentException("Incorrect name for world file. Must end in .world or .ser.");
        }

        if (fileExists(CONFIG_FILENAME)) {
            In in = new In(CONFIG_FILENAME);
            h.pauseTimePerSimstep = in.readInt();
            in.close();
        } else {
            h.pauseTimePerSimstep = DEFAULT_PAUSE;
        }

        return h;
    }


    /**
     * Reads a .world file
     * @param  wordFilename to read from
     * @return a newly initialized HugLife
     */
    private static HugLife readWorldFile(String worldFilename) {
        if (!worldFilename.endsWith(".world")) {
            throw new IllegalArgumentException("worldFilename must end with .world.");
        }

        In in = new In(worldFilename);
        int worldSize = in.readInt();

        HugLife h = new HugLife(worldSize);
        while (!in.isEmpty()) {
            String creature = in.readString();
            int x = in.readInt();
            int y = in.readInt();
            switch (creature) {
                //Uncomment this when you're ready to test out your clorus class
                case "clorus":
                    h.addCreature(x, y, new Clorus(1));
                    break;
                case "plip":
                    h.addCreature(x, y, new Plip());
                    break;
                case "samplecreature":
                    h.addCreature(x, y, new SampleCreature());
                    break;
            }
        }

        return h;        
    }

    /**
     * Reads a .ser file containing a world state.
     * @param  wordFilename to read from
     * @return a newly initialized HugLife
     */
    private static HugLife readWorldStateFile(String worldStateFilename) {
        if (!worldStateFilename.endsWith(".ser")) {
            throw new IllegalArgumentException("worldStateFilename must end with .ser.");
        }

        try {
            FileInputStream fin = new FileInputStream(worldStateFilename);
            ObjectInputStream ois = new ObjectInputStream(fin);
            Object historyObject = ois.readObject();
            ois.close();
            Grid g = (Grid) historyObject;
            HugLife h = new HugLife(g.size());
            h.g = g;     
            return h;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Could not read world!");
            System.out.println(e);
            System.exit(1);
        }

        return null;
    }

    /**
     * Write a .ser file containing a world state.
     * @param  wordFilename to write to
     */
    private void writeWorldStateFile(String worldStateFilename) {
        if (!worldStateFilename.endsWith(".ser")) {
            throw new IllegalArgumentException("worldStateFilename must end with .ser.");
        }

        try {
            File stateDir = new File("savedStates");
            if (!stateDir.exists()) {
                stateDir.mkdir();
            }

            FileOutputStream fout = new FileOutputStream(worldStateFilename);
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeObject(g);
            oos.close();
        } catch (IOException e) {
            System.out.println("Could not write world state: " + e);
        }
    }

    /** Returns true if quickSave file exists. 
     *  @return existence of quickSave file. 
     */
    private static boolean fileExists(String filename) {
        boolean fileExists = new File(filename).exists();        
        return fileExists;
    }

    /** Runs world name specified by ARGS[0]. */
    public static void main(String[] args) {
        if (args.length != 1 && !fileExists("savedStates/quicksave.ser")) {
            System.out.println("Usage: java huglife.HugLife [worldname]");
            System.out.println("World may be specified as a .world file or a .ser file.");
            System.out.println("[worldname] not necessary if a ./savedStates/quickSave.ser exists.");
            return;
        }

        String worldFile;
        if (args.length > 0) {
            worldFile = args[0];
        } else {
            worldFile = "quickSave";
        }

        HugLife h = readWorld(worldFile, CONFIG_FILENAME);

        StdDraw.show(10);
        h.simulate();
    }

    /** Grid for holding all the creatures. */
    private Grid g;

    /** Number of tics to simulate between draw ops. */
    public static final int TICS_BETWEEN_DRAW = 10;


    /** Time in milliseconds between simulation steps. */
    private int pauseTimePerSimstep;

    /** Default pause between sim steps. Can be adjusted with + or -. */
    private static final int DEFAULT_PAUSE = 50;

    private static final String CONFIG_FILENAME = "preferences.cfg";

    private static final String[] filenameWords = {"sausage", "blubber", "pencil", "cloud", "moon", "water", "computer", "school", "network", "hammer", "walking", "violently", "mediocre", "literature", "chair", "two", "window", "cords", "musical", "zebra", "xylophone", "penguin", "home", "dog", "final", "ink", "teacher", "fun", "website", "banana", "uncle", "softly", "mega", "ten", "awesome", "attatch", "blue", "internet", "bottle", "tight", "zone", "tomato", "prison", "hydro", "cleaning", "telivision", "send", "frog", "cup", "book", "zooming", "falling", "evily", "gamer", "lid", "juice", "moniter", "captain", "bonding", "loudly", "thudding", "guitar", "shaving", "hair", "soccer", "water", "racket", "table", "late", "media", "desktop", "flipper", "club", "flying", "smooth", "monster", "purple", "guardian", "bold", "hyperlink", "presentation", "world", "national", "comment", "element", "magic", "lion", "sand", "crust", "toast", "jam", "hunter", "forest", "foraging", "silently", "tawesomated", "joshing", "pong"};

}
