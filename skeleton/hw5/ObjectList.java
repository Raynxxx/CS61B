/** A linked list class that stores items of type Object. */
public class ObjectList {

    /** Inner class used to represent a single node in this linked list. */
    private class Node {
        
        /** Constructs a new Node with head VALUE and a null tail. */
        public Node(Object value) {
            this(value, null);
        }

        /** Constructs a new Node with head VALUE and tail NEXT. */
        public Node(Object value, Node next) {
            this.value = value;
            this.next = next;
        }

        /** Returns the element at index i starting at this node in
         *  the linked list. */
        public Object get(int i) {
            if (i == 0) return value;
            if (next == null) {
                throw new IllegalArgumentException("Index out of bounds");
            } else {
                return next.get(i - 1);
            }
        }

        /** The value stored in this node */
        Object value;
        /** The next node in the list */
        Node next;
    }

    /** Returns a string representation of this list of the form:
     *  [item1, item2, ..., itemN]. */
    @Override
    public String toString() {
        String rep = "[";
        Node cur = head;
        while (cur != null && cur.next != null) {
            rep += cur.value + ", ";
            cur = cur.next;
        }
        if (cur != null) {
            rep += cur.value;
        }
        rep += "]";
        return rep;
    }

    /** Returns number of items in this list. */
    public int length() {
        return length;
    }

    /** Returns ith element of this list, throwing an exception if it
     *  does not exist. */
    public Object get(int i) {
        if (head == null) {
            throw new IllegalArgumentException("Index out of bounds");
        }
        return head.get(i);
    }

    /** Inserts VAL into the front of this list. */
    public void insert(Object val) {
        head = new Node(val, head);
        length += 1;
    }

    /** The head of this linked list. */
    private Node head;
    /** The number of elements in this linked list */
    private int length;

}
