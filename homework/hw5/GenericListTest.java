import org.junit.Test;
import static org.junit.Assert.*;

public class GenericListTest {

    @Test
    public void testInsertAndGet() {
        GenericList<Integer> a = new GenericList<Integer>();
        a.insert(10);
        assertEquals(10, (int) a.get(0));
        a.insert(29);
        assertEquals(29, (int) a.get(0));
        assertEquals(10, (int) a.get(1));
    }

    @Test
    public void testLength() {
        GenericList<String> b = new GenericList<String>();
        assertEquals(0, b.length());
        b.insert("a");
        b.insert("Bb");
        assertEquals(2, b.length());
    }

    @Test
    public void testToString() {
        GenericList<Integer> a = new GenericList<Integer>();
        assertEquals("[]", a.toString());
        a.insert(9);
        assertEquals("[9]", a.toString());
        a.insert(5);
        a.insert(7);
        assertEquals("[7, 5, 9]", a.toString());
    }

    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(GenericListTest.class);
    }

}
