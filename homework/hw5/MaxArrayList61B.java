/**
 * MaxArrayList61B
 * Created by rayn on 2015-9-7.
 */
public class MaxArrayList61B<E extends Comparable<E>> extends ArrayList61B<E> {

    private E maxSoFar;

    public MaxArrayList61B() {
        super();
    }

    public MaxArrayList61B(int initialCapacity){
        super(initialCapacity);
    }

    @Override
    public boolean add(E item) {
        if (item == null) {
            return false;
        }
        if (maxSoFar != null  && item.compareTo(maxSoFar) <= 0) {
            return false;
        }
        maxSoFar = item;
        return super.add(item);
    }
}
