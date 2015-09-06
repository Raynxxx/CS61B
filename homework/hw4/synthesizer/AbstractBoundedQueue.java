package synthesizer;

public abstract class AbstractBoundedQueue implements BoundedQueue {
    protected int fillCount;
    protected int capacity;

    public int capacity() {
        return capacity;
    }

    public int fillCount() {
        return fillCount;
    }

    public boolean isEmpty() {
        return fillCount == 0;
    }

    public boolean isFull() {
        return fillCount == capacity;
    }
}
