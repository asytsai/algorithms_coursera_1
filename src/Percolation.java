import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private int size;  //grid size
    private int numberOfOpenSites;
    private boolean[] openSites; //keeps track of open sites
    private WeightedQuickUnionUF connectedSites; //which sites are connected



     /*
    ========================= Private methods ===================
     */


    public int xyTo1D(int n, int k) {
        // Converts 2-dim to 1-dim of range 0 to n-1
        return this.size * (n - 1) + k - 1;
    }

    public Boolean isIndexValid(int idx) {
        // Tests 1-dim input: valid from 0 to n*n and if it's a corner case (should not be joined)
        // (1, 1) -> 0
        return idx >= 0 && idx < this.size * this.size;
    }

    public Boolean canConnect(int idx) {
        // Tests 1-dim input:  if it's a corner case (should not be joined)
        // (1, 1) -> 0
        return !(idx % this.size == 0 || idx % this.size == (this.size - 1));
    }

    public Boolean isConnected(int idx1, int idx2) {
        return this.connectedSites.find(idx1+ this.size) == this.connectedSites.find(idx2+ this.size);
    }

    private Boolean isSiteOpen(int idx) {
        return this.openSites[idx];
    }

    private void openVirtualSite() {
        for (int i=0; i < this.size; i++) {
            this.connectedSites.union(i, i + 1);
            int bottom = this.size + i;
            if (this.isSiteOpen(bottom)) {
                this.connectedSites.union(i, bottom );
            }
        }
        System.out.println(this.connectedSites.find(0) == this.connectedSites.find(this.size-1));
    }

    private void openSiteAndConnect(int site, int neighbor) {
        if ( this.isSiteOpen(neighbor) ) {
            System.out.printf("Connecting %d to %d\n", site, neighbor);
            this.connectedSites.union(site+ this.size, neighbor+ this.size);
            System.out.println("Is connected? "+ this.isConnected(site, neighbor) + "\n");
        }
    }


    /*
    ========================= Public methods ===================
     */

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IndexOutOfBoundsException("row index i out of bounds");
        }
        this.size = n;
        this.numberOfOpenSites = 0;
        this.openSites = new boolean[n*n];
        // 1 extra row for virtual top site
        this.connectedSites = new WeightedQuickUnionUF((n + 1) * n);
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        int cell = this.xyTo1D(row, col);
        if (!this.isIndexValid(cell) ) {
            return;
        }
        this.openSites[cell] = true ;  // 1 is true, 0 is false
        this.numberOfOpenSites++;

        int top = this.xyTo1D(row-1, col);
        if (this.isIndexValid(top)) {
            this.openSiteAndConnect(cell, top);
        }
        int bottom = this.xyTo1D(row+1, col);
        if (this.isIndexValid(bottom)) {
            this.openSiteAndConnect(cell, bottom);
        }
        // Handling corner cells that can't be connected
        int left = this.xyTo1D(row, col-1);
        if (this.isIndexValid(left) && this.canConnect(left)) {
            this.openSiteAndConnect(cell, left);
        }
        int right = this.xyTo1D(row, col+1);
        if (this.isIndexValid(right) && this.canConnect(right)) {
            this.openSiteAndConnect(cell, right);
        }

        // if sites are fully open - need to mark them fully open recursively somehow https://www.coursera.org/learn/algorithms-part1/discussions/forums/8i0LoDcjEeaibQryEY-vTQ/threads/b_PB8frWEeaBJRJ6cGwgrA



    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row > this.size || row <= 0 || col > this.size || col <= 0) {
            throw new IllegalArgumentException();
        }
        return isSiteOpen(this.xyTo1D(row, col));
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
        this.openVirtualSite();
        return true;

    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        for (boolean  i: this.openSites) result.append(i + " ");
        result.append("\n");
        for (int k=1; k <= this.size; k++) {
            result.append(this.connectedSites.find(k) + " ");
        }
        result.append("\n");
        return result.toString();
    }

    public static void main(String[] args) {
        Percolation p = new Percolation(5);
        p.open(1, 1);
        p.open(1, 3);
        p.open(1, 4);
        p.open(2, 2);
        p.open(2, 4);
        p.open(3, 3);
        p.open(3, 4);
        p.open(3, 5);
        p.open(4, 1);
        p.open(4, 5);
        p.open(5, 2);
        p.open(5, 4);
        p.open(5, 5);

        p.percolates();
    }



}

