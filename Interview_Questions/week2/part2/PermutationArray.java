/* *****************************************************************************
 * Permutation. Given two integer arrays of size n, design a subquadratic algorithm
 * to determine whether one is a permutation of the other.
 * That is, do they contain exactly the same entries but, possibly, in a different order.
 * Hint: sort both arrays.
 * Shellsort - https://algs4.cs.princeton.edu/code/edu/princeton/cs/algs4/Shell.java.html
 * Shuffle -https://algs4.cs.princeton.edu/code/edu/princeton/cs/algs4/StdRandom.java.html
 **************************************************************************** */

import edu.princeton.cs.algs4.Shell;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

public final class PermutationArray {
    public static boolean isPermutation(Integer[] a, Integer[] b) {
        Shell.sort(a);
        Shell.sort(b);
        for (int i = 0; i < a.length; i++) {
            int compare = a[i].compareTo(b[i]);
            if (compare != 0) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Integer[] a = { 2, 5, 7, 9, 2, 6, 9, 4, 23, 6, 15, 98 };
        Integer[] b = a.clone();
        StdRandom.shuffle(b);
        StdOut.println("a: " + Arrays.toString(a));
        StdOut.println("shuffled a: " + Arrays.toString(b));
        StdOut.println("isPermutation: " + PermutationArray.isPermutation(a, b));
        Integer[] d = { 2, 5, 7, 9, 2, 15, 7, 98, 3, 1, 8, 17 };
        StdOut.println("a: " + Arrays.toString(a));
        StdOut.println("d: " + Arrays.toString(d));
        StdOut.println("isPermutation: " + PermutationArray.isPermutation(a, d));
    }
}
