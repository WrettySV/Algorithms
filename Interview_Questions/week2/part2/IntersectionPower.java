/* *****************************************************************************
 * Intersection of two sets.
 * Given two arrays a[] and b[],
 * each containing n distinct 2D points in the plane,
 * design a subquadratic algorithm to count the number of points
 * that are contained both in array a[] and array b[].
 * Hint: shellsort (or any other subquadratic sort).
 **************************************************************************** */
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Shell;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class IntersectionPower {
    public int count(Point2D[] a, Point2D[] b) {
        Shell.sort(a);
        Shell.sort(b);
        int i = 0;
        int j = 0;
        int count = 0;
        while (i < a.length && j < b.length) {
            int compare = a[i].compareTo(b[j]);
            switch (compare) {
                case (0): {
                    count++;
                    i++;
                    j++;
                    break;
                }
                case (-1): {
                    i++;
                    break;
                }
                case (1): {
                    j++;
                    break;
                }
            }
        }
        return count;
    }

    public static <File, FileOutputStream> void main(String[] args) {
        Point2D[] a = new Point2D[30];
        Point2D[] b = new Point2D[35];
        double max = 2;
        double min = -1;
        for (int i = 0; i < 30; i++) {
            double x = Math.round(StdRandom.uniform(min, max) * 10) / 10.;
            double y = Math.round(StdRandom.uniform(min, max) * 10) / 10.;
            a[i] = new Point2D(x, y);
        }
        for (int i = 0; i < 35; i++) {
            double x = Math.round(StdRandom.uniform(min, max) * 10) / 10.;
            double y = Math.round(StdRandom.uniform(min, max) * 10) / 10.;

            b[i] = new Point2D(x, y);
        }
        IntersectionPower inter = new IntersectionPower();
        StdOut.println("count the number of points that are contained both: " + inter.count(a, b));

        StdOut.println(" sort array a");
        for (Point2D point : a)
            StdOut.println(point.toString());

        StdOut.println("sort array b");
        for (Point2D point : b)
            StdOut.println(point.toString());

    }

}
