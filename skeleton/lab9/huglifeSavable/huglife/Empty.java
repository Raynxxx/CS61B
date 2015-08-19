package huglife;
import java.awt.Color;
import java.io.Serializable;

public class Empty extends Occupant implements Serializable {
    public Empty() {
        super("empty");
    }

    /** Returns hardcoded black */
    public Color color() {
        return color(255, 255, 255);
    }    

    private static final long serialVersionUID = 198718237411118700L;    
}