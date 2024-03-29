import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;


public class Permutation {

    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
         RandomizedQueue<String> rq = new RandomizedQueue<>();

         while (!StdIn.isEmpty()) {
             String ch = StdIn.readString();
             rq.enqueue(ch);
         }
        final Iterator<String> it = rq.iterator();
         for (int i = 0; i < k; i++) {
             if (it.hasNext()) {
                 StdOut.println(it.next());
             }
         }

    }
}
