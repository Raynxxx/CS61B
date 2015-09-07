import ngordnet.GraphHelper;
import edu.princeton.cs.algs4.Digraph;
import java.util.Set;
import java.util.TreeSet;

/* Demonstration class showing the basic functionality of the Digraph and
 * GraphHelper classes. */
public class GraphDemo {
    public static void main(String[] args) {
        Digraph g = new Digraph(14);
        g.addEdge(0, 1);
        g.addEdge(1, 2);
        g.addEdge(2, 13);
        g.addEdge(3, 4);
        g.addEdge(5, 6);
        g.addEdge(5, 7);
        g.addEdge(6, 11);
        g.addEdge(7, 12);
        g.addEdge(7, 13);
        g.addEdge(6, 13);
        g.addEdge(8, 10);
        g.addEdge(9, 10);
        System.out.println(g);

        Set<Integer> five = new TreeSet<Integer>();
        five.add(5);

        System.out.println("Descendants of 5: ");
        System.out.println(GraphHelper.descendants(g, five));

        Set<Integer> zeroAndOne = new TreeSet<Integer>();
        zeroAndOne.add(0);
        zeroAndOne.add(1);

        System.out.println("\nDescendants of 0 and 1: ");
        System.out.println(GraphHelper.descendants(g, zeroAndOne));

        Set<Integer> ten = new TreeSet<Integer>();
        ten.add(10);
        System.out.println("\nDescendants of 10: ");        
        System.out.println(GraphHelper.descendants(g, ten));
    }
}
