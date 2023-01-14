import java.util.Comparator;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdIn;

public class Point implements Comparable<Point> {
    private final int x;     // x-coordinate of this point
    private final int y;     // y-coordinate of this point
    private final double POSITIVE_ZERO = 0d;
    private final double NEGATIVE_ZERO = -0d;
    private final double POSITIVE_INFINITY = Double.POSITIVE_INFINITY;
    private final double NEGATIVE_INFINITY = Double.NEGATIVE_INFINITY;
    public static final double EPSILON = 0.000001d;

    /**
     * Initializes a new point.
     *
     * @param  x the <em>x</em>-coordinate of the point
     * @param  y the <em>y</em>-coordinate of the point
     */
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    /**
     * Draws this point to standard draw.
     */
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    /**
     * Draws the line segment between this point and the specified point
     * to standard draw.
     *
     * @param that the other point
     */
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    /**
     * Returns the slope between this point and the specified point.
     * Formally, if the two points are (x0, y0) and (x1, y1), then the slope
     * is (y1 - y0) / (x1 - x0). For completeness, the slope is defined to be
     * +0.0 if the line segment connecting the two points is horizontal;
     * Double.POSITIVE_INFINITY if the line segment is vertical;
     * and Double.NEGATIVE_INFINITY if (x0, y0) and (x1, y1) are equal.
     *
     * @param that the other point
     * @return the slope between this point and the specified point
     */
    public double slopeTo(Point that) {
        /* YOUR CODE HERE */
        if (this == that) {
            return NEGATIVE_INFINITY;
        }

        double num = this.y - that.y;
        if (num == 0) {
            return POSITIVE_ZERO;
        }
        double denom = this.x - that.x;
        if (Math.abs(denom - 0d) < EPSILON) {
            return POSITIVE_INFINITY;
        }
        double slope = num / denom;
        System.out.println("SlopeTo: "+ slope);
        return slope;
    }

    /**
     * Compares two points by y-coordinate, breaking ties by x-coordinate.
     * Formally, the invoking point (x0, y0) is less than the argument point
     * (x1, y1) if and only if either y0 < y1 or if y0 = y1 and x0 < x1.
     *
     * @param that the other point
     * @return the value <tt>0</tt> if this point is equal to the argument
     * point (x0 = x1 and y0 = y1);
     * a negative integer if this point is less than the argument
     * point; and a positive integer if this point is greater than the
     * argument point
     */
    public int compareTo(Point that) {
        /* YOUR CODE HERE */
        if (this.y  < that.y) {
            return -1;
        } else if (this.y > that.y) {
            return 1;
        } else {
            if ( this.x < that.x) {
                return -1;
            } else if (this.x > that.x) {
                return 1;
            } else{
                return 0;
            }
        }
    }

    /**
     * Compares two points by the slope they make with this point.
     * The slope is defined as in the slopeTo() method.
     *
     * @return the Comparator that defines this ordering on points
     */
    public Comparator<Point> slopeOrder() {
        /* YOUR CODE HERE */
        return new SlopeOrder();
    }

    private class SlopeOrder  implements Comparator<Point> {

        public int compare(Point p1, Point p2) {
            double dist1 = slopeTo(p1);
            double dist2 = slopeTo(p2);
            if (dist1 < dist2)      return -1;
            else if (dist1 > dist2) return +1;
            else                    return 0;
        }
    }


    /**
     * Returns a string representation of this point.
     * This method is provide for debugging;
     * your program should not rely on the format of the string representation.
     *
     * @return a string representation of this point
     */
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    /**
     * Unit tests the Point data type.
     */
    public static void main(String[] args) {
        int N = StdIn.readInt();
        Point[] points = new Point[N];

        int idx = 0;
        while (!StdIn.isEmpty()) {
            int x = StdIn.readInt();
            int y = StdIn.readInt();
            points[idx] = new Point(x, y);
            idx++;
        }
    }
}