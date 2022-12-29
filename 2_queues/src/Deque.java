import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node first;
    private Node last;
    private int size;


    public Deque() {
        this.first = null;
        this.last = null;
        this.size = 0;
        assert check();
    }

    private class Node {
        Node prev;
        Item val;
        Node next;

        public Node(Node prev, Item i, Node next) {
            this.prev = prev;
            this.val = i;
            this.next = next;
        }
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public int size() {
        return this.size;
    }

    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        if (size == 0) {
            Node newItem = new Node(null, item, null);
            this.last = newItem;
            this.first = newItem;
        }
        else if (size == 1) {
            Node newItem = new Node(null, item, this.first);
            this.first = newItem;
            this.last.prev = newItem;
        } else {
            Node second = this.first;
            Node newItem = new Node(null, item, this.first);
            this.first = newItem;
            second.prev = first;
        }
        this.size++;
        assert check();
    }

    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        if (size == 0) {
            Node newItem = new Node(null, item, null);
            this.last = newItem;
            this.first = newItem;
        } else if (size == 1) {
            Node newItem = new Node(this.first, item, null);
            this.last = newItem;
            this.first.next = this.last;
        } else {
            Node prevNode = last;
            Node newItem = new Node(prevNode, item, null);
            this.last = newItem;
            prevNode.next = this.last;
        }
        this.size++;
        assert check();
    }

    public Item removeFirst() {
        if (this.size == 0) {
            throw new java.util.NoSuchElementException();
        }
        Node toRemove = this.first;
        if (size == 1) {
            this.first = null;
            this.last = null;
        } else {
            this.first = this.first.next;
            this.first.prev = null;
        }
        toRemove.next = null;
        this.size--;
        assert check();
        return toRemove.val;
    }

    public Item removeLast() {
        if (this.size == 0) {
            throw new java.util.NoSuchElementException();
        }
        Node toRemove = this.last;
        if (this.size == 1) {
            this.first = null;
            this.last = null;
        } else {
            Node prevToLast = this.last.prev;
            this.last = prevToLast;
            this.last.next = null;
        }
        this.size--;
        assert check();
        return toRemove.val;
    }

    @Override
    public java.util.Iterator<Item> iterator() {
        return new DequeIterator();
    }


    private class DequeIterator implements Iterator<Item> {
        private Node curr = first;

        public boolean hasNext() {
            return curr != null;
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = curr.val;
            curr = curr.next;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private boolean check() {
        if (size == 0) {
            if (first != null) return false;
            if (last != null) return false;
        } else if (size == 1) {
            if (first != last) return false;
            if (first.next != null) return false;
            if (last.next != null) return false;
        } else if (size == 2) {
            if (first.next != last) return false;
            if (last.prev != first) return false;
        } else {
            if (first == null) return false;
            if (last == null) return false;
            if (first.next == null) return false;
            if (last.prev == null) return false;
        }
        int numOfNodes = 0;
        Node curr = first;
        while (curr != null) {
            numOfNodes++;
            curr = curr.next;
        }
        if (size != numOfNodes) return false;
        return true;
    }

    public static void main(String[] args) {


        Deque<Integer> deque = new Deque<>();
        // deque.isEmpty();
        deque.addFirst(3);
        deque.removeLast();


        Deque<Integer> d = new Deque<>();

        d.addLast(1);
        d.addLast(3);
        d.addLast(4);
        d.removeLast();

        Iterator<Integer> it = d.iterator();

        while (it.hasNext()) {
            System.out.println(it.next());

        }


//        Deque<String> d = new Deque<>();
//        System.out.println("Is empty initially: "+ d.isEmpty());
//        d.addFirst("First");
//        System.out.println("Is empty after 1st addition? "+ d.isEmpty());
//        d.addLast("Last");
//        d.addFirst("New First");
//        d.addLast("New Last");
//        System.out.printf("Size %d\n", d.size());
//        Iterator<String> it = d.iterator();
//        while (it.hasNext()) {
//            String i = it.next();
//            System.out.println(i);
//        }
//
//        d.removeFirst();
//        System.out.printf("Size after removed new first %d\n", d.size());
//        d.removeLast();
//        System.out.printf("Size after removed new last %d\n", d.size());
//        d.removeFirst();
//        d.removeFirst();
//        d.addFirst("First");
//        System.out.printf("Size should be 1 vs.  %d\n", d.size());
//        d.removeLast();
//        System.out.println(d.isEmpty());
    }



}
