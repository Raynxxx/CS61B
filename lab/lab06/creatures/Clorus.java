package creatures;
import huglife.Creature;
import huglife.Direction;
import huglife.Action;
import huglife.Occupant;
import huglife.HugLifeUtils;
import java.awt.Color;
import java.util.Map;
import java.util.List;

/** An implementation of Clorus Creature.
 *  @author Rayn
 */
public class Clorus extends Creature {
    /** red color. */
    private int r;
    /** green color. */
    private int g;
    /** blue color. */
    private int b;

    /** creates clorus with energy equal to E. */
    public Clorus(double e) {
        super("clorus");
        r = 34;
        g = 0;
        b = 231;
        energy = e;
    }

    /** creates a clorus with energy equal to 1. */
    public Clorus() {
        this(1);
    }

    /** Should return a color with red = 34, green = 0, blue = 231. */
    public Color color() {
        return color(r, g, b);
    }

    /** Should gain another Creature's energy. */
    public void attack(Creature c) {
        this.energy += c.energy();
    }

    /** Cloruses should lose 0.03 units of energy when moving.
     */
    public void move() {
        this.energy -= 0.03;
    }


    /** Cloruses lose 0.01 energy when staying. */
    public void stay() {
        this.energy -= 0.01;
    }

    /** Cloruses and their offspring each get 50% of the energy.
     */
    public Clorus replicate() {
        this.energy = this.energy / 2;
        return new Clorus(this.energy);
    }

    /** Cloruses take exactly the following actions based on NEIGHBORS:
     *  1. If no empty adjacent spaces, STAY.
     *  2. Otherwise, any plips seen, ATTACK one of them randomly.
     *  3. Otherwise, if energy >= 1, REPLICATE.
     *  4. Otherwise, MOVE
     */
    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        List<Direction> empties = getNeighborsOfType(neighbors, "empty");
        List<Direction> plips = getNeighborsOfType(neighbors, "plip");

        if (empties.size() == 0) {
            return new Action(Action.ActionType.STAY);
        } else if (plips.size() > 0) {
            Direction dir = HugLifeUtils.randomEntry(plips);
            return new Action(Action.ActionType.ATTACK, dir);
        } else if (this.energy >= 1) {
            Direction dir = HugLifeUtils.randomEntry(empties);
            return new Action(Action.ActionType.REPLICATE, dir);
        }
        Direction dir = HugLifeUtils.randomEntry(empties);
        return new Action(Action.ActionType.MOVE, dir);
    }
}
