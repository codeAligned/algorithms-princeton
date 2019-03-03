import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item>{
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
            throw new IllegalArgumentException("Enqueue called with null argument.");
        }
        // double capacity if full
        if (N == s.length) {
            Item[] temp = (Item[]) new Object[s.length * 2];
            for (int i = 0; i < N; i++) {
                temp[i] = s[i];
            }
            s = temp;
        }
        s[N] = item;
        N++;
    }

    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Dequeue called with empty RQ.");
        }

        // search for random values
        int n = StdRandom.uniform(N);
        Item value = s[n];

        // set the retrieved index value to last value, and set last value to null
        s[n] = s[N - 1];
        s[N - 1] = null;
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
            throw new NoSuchElementException("Sample called with empty RQ.");
        }

        // search for random values
        int n = StdRandom.uniform(N);
        Item value = s[n];

        return value;

    }

    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {

        private Item[] copy;
        private int copySize = N;

        private RandomizedQueueIterator() {
            copy = (Item[]) new Object[copySize];
            for (int i = 0; i < copySize; i++ ){
                copy[i] = s[i];
            }

        }

        public boolean hasNext() {
            return copySize > 0;
        }

        public Item next() {
            if (!hasNext()){ throw new IllegalArgumentException("next() called when no more next items.");}

            // search for random values
            int n = StdRandom.uniform(copySize);
            Item value = copy[n];

            // set the retrieved value to last value, and set last value to null
            copy[n] = copy[copySize - 1];
            copy[copySize - 1] = null;
            copySize--;
            return value;
        }

        public void remove() {
            throw new UnsupportedOperationException("Remove is not allowed.");
        }

    }

    public static void main(String[] args) {
        RandomizedQueue<String> r = new RandomizedQueue<>();

        r.enqueue("to");
        r.enqueue("be");
        r.enqueue("or");
        r.dequeue();
        var x = r.sample();

        StdOut.println("Random sample: "+x);

        StdOut.println("Enumerating...");
        for (String s: r) {
            StdOut.println(s);
        }
    }
}
