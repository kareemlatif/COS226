/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;


public class Permutation {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        if (k == 0) return;


        RandomizedQueue<String> rq = new RandomizedQueue<String>();
        int count = 0;

        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            count++;

            if (rq.size() < k) {
                rq.enqueue(s);
            }
            else {
                int r = StdRandom.uniformInt(count);
                if (r < k) {
                    rq.dequeue();
                    rq.enqueue(s);
                }
            }
        }

        for (int i = 0; i < k; i++) {
            StdOut.println(rq.dequeue());
        }


    }
}
