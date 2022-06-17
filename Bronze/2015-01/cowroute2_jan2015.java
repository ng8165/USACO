// Cow Routing 2 - USACO Bronze January 2015 (http://www.usaco.org/index.php?page=viewproblem2&cpid=508)
// This problem was completed as homework for the USACO Silver 1 Class on 3/6/21.

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class L5_cowroute {
    static int origin;
    static int destination;
    static int numFlights;
    static int[] cost;
    static ArrayList<Integer>[] flights;
    static HashMap<Integer, Integer> fromOrigin = new HashMap<>(); // key = city, value = flight idx
    static HashMap<Integer, Integer> toDestination = new HashMap<>();

    public static int cowroute() {
        for (int i=0; i<numFlights; i++) {
            // find cities where the origin has flights to
            boolean originFound = false;
            for (int city: flights[i]) {
                if (origin == city) {
                    originFound = true;
                    continue;
                }

                if (originFound) {
                    // ensure that this flight is the cheapest way to get to that city
                    if (!fromOrigin.containsKey(city)) {
                        fromOrigin.put(city, i);
                    } else if (cost[i] < cost[fromOrigin.get(city)]) {
                        fromOrigin.put(city, i);
                    }
                }
            }

            // find cities which have flights to destination
            boolean destinationFound = false;
            for (int j=flights[i].size()-1; j>=0; j--) {
                int city = flights[i].get(j);
                if (destination == city) {
                    destinationFound = true;
                    continue;
                }

                if (destinationFound) {
                    // ensure that this flight is the cheapest way to get to destination
                    if (!toDestination.containsKey(city)) {
                        toDestination.put(city, i);
                    } else if (cost[i] < cost[toDestination.get(city)]) {
                        toDestination.put(city, i);
                    }
                }
            }
        }

        // find minimum cost
        int minCost = Integer.MAX_VALUE;
        for (Map.Entry<Integer, Integer> entry: fromOrigin.entrySet()) {
            int city = entry.getKey();
            int flightIdx = entry.getValue();

            if (city == destination) {
                // direct flight
                minCost = Math.min(minCost, cost[flightIdx]);
            } else if (toDestination.containsKey(city) && flightIdx != toDestination.get(city)) {
                // city is present in both HashMaps (ok for transit) and idx are not the same
                minCost = Math.min(minCost, cost[flightIdx] + cost[toDestination.get(city)]);
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

        origin = sc.nextInt();
        destination = sc.nextInt();
        numFlights = sc.nextInt();
        cost = new int[numFlights];
        flights = new ArrayList[numFlights];
        for (int i=0; i<numFlights; i++) {
            cost[i] = sc.nextInt();

            int numCities = sc.nextInt();
            flights[i] = new ArrayList<>();
            for (int j=0; j<numCities; j++) {
                flights[i].add(sc.nextInt());
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
