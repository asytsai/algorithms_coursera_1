import javax.sound.sampled.Line;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class BruteCollinearPoints {
    private ArrayList<LineSegment> segments = new ArrayList<>();
    private ArrayList<Point> sortedPoints = new ArrayList<>();
    private Point[] points;

    /*
    Finds all line segments containing 4 points
     */
    public BruteCollinearPoints(Point[] points)  {
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
                } else if (comparison < 0) {
                    Point temp = this.points[j];
                    this.points[j] = p;
                    this.points[j+1] = temp;
                } else{
                    break;
                }
            }
        }
        for (Point s: this.points) {
            System.out.println(s);
        }
        this.segments();
    }


//    private void sort() {
//        Point[] aux = new Point[this.points.length];
//        mergeSort(this.points, aux, 0, this.points.length - 1);
//    }
//    private void mergeSort(Point[] arr, Point[] aux, int lo, int hi) {
//        if (hi <= lo) return;
//        int mid = lo + (hi - lo) / 2;
//        mergeSort(arr, aux, lo, mid);
//        mergeSort(arr, aux, mid+1, hi);
//        merge(arr, aux, lo, mid, hi);
//
//    }
//
//    private void merge(Point[] arr, Point[] aux, int lo, int mid, int hi) throws IllegalArgumentException {
//        int i = lo, j = mid+1;
//        for (int k = lo; k <= hi; k++) {
//            aux[k] = arr[k];
//        }
//        for (int k = lo; k <= hi; k++) {
//            if (arr[i].compareTo(arr[mid]) == 0)  throw new IllegalArgumentException();
//            else if (arr[i].compareTo(arr[mid]) > 0) arr[k] = aux[j++];
//            else if (arr[j].compareTo(arr[hi]) > 0) arr[k] = aux[i++];
//            else if (aux[j].compareTo(aux[i]) < 0) arr[k] = aux[j++];
//            else arr[k] = aux[i++];
//        }
//
//    }

    /*
    The number of line segments
     */
    public int numberOfSegments() {
        return segments.size();
    }

    /*
    Generates line segments for the array of points by processing 4 at a time.
     */
    public LineSegment[] segments() {
        int N = this.points.length;

        for (int p = 0; p < N-3; p++) {
            for (int q = p + 1; q < N-2; q++) {
                double slope1 = this.points[p].slopeTo(this.points[q]);
                for (int r = q + 1; r < N-1; r++) {
                    double slope2 = this.points[q].slopeTo(this.points[r]);
                    if (Math.abs(slope2 - slope1) >= 0.000001d) {
                        break;
                    }
                    for (int s = r + 1; s < N; s++) {
                        double slope3 = this.points[r].slopeTo(this.points[s]);
                        if (Math.abs(slope3 - slope2) >= 0.000001d) {
                            break;
                        }
                        LineSegment l = new LineSegment(this.points[p], this.points[s]);
                        this.segments.add(l);
                    }
                }
            }
        }
//        ArrayList<Point> seen = new ArrayList<>();
//        for (int p = 0; p < N-3; p++) {
//            ArrayList<Point> segmentPoints = new ArrayList<>();
//            Point point1 = this.points[p];
//            segmentPoints.add(point1);
//
//            double slope = this.points[p].slopeTo(this.points[p+1]);
//            for (int q = p + 2; q < N; q++) {
//                Point point2 = this.points[q];
//                double slope2 = point1.slopeTo(point2);
//                if (Math.abs(slope2 - slope) < 0.000001d) {
//                        segmentPoints.add(point2);
//                }
//            }
//            if (segmentPoints.size() < 2) {
//                continue;
//            }
//            Point start = segmentPoints.get(0);
//            Point end = segmentPoints.get(segmentPoints.size()-1);
//            if (!seen.contains(start) && !seen.contains(end)) {
//                LineSegment l = new LineSegment(start, end);
//                this.segments.add(l);
//            }
//            seen.addAll(segmentPoints);
//
//        }




        LineSegment[] ls = new LineSegment[segments.size()];
        for (int is = 0; is < segments.size(); is++) {
            ls[is] = segments.get(is);
            System.out.println(segments.get(is));
        }
        return ls;

    }
}
