package synthesizer;

//Make sure this class is public
public class GuitarString {
    /** Constants. Do not change. In case you're curious, the keyword final means
      * the values cannot be changed at runtime. We'll discuss this and other topics
      * in lecture on Friday. */
    private static final int SR = 44100;      // Sampling Rate
    private static final double DECAY = .996; // energy decay factor
    
    /* Buffer for storing sound data. */
    private BoundedQueue buffer;
    
    /* Create a guitar string of the given frequency.  */
    public GuitarString(double frequency) {
        int capacity = (int) Math.round(SR / frequency);
        buffer = new ArrayRingBuffer(capacity);
        while (!buffer.isFull()) {
            buffer.enqueue(0.0);
        }
    }
    
    
    /* Pluck the guitar string by replacing the buffer with white noise. */
    public void pluck() {
        while (!buffer.isEmpty()) {
            buffer.dequeue();
        }
        while (!buffer.isFull()) {
            buffer.enqueue(Math.random() - 0.5);
        }
    }
    
    /* Advance the simulation one time step by performing one iteration of
     * the Karplus-Strong algorithm. 
     */
    public void tic() {
        double front = buffer.dequeue();
        front = DECAY * (front + buffer.peek()) * 0.5;
        buffer.enqueue(front);
    }
    
    /* Return the double at the front of the buffer. */
    public double sample() {
        if (buffer.isEmpty()) {
            return 0;
        }
        return buffer.peek();
    }
    
}
