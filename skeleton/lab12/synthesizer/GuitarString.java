// Make sure to make this class a part of the synthesizer package
//package <package name>;
package synthesizer; 

//Make sure this class is public
public class GuitarString extends SoundGenerator {
    /** Constants. Do not change. In case you're curious, the keyword final means
      * the values cannot be changed at runtime. We'll discuss this and other topics
      * in lecture on Friday. */
    private static final int SR = 44100;      // Sampling Rate
    private static final double DECAY = .991; // energy decay factor
    
    /* Buffer for storing sound data. */
    private BoundedQueue buffer;

    /* Number of times the string has ticced. */
    private int age = 0;
    
    /* Create a guitar string of the given frequency.  */
    public GuitarString(double frequency) {
        // TODO: Create a buffer with capacity = SR / frequency. You'll need to
        //       cast the result of this divsion operation into an int. For better
        //       accuracy, use the Math.round() function before casting.
        //       Your buffer should be initially filled with zeros.
        // how big?
        int N = (int) Math.round(SR / frequency);
        // set up the buffer of a string at rest
        buffer = new ArrayRingBuffer(N);
        for (int i = 0; i < N; i++)
            buffer.enqueue(0.0);

        this.pluck();
    }
    
    
    /* Pluck the guitar string by replacing the buffer with white noise. */
    public void pluck() {
        int N = buffer.capacity();
        // random numbers from -1/2 to +1/2
        for (int i = 0; i < N; i++) {
            double r = Math.random() - 0.5;
            buffer.dequeue();
            buffer.enqueue(r);
        }
    }
    
    /* Advance the simulation one time step by performing one iteration of
     * the Karplus-Strong algorithm. 
     */
    public void tic() {
        // TODO: Dequeue the front sample and enqueue a new sample that is
        //       the average of the two multiplied by the DECAY factor.
        //       Do not call StdAudio.play().
        // average the first two samples
        double ave = (buffer.dequeue() + buffer.peek()) / 2;
        // apply decay and replace the previously first sample
        buffer.enqueue(ave * DECAY);
        age += 1;
    }
    
    /* Return the double at the front of the buffer. */
    public double sample() {
        return buffer.peek();
    }
    
    /* Number of times this string has ticced. */
    public int age() {
        return age;
    }
}
