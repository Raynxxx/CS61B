import java.util.Formatter;

/**
 * ApplicableIntList.java
 * A list that stores integers in ascending order.
 */
public class ApplicableIntList{
    /** First element of list. */
    public int head;
    /** Remaining elements of list. */
    public ApplicableIntList tail;

    /** A list with head HEAD0 and tail TAIL0. */
    public ApplicableIntList(int head0, ApplicableIntList tail0) {
        // REPLACE THIS LINE WITH YOUR SOLUTION
        this.head = head0;
        this.tail = tail0;
    }

    /** A list with null tail, and head = 0. */
    public ApplicableIntList() {
        // REPLACE THIS LINE WITH YOUR SOLUTION
        this(0, null);
    }

    /** Inserts int i into its correct location, doesn't handle cycles. */
    public void insert(int i) {
        // REPLACE THIS LINE WITH YOUR SOLUTION
        if (i < head) {
            tail = new ApplicableIntList(head, tail);
            head = i;
        } else {
            ApplicableIntList cur = this;
            while (cur.tail != null && i > cur.tail.head) {
                cur = cur.tail;
            }
            cur.tail = new ApplicableIntList(i, cur.tail);
        }
    }

    /** Returns the i-th int in this list.
     *  The first element, which is in location 0, is the 0th element.
     *  Assume i takes on the values [0, length of list - 1]. */
    public int get(int i) {
        // REPLACE THIS LINE WITH YOUR SOLUTION
        ApplicableIntList curr = this;
        while (curr.tail != null && i != 0) {
            curr = curr.tail;
            i--;
        }
        return curr.head;
    }

    /** Applies the function f to every item in this list. */
    public void apply(IntUnaryFunction f) {
        // REPLACE THIS LINE WITH YOUR SOLUTION
        ApplicableIntList curr = this;
        //applying function
        while (curr != null) {
            curr.head = f.apply(curr.head);
            curr = curr.tail;
        }
        //reordering list
        ApplicableIntList newList = new ApplicableIntList(get(0), null);
        curr = this.tail;
        while (curr != null) {
            newList.insert(curr.head);
            curr = curr.tail;
        }
        //update pointers
        head = newList.head;
        tail = newList.tail;
    }

    /** Returns NULL if no cycle exists, else returns cycle location. */
    private int detectCycles(ApplicableIntList A) {
        ApplicableIntList tortoise = A;
        ApplicableIntList hare = A;
        if (A == null) {
            return 0;
        }
        int cnt = 0;
        while (true) {
            cnt++;
            if (hare.tail != null) {
                hare = hare.tail.tail;
            }
            else{
                return 0;
            }
            tortoise = tortoise.tail;
            if (tortoise == null || hare == null) {
                return 0;
            }
            if (hare == tortoise) {
                return cnt;
            }
        }
    }

    /** Returns true iff X is a ApplicableIntList containing the
     *  same sequence of Comparables as THIS. Cannot handle cycles. */
    public boolean equals(Object x) {
        if (!(x instanceof ApplicableIntList)) {
            return false;
        }
        ApplicableIntList L = (ApplicableIntList) x;
        ApplicableIntList p;
        for (p = this; p != null && L != null; p = p.tail, L = L.tail) {
            if (p.head != L.head) {
                return false;
            }
        }
        if (p != null || L != null) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        Formatter out = new Formatter();
        String sep;
        sep = "(";
        int cycleLocation = detectCycles(this);
        int cnt = 0;
        for (ApplicableIntList p = this; p != null; p = p.tail) {
            out.format("%s%d", sep, p.head);
            sep = ", ";

            cnt++;
            if ((cnt > cycleLocation) && (cycleLocation > 0)) {
                out.format("... (cycle exists) ...");
                break;
            }
        }
        out.format(")");
        return out.toString();
    }
}
