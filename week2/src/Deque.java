import edu.princeton.cs.algs4.StdOut;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item>{
        // because constant worst-case time is needed for all operations, implement using Doubly Linked-List

        private Node first;
        private Node last;
        private int numItems = 0;

        private class Node {
            Item item;
            Node next;
            Node previous;
        }

        public Deque() {
            Node firstNode = new Node();
            Node lastNode = new Node();

            first = firstNode;
            last = lastNode;

            first.item = null;
            first.next = last;
            first.previous = null;

            last.item = null;
            last.next = null;
            last.previous= first;

        }

        public boolean isEmpty() {
            return (numItems == 0);
        }

        public int size() {
            return numItems;
        }

        public void addFirst(Item item) {
            if (item == null) {
                throw new IllegalArgumentException("Item provided is null.");
            }

            if (isEmpty()) {
                Node newFirst = new Node();
                first = newFirst;
                last = first;
                first.item = item;
                first.next = last;
                first.previous = null;
                numItems++;
            } else {

                Node oldFirst = first;
                Node newNode = new Node();
                newNode.item = item;
                newNode.next = oldFirst;
                newNode.previous = null;
                oldFirst.previous = newNode;
                first = newNode;
                numItems++;
            }
        }

        public void addLast(Item item) {
            if (item == null) {
                throw new IllegalArgumentException("Item provided is null.");
            }

            if (isEmpty()) {
                Node newLast = new Node();
                last = newLast;
                first = last;
                last.item = item;
                last.next = null;
                last.previous = first;
                numItems++;
            } else {
                Node oldLast = last;
                Node newNode = new Node();
                newNode.item = item;
                newNode.previous = oldLast;
                newNode.next = null;
                oldLast.next = newNode;
                last = newNode;
                numItems++;
            }

        }

        public Item removeFirst() {
            if (isEmpty()) {
                throw new NoSuchElementException("Deque is empty.");
            }
            Node oldFirst = first;
            first = oldFirst.next;
            first.previous = null;
            numItems--;
            return oldFirst.item;
        }

        public Item removeLast() {
            if (isEmpty()) {
                throw new NoSuchElementException("Deque is empty.");
            }
            Node oldLast = last;
            last = oldLast.previous;
            last.next = null;
            numItems--;
            return oldLast.item;

        }

        public Iterator<Item> iterator() {
            return new DequeIterator();
        }
        private class DequeIterator implements Iterator<Item> {
            private Node current;

            private DequeIterator(){
                // constructor so that we point BEFORE the first one, so that hasNext() and next() works well
                current = new Node();
                current.next = first;
            }

            public boolean hasNext() {
                return current.next != null;
            }

            public Item next() {
                current = current.next;
                return current.item;
            }

            public void remove() {
                throw new UnsupportedOperationException("Remove is not allowed.");
            }
        }

    public static void main(String[] args) {
        Deque<String> d = new Deque<>();

        d.addFirst("hello");
        d.addLast("world");
        d.addFirst("james");
        d.addLast("sarah");

        for (String s:d){
            StdOut.println(s);
        }

        d.removeFirst();
        d.removeLast();

        StdOut.println("Second test...");
        for (String s:d){
            StdOut.println(s);
        }

    }
}