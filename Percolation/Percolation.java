/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private boolean[][] grid; // Percolation grid
    private int count; // number of open sites
    private WeightedQuickUnionUF uf; // Union-find
    private WeightedQuickUnionUF ufBack; // Union-find to prevent backwash

    // Virtual Sites
    private int topVirtual;
    private int bottomVirtual;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("N cannot be <= 0!");
        }

        grid = new boolean[n + 1][n + 1];
        uf = new WeightedQuickUnionUF(n * n + 2);
        ufBack = new WeightedQuickUnionUF(n * n + 1);

        // Add virtual sites to union find
        topVirtual = n * n;
        bottomVirtual = n * n + 1;

        // Set count to 0
        count = 0;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {

        if (invalid(row, col)) {
            throw new IllegalArgumentException("Outside perscribed range");
        }

        if (!isOpen(row, col)) {
            grid[row][col] = true;
            count++;

            // If site is in top row, connect to topVirtual site
            if (row == 1) {
                uf.union(index(row, col), topVirtual);
                ufBack.union(index(row, col), topVirtual);
            }

            // If site is in bottom row, connect to bottomVirtual site
            if (row == grid.length - 1) {
                uf.union(index(row, col), bottomVirtual);
            }

            // If sites directly around inputted site are open,
            // connects inputted site to sites directly around it
            if (!invalid(row + 1, col) && isOpen(row + 1, col)) {
                uf.union(index(row, col), index(row + 1, col));
                ufBack.union(index(row, col), index(row + 1, col));
            }

            if (!invalid(row - 1, col) && isOpen(row - 1, col)) {
                uf.union(index(row, col), index(row - 1, col));
                ufBack.union(index(row, col), index(row - 1, col));
            }

            if (!invalid(row, col + 1) && isOpen(row, col + 1)) {
                uf.union(index(row, col), index(row, col + 1));
                ufBack.union(index(row, col), index(row, col + 1));
            }

            if (!invalid(row, col - 1) && isOpen(row, col - 1)) {
                uf.union(index(row, col), index(row, col - 1));
                ufBack.union(index(row, col), index(row, col - 1));
            }

        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (invalid(row, col)) {
            throw new IllegalArgumentException("Outside perscribed range");
        }

        return grid[row][col];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (invalid(row, col)) {
            throw new IllegalArgumentException("Outside perscribed range");
        }

        return isOpen(row, col) && ufBack.find(topVirtual) == ufBack.find(index(row, col));
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return count;
    }

    // Maps 2D grid to 1D array
    private int index(int row, int col) {
        return (row - 1) * (grid.length - 1) + (col - 1);
    }

    // Checks if a block exists in our grid
    private boolean invalid(int row, int col) {
        return row < 1 || row > grid.length - 1 || col < 1 || col > grid.length - 1;
    }

    // does the system percolate?
    public boolean percolates() {
        return uf.find(topVirtual) == uf.find(bottomVirtual);
    }

    // test client (optional)
    public static void main(String[] args) {
        int n = StdIn.readInt();

        Percolation p = new Percolation(n);

        System.out.println("Created grid of " + n + " x " + n);

        while (!StdIn.isEmpty()) {
            int row = StdIn.readInt();
            int col = StdIn.readInt();
            p.open(row, col);

            System.out.println("Opened | Row: " + row + ", Col:  " + col);

        }

        System.out.println("Count: " + p.numberOfOpenSites());
        if (p.isOpen(2, 1)) {
            System.out.println("2, 1 open");
        }

        if (p.isOpen(3, 3)) {
            System.out.println("3, 3 open");
        }

        if (!p.isOpen(3, 3)) {
            System.out.println("3, 3 is not open");
        }

        if (p.isFull(8, 10)) {
            System.out.println("3, 1 is full");
        }

        if (p.percolates()) {
            System.out.println("Percolates!");
        }


    }
}
