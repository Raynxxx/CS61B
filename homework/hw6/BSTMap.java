import java.util.*;

/**
 * BSTMap
 * Created by Rayn on 2015-09-16
 */
public class BSTMap<K extends Comparable<K>, V> implements Map61B<K,V> {

    private Node root;
    private int size;

    public BSTMap() {}


    @Override
    public V get(K key) {
        return get(root, key);
    }

    @Override
    public int size() {
        return size(root);
    }

    @Override
    public void put(K key, V value) {
        root = put(root, key, value);
    }

    @Override
    public boolean containsKey(K key) {
        return get(key) != null;
    }

    @Override
    public void clear() {
        root = null;
    }

    public void printInOrder() {

    }
    
    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    private V get(Node x, K key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            return get(x.left, key);
        } else if (cmp > 0) {
            return get(x.right, key);
        } else {
            return x.val;
        }
    }

    private Node put(Node x, K key, V val) {
        if (x == null) return new Node(key, val, 1);
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            x.left = put(x.left, key, val);
        } else if (cmp > 0) {
            x.right = put(x.right, key, val);
        } else {
            x.val = val;
        }
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    private int size(Node x) {
        if (x == null) return 0;
        return x.N;
    }

    private class Node {
        
        private K key;
        private V val;
        private Node left, right;
        private int N;

        public Node(K key, V val, int N) {
            this.key = key;
            this.val = val;
            this.N = N;
        }
    }
}
