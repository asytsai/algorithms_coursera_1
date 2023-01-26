
import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints {
    private ArrayList<ArrayList<Point>> segments = new ArrayList<>();
    private Point[] points;
    private LineSegment[] lineSegments;

    /*
    Finds all line segments of collinear points
     */
    public FastCollinearPoints(Point[] points)  {
        if (points == null) {
            throw new IllegalArgumentException();
        }
        this.points = new Point[points.length];

        for (int i = 0; i < points.length; i++) {
            Point p = points[i];
            if (p == null) {
                throw new IllegalArgumentException();
            }
            this.points[i] = p;
            for (int j = i-1; j >= 0; j--) {
                int comparison = p.compareTo(this.points[j]);
                if (comparison == 0) {
                    throw new IllegalArgumentException();
                }
            }
        }

//        this.segments();
        this.createSegments();
    }

    private void mergeSort(ArrayList<Point> arr) {
        ArrayList<Point> aux = new ArrayList<Point>(arr.size());
        for (int k = 0; k <= arr.size(); k++) {
            aux.add(null);
        }
        this.sort(arr, aux, 0, arr.size() - 1);
    }

    private void sort(ArrayList<Point> arr, ArrayList<Point> aux, int lo, int hi) {
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        sort(arr, aux, lo, mid);
        sort(arr, aux, mid+1, hi);
        merge(arr, aux, lo, mid, hi);
    }

    private void merge(ArrayList<Point> arr, ArrayList<Point> aux, int lo, int mid, int hi) {
        int i = lo, j = mid+1;
        for (int k = lo; k <= hi; k++) {
            aux.set(k, arr.get(k));
        }
        for (int k = lo; k <= hi; k++) {
            if (i > mid) arr.set(k, aux.get(j++));
            else if (j > hi) arr.set(k, aux.get(i++));
            else if (aux.get(j).compareTo(aux.get(i)) < 0) arr.set(k, aux.get(j++));
            else arr.set(k, aux.get(i++));
        }

    }

private void createSegments() {
    int n = this.points.length;
    ArrayList<Point> sourcePoints = new ArrayList<>();

    for (int p = 0; p < n-2; p++) {
        Point point = this.points[p];

        sourcePoints.addAll(Arrays.asList(this.points));
        sourcePoints.sort(this.points[p].slopeOrder());
        ArrayList<Point> pointsWithTheSameSlope = new ArrayList<>();
        pointsWithTheSameSlope.add(point);
//            System.out.println("Slope to point " + point1);
        double slope = point.slopeTo(this.points[p+1]);

        int nextIdx = 1;
        Point nextPoint = null;
        //boolean slopeSeen = false;
        while (nextIdx < n - 1) {
            nextPoint = sourcePoints.get(nextIdx);
            Double slope2 = point.slopeTo(nextPoint);
            if (Double.compare(slope2, slope) == 0) {
                //slopeSeen = true;
                pointsWithTheSameSlope.add(nextPoint);
//            } else if (slopeSeen) {
//                break;
            }
            nextIdx++;

        }
        if (pointsWithTheSameSlope.size() > 3 &&
                pointsWithTheSameSlope.get(0).compareTo(pointsWithTheSameSlope.get(1)) < 0) {
            this.mergeSort(pointsWithTheSameSlope);
            segments.add(pointsWithTheSameSlope);
//            System.out.println("After sort");
//            System.out.println(pointsWithTheSameSlope);

            }
            sourcePoints.clear();
        }

        this.lineSegments = new LineSegment[segments.size()];
        System.out.println("Printing segments");
        for (int is = 0; is < segments.size(); is++) {
            System.out.println(segments.get(is));
            LineSegment line = new LineSegment(segments.get(is).get(0), segments.get(is).get(segments.get(is).size()-1));
            this.lineSegments[is] = line;
    //            System.out.println(segments.get(is));
        }
    }

    /*
    The number of line segments
     */
    public int numberOfSegments() {
        for (LineSegment ls: this.lineSegments) {
            System.out.println(ls);
        }
        return segments.size();
    }

//    private Point findMinPoint(ArrayList<Point> array) {
////        System.out.println(array);
//        Point min = new Point(Integer.MAX_VALUE, Integer.MAX_VALUE);
//        for (Point p: array) {
//            if (p.compareTo(min) < 0) {
//                min = p;
//            }
//        }
//        return min;
//    }
//
//    private Point findMaxPoint(ArrayList<Point> array) {
//        Point max = new Point(Integer.MIN_VALUE, Integer.MIN_VALUE);
//        for (Point p: array) {
//            if (p.compareTo(max) > 0) {
//                max = p;
//            }
//        }
//        return max;
//    }

    /*
    Generates line segments for the array of points by processing 4 at a time.
     */
    public LineSegment[] segments() {
        return this.lineSegments;

    }
}
