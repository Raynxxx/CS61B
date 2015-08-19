/* Sort.java */

package sorting;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdDraw;

/**
 *  Contains several sorting routines implemented as static methods.
 *  Arrays are rearranged with smallest item first using compares.
 *  Sorting algorithms are modified to make visualization better
 *  Not all algorithms will be implemented as efficiently as they could be
 *  @author
 **/
public final class Sort {
    /**
     *  Simple insertion sort.
     *  @param a an array of int items.
     **/
    public static void insertionSort(int[] a) {
        int N = a.length;

        for (int i = 0; i < N; i++) {
            for (int j = i; j > 0; j -= 1) {
                if (less(a[j-1], a[j])) {
                    break;
                }
                exch(a, j, j-1);
            }
        }

    } 
    /**
     *  Implementation of the SelectionSort algorithm on integer arrays.
     *  @param a an array of int items.
     **/
    public static void selectionSort(int[] a) {
        int N = a.length;

        for (int i = 0; i < N; i += 1) {
            int min = i;
            for (int j = i+1; j < N; j += 1) {
                if (less(a[j], a[min])) {
                    min = j;
                }
            }
            exch(a, i, min);
        }

    }    

    /**
     *  Shellsort, using a sequence suggested by Gonnet.
     *  @param a an array of int items.
     **/
    public static void shellsort(int[] a) {
        int N = a.length;

        // 3x+1 increment sequence:  1, 4, 13, 40, 121, 364, 1093, ... 
        //int h = 1;
        //while (h < N/3) h = 3*h + 1; 

        for (int h = a.length / 2; h > 0;
                 h = (h == 2) ? 1 : (int) (h / 2.2)) {
            for (int i = h; i < N; i++) {
                for (int j = i; j >= h && (a[j] < a[j-h]); j -= h) {
                    exch(a, j, j-h);
                }
            }
        }
    }

    /**
     *  Standard heapsort.
     *  @param a an array of int items.
     **/
    public static void heapsort(int[] a) {
        heapify(a);
        for (int end = a.length - 1; end > 0;) {
            exch(a, end, 0);
            end--;
            siftDown(a, 0, end);
        }
    }

    /**
     * Turns an array into a max heap
     * @param a the array to heapify
     */
    private static void heapify(int[] a) {
        for (int i = (a.length - 2) / 2; i >= 0; i--) {
            siftDown(a, i, a.length - 1);
        }
    }

    /**
     * Sifts down an element through the heap. 
     * @param a array that represents the heap
     * @param start the index of the element to sift down
     * @param end the end of the heap, the right boundary in the array
     */
    private static void siftDown(int[] a, int start, int end) {
        int root = start;
        while (root * 2 + 1 <= end) {
            int child = leftChild(root);
            int tmp = root;
            if (a[tmp] < a[child]) {
                tmp = child;
            }
            if (child + 1 <= end && a[tmp] < a[child + 1]) {
                tmp = child + 1;
            }
            if (tmp != root) {
                exch(a, root, tmp);
                root = tmp;
            } else {
                break;
            }
        }
    }

    /**
     *  Internal method for heapsort.
     *  @param i the index of an item in the heap.
     *  @return the index of the left child.
     **/
    private static int leftChild(int i) {
        return 2 * i + 1;
    }

    /**
     *  Mergesort algorithm.
     *  @param a an array of int items.
     **/
    public static void mergeSort(int[] a) {
        int[] tmpArray = new int[a.length];
        mergeSort(a, tmpArray, 0, a.length - 1);
    }

    /**
     *  Internal method that makes recursive calls.
     *  @param a an array of int items.
     *  @param tmpArray an array to place the merged result.
     *  @param left the left-most index of the subarray.
     *  @param right the right-most index of the subarray.
     **/
    private static void mergeSort(int[] a, int[] tmpArray, int left, int right) {
        if (left < right) {
            int center = (left + right) / 2;
            mergeSort(a, tmpArray, left, center);
            mergeSort(a, tmpArray, center + 1, right);
            merge(a, tmpArray, left, center + 1, right);
        }
    }

    /**
     *  Internal method that merges two sorted halves of a subarray.
     *  @param a an array of int items.
     *  @param tmpArray an array to place the merged result.
     *  @param leftPos the left-most index of the subarray.
     *  @param rightPos the index of the start of the second half.
     *  @param rightEnd the right-most index of the subarray.
     **/
    private static void merge(int[] a, int[] tmpArray, int leftPos, int rightPos,
                                                        int rightEnd) {
        int leftEnd = rightPos - 1;
        int tmpPos = leftPos;
        int numElements = rightEnd - leftPos + 1;

        // Main loop
        while (leftPos <= leftEnd && rightPos <= rightEnd) {
            if (a[leftPos] < a[rightPos]) {
                tmpArray[tmpPos++] = a[leftPos++];
            } else {
                tmpArray[tmpPos++] = a[rightPos++];
            }
        }
        while (leftPos <= leftEnd) {
            tmpArray[tmpPos++] = a[leftPos++];
        }
        while(rightPos <= rightEnd) {
            tmpArray[tmpPos++] = a[rightPos++];
        }

        // Copy TmpArray back
        for (int i = 0; i < numElements; i++, rightEnd--) {
            SortSounds.clearRectangle(rightEnd);
            a[rightEnd] = tmpArray[rightEnd];
            SortSounds.drawRectangle(StdDraw.RED, rightEnd);
            StdDraw.show(5);
            SortSounds.play(rightEnd);
            SortSounds.drawRectangle(StdDraw.CYAN, rightEnd);
            StdDraw.show(5);
        }
    }

    /** Quicksort 
     */
    public static void quicksort(int[] a) {
       // StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
    }

    // quicksort the subarray from a[lo] to a[hi]
    private static void sort(int[] a, int lo, int hi) { 
        if (hi <= lo) return;
        int j = partition(a, lo, hi);
        sort(a, lo, j-1);
        sort(a, j+1, hi);
    }

    // partition the subarray a[lo..hi] so that a[lo..j-1] <= a[j] <= a[j+1..hi]
    // and return the index j.
    private static int partition(int[] a, int lo, int hi) {
        int i = lo;
        int j = hi + 1;
        int v = a[lo];
        while (true) { 

            // find item on lo to swap
            while (less(a[++i], v))
                if (i == hi) break;

            // find item on hi to swap
            while (less(v, a[--j]))
                if (j == lo) break;      // redundant since a[lo] acts as sentinel

            // check if pointers cross
            if (i >= j) break;

            exch(a, i, j);
        }

        // put partitioning item v at a[j]
        exch(a, lo, j);

        // now, a[lo .. j-1] <= a[j] <= a[j+1 .. hi]
        return j;
    }

    /** Returns true if x < y, false otherwise. */
    private static boolean less(int x, int y) {
        /** YOUR CODE HERE! */
        
        return x < y;
    }

    /**
     *  Method to swap two ints in an array.
     *  @param a an array of ints.
     *  @param index1 the index of the first int to be swapped.
     *  @param index2 the index of the second int to be swapped.
     **/
    private static void exch(int[] a, int index1, int index2) {
        /** YOUR CODE HERE! */

        int tmp = a[index1];
        a[index1] = a[index2];
        a[index2] = tmp;

        /** YOUR CODE HERE! */

    }

}