import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        String s;
        RandomizedQueue<String> r = new RandomizedQueue<>();

        // read all strings into RandomizedQueue
        while (!StdIn.isEmpty()) {
            s = StdIn.readString();
            r.enqueue(s);
        }

        for (int i = 0; i < k; i++) {
            String item = r.dequeue();
            StdOut.println(item);
        }

    }
}
