import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@TestInstance(Lifecycle.PER_CLASS)
public class TestPercolation {

//    @BeforeAll
//    public void beforeClass() {
        private Percolation p = new Percolation(5);
    @BeforeAll
    public void beforeClass() {
        this.p.open(1, 1);
        this.p.open(1, 3);

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
    }



    @Test
    public void testIsOpen(){
        assert(this.p.isOpen(1, 1));
        assert(this.p.isOpen(1, 3));
        assert(this.p.isOpen(3, 4));
        assert(this.p.isOpen(5, 5));
    }

    @Test
    public void testIsNotOpen(){
        assert(!this.p.isOpen(1, 2));
        assert(!this.p.isOpen(1, 5));
        assert(!this.p.isOpen(2, 1));
        assert(!this.p.isOpen(4, 4));
        assert(!this.p.isOpen(5, 3));
    }

    @Test
    public void isConnected() {
        assert (p.isConnected(p.xyTo1D(1, 3), p.xyTo1D(1, 4)));
        assert (p.isConnected(p.xyTo1D(1, 4), p.xyTo1D(2, 4)));
        assert (p.isConnected(p.xyTo1D(1, 3), p.xyTo1D(2, 4)));
        assert (p.isConnected(p.xyTo1D(3, 3), p.xyTo1D(3, 5)));
        assert (p.isConnected(p.xyTo1D(1, 3), p.xyTo1D(1, 4)));
        assert (p.isConnected(p.xyTo1D(3, 4), p.xyTo1D(5, 4)));
        assert (p.isConnected(p.xyTo1D(4, 5), p.xyTo1D(5, 4)));
        assert (p.isConnected(p.xyTo1D(5, 4), p.xyTo1D(5, 5)));

    }

    @Test
    public void isNotConnected() {
        assert (!p.isConnected(p.xyTo1D(1, 1), p.xyTo1D(1, 3)));
        assert (!p.isConnected(p.xyTo1D(1, 2), p.xyTo1D(2, 2)));
        assert (!p.isConnected(p.xyTo1D(1, 4), p.xyTo1D(1, 5)));
        assert (!p.isConnected(p.xyTo1D(3, 1), p.xyTo1D(1, 3)));
        assert (!p.isConnected(p.xyTo1D(4, 2), p.xyTo1D(4, 3)));
        assert (!p.isConnected(p.xyTo1D(5, 2), p.xyTo1D(1, 5)));
    }

    public static void afterAll () {
        System.out.println("Base::afterAll" ); }

}
