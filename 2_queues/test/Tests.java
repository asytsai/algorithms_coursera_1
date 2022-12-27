import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;


@TestInstance(Lifecycle.PER_CLASS)
public class Tests {

    @Test
    public void testDeque(){
        Deque<String> d = new Deque<>();
        assertTrue( d.isEmpty());
        d.addFirst("First");
        assertFalse(d.isEmpty());
        d.addLast("Last");
        d.addFirst("New First");
        d.addLast("New Last");
        assertEquals(d.size(), 4);


        Iterator it = d.iterator();
        assertEquals(it.next(), "New First");
        assertEquals(it.next(), "First");
        assertEquals(it.next(), "Last");
        assertEquals(it.next(), "New Last");
        assertFalse(it.hasNext());

        assertThrows(UnsupportedOperationException.class, ()-> it.remove());


    }

    @Test
    public void testRandomQueue(){
        RandomizedQueue rq = new RandomizedQueue();
        assertTrue(rq.isEmpty());
        assertEquals(rq.size(), 0);

        int n = 10;
        for (int i = 0; i < n; i++) {
            rq.enqueue(i);
        }
        assertFalse(rq.isEmpty());
        assertEquals(rq.size(), n);

        rq.dequeue();
        assertEquals(rq.size(), n-1);

        Iterator it = rq.iterator();
        assertThrows(UnsupportedOperationException.class, ()-> it.remove());

        for (int j=0; j + 1 < n; j++) {
            rq.dequeue();
        }
        assertTrue(rq.isEmpty());
        assertEquals(rq.size(), 0);

        assertThrows(IllegalArgumentException.class, () -> rq.enqueue(null));
        assertThrows(NoSuchElementException.class, () -> rq.sample());
        assertThrows(NoSuchElementException.class, () -> rq.dequeue());
    }

    @Test
    public void testDequeIterator() {
        Deque<Integer> d = new Deque<>();

        d.addLast(1);
        d.addLast(3);
        d.addLast(4);
        d.removeLast();

        Iterator<Integer> it = d.iterator();

        while (it.hasNext()) {
            System.out.println(it.next());

        }


    }
}
