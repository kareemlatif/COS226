/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private static final double CONFIDENCE_95 = 1.96;
    private double[] xi;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException("Trials and n must be greater than 0!");
        }

        xi = new double[trials];

        for (int i = 0; i < trials; i++) {
            Percolation p = new Percolation(n);
            while (!p.percolates()) {
                int row = StdRandom.uniformInt(1, n + 1);
                int col = StdRandom.uniformInt(1, n + 1);
                p.open(row, col);
            }
            xi[i] = (double) p.numberOfOpenSites() / (n * n);

        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(xi);

    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(xi);

    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - (CONFIDENCE_95 * stddev() / Math.sqrt(xi.length));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + (CONFIDENCE_95 * stddev() / Math.sqrt(xi.length));
    }

    // test client (see below)
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int numberOfTrials = Integer.parseInt(args[1]);

        PercolationStats p = new PercolationStats(n, numberOfTrials);

        System.out.println("mean = " + p.mean());
        System.out.println("stddev = " + p.stddev());
        System.out.println(
                "95% confidence interval = [" + p.confidenceLo() + ", " + p.confidenceHi() + "]");


    }

}
