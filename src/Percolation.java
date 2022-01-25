import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private int size;  //grid size
    private int numberOfOpenSites;
    private boolean[] openSites; //keeps track of open sites
    private WeightedQuickUnionUF connectedSites; //which sites are connected

    public int xyTo1D(int n, int k) {
        //convert 2d to 1d
        return this.size * (n - 1) + k - 1;  // 0 to n-1
    }


// (1, 1) -> 0  Tests 1-dim input: valid from 0 to n*n
    public Boolean isIndexValid(int idx) {
        return idx >= 0 && idx < this.size * this.size;
    }

    public Boolean isConnected(int el1, int el2) {
        return this.connectedSites.find(el1) == this.connectedSites.find(el2);
    }

    private Boolean isSiteOpen(int idx) {
        System.out.println("Is site open? " +idx + " "+  this.openSites[idx]);
        return this.openSites[idx];
    }

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IndexOutOfBoundsException("row index i out of bounds");
        }

        this.size = n;
        this.numberOfOpenSites = 0;
        this.openSites = new boolean[n*n];
        // 1 extra row for virtual site
        this.connectedSites = new WeightedQuickUnionUF((n+1) * n);
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        int idIdx = this.xyTo1D(row, col);
        if (this.isIndexValid(idIdx) ) {
            this.openSites[idIdx] = true ;  // 1 is true, 0 is false
            this.numberOfOpenSites++;

            int top = this.xyTo1D(row-1, col);
            if (this.isIndexValid(top) && this.isSiteOpen(top)) {
                this.connectedSites.union(top, idIdx);
            }
            int left = this.xyTo1D(row, col-1);
            if (this.isIndexValid(left) && this.isSiteOpen(left)) {
                System.out.printf("Connecting %x to %x\n", left, idIdx);
                this.connectedSites.union(left, idIdx);
                System.out.println("Is connected? "+ this.isConnected(left, idIdx) + "\n");
            }
            int right = this.xyTo1D(row, col+1);
            if (this.isIndexValid(right) && this.isSiteOpen(right)) {
                this.connectedSites.union(right, idIdx);
            }
            int bottom = this.xyTo1D(row+1, col);
            if (this.isIndexValid(bottom) && this.isSiteOpen(bottom)) {
                this.connectedSites.union(bottom, idIdx);
            }
            // if sites are fully open - need to mark them fully open recursively somehow https://www.coursera.org/learn/algorithms-part1/discussions/forums/8i0LoDcjEeaibQryEY-vTQ/threads/b_PB8frWEeaBJRJ6cGwgrA


        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row > this.size || row <= 0 || col > this.size || col <= 0) {
            throw new IllegalArgumentException();
        }
        return this.openSites[this.xyTo1D(row, col)];
    }

    // is the site (row, col) full? way to check if a site is full is to check if it's opened and connected to the top virtual site.
    public boolean isFull(int row, int col) {
        if (row > this.size || row < 0 || col > this.size || col < 0) {
            throw new IllegalArgumentException();
        }
        // A full site is an open site that can be connected to an open site in the top row via a chain of neighboring (left, right, up, down) open sites.
        return false;
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return this.numberOfOpenSites;

    }
    //new IndexOutOfBoundsException("row index i out of bounds");

    // does the system percolate =  one or few open top sites connected to one bottom
    public boolean percolates() {
        return this.size == 42;

    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        for (boolean  i: this.openSites) result.append(i + " ");
        result.append("\n");
        for (int k=0; k < this.size; k++) {
            result.append(this.connectedSites.find(k) + " ");
        }
        result.append("\n");
        return result.toString();
    }


}

