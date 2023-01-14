import edu.princeton.cs.algs4.In;

import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints {
    private ArrayList<LineSegment> segments = new ArrayList<>();
    private Point[] points;

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
//                else if (comparison < 0) {
//                    Point temp = this.points[j];
//                    this.points[j] = p;
//                    this.points[j+1] = temp;
//                } else{
//                    break;
//                }
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

    private Point[] findMinMaxPoint(ArrayList<Point> array) {
        System.out.println(array);
        Point min = new Point(Integer.MAX_VALUE, Integer.MAX_VALUE);
        Point max = new Point(Integer.MIN_VALUE, Integer.MIN_VALUE);
        for (Point p: array) {
            if (p.compareTo(min) < 0) {
                min = p;
            }
            if (p.compareTo(max) > 0) {
                max = p;
            }
        }
        return new Point[]{min, max};
    }

    /*
    Generates line segments for the array of points by processing 4 at a time.
     */
    public LineSegment[] segments() {
        int N = this.points.length;

        ArrayList<Point> seen = new ArrayList<>();

        for (int p = 0; p < N-2; p++) {
            Point point1 = this.points[p];
            if (seen.contains(point1)){
                continue;
            }

            ArrayList<Point> pointsWithTheSameSlope = new ArrayList<>();
            ArrayList<Point> sourcePoints = new ArrayList<>(Arrays.asList(this.points));
            sourcePoints.sort(this.points[p].slopeOrder());


            pointsWithTheSameSlope.add(point1);
            System.out.println("Slope to point " + point1);
            double slope = point1.slopeTo(this.points[p+1]);

            for (int q = 0; q < N; q++) {
                Point point2 = sourcePoints.get(q);

                if (point1.equals(point2)
                        || pointsWithTheSameSlope.contains((point2))
                        || seen.contains(point2)) {
                    continue;
                }
                double slope2 = point1.slopeTo(point2);
                if (Math.abs(slope2 - slope) < 0.000001d) {
                    pointsWithTheSameSlope.add(point2);
                }
            }
            if (pointsWithTheSameSlope.size() < 2) {
                continue;
            }

            Point start = findMinMaxPoint(pointsWithTheSameSlope)[0];
            Point end = findMinMaxPoint(pointsWithTheSameSlope)[1];
            if (!seen.contains(start) && !seen.contains(end)) {
                LineSegment l = new LineSegment(start, end);
                this.segments.add(l);
                seen.addAll(pointsWithTheSameSlope);
            }


        }




        LineSegment[] ls = new LineSegment[segments.size()];
        for (int is = 0; is < segments.size(); is++) {
            ls[is] = segments.get(is);
            System.out.println(segments.get(is));
        }
        return ls;

    }
}
