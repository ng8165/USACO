// Cow Routing - USACO Bronze January 2015 (http://www.usaco.org/index.php?page=viewproblem2&cpid=507)
// This problem was completed on November 28, 2020, in 17 minutes, with all 12/12 test cases passed (second try)

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class cowroute_jan2015 {
    static int startingCity;
    static int destinationCity;
    static int numRoutes;
    static int[] cost;
    static int[] numStops;
    static ArrayList<Integer>[] stops;

    public static int cowroute() {
        int minCost = Integer.MAX_VALUE;

        for (int i=0; i<numRoutes; i++) {
            int startingLoc = -1;
            int destinationLoc = -1;

            for (int j=0; j<numStops[i]; j++) {
                if (stops[i].get(j) == startingCity) {
                    startingLoc = j;
                } else if (stops[i].get(j) == destinationCity) {
                    destinationLoc = j;
                }
            }

            if (startingLoc != -1 && destinationLoc != -1 && startingLoc < destinationLoc) {
                minCost = Math.min(minCost, cost[i]);
            }
        }

        if (minCost == Integer.MAX_VALUE) {
            return -1;
        } else {
            return minCost;
        }
    }

    public static void main(String[] args) throws IOException {
        // input
        String problemName = "cowroute";
        Scanner sc = new Scanner(new File(problemName + ".in"));

        startingCity = sc.nextInt();
        destinationCity = sc.nextInt();
        numRoutes = sc.nextInt();
        cost = new int[numRoutes];
        numStops = new int[numRoutes];
        stops = new ArrayList[numRoutes];
        for (int i=0; i<numRoutes; i++) {
            cost[i] = sc.nextInt();
            numStops[i] = sc.nextInt();
            stops[i] = new ArrayList<>();
            for (int j=0; j<numStops[i]; j++) {
                stops[i].add(sc.nextInt());
            }
        }

        // algorithm
        int minCost = cowroute();

        // output
        PrintWriter out = new PrintWriter(new FileWriter(problemName + ".out"));
        out.println(minCost);
        out.close();
    }
}
