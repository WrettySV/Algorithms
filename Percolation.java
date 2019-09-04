import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    final private int n;
    final private int numOfsites;
    final private WeightedQuickUnionUF uf;
    private boolean[][] grid;
    private int numOfopensites;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException(
                    "n must be bigger than 0");
            //The constructor should throw a java.lang.IllegalArgumentException
            // if either n ≤ 0 or trials ≤ 0
        }
        this.n = n;
        numOfsites = n * n;
        uf = new WeightedQuickUnionUF(numOfsites + 2);  //+ virtual top and bottom sites

        grid = new boolean[n + 1][n + 1]; //indexes in [1,n]
        numOfopensites = 0;
    }


    public void open(int row, int col) {
        if (row <= 0 || row > n) {
            throw new IllegalArgumentException("row index is out of bounds");
        }
        if (col <= 0 || col > n) {
            throw new IllegalArgumentException("column index is out of bounds");
        }

        int indexOfid = (row - 1) * n + col;
        if (!grid[row][col]) { //if site is not open
            grid[row][col] = true;
            if (row != 1 && grid[row - 1][col]) {
                uf.union(indexOfid, indexOfid - n);
            }
            else if (row == 1) {
                uf.union(0, indexOfid);
            }
            if (row != n && grid[row + 1][col]) {
                uf.union(indexOfid, indexOfid + n);
            }
            else if (row == n) {
                uf.union(numOfsites + 1, indexOfid);
            }
            if (col != 1 && grid[row][col - 1]) {
                uf.union(indexOfid, indexOfid - 1);
            }
            if (col != n && grid[row][col + 1]) {
                uf.union(indexOfid, indexOfid + 1);
            }
            numOfopensites += 1;
        }
    }

    public boolean isOpen(int row, int col) {
        if (row <= 0 || row > n)
            throw new java.lang.IllegalArgumentException("row index is out of bounds");
        if (col <= 0 || col > n)
            throw new java.lang.IllegalArgumentException("column index is out of bounds");
        return grid[row][col];
    }

    public boolean isFull(int row, int col) {
        if (row <= 0 || row > n)
            throw new java.lang.IllegalArgumentException("row index is out of bounds");
        if (col <= 0 || col > n)
            throw new java.lang.IllegalArgumentException("column index is out of bounds");
        int indexOfid = (row - 1) * n + col;
        if (uf.connected(0, indexOfid)) {//is connected to virtual top?
            return true;
        }
        return false;
    }

    public int numberOfOpenSites() {
        return numOfopensites;
    }

    public boolean percolates() {
        if (uf.connected(0, numOfsites + 1)) {
            return true; //if virtual top connected with virtual bottom
        }
        return false;
    }


    public static void main(String[] args) {
    }
}

