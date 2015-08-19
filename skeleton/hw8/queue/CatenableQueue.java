/* CatenableQueue.java */

package queue;

public class CatenableQueue<Item> {

    private QueueNode<Item> head;
    private QueueNode<Item> tail;
    private int size;

    /**
     *  CatenableQueue() constructs an empty CatenableQueue.
     **/
    public CatenableQueue() {
        size = 0;
        head = null;
        tail = null;
    }

    /** 
     *  size() returns the number of objects in this CatenableQueue.
     *  @return the size of this CatenableQueue.
     **/
    public int size() {
        return size;
    }

    /**
     *  isEmpty() returns true if this CatenableQueue is empty, false otherwise.
     *  @return true if this CatenableQueue is empty, false otherwise. 
     **/
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     *  enqueue() inserts an object at the end of the CatenableQueue.
     *  @param o, the object to be inserted.
     **/
    public void enqueue(Item o) {
        if (head == null) {
            head = new QueueNode<Item>(o);
            tail = head;
        } else {
            tail.next = new QueueNode<Item>(o);
            tail = tail.next;
        }
        size++;
    }

    /**
     *  dequeue() removes and returns the object at the front of the CatenableQueue,
     *  or returns null if the queue is empty.
     **/
    public Item dequeue() {
        if (head == null) {
            return null;
        } else {
            Item o = head.item;
            head = head.next;
            size--;
            if (size == 0) {
                tail = null;
            }
            return o;
        }
    }

    /**
     *  front() returns the object at the front of the CatenableQueue.
     *  @return the object at the front of the CatenableQueue.
     *  @throws a CatenableQueueEmptyException if the CatenableQueue is empty.
     **/
    public Item front() {
        if (head == null) {
            return null;
        } else {
            return head.item;
        }
    }

    /**
     *  nth() returns the nth object in the CatenableQueue.
     *    Items in the queue are numbered from 0.
     *    Returns null if there is no nth item.
     *  @param n the number of the item to return.
     */
    public Item nth(int n) {
        if (n >= size){
            return null;
        } else {
            QueueNode<Item> node = head;
            for (; n > 0; n--) {
                node = node.next;
            }
            return node.item;
        }
    }

    /**
     *  append() appends the contents of q onto the end of this CatenableQueue.
     *    On completion, q is empty.
     *  @param q the CatenableQueue whose contents should be appended onto this
     *    CatenableQueue.
     **/
    public void append(CatenableQueue<Item> q) {
        if (q != null){
            if (head == null) {
                head = q.head;
            } else {
                tail.next = q.head;
            }
            if (q.head != null) {
                tail = q.tail;
            }
            size = size + q.size;
            q.head = null;
            q.tail = null;
            q.size = 0;
        }
    }

    /**
     *  toString() converts this queue to a String.
     **/
    public String toString() {
        String out = "[ ";
        for (int i = 0; i < size(); i++) {
            out = out + front();
            enqueue(dequeue());
            if (i + 1 < size()){
                out = out + ",\n  ";
            }
        }
        return out + " ]";
    }

}
