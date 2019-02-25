import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item>{
        // because constant worst-case time is needed for all operations, implement using Doubly Linked-List

        private Node first = null;
        private Node last = null;
        private int numItems = 0;

        private class Node {
            Item item;
            Node next;
            Node previous;
        }

        public Deque() {
            // empty constructor
        }

        public boolean isEmpty() {
            return (first == null && last == null);
        }

        public int size() {
            return numItems;
        }

        public void addFirst(Item item) {
            if (item == null) {
                throw new IllegalArgumentException("Item provided is null.");
            }

            boolean startedEmpty = false;
            if (isEmpty()) {
                startedEmpty = true;
            }
            Node oldFirst = first;
            first = new Node();
            first.item = item;
            first.next = oldFirst;
            first.previous = null;
            oldFirst.previous = first;
            numItems++;

            if (startedEmpty) {
                last = first;
            }
        }

        public void addLast(Item item) {
            if (item == null) {
                throw new IllegalArgumentException("Item provided is null.");
            }

            boolean startedEmpty = false;
            if (isEmpty()) {
                startedEmpty = true;
            }
            Node oldLast = last;
            last = new Node();
            last.item = item;
            last.previous = oldLast;
            last.next = null;
            oldLast.next = last;
            numItems++;

            if (startedEmpty) {
                first = last;
            }

        }

        public Item removeFirst() {
            if (isEmpty()) {
                throw new NoSuchElementException("Deque is empty.");
            }
            Node oldFirst = first;
            first = oldFirst.next;
            first.previous = null;
            return oldFirst.item;
        }

        public Item removeLast() {
            if (isEmpty()) {
                throw new NoSuchElementException("Deque is empty.");
            }
            Node oldLast = last;
            last = oldLast.previous;
            last.next = null;
            return oldLast.item;

        }

        public Iterator<Item> iterator() {
            return new DequeIterator();
        }
        private class DequeIterator implements Iterator<Item> {
            private Node current = first;

            public boolean hasNext() {
                return !isEmpty();
            }

            public Item next() {
                Item item = current.item;
                current = current.next;
                return item;
            }

            public void remove() {
                throw new UnsupportedOperationException("Remove is not allowed.");
            }
        }

}
