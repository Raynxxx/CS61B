import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Test;
// import jh61b.grader.Test61B;

// VERSION 2
/* This file is identical to the Autograder except that it commented out 
 * a few things that were causing errors on students' computers. */

public class CardAutoGrader /*extends jh61b.grader.Autograder*/ {

    // @Test61B(name = "Card Perfect Hash Check")
    public static void testHashCodes() {
        // First generate all of the possible cards in order
        ArrayList<Card> c1 = makeFullHand();
        ArrayList<Card> c2 = makeFullHand();
        // Proceed to check every single possible pair
        for (int i = 0; i < 52; i++) {
            Card card1 = c1.get(i);
            for (int j = 0; j < 52; j++) {
                Card card2 = c2.get(j);
                boolean isEqualHashCode = card1.hashCode() == card2.hashCode();
                if (i == j) {
                    String msg = "hashCode not valid!";
                    assertTrue(msg, isEqualHashCode); 
                    assertEquals("Cards incorrectly unequal!", card1, card2);
                    // This passes for valid hash codes.
                } else {
                    String msg = "hashCode not perfect!";
                    assertFalse(msg, isEqualHashCode); 
                    assertNotEquals("Cards incorrectly equal!", card1, card2);
                    // This passes for perfect hash codes.
                }
            }
        }
    }

    private static ArrayList<Card> makeFullHand() {
        ArrayList<Card> toRtn = new ArrayList<Card>(52);
        for (int suit = 1; suit <= 4; suit++) {
            for (int num = 1; num <= 13; num++) {
                Card curr = new Card(suit, num);
                toRtn.add(curr);
            }
        }
        return toRtn;
    }

    public static void main(String[] args) {
        // runStaticTests(CardAutoGrader.class);
        testHashCodes();
        System.out.println("All tests passed!");
    }

}
