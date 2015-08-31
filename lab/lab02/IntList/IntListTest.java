import static org.junit.Assert.*;
import org.junit.Test;

public class IntListTest {

    /** Example test that verifies correctness of the IntList.list static 
     *  method. The main point of this is to convince you that 
     *  assertEquals knows how to handle IntLists just fine.
     */

    @Test 
    public void testList() {
        IntList one = new IntList(1, null);
        IntList twoOne = new IntList(2, one);
        IntList threeTwoOne = new IntList(3, twoOne);

        IntList x = IntList.list(3, 2, 1);
        assertEquals(threeTwoOne, x);
    }

    @Test
    public void testdSquareList() {
      IntList L = IntList.list(1, 2, 3);
      IntList.dSquareList(L);
      assertEquals(IntList.list(1, 4, 9), L);
    }

    /** Do not use the new keyword in your tests. You can create
     *  lists using the handy IntList.list method.  
     * 
     *  Make sure to include test cases involving lists of various sizes
     *  on both sides of the operation. That includes the empty list, which
     *  can be instantiated, for example, with 
     *  IntList empty = IntList.list(). 
     *
     *  Keep in mind that dcatenate(A, B) is NOT required to leave A untouched.
     *  Anything can happen to A. 
     */

    //TODO:  Create testSquareListRecursive()
    @Test
    public void testSquareListRecursive() {
        IntList list1 = IntList.list(1, 2, 3);
        IntList list2 = IntList.squareListRecursive(list1);
        assertEquals(IntList.list(1, 2, 3), list1);
        assertEquals(IntList.list(1, 4, 9), list2);
    }
    //TODO:  Create testDcatenate and testCatenate
    @Test
    public void testDcatenate() {
        IntList A = IntList.list(1, 2, 3);
        IntList B = IntList.list(4, 5);
        IntList C = IntList.dcatenate(A, B);
        assertEquals(IntList.list(1, 2, 3, 4, 5), C);
        assertEquals(C, A);
    }
    
    @Test
    public void testCatenate() {
        IntList A = IntList.list(1, 2, 3);
        IntList B = IntList.list(4, 5);
        IntList C = IntList.catenate(A, B);
        IntList empty = IntList.list();
        assertEquals(IntList.list(1, 2, 3, 4, 5), C);
        assertEquals(IntList.list(1, 2, 3), A);
        assertEquals(IntList.list(1, 2, 3), IntList.catenate(A, empty));
        assertEquals(IntList.list(4, 5), IntList.catenate(empty, B));
        assertEquals(IntList.list(), IntList.catenate(empty, empty));
    }

    /* Run the unit tests in this file. */
    public static void main(String... args) {
        jh61b.junit.textui.runClasses(IntListTest.class);
    }       
}   
