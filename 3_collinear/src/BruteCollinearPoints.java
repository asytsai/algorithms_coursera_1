
import java.util.ArrayList;

public class BruteCollinearPoints {
    private ArrayList<ArrayList<Point>> segments = new ArrayList<>();
    private Point[] points;
    private LineSegment[] lineSegments;

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
                } else {
                    break;
                }
            }
        }
        this.createSegments();
    }

    /*
       Creates 4-point segments of collinear points
        */
    private void createSegments() {
        int n = this.points.length;

        for (int p = 0; p < n; p++) {
            Point p1 = this.points[p];

            for (int q = p + 1; q < n; q++) {
                Point p2 = this.points[q];

                for (int r = q + 1; r < n; r++) {
                    Point p3 = this.points[r];

                    for (int s = r + 1; s < n; s++) {
                        Point p4 = this.points[s];
                        double slope1 = p1.slopeTo(p2);
                        double slope2 = p2.slopeTo(p3);
                        double slope3 = p3.slopeTo(p4);

                        if (Double.compare(slope1, slope2) == 0.0 && Double.compare(slope1, slope3) == 0.0) {
                            ArrayList<Point> segmentPoints = new ArrayList<>();
                            segmentPoints.add(p1);
                            segmentPoints.add(p2);
                            segmentPoints.add(p3);
                            segmentPoints.add(p4);
                            this.segments.add(segmentPoints);
                        }
                    }
                }
            }
        }
        this.lineSegments = new LineSegment[segments.size()];
        for (int is = 0; is < segments.size(); is++) {
            ArrayList<Point> segment = segments.get(is);
            LineSegment line = new LineSegment(segment.get(0), segment.get(segment.size()-1));
            this.lineSegments[is] = line;
        }
    }

    /*
    Returns the number of line segments
     */
    public int numberOfSegments() {
        return segments.size();
    }


    /*
    Generates line segments for the array of segments
     */
    public LineSegment[] segments() {
        return this.lineSegments;
    }

}
