import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    final private int n;
    final private int N;
    final private WeightedQuickUnionUF uf;
    private boolean[][] grid;
    private int Nopen;

    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException(
                    "N must be bigger than 0"); //The constructor should throw a java.lang.IllegalArgumentException
            // if either n ≤ 0 or trials ≤ 0
        }
        this.n = n;
        N = n * n;
        uf = new WeightedQuickUnionUF(N + 2);  //+ virtual top and bottom sites

        grid = new boolean[n + 1][n + 1]; //indexes in [1,n]
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                grid[i][j] = false;
            }
        }
        Nopen = 0;
    }


    public void open(int row, int col) {
        if (row <= 0 || row > n) {
            throw new java.lang.IllegalArgumentException("row index is out of bounds");
        }
        if (col <= 0 || col > n) {
            throw new java.lang.IllegalArgumentException("column index is out of bounds");
        }

        int x = (row - 1) * n + col;                  // x is the index of id[], object

        if (!grid[row][col]) { //if site is not open
            if (n == 1) { //if 1X1
                grid[row][col] = true;
                uf.union(n, 0);
                uf.union(n, N + 1);
                Nopen++;
            }
            else {
                grid[row][col] = true;
                if (row == 1 && col == 1) {//left top corner
                    if (grid[row][col + 1]) {//if open neighbour site
                        uf.union(x, x + 1); //union with right
                    }
                    if (grid[row + 1][col]) {
                        uf.union(x, x + n);//union with bottom
                    }
                    uf.union(0, x);                 // Union with virtual top
                }
                else if (row == 1 && col == n) {
                    if (grid[row][col - 1]) {
                        uf.union(x, x - 1); //union with left
                    }
                    if (grid[row + 1][col]) {
                        uf.union(x, x + n); //union with bottom
                    }
                    uf.union(0, x);                 // Union with virtual top
                }
                else if (row == n && col == 1) {
                    if (grid[row][col + 1]) {
                        uf.union(x, x + 1); //union with right
                    }
                    if (grid[row - 1][col]) {
                        uf.union(x, x - n); //union with top
                    }
                    uf.union(N + 1, x);                 // Union with virtual bottom
                }
                else if (row == n && col == n) {
                    if (grid[row][col - 1]) {
                        uf.union(x, x - 1); //union with left
                    }
                    if (grid[row - 1][col]) {
                        uf.union(x, x - n); ////union with top
                    }
                    uf.union(N + 1, x);                 // Union with virtual bottom
                }
                else if (row == 1 && col != 1 && col != n) {
                    if (grid[row][col - 1]) {
                        uf.union(x, x - 1);
                    }
                    if (grid[row][col + 1]) {
                        uf.union(x, x + 1);
                    }
                    if (grid[row + 1][col]) {
                        uf.union(x, x + n);
                    }
                    uf.union(0, x);                 // Union with virtual top
                }
                else if (row == n && col != 1 && col != n) {
                    if (grid[row][col - 1]) {
                        uf.union(x, x - 1);
                    }
                    if (grid[row][col + 1]) {
                        uf.union(x, x + 1);
                    }
                    if (grid[row - 1][col]) {
                        uf.union(x, x - n);
                    }
                    uf.union(N + 1, x);                 // Union with virtual bottom
                }
                else if (col == 1 && row != 1 && row != n) {
                    if (grid[row][col + 1]) {
                        uf.union(x, x + 1);
                    }
                    if (grid[row + 1][col]) {
                        uf.union(x, x + n);
                    }
                    if (grid[row - 1][col]) {
                        uf.union(x, x - n);
                    }
                }
                else if (col == n && row != 1 && row != n) {
                    if (grid[row][col - 1]) {
                        uf.union(x, x - 1);
                    }
                    if (grid[row + 1][col]) {
                        uf.union(x, x + n);
                    }
                    if (grid[row - 1][col]) {
                        uf.union(x, x - n);
                    }
                }
                else {
                    if (grid[row][col - 1]) {
                        uf.union(x, x - 1);
                    }
                    if (grid[row][col + 1]) {
                        uf.union(x, x + 1);
                    }
                    if (grid[row + 1][col]) {
                        uf.union(x, x + n);
                    }
                    if (grid[row - 1][col]) {
                        uf.union(x, x - n);
                    }
                }
                Nopen++;
            }
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
        int x = (row - 1) * n + col;
        if (uf.connected(0, x)) {//is connected to virtual top?
            return true;
        }
        return false;
    }

    public int numberOfOpenSites() {
        return Nopen;//number of opened sites
    }

    public boolean percolates() {
        if (uf.connected(0, N + 1)) {
            return true; //if virtual top connected with virtual bottom
        }
        return false;
    }


    public static void main(String[] args) {
    }
}


