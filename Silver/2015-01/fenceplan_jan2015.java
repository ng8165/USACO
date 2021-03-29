// Meeting Time - USACO Bronze January 2015 (http://www.usaco.org/index.php?page=viewproblem2&cpid=510)
// This problem was completed as classwork for the USACO Silver 1 Class on 3/28/21.

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class L8_fenceplan {
    static class Cow {
        int id;
        ArrayList<Cow> adjList;
        int x;
        int y;

        Cow(int id, int x, int y) {
            this.id = id;
            adjList = new ArrayList<>();
            this.x = x;
            this.y = y;
        }
    }

    static int numCows;
    static int numGroups;
    static Cow[] cows;

    static boolean[] visited;
    static int minX, maxX, minY, maxY;

    public static void fenceplanDFS(Cow cow) {
        visited[cow.id] = true;

        minX = Math.min(minX, cow.x);
        maxX = Math.max(maxX, cow.x);
        minY = Math.min(minY, cow.y);
        maxY = Math.max(maxY, cow.y);

        for (Cow friend: cow.adjList) {
            if (!visited[friend.id]) {
                fenceplanDFS(friend);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        // input
        String problemName = "fenceplan";
        Scanner sc = new Scanner(new File(problemName + ".in"));

        numCows = sc.nextInt();
        numGroups = sc.nextInt();
        cows = new Cow[numCows+1];
        for (int i=1; i<=numCows; i++) {
            cows[i] = new Cow(i, sc.nextInt(), sc.nextInt());
        }
        for (int i=0; i<numGroups; i++) {
            int cow1 = sc.nextInt(), cow2 = sc.nextInt();
            cows[cow1].adjList.add(cows[cow2]);
            cows[cow2].adjList.add(cows[cow1]);
        }
        visited = new boolean[numCows+1];

        // algorithm
        int minPerimeter = Integer.MAX_VALUE;
        for (int i=1; i<=numCows; i++) {
            if (!visited[i]) {
                // new cow group found
                minX = maxX = cows[i].x;
                minY = maxY = cows[i].y;

                fenceplanDFS(cows[i]);

                int perimeter = ((maxX-minX)+(maxY-minY))*2;
                minPerimeter = Math.min(minPerimeter, perimeter);
            }
        }

        // output
        PrintWriter out = new PrintWriter(new FileWriter(problemName + ".out"));
        out.println(minPerimeter);
        out.close();
    }
}
