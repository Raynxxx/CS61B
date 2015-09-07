import java.util.AbstractList;

/**
 * ArrayList61B
 * Created by rayn on 2015-9-7.
 */
public class ArrayList61B<E> extends AbstractList<E> {

    private E[] items;
    private int size;

    public ArrayList61B(int initialCapacity) {
        if (initialCapacity < 1) {
            throw new IllegalArgumentException("initialCapacity should be less than 1");
        }
        items = (E[]) new Object[initialCapacity];
        size = 0;
    }
    public ArrayList61B() {
        this(1);
    }

    @Override
    public E get(int index) {
        if (index < 0 || index == size) {
            throw new IllegalArgumentException("Out of bound");
        }
        return items[index];
    }

    public boolean add(E item) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[size] = item;
        size++;
        return true;
    }

    @Override
    public int size() {
        return size;
    }

    private void resize(int capacity) {
        E[] newItems = (E[]) new Object[capacity];
        for (int i = 0; i < items.length; ++i) {
            newItems[i] = items[i];
        }
        items = newItems;
    }
}
