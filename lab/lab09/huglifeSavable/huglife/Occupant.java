package huglife;
import java.awt.Color;
import java.io.Serializable;

/**
 *  @author Josh Hug
 *  Represents possible occupants of the grid world.
 *  Intended for extension by:
 *     Creature, Empty, and Impassible only.
 */
public abstract class Occupant implements Serializable {
    /** Name for this type of Occupant. */
    protected final String name;

    /** Creates an Occupant with name equal to N. */
    public Occupant(String n) {
        name = n;
    }

    /** Serializable test. */
    public Occupant() {
        name = null;
    }

    /** Returns the name of this occupant. */
    public String name() {
        return name;
    }

    /** Returns a Color object given R, G, and B values.
     *  Intended for use by subtypes so they don't have to import
     *  or think about colors.
     */
    protected static Color color(int r, int g, int b) {
        return new Color(r, g, b);
    }

    /** Required method that returns a color. */
    public abstract Color color();

    private static final long serialVersionUID = 9283917823981723L;

}
