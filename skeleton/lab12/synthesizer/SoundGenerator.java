package synthesizer; 

public abstract class SoundGenerator {    
    /* Number of times that tic has been called on this unit generator. */
    protected int age;

    /* Move the unit generator to the next sample. Should increment age. */
    public abstract void tic();
    
    /* Return the next sample. */
    public abstract double sample();
    
    /* Number of times this string has ticced. */
    public int age() {
        return age;
    }
}
