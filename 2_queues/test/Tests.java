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


        final Iterator<String> it1 = d.iterator();
        assertEquals(it1.next(), "New First");
        assertEquals(it1.next(), "First");
        assertEquals(it1.next(), "Last");
        assertEquals(it1.next(), "New Last");
        assertFalse(it1.hasNext());

        assertThrows(UnsupportedOperationException.class, ()-> it1.remove());
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

        final Iterator it = rq.iterator();
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

    @Test
    public void testRandomizedDeque() {
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();
        assertTrue(queue.isEmpty());
        queue.enqueue(57);
        queue.enqueue(924);
        assertNotNull(queue.dequeue());
        assertNotNull(queue.dequeue());
    }

    @Test
    public void testRandomizedIsRamdom() {
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();
        assertTrue(queue.isEmpty());
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);
        queue.enqueue(6);
        queue.enqueue(7);
        queue.enqueue(8);
        queue.enqueue(9);
        queue.enqueue(10);
        String res1 = "";
        String res2 = "";
        Iterator<Integer> it1 = queue.iterator();
        Iterator<Integer> it2 = queue.iterator();
        while (it1.hasNext()) {
            res1 += it1.next();
        }
        while (it2.hasNext()) {
            res2 += it2.next();
        }
        System.out.println(res1 + " " + res2);
        assertNotEquals(res1, res2);
    }
}
