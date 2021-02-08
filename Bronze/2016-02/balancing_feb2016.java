// Load Balancing - USACO Bronze February 2016 (http://www.usaco.org/index.php?page=viewproblem2&cpid=617)
// This problem was mostly completed on November 23, 2020, in 46 minutes, with 7/10 test cases passed (second try)
// This problem was completed on November 25, 2020, during review, with all 10/10 test cases passed (review)
// do again

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class balancing_feb2016 {
    static int numCows;
    static int largestCoordinates;
    static int[] x;
    static int[] y;

    public static int balancing() {
        int minMaxCows = Integer.MAX_VALUE;

        for (int i=0; i<numCows; i++) {
            for (int j=0; j<numCows; j++) {
                minMaxCows = Math.min(minMaxCows, countMaxCows(x[i]+1, y[j]+1));
            }
        }

        return minMaxCows;
    }

    public static int countMaxCows(int xLine, int yLine) {
        int Q1 = 0, Q2 = 0, Q3 = 0, Q4 = 0;
        for (int i=0; i<numCows; i++) {
            if (x[i] > xLine && y[i] > yLine) {
                Q1++;
            } else if (x[i] < xLine && y[i] > yLine) {
                Q2++;
            } else if (x[i] < xLine && y[i] < yLine) {
                Q3++;
            } else {
                Q4++;
            }
        }

        return Math.max(Q1, Math.max(Q2, Math.max(Q3, Q4)));
    }

    public static void main(String[] args) throws IOException {
        // input
        String problemName = "balancing";
        Scanner sc = new Scanner(new File(problemName + ".in"));

        numCows = sc.nextInt();
        largestCoordinates = sc.nextInt();

        x = new int[numCows];
        y = new int[numCows];
        for (int i=0; i<numCows; i++) {
            x[i] = sc.nextInt();
            y[i] = sc.nextInt();
        }

        // algorithm
        int maxCows = balancing();

        // output
        PrintWriter out = new PrintWriter(new FileWriter(problemName + ".out"));
        out.println(maxCows);
        out.close();
    }
}
