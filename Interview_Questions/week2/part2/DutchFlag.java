/* *****************************************************************************
 *  Dutch national flag. Given an array of nn buckets, each containing
 * a red, white, or blue pebble, sort them by color. The allowed operations are:
 *          - swap(i,j): swap the pebble in bucket i with the pebble in bucket j.
 *          - color(i): determine the color of the pebble in bucket i.
 * The performance requirements are as follows:
 *      - At most n calls to color().
 *      - At most n calls to swap().
 *      - Constant extra space.
 * Hint: 3-way partitioning.
 **************************************************************************** */
 import edu.princeton.cs.algs4.StdOut;
 
class Bucket {
    private char color;

    Bucket(char value) {
        this.color = value;
    }

    void setColor(char value) {
        this.color = value;
    }

    char getColor() {
        return this.color;
    }
}

public class DutchFlag {

    public void sortByColor(Bucket[] buckets) {
        int iRed = 0;  // red at first, then white
        int iBlue = buckets.length - 1; // blue in the end
        int i = 0;
        while (i <= iBlue) {
            char col = color(i, buckets);
            switch (col) {
                case ('r'): {
                    swap(i++, iRed++,
                         buckets); //index iRed points to the next bucket on the right after the red bucket
                    break;
                }
                case ('b'): {
                    swap(i, iBlue--,
                         buckets);//index iBlue points to the next bucket on the left after the blue bucket
                    break;
                }
                case ('w'): {
                    i++;
                }
            }
        }
    }

    public void swap(int i, int j, Bucket[] buckets) {
        char tmp = color(i, buckets);
        buckets[i].setColor(color(j, buckets));
        buckets[j].setColor(tmp);
    }

    public char color(int i, Bucket[] buckets) {
        return buckets[i].getColor();
    }

    public static void main(String[] args) {
        Bucket[] buckets = new Bucket[10];
        buckets[0] = new Bucket('r');
        buckets[1] = new Bucket('w');
        buckets[2] = new Bucket('r');
        buckets[3] = new Bucket('b');
        buckets[4] = new Bucket('b');
        buckets[5] = new Bucket('w');
        buckets[6] = new Bucket('b');
        buckets[7] = new Bucket('r');
        buckets[8] = new Bucket('w');
        buckets[9] = new Bucket('b');
        DutchFlag flag = new DutchFlag();
        flag.sortByColor(buckets);
        for (int i = 0; i < buckets.length; i++) {
            StdOut.print(buckets[i].getColor() + " ");
        }

    }
}
