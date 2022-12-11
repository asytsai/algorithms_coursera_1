import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;


public class PercolationStats {
    private static final double CONFIDENCE_95 = 1.96;
    private int n;
    private int trials;
    private Percolation grid;
    private double[] percolatesWithP;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException();
        }
        this.n = n;
        this.trials = trials;


        this.percolatesWithP = new double[trials];
        for (int t = 0; t < trials; t++) {
            this.grid = new Percolation(this.n);
            this.runExperiment(t);
        }
    }

    private void runExperiment(int experiment) {
        while (!this.grid.percolates()) {

            int x = StdRandom.uniformInt(1, this.n + 1);
            int y = StdRandom.uniformInt(1, this.n + 1);
            this.grid.open(x, y);
        }
        this.percolatesWithP[experiment] = (double) this.grid.numberOfOpenSites() / (this.n * this.n);

    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(this.percolatesWithP);

    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        if (this.trials <= 1) {
            return Double.NaN;
        }
        return StdStats.stddev(this.percolatesWithP);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - CONFIDENCE_95 * Math.sqrt(stddev() * stddev() / this.trials);

    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + CONFIDENCE_95 * Math.sqrt(stddev() * stddev() / this.trials);

    }

    // test client (see below)
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int t = Integer.parseInt(args[1]);

        PercolationStats stats = new PercolationStats(n, t);
        System.out.println(String.format("%-25s= %s", "mean", stats.mean()));
        System.out.println(String.format("%-25s= %s", "stddev", stats.mean()));
        System.out.println(String.format("%-25s= %s", "95% confidence interval", "[" + stats.confidenceLo() + ", "+ stats.confidenceHi() + "]"));


    }
}
