package synthesizer;

public class ArrayRingBuffer extends AbstractBoundedQueue {
  /* Index for the next dequeue or peek. */
  private int first;           
  /* Index for the next enqueue. */
  private int last;             
  /* Array for storing the buffer data. */
  private double[] rb;

  /** Create a new ArrayRingBuffer with the given capacity. */
  public ArrayRingBuffer(int capacity) {
    this.rb = new double[capacity];
    this.first = this.last = this.fillCount = 0;
    this.capacity = capacity;
  }

  /** Adds x to the end of the ring buffer. If there is no room, then
    * throw new RuntimeException("Ring buffer overflow") 
    */
  public void enqueue(double x) {
    if (this.isFull()) {
        throw new RuntimeException("Ring buffer overflow");
    }
    rb[last] = x;
    fillCount++;
    last++;
    if (last == capacity) {
        last = 0;
    }
  }

  /** Dequeue oldest item in the ring buffer. If the buffer is empty, then
    * throw new RuntimeException("Ring buffer underflow");
    */
  public double dequeue() {
    if (this.isEmpty()) {
        throw new RuntimeException("Ring buffer overflow");
    }
    double ret = rb[first];
    rb[first] = 0;
    first++;
    fillCount--;
    if (first == capacity) {
        first = 0;
    }
    return ret;
  }

  /** Return oldest item, but don't remove it. */
  public double peek() {
    if (this.isEmpty()) {
        throw new RuntimeException("Ring buffer overflow");
    }
    return rb[first];
  }

}
