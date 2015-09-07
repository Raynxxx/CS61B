import java.util.Iterator;
import java.util.Set; /* java.util.Set needed only for challenge problem. */
import java.util.HashSet;

/** A data structure that uses a linked list to store pairs of keys and values.
 *  Any key must appear at most once in the dictionary, but values may appear multiple
 *  times. Supports get(key), put(key, value), and contains(key) methods. The value
 *  associated to a key is the value in the last call to put with that key. 
 *
 *  For simplicity, you may assume that nobody ever inserts a null key or value
 *  into your map.
 */ 
public class ULLMap<K, V> implements Map61B<K,V>, Iterable<K> {
    /** Keys and values are stored in a linked list of Entry objects.
      * This variable stores the first pair in this linked list. You may
      * point this at a sentinel node, or use it as a the actual front item
      * of the linked list. 
      */
    private Entry front;
    private int size = 0;

    @Override
    public V get(K key) {
        if (front == null) {
            return null;
        }
        return front.get(key).val;
    }

    @Override
    public void put(K key, V val) {
        if (!containsKey(key)) {
            front = new Entry(key, val, front);
            size++;
        }
    }

    @Override
    public boolean containsKey(K key) {
        if (front == null) {
            return false;
        }
        return front.get(key) != null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        front = null;
        size = 0;
    }

    public static <K, V> ULLMap<V, K> invert(ULLMap<K, V> origin) {
        ULLMap<V, K> inverted = new ULLMap<V, K>();
        for (K key : origin) {
            inverted.put(origin.get(key), key);
        }
        return inverted;
    }

    @Override
    public Iterator<K> iterator() {
        return new ULLMapIterator(front);
    }

    private class ULLMapIterator implements Iterator<K> {

        private Entry cur;

        public ULLMapIterator(Entry e) {
            cur = e;
        }

        @Override
        public boolean hasNext() {
            return cur != null;
        }

        @Override
        public K next() {
            K ret = cur.key;
            cur = cur.next;
            return ret;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }


    /** Represents one node in the linked list that stores the key-value pairs
     *  in the dictionary. */
    private class Entry {
    
        /** Stores KEY as the key in this key-value pair, VAL as the value, and
         *  NEXT as the next node in the linked list. */
        public Entry(K k, V v, Entry n) { //FIX ME
            key = k;
            val = v;
            next = n;
        }

        /** Returns the Entry in this linked list of key-value pairs whose key
         *  is equal to KEY, or null if no such Entry exists. */
        public Entry get(K k) {
            Entry cur = this;
            while (cur != null) {
                if (cur.key.equals(k)) {
                    return cur;
                }
                cur = cur.next;
            }
            return null;
        }

        /** Stores the key of the key-value pair of this node in the list. */
        private K key; //FIX ME
        /** Stores the value of the key-value pair of this node in the list. */
        private V val; //FIX ME
        /** Stores the next Entry in the linked list. */
        private Entry next;
    
    }

    /* Methods below are all challenge problems. Will not be graded in any way. 
     * Autograder will not test these. */
    @Override
    public V remove(K key) {
        //throw new UnsupportedOperationException();
        if (front == null) {
            return null;
        }
        if (front.key.equals(key)) {
            V ret = front.val;
            front = front.next;
            size--;
            return ret;
        }
        Entry cur = front;
        while (cur.next != null) {
            if (cur.next.key.equals(key)) {
                V ret = cur.next.val;
                cur.next = cur.next.next;
                size--;
                return ret;
            }
            cur = cur.next;
        }
        return null;
    }

    @Override
    public V remove(K key, V value) {
        //throw new UnsupportedOperationException();
        if (front == null) {
            return null;
        }
        if (front.key.equals(key) && front.val.equals(value)) {
            V ret = front.val;
            front = front.next;
            size--;
            return ret;
        }
        Entry cur = front;
        while (cur.next != null) {
            if (cur.next.key.equals(key) && cur.next.val.equals(value)) {
                V ret = cur.next.val;
                cur.next = cur.next.next;
                size--;
                return ret;
            }
            cur = cur.next;
        }
        return null;
    }

    @Override
    public Set<K> keySet() {
        //throw new UnsupportedOperationException();
        if (front == null) {
            return null;
        }

        Set<K> keys = new HashSet<>();
        Entry cur = front;
        while (cur != null) {
            keys.add(cur.key);
            cur = cur.next;
        }
        return keys;
    }

}

