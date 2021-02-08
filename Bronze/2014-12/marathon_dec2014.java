// Marathon - USACO Bronze December 2014 (http://www.usaco.org/index.php?page=viewproblem2&cpid=487)
// This problem was completed on December 5, 2020, 10 minutes, with all 15/15 test cases passed (second try)

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class marathon_dec2014 {
    static int numCheckpoints;
    static int[][] checkpoints;
    static int distance = 0;

    public static int marathon() {
        int minDistance = Integer.MAX_VALUE;

        for (int i=1; i<numCheckpoints-1; i++) {
            int dist1 = Math.abs(checkpoints[i][0] - checkpoints[i-1][0]) + Math.abs(checkpoints[i][1] - checkpoints[i-1][1]);
            int dist2 = Math.abs(checkpoints[i+1][0] - checkpoints[i][0]) + Math.abs(checkpoints[i+1][1] - checkpoints[i][1]);
            int dist3 = Math.abs(checkpoints[i+1][0] - checkpoints[i-1][0]) + Math.abs(checkpoints[i+1][1] - checkpoints[i-1][1]);

            minDistance = Math.min(minDistance, (dist3 - dist1 - dist2));
        }

        return distance + minDistance;
    }

    public static void main(String[] args) throws IOException {
        // input
        String problemName = "marathon";
        Scanner sc = new Scanner(new File(problemName + ".in"));

        numCheckpoints = sc.nextInt();
        checkpoints = new int[numCheckpoints][2];
        for (int i=0; i<numCheckpoints; i++) {
            checkpoints[i][0] = sc.nextInt();
            checkpoints[i][1] = sc.nextInt();
            if (i>0) {
                distance += Math.abs(checkpoints[i][0] - checkpoints[i-1][0]) + Math.abs(checkpoints[i][1] - checkpoints[i-1][1]);
            }
        }

        // algorithm
        int minDistance = marathon();

        // output
        PrintWriter out = new PrintWriter(new FileWriter(problemName + ".out"));
        out.println(minDistance);
        out.close();
    }
}
