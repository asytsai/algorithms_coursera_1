import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation {
    private int gridSize;
    private int numberOfOpenSites;
    private boolean[] openSites;
    private WeightedQuickUnionUF connectedSites;
    private int topParentId;


    /*
    Creates n-by-n grid, with all sites initially blocked.
    Implementation has top and bottom rows reserved for virtual sites.
     */
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Constructor received invalid value for N");
        }
        this.gridSize = n;
        int totalCells = n * n + 1;  // 1 cell for virtual sites
        this.numberOfOpenSites = 0;
        this.openSites = new boolean[totalCells];
        this.connectedSites = new WeightedQuickUnionUF(totalCells);
        this.topParentId = 0/*this.connectedSites.find(0)*/;
    }

    // Converts 2D coordinates to 1d.
    // Returns coordinates within a one-dimensional array if two-dimensional coordinates are valid.
    // Otherwise, return -1;
    private int xyTo1D(int x, int y) {
        if (!isIndexValid(x, y)) {
            return -1;
        }
        int cell = this.gridSize * (x - 1) + y;
        return cell;
    }

    // Performs validation of index: min is 1, max is gridSize.
    private boolean isIndexValid(int x, int y) {
        return x > 0 && x <= this.gridSize && y > 0 && y <= this.gridSize;
    }

    private boolean isIndexValid(int idx) {
        return idx > 0 && idx <= this.gridSize * this.gridSize;
    }

    private void dfs(int row, int col) {
        int cell = this.xyTo1D(row, col);

        int top = this.xyTo1D(row-1, col);
        int bottom = this.xyTo1D(row+1, col);
        int left = this.xyTo1D(row, col - 1);
        int right = this.xyTo1D(row, col + 1);

        int[] neighbours = new int[] {top, bottom, left, right};
        for (int n: neighbours) {
            if (this.isIndexValid(n) && this.openSites[n]) {
                this.connectedSites.union(cell, n);
            }
        }
    }


/*
========================= Public methods ===================
 */


    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (!this.isIndexValid(row, col)) {
            throw new IllegalArgumentException("Trying to open a site: row or column invalid");
        }
        int cell = this.xyTo1D(row, col);
        if (!this.openSites[cell]) {
            this.openSites[cell] = true;
            this.numberOfOpenSites++;
            if (row == 1) {
                this.connectedSites.union(cell, this.topParentId);
            }
            this.dfs(row, col);
        }

    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row > this.gridSize || row < 1 || col > this.gridSize || col < 1) {
            throw new IllegalArgumentException("IsOpen() method: row or column invalid");
        }
        return this.openSites[this.xyTo1D(row, col)];

    }

    // is the site (row, col) full? way to check if a site is full is to check if it's opened and connected to the top virtual site.
    public boolean isFull(int row, int col) {
        if (row > this.gridSize || row  < 1 || col > this.gridSize || col < 1) {
            throw new IllegalArgumentException();
        }
        if (!this.isOpen(row, col)) {
            return false;
        }
        // A full site is an open site that can be cint topId = this.connectedSites.find(0);
        int el = this.xyTo1D(row, col);
        return this.connectedSites.find(el) == this.connectedSites.find(this.topParentId);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return this.numberOfOpenSites;
    }
    // new IndexOutOfBoundsException("row index i out of bounds");

    // does the system percolate =  one or few open top sites connected to one bottom
    public boolean percolates() {
        int top = this.connectedSites.find(this.topParentId);
        for (int i = 1; i <= this.gridSize; i++) {
            if (this.isOpen(this.gridSize, i) && this.connectedSites.find(xyTo1D(this.gridSize, i)) == top) {
                return true;
            }
        }
        return false;
    }


//    public static void main(String[] args) {
//        Percolation p2 = new Percolation(2);
//        p2.open(1, 1);
//        p2.open(1, 2);
//        p2.open(2, 2);
//        System.out.println(p2.numberOfOpenSites());
//        System.out.println(p2.isFull(1, 1));
//        System.out.println(p2.isFull(2, 1));
//        System.out.println(p2.percolates());
//
//
//        Percolation p3 = new Percolation(2);
//        p3.open(1, 1);
//
//        System.out.println(p3.numberOfOpenSites());
//        System.out.println(p3.percolates());
//
//
//        Percolation p4 = new Percolation(2);
//        p4.open(1, 1);
//        p4.open(2, 1);
//        System.out.println(p4.numberOfOpenSites());
//        System.out.println(p4.percolates());
//    }






}
