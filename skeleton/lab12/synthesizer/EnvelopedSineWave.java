package synthesizer; 
import util.StdAudio;

public class EnvelopedSineWave extends SoundGenerator {
    private double frequency;
    private double amplitude;
    private int lifespan;
    private int fade_in;
    private int fade_out;

    /* Number of times the string has ticced. */
    private int age = 0;
    
    /* Create a sine wave of the given amplitude and frequency and lifespan in samples. */
    public EnvelopedSineWave(double a, double f, int l) {        
        amplitude = a;
        frequency = f;
        lifespan = l;
        fade_in = lifespan / 20;
        fade_out = lifespan / 20;
    }
    
    /* Returns scaling factor to reduce arrival pop. */
    private double envelope() {
        if (age < fade_in) {            
            return ((double) age) / fade_in;
        }
        
        int lifeLeft = lifespan - age;

        if (lifeLeft < fade_out) {            
            return ((double) lifeLeft) / fade_out;
        }
        return 1;
    }


    /* Advance the simulation one time step by increasing age.
     */
    public void tic() {
        age += 1;
    }
    
    /* Return the double at the front of the buffer. */
    public double sample() {
        return envelope() * amplitude * Math.sin(2 * Math.PI * age * frequency / StdAudio.SAMPLE_RATE);        
    }

    /* Number of times this string has ticced. */
    public int age() {
        return age;
    }

}
