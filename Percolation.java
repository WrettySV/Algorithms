import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    final private int n;
    final private int numOfSites;
    final private WeightedQuickUnionUF uf;
    final private WeightedQuickUnionUF uf_up;
    private boolean[][] grid;
    private int numOfOpenSites;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException(
                    "n must be bigger than 0");
            //The constructor should throw a java.lang.IllegalArgumentException
            // if either n ≤ 0 or trials ≤ 0
        }
        this.n = n;
        numOfSites = n * n;
        uf = new WeightedQuickUnionUF(numOfSites + 2);  //+ virtual top and bottom sites
        uf_up = new WeightedQuickUnionUF(numOfSites + 1);

        grid = new boolean[n + 1][n + 1]; //indexes in [1,n]
        numOfOpenSites = 0;
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
                uf_up.union(indexOfid, indexOfid - n);
            }
            else if (row == 1) {
                uf.union(0, indexOfid);
                uf_up.union(0, indexOfid);
            }
            if (row != n && grid[row + 1][col]) {
                uf.union(indexOfid, indexOfid + n);
                uf_up.union(indexOfid, indexOfid + n);
            }
            else if (row == n) {
                uf.union(numOfSites + 1, indexOfid);
            }
            if (col != 1 && grid[row][col - 1]) {
                uf.union(indexOfid, indexOfid - 1);
                uf_up.union(indexOfid, indexOfid - 1);
            }
            if (col != n && grid[row][col + 1]) {
                uf.union(indexOfid, indexOfid + 1);
                uf_up.union(indexOfid, indexOfid + 1);
            }
            numOfOpenSites += 1;
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
        if (uf.connected(0, indexOfid) && uf_up
                .connected(0, indexOfid)) {//is connected to virtual top besides bottom virtual site
            return true;
        }
        return false;
    }

    public int numberOfOpenSites() {
        return numOfOpenSites;
    }

    public boolean percolates() {
        if (uf.connected(0, numOfSites + 1)) {
            return true; //if virtual top connected with virtual bottom
        }
        return false;
    }


    public static void main(String[] args) {
    }
}
