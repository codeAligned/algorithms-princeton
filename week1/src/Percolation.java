/**
 * The Percolation class models a percolation system.
 *
 * @author Tobias Leong
 */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private WeightedQuickUnionUF uf;
    private boolean[][] openGrid;
    private int top = 0;
    private int bottom;
    private int n;


    public Percolation(int inputN) {
        n = inputN;
        if (n <= 0) {
            throw new IllegalArgumentException("n provided to initialise Percolation is less than or equal to zero.");
        }

        // add two because of virtual top and bottom
        uf = new WeightedQuickUnionUF(n * n + 2);
        openGrid = new boolean[n][n];
        bottom = n * n + 1;
    }

    public boolean isOpen(int row, int col) {
        if (row <= 0 || col <= 0 || row > n || col > n) {
            throw new IllegalArgumentException();
        }
        return openGrid[row - 1][col - 1];
    }

    private int getIndex(int i, int j) {

        return n * (i - 1) + j;
    }

    public void open(int row, int col) {
        if (row <= 0 || col <= 0 || row > n || col > n) {
            throw new IllegalArgumentException();
        }

        // open the site
        openGrid[row - 1][col - 1] = true;

        // if top row, connect to top
        if (row == 1) {
            uf.union(getIndex(row, col), top);
        }

        // if bottom row, connect to bottom
        if (row == n) {
            uf.union(getIndex(row, col), bottom);
        }

        // check neighbours (T,B,L,R) and union
        if (row > 1 && isOpen(row - 1, col)) {
            uf.union(getIndex(row, col), getIndex(row - 1, col));
        }

        if (row < n && isOpen(row + 1, col)) {
            uf.union(getIndex(row, col), getIndex(row + 1, col));
        }

        if (col > 1 && isOpen(row, col - 1)) {
            uf.union(getIndex(row, col), getIndex(row, col - 1));
        }

        if (col < n && isOpen(row, col + 1)) {
            uf.union(getIndex(row, col), getIndex(row, col + 1));
        }
    }

    public boolean isFull(int row, int col) {
        if (row <= 0 || col <= 0 || row > n || col > n) {
            throw new IllegalArgumentException();
        }

        return uf.connected(getIndex(row ,col), top);

    }

    public int numberOfOpenSites() {
        int sum = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (openGrid[i][j]) {
                    sum++;
                }
            }
        }
        return sum;
    }

    public boolean percolates() {
        return uf.connected(bottom, top);
    }
}
