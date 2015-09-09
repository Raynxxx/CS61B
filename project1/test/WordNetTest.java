package test;

import ngordnet.WordNet;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * WordNetTest
 * Created by rayn on 2015-9-9.
 */
public class WordNetTest {

    @Test
    public void testBasic() {
        WordNet wn = new WordNet("p1data/wordnet/synsets11.txt",
                "p1data/wordnet/hyponyms11.txt");
        assertTrue(wn.isNoun("jump"));
        assertTrue(wn.isNoun("leap"));
        assertTrue(wn.isNoun("nasal_decongestant"));
        assertTrue(wn.hyponyms("increase").contains("augmentation"));
        assertTrue(wn.hyponyms("jump").contains("parachuting"));
    }

    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(WordNetTest.class);
    }
}
