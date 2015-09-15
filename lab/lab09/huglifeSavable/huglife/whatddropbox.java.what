package huglife;
import creatures.*;
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

    /** Size of the world. Probably best to keep this under 100 
      *  or so.
     */
    public static final int WORLD_SIZE = 15;

    /** Maximum number of cycles to simulate by default. */
    public static final int MAX_CYCLES = 1000;

    /** Time in milliseconds between simulation steps. 
     *  Reduce to make things run faster.
     */
    public static final int PAUSE_TIME_PER_SIMSTEP = 100;

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
    public void simulate(int cycles) {
        int cycleCount = 0;
        while (cycleCount < cycles) {
            boolean cycleCompleted = g.tic();
            if (cycleCompleted) {
                g.drawWorld();
                StdDraw.show(PAUSE_TIME_PER_SIMSTEP);
                cycleCount += 1;                
            }
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                if      (key == 's') { 

                    writeWorldStateFile("savedStates/" + "ketchup" + ".ser"); 
                }
            }            
        }
    }

    /** Simulates the world for TICS tics, simulating
     *   TICSBETWEENDRAW in between world drawing events.
     */
    public void simulate(int tics, int ticsBetweenDraw) {
        for (int i = 0; i < tics; i++) {
            g.tic();
            if ((i % ticsBetweenDraw) == 0) {
                g.drawWorld();
                StdDraw.show(PAUSE_TIME_PER_SIMSTEP);
            }
        }
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
    public static HugLife readWorld(String worldName) {
        String worldFilename = getWorldFilename(worldName);
        if (worldFilename == null) {
            throw new IllegalArgumentException("No world file found!");
        }

        if (worldFilename.endsWith(".world")) {
            return readWorldFile(worldFilename);
        } else if (worldFilename.endsWith(".ser")) {
            return readWorldStateFile(worldFilename);
        } else {
            throw new IllegalArgumentException("Incorrect name for world file. Must end in .world or .ser.");
        }
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
        HugLife h = new HugLife(WORLD_SIZE);
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

    /** Runs world name specified by ARGS[0]. */
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java huglife.HugLife [worldname]");
            System.out.println("World may be specified as a .world file or a .ser file.");
            return;
        }
        HugLife h = readWorld(args[0]);
        // HugLife h = new HugLife(WORLD_SIZE);
        // h.initialize(args[0]); DON'T USE ME
        if (SIMULATE_BY_CYCLE) {
            h.simulate(MAX_CYCLES);
        } else {
            h.simulate(MAX_TICS, TICS_BETWEEN_DRAW);
        }
    }

    /** Grid for holding all the creatures. */
    private Grid g;


    /** By default, the simulator simulates by cycle, i.e.
     *  allows every creature to move before drawing. 
     *  If you set this to false, then the world will be drawn
     *  between moves (much slower).
     */
    public static final boolean SIMULATE_BY_CYCLE = true;

    /** Maximum number of tics to simulate by default if using. */
    public static final int MAX_TICS = 100000;
    /** Number of tics to simulate between draw ops. */
    public static final int TICS_BETWEEN_DRAW = 10;


    private static final String[] words = {"sausage", "blubber", "pencil", "cloud", "moon", "water", "computer", "school", "network", "hammer", "walking", "violently", "mediocre", "literature", "chair", "two", "window", "cords", "musical", "zebra", "xylophone", "penguin", "home", "dog", "final", "ink", "teacher", "fun", "website", "banana", "uncle", "softly", "mega", "ten", "awesome", "attatch", "blue", "internet", "bottle", "tight", "zone", "tomato", "prison", "hydro", "cleaning", "telivision", "send", "frog", "cup", "book", "zooming", "falling", "evily", "gamer", "lid", "juice", "moniter", "captain", "bonding", "loudly", "thudding", "guitar", "shaving", "hair", "soccer", "water", "racket", "table", "late", "media", "desktop", "flipper", "club", "flying", "smooth", "monster", "purple", "guardian", "bold", "hyperlink", "presentation", "world", "national", "comment", "element", "magic", "lion", "sand", "crust", "toast", "jam", "hunter", "forest", "foraging", "silently", "tawesomated", "joshing", "pong"};
    
}
