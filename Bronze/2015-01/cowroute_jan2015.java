// Cow Routing - USACO Bronze January 2015 (http://www.usaco.org/index.php?page=viewproblem2&cpid=507)

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class L5_cowroute {
    public static void main(String[] args) throws IOException {
        // input
        String problemName = "cowroute";
        Scanner sc = new Scanner(new File(problemName + ".in"));
        int A = sc.nextInt();
        int B = sc.nextInt();
        int N = sc.nextInt();

        // algorithm
        int totalCost = Integer.MAX_VALUE;
        for (int i=0; i<N; i++) {
            // inputs
            int cost = sc.nextInt();
            int totalStops = sc.nextInt();
            int[] stops = new int[totalStops];
            for (int j=0; j<totalStops; j++) {
                stops[j] = sc.nextInt();
            }
            /*System.out.println("$" + cost + ", " + totalStops + " stops");
            for (int j=0; j<totalStops; j++) {
                System.out.print(stops[j] + " ");
            }*/

            int startLoc = Integer.MAX_VALUE; // we use max value to make comparisons easier in the loop
            int endLoc = Integer.MAX_VALUE;
            boolean A_found = false;
            for (int j=0; j<totalStops; j++) {
                if (!A_found) {
                    if (stops[j] == A) {
                        startLoc = j;
                        A_found = true;
                    }
                } else {
                    if (stops[j] == B) {
                        endLoc = j;
                        break;
                    }
                }
            }
            //System.out.println("\n" + startLoc + " " + endLoc);
            if (startLoc != Integer.MAX_VALUE && endLoc != Integer.MAX_VALUE) {
                // if either start or end is big value, then do nothing, try next
                totalCost = Math.min(cost, totalCost);
            }
        }

        if (totalCost == Integer.MAX_VALUE) { // if it is the max value, change to -1 to satisfy requirements
            totalCost = -1;
        }

        //output
        PrintWriter out = new PrintWriter(new FileWriter(problemName + ".out"));
        out.println(totalCost);
        out.close();
    }
}
