import edu.princeton.cs.algs4.StdRandom;

import java.util.Comparator;

public class BinarySearchDeluxe {

    // Returns the index of the first key in the sorted array a[]
    // that is equal to the search key, or -1 if no such key.
    public static <Key> int firstIndexOf(Key[] a, Key key, Comparator<Key> comparator) {
        if (a == null || key == null || comparator == null) {
            throw new IllegalArgumentException("No arguments can be null!");
        }

        int lo = 0, hi = a.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int compare = comparator.compare(key, a[mid]);
            if (compare < 0) hi = mid - 1;
            else if (compare > 0) lo = mid + 1;
            else if (mid == 0) return mid;
            else if (a[mid - 1] == key) hi = mid - 1;
            else return mid;
        }
        return -1;
    }

    // Returns the index of the last key in the sorted array a[]
    // that is equal to the search key, or -1 if no such key.
    public static <Key> int lastIndexOf(Key[] a, Key key, Comparator<Key> comparator){
        if (a == null || key == null || comparator == null) {
            throw new IllegalArgumentException("No arguments can be null!");
        }

        int lo = 0, hi = a.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int compare = comparator.compare(key, a[mid]);
            if (compare < 0) hi = mid - 1;
            else if (compare > 0) lo = mid + 1;
            else if (mid == a.length - 1) return mid;
            else if (a[mid + 1] == key) lo = mid + 1;
            else return mid;
        }
        return -1;
    }

    // unit testing
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        Integer[] testArray = new Integer[5*n];
        int key = StdRandom.uniformInt(0, n + 1);

        for (int i = 0; i < testArray.length; i++){
            if (i < 5) testArray[i] = 1;
            else if (i < 10) testArray[i] = 2;
            else testArray[i] = 3;
        }


        for (Integer integer : testArray) {
            if (integer == null) throw new NullPointerException("Null found ");
        }


        System.out.println("Key: " + key);
        System.out.println("First index of: " + firstIndexOf(testArray, key, Integer::compare));
        System.out.println("Last index of: " + lastIndexOf(testArray, key, Integer::compare));

    }
}
