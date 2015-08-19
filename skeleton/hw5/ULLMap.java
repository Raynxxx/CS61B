import java.util.Set; /* java.util.Set needed only for challenge problem. */

/** A data structure that uses a linked list to store pairs of keys and values.
 *  Any key must appear at most once in the dictionary, but values may appear multiple
 *  times. Supports get(key), put(key, value), and contains(key) methods. The value
 *  associated to a key is the value in the last call to put with that key. 
 *
 *  For simplicity, you may assume that nobody ever inserts a null key or value
 *  into your map.
 */ 
public class ULLMap { //FIX ME
    /** Keys and values are stored in a linked list of Entry objects.
      * This variable stores the first pair in this linked list. You may
      * point this at a sentinel node, or use it as a the actual front item
      * of the linked list. 
      */
    private Entry front;

    @Override
    public get(key) { //FIX ME
    //FILL ME IN
        return null; //FIX ME
    }

    @Override
    public void put(key, val) { //FIX ME
    //FILL ME IN
    }

    @Override
    public boolean containsKey(key) { //FIX ME
    //FILL ME IN
        return false; //FIX ME
    }

    @Override
    public int size() {
        return 0; // FIX ME (you can add extra instance variables if you want)
    }

    @Override
    public void clear() {
    //FILL ME IN
    }


    /** Represents one node in the linked list that stores the key-value pairs
     *  in the dictionary. */
    private class Entry {
    
        /** Stores KEY as the key in this key-value pair, VAL as the value, and
         *  NEXT as the next node in the linked list. */
        public Entry(k, v, Entry n) { //FIX ME
            key = k;
            val = v;
            next = n;
        }

        /** Returns the Entry in this linked list of key-value pairs whose key
         *  is equal to KEY, or null if no such Entry exists. */
        public Entry get(k) { //FIX ME
            //FILL ME IN (using equals, not ==)
            return null; //FIX ME
        }

        /** Stores the key of the key-value pair of this node in the list. */
        private key; //FIX ME
        /** Stores the value of the key-value pair of this node in the list. */
        private val; //FIX ME
        /** Stores the next Entry in the linked list. */
        private Entry next;
    
    }

    /* Methods below are all challenge problems. Will not be graded in any way. 
     * Autograder will not test these. */
    @Override
    public remove(key) { //FIX ME SO I COMPILE
        throw new UnsupportedOperationException();
    }

    @Override
    public remove(key, value) { //FIX ME SO I COMPILE
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<> keySet() { //FIX ME SO I COMPILE
        throw new UnsupportedOperationException();
    }


}