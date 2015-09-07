import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Iterator;

/** ULLMapTest. You should write additional tests.
 *  @author Josh Hug
 */

public class ULLMapTest {
    @Test
    public void testBasic() {
        ULLMap<String, String> um = new ULLMap<String, String>();
        um.put("Gracias", "Dios Basado");
        assertEquals("Dios Basado", um.get("Gracias"));
        assertTrue(um.containsKey("Gracias"));
        assertFalse(um.containsKey("Gra"));
        assertEquals(1, um.size());
        assertEquals("Dios Basado", um.remove("Gracias"));
        assertEquals(0, um.size());
        
        um.put("one", "Josh Hug");
        um.put("two", "Rayn");
        assertEquals(null, um.remove("one", "Rayn"));
        assertEquals("Rayn", um.remove("two", "Rayn"));
    }

    
    @Test
    public void testIterator() {
        ULLMap<Integer, String> um = new ULLMap<Integer, String>();
        um.put(0, "zero");
        um.put(1, "one");
        um.put(2, "two");
        Iterator<Integer> umi = um.iterator();
        int key = umi.next();
        assertEquals(2, key);
        assertEquals("two", um.get(key));
    }
    

    /** Runs tests. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(ULLMapTest.class);
    }
} 
