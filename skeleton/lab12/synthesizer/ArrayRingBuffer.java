// Make sure to make this class a part of the synthesizer package
//package <package name>;
package synthesizer;

//Make sure to make this class and all of its methods public
//Make sure to make this class extend AbstractBoundedQueue
public class ArrayRingBuffer extends AbstractBoundedQueue {
  /* Index for the next dequeue or peek. */
  private int first;            // index for the next dequeue or peek
  /* Index for the next enqueue. */
  private int last;             
  /* Array for storing the buffer data. */
  private double[] rb;

  /** Create a new ArrayRingBuffer with the given capacity. */
  public ArrayRingBuffer(int capacity) {
    // TODO: Create new array with capacity elements.
    //       first, last, and fillCount should all be set to 0. 
    //       this.capacity should be set appropriately. Note that the local variable
    //       here shadows the field we inherit from AbstractBoundedQueue.
    rb = new double[capacity];
    this.capacity = capacity;
    first = last = fillCount = 0;
  }

  /** Adds x to the end of the ring buffer. If there is no room, then
    * throw new RuntimeException("Ring buffer overflow") 
    */
  public void enqueue(double x) {
    // TODO: Enqueue the item. Don't forget to increase fillCount and update last.
    // is there room?
    if (isFull()) {
      throw new RuntimeException("Ring buffer overflow");
    }
    // add to buffer, move end pointer, check for wraparound
    rb[last] = x;
    fillCount = fillCount + 1;
    last = (last + 1) % rb.length;
  }

  /** Dequeue oldest item in the ring buffer. If the buffer is empty, then
    * throw new RuntimeException("Ring buffer underflow");
    */
  public double dequeue() {
    // TODO: Dequeue the first item. Don't forget to decrease fillCount and update first.
    if (isEmpty()) {
      throw new RuntimeException("Ring buffer underflow");
    }
    // save item to be returned, move first pointer, wrap?
    double temp = rb[first];
    fillCount = fillCount - 1;
    first = (first + 1) % rb.length;
    // return saved element
    return temp;
  }

  /** Return oldest item, but don't remove it. */
  public double peek() {
    // TODO: Return the first item. None of your instance variables should change.
    if (isEmpty()) {
      throw new RuntimeException("Ring buffer underflow");
    }
    return rb[first];
  }

}
