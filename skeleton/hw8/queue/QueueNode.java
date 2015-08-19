/* User.java */

package queue;

/**
 *  A QueueNode object is used internally by the CatenableQueue class, acting
 *  like a singly-linked list node. It has two references: to the item it
 *  stores, and to the next QueueNode in the queue.
 */
class QueueNode<Item> {
    Item item;
    QueueNode<Item> next;

    /**
     *  QueueNode() (with one parameter) constructs a QueueNode holding object "i".
     */
    QueueNode(Item i) {
        item = i;
        next = null;
    }

    /**
     *  QueueNode() (with two parameters) constructs QueueNode holding "i", with
     *  the next QueueNode of "next".
     */

    QueueNode(Item i, QueueNode<Item> next) {
        item = i;
        this.next = next;
    }

}
