import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<item>{
    // use resizing array: 100% full to double

    private int N = 0;
    private Item[] s;

    public RandomizedQueue() {
        // init at length 1 capacity
        s = (Item[]) new Object[1];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Enqueue called with null argument");
        }
        // double capacity if full
        if (N == s.length) {
            Item[] temp = (Item[]) new Object[s.length * 2];
            for (int i = 0; i < N; i++) {
                temp[i] = s[i];
            }
            s = temp;
        }
        N++;
        s[N] = item;
    }

    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Dequeue called with empty RQ");
        }

        // search for random values
        int n = StdRandom.uniform(size);
        value = s[n];

        // set the retrieved value to last value, and set last value to null
        s[n] = s[N];
        s[N] = null;
        N--;

        // resize if less than 25% full
        if (N <= s.length/4) {
            Item[] temp = (Item[]) new Object[s.length / 2];
            for (int i = 0; i < N; i++) {
                temp[i] = s[i];
            }
            s = temp;
        }

        return value;
    }

    public Item sample() {
        if(isEmpty()) {
            throw new NoSuchElementException("Sample called with empty RQ");
        }

        // search for random values
        int n = StdRandom.uniform(size);
        value = s[n];

        return value;

    }

    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {

        private RandomizedQueueIterator() {

        }

        public boolean hasNext() {
            //
        }

        public Item next() {
            //
        }

        public void remove() {
            throw new UnsupportedOperationException("Remove is not allowed.");
        }

    }
}
