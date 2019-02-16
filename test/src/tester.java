import edu.princeton.cs.algs4.StdOut;

public class tester {

    public static void main(String[] args) {
        StdOut.println("Hello world");
        StdOut.printf("Is your name %s?\n", args[0]);

        TestingMan x = new TestingMan("kool");
        String y = x.sayThat();

        System.out.println(y);
    }
}
