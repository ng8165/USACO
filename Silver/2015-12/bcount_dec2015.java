// Breed Counting - USACO Silver December 2015 (http://www.usaco.org/index.php?page=viewproblem2&cpid=572)
// This problem was completed as classwork for the USACO Silver 2 Class on 6/21/21.

import java.io.*;
import java.util.StringTokenizer;

public class L15_bcount {
    public static void main(String[] args) throws IOException {
        // input
        String problemName = "bcount";
        BufferedReader br = new BufferedReader(new FileReader(problemName + ".in"));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int numCows = Integer.parseInt(st.nextToken());
        int numQueries = Integer.parseInt(st.nextToken());

        // algorithm and output

        // convert into prefix sums
        int[] holsteinsPSum = new int[numCows+1];
        int[] guernseysPSum = new int[numCows+1];
        int[] jerseysPSum = new int[numCows+1];

        for (int i=1; i<=numCows; i++) {
            int breed = Integer.parseInt(br.readLine());

            if (breed == 1) {
                holsteinsPSum[i] = holsteinsPSum[i-1] + 1;
                guernseysPSum[i] = guernseysPSum[i-1];
                jerseysPSum[i] = jerseysPSum[i-1];
            } else if (breed == 2) {
                holsteinsPSum[i] = holsteinsPSum[i-1];
                guernseysPSum[i] = guernseysPSum[i-1] + 1;
                jerseysPSum[i] = jerseysPSum[i-1];
            } else {
                holsteinsPSum[i] = holsteinsPSum[i-1];
                guernseysPSum[i] = guernseysPSum[i-1];
                jerseysPSum[i] = jerseysPSum[i-1] + 1;
            }
        }

        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(problemName + ".out")));

        for (int i=0; i<numQueries; i++) {
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            pw.println((holsteinsPSum[end] - holsteinsPSum[start-1]) + " " +
                       (guernseysPSum[end] - guernseysPSum[start-1]) + " " +
                       (jerseysPSum[end] - jerseysPSum[start-1]));
        }

        br.close();
        pw.close();
    }
}
