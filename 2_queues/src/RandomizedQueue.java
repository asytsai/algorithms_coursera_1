import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;


public class RandomizedQueue<Item> implements Iterable<Item> {
    private static final int INIT_CAPACITY = 10;
    private Item[] queue;
    private int size;

    public RandomizedQueue() {
        this.queue =  (Item[]) new Object[INIT_CAPACITY];
        this.size = 0;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public int size() {
        return this.size;
    }

    private void resize(int capacity) {
        assert capacity >= size;
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            copy[i] = queue[i];
        }
        this.queue = copy;
    }

    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        if (this.queue.length == this.size) {
            this.resize(2 * this.size);
        }
        this.queue[size] = item;
        this.size++;
    }


    public Item dequeue() {
        if (this.size == 0) {
            throw new NoSuchElementException();
        }
        int idx = StdRandom.uniformInt(this.size);
        Item item = this.queue[idx];
        queue[idx] = queue[size-1];
        queue[size-1] = null;
        this.size--;

        if (size * 4 == queue.length) {
            Item[] copy = (Item[]) new Object[size * 2];
            for (int i = 0; i < size; i++) {
                copy[i] = queue[i];
            }
            this.queue = copy;
        }


        return item;
    }

    public Item sample() {
        if (this.size == 0) {
            throw new NoSuchElementException();
        }
        int idx = StdRandom.uniformInt(this.size);
        return this.queue[idx];
    }

    @Override
    public Iterator<Item> iterator() {
        return new RandomItemIterator();
    }

    private class RandomItemIterator implements Iterator<Item> {
//        private boolean[] visited = new boolean[size];
        private int nVisited = 0;
        private Item[] q = (Item[]) new Object[size];


        private RandomItemIterator() {
            for (int i=0; i < size; i++) {
                q[i] = queue[i];
            }
            StdRandom.shuffle(q);
        }


        public boolean hasNext() {

            return nVisited < size;
        }

        public Item next() {
            if (nVisited == size) {
                throw new NoSuchElementException();
            }
//            int curr = StdRandom.uniformInt(size);
//
//            while (visited[curr]) {
//                curr = StdRandom.uniformInt(size);
//            }
//
//            visited[curr] = true;
//            nVisited++;
//            return queue[curr];
            return q[nVisited++];
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }


    public static void main(String[] args) {

        RandomizedQueue<Integer> queue = new RandomizedQueue<>();
        System.out.println(queue.isEmpty());
        queue.enqueue(57);
        queue.enqueue(924);
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());


//        RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();
//        System.out.println(rq.isEmpty());
//        System.out.println(rq.size());
//        for (int i = 0; i < 100; i++) {
//            rq.enqueue(i);
//        }
//        System.out.println(rq.isEmpty());
//        System.out.println(rq.size());
//        System.out.println(rq.sample());
//        rq.dequeue();
//        System.out.println(rq.isEmpty());
//        System.out.println(rq.size());
//        Iterator<Integer> it = rq.iterator();
//        if (it.hasNext()) {
//            int i = it.next();
//            System.out.println(i);
//        }


//        RandomizedQueue<Integer> queue = new RandomizedQueue<>();
//        queue.enqueue(27);
//        queue.enqueue(41);
//        queue.enqueue(26);
//        queue.enqueue(23);
//        queue.enqueue(1);
//        queue.enqueue(35);
//        queue.enqueue(13);
//        queue.enqueue(38);
//        queue.enqueue(43);
//        Iterator<Integer> it = queue.iterator();
//        while (it.hasNext()) {
//            System.out.println(it.next());
//        }

    }
}
