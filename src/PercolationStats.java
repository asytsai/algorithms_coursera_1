import edu.princeton.cs.algs4.StdIn;

public class PercolationStats {
    int n;
    int trials;
    Percolation grid;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        this.n = n;
        this.trials = trials;
        this.grid = new Percolation(this.n);
    }

    // sample mean of percolation threshold
    public double mean() {
        return 0.0;

    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        if (this.trials <= 1) {
            return Double.NaN;
        }
        return 0.0;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return 0.0;

    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return 0.0;

    }

    // test client (see below)
    public static void main(String[] args) {
        int n = StdIn.readInt();
        int T = StdIn.readInt();
    }
}
