import edu.princeton.cs.algs4.In;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestPoint {
    private final double POSITIVE_ZERO = 0d;
    private final double NEGATIVE_ZERO = -0d;
    private final double POSITIVE_INFINITY = Double.POSITIVE_INFINITY;
    private final double NEGATIVE_INFINITY = Double.NEGATIVE_INFINITY;
    private final double EPSILON = 0.000001d;

    @Test
    public void testCompareTo() {
        Point p1 = new Point(1, 2);
        Point p2 = new Point(1, 2);
        Point p3 = new Point(3, 3);
        Point p4 = new Point(2, 3);
        Point p5 = new Point(2, 1);
        Point p6 = new Point(2, 2);
        Point p7 = new Point(1, 2);
        Point p8 = new Point(2, 2);
        Point p9 = new Point(3, 4);
        Point p10 = new Point(3, 3);

        assertEquals(p1.compareTo(p2), 0);
        assertEquals(p1.compareTo(p1), 0);
        assertEquals(p3.compareTo(p4), 1);
        assertEquals(p5.compareTo(p6), -1);
        assertEquals(p7.compareTo(p8), -1);
        assertEquals(p9.compareTo(p10), 1);


        Point[] points = {p1, p3, p6, p9};
        Arrays.sort(points, points[0].slopeOrder());
    }


    @Test
    public void testSlopeTo() {
        Point p1 = new Point(1, 2);
        Point p2 = new Point(0, 2);
        assertEquals(p1.slopeTo(p2), POSITIVE_ZERO);
        assertEquals(p1.slopeTo(p1), NEGATIVE_INFINITY);

        Point p3 = new Point(2, 1);
        Point p4 = new Point(2, 2);
        assertEquals(p3.slopeTo(p4), POSITIVE_INFINITY);

        Point p5 = new Point(3, 2);
        Point p6 = new Point(4, 4);
        assertEquals(p5.slopeTo(p6), 0.5);

        Point p7 = new Point(5, 1);
        Point p8 = new Point(4, 2);
        assertEquals(p7.slopeTo(p8), -1);
    }

    @Test
    public void testBruteCollinearPointsConstructor() {
        Point[] p1 = new Point[]{null};
        assertThrows(IllegalArgumentException.class, () -> new BruteCollinearPoints(p1));
        Point[] p2 = new Point[]{new Point(0, 3), new Point(1, 3), new Point(2, 4), new Point(1, 3)};
        assertThrows(IllegalArgumentException.class, () -> new BruteCollinearPoints(p2));
        Point[] p3 = new Point[]{new Point(1, 3), new Point(2, 4), new Point(1, 3), new Point(2, 3)};
        assertThrows(IllegalArgumentException.class, () -> new BruteCollinearPoints(p3));
    }

    @Test
    public void testBruteCollinearPointsInput6() {
        Point[] points = {new Point(19000, 10000), new Point(18000, 10000),
                new Point(32000, 10000), new Point( 21000, 10000),
                new Point(1234 , 5678), new Point(14000, 10000)};

        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        assertEquals(collinear.numberOfSegments(), 1);
    }

    @Test
    public void testBruteCollinearPointsInput8() {
        Point[] points = {new Point(10000, 0), new Point(0, 10000),
                new Point(3000, 7000), new Point( 7000, 3000),
                new Point(20000 , 21000), new Point(3000, 4000),
                new Point(14000 , 15000), new Point(6000, 7000)};

        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        assertEquals(collinear.numberOfSegments(), 2);
    }

    @Test
    public void testFastCollinearPointsInput6() {
        Point[] points = {new Point(19000, 10000), new Point(18000, 10000),
                new Point(32000, 10000), new Point( 21000, 10000),
                new Point(1234 , 5678), new Point(14000, 10000)};

        FastCollinearPoints collinear = new FastCollinearPoints(points);
        assertEquals(collinear.numberOfSegments(), 1);
    }

    @Test
    public void testFastCollinearPointsInput8() {
        Point[] points = {new Point(10000, 0), new Point(0, 10000),
                new Point(3000, 7000), new Point( 7000, 3000),
                new Point(20000 , 21000), new Point(3000, 4000),
                new Point(14000 , 15000), new Point(6000, 7000)};

        FastCollinearPoints collinear = new FastCollinearPoints(points);
        assertEquals(collinear.numberOfSegments(), 2);
    }

}
