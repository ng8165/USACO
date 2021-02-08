// The Bovine Shuffle - USACO Bronze December 2017 (http://www.usaco.org/index.php?page=viewproblem2&cpid=760)

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class L11_shuffle {
    public static int[] shuffle(int[] shuffleOrder, int[] endOrder, int numCows) {
        // generate un-shuffle order
        int[] unShuffleOrder = new int[numCows+1];

        for (int i=1; i<numCows+1; i++) {
            unShuffleOrder[shuffleOrder[i]] = i;
        }

        // un-shuffle 3 times
        int[] currOrder = endOrder;

        for (int i=0; i<3; i++) {
            currOrder = shuffleCows(currOrder, unShuffleOrder, numCows);
        }

        return currOrder;
    }
    public static int[] shuffleCows(int[] oldOrder, int[] shuffleOrder, int numCows) {
        int[] newOrder = new int[numCows+1];

        for (int i=1; i<numCows+1; i++) {
            newOrder[shuffleOrder[i]] = oldOrder[i];
        }

        return newOrder;
    }

    public static void main(String[] args) throws IOException {
        // input
        String problemName = "shuffle";
        Scanner sc = new Scanner(new File(problemName + ".in"));
        int numCows = sc.nextInt();
        int[] shuffleOrder = new int[numCows+1];
        int[] endOrder = new int[numCows+1];
        for (int i=1; i<numCows+1; i++) {
            shuffleOrder[i] = sc.nextInt();
        }
        for (int i=1; i<numCows+1; i++) {
            endOrder[i] = sc.nextInt();
        }

        // algorithm
        int[] startOrder = shuffle(shuffleOrder, endOrder, numCows);

        // output
        PrintWriter out = new PrintWriter(new FileWriter(problemName + ".out"));
        for (int i=1; i<numCows+1; i++) {
            out.println(startOrder[i]);
        }
        out.close();
    }
}
