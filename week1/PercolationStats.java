import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private double[] p_ar;
    private int tr;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        this.tr = trials;
        if (n <= 0 || trials <= 0)
            throw new IllegalArgumentException(
                    "Illegal argument");//T is sufficiently large (say, at least 30
        p_ar = new double[trials];
        for (int i = 0; i < trials; i++) {
            Percolation perco = new Percolation(n);
            while (!perco.percolates()) {
                int a = 1 + StdRandom.uniform(n);
                int b = 1 + StdRandom.uniform(n);
                perco.open(a, b);
            }
            int openblock = perco.numberOfOpenSites();
            double p = (double) openblock / (n * n);
            p_ar[i] = p;
        }

    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(p_ar);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(p_ar);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - 1.96 * stddev() / Math.sqrt((double) tr);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + 1.96 * stddev() / Math.sqrt((double) tr);
    }

    // test client (see below)
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        PercolationStats percStats = new PercolationStats(n, trials);
        StdOut.printf("mean                    = %.16f\n", percStats.mean());
        StdOut.printf("stddev                  = %.16f\n", percStats.stddev());
        StdOut.printf("stddev                  = [%.16f, %.16f]", percStats.confidenceLo(),
                      percStats.confidenceHi());
    }
}
