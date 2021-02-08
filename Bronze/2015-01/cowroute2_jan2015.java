// Cow Routing II - USACO Bronze January 2015 (http://www.usaco.org/index.php?page=viewproblem2&cpid=508)
// This problem was completed on November 28, 2020, in 1 hour 28 minutes, with 7/10 test cases passed (first try)
// This problem was completed on November 29, 2020, during review, with all 10/10 test cases passed (review)
// Do again: pretty hard to get it right on first try (this likely barely passed all test cases)

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class cowroute2_jan2015 {
    static int startingCity;
    static int destinationCity;
    static int numRoutes;
    static int[] cost;
    static int[] numStops;
    static ArrayList<Integer>[] stops;

    public static int cowroute() {
        int minCost = Integer.MAX_VALUE;

        for (int i=0; i<numRoutes; i++) {
            int startIdx = stops[i].indexOf(startingCity);
            int destIdx = stops[i].indexOf(destinationCity);

            System.out.println(i + ": " + startIdx + ", " + destIdx);

            if (startIdx != -1 && destIdx != -1 && startIdx < destIdx) {
                // the route can be a direct route and will not be considered for non-direct routes
                System.out.println("found direct cost: " + cost[i]);
                minCost = Math.min(minCost, cost[i]);
            } else if (startIdx != -1) {
                // we can start from here, but it isn't direct, so let's try non-direct flights
                for (int j=startIdx+1; j<numStops[i]; j++) {
                    int flight2Cost = findMinCostDirect(stops[i].get(j), destinationCity);

                    if (flight2Cost != -1) {
                        int costNonDirect = cost[i] + flight2Cost;
                        System.out.println("found non-direct cost from city " + stops[i].get(j) + ": " + costNonDirect);

                        minCost = Math.min(minCost, costNonDirect);
                    }
                }
            }
        }

        if (minCost == Integer.MAX_VALUE) {
            return -1;
        } else {
            return minCost;
        }
    }

    public static int findMinCostDirect(int starting, int destination) {
        int minCost = Integer.MAX_VALUE;

        for (int i=0; i<numRoutes; i++) {
            int startingLoc = -1;
            int destinationLoc = -1;

            for (int j=0; j<numStops[i]; j++) {
                if (stops[i].get(j) == starting) {
                    startingLoc = j;
                } else if (stops[i].get(j) == destination) {
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
