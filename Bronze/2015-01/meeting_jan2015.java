// Meeting Time - USACO Bronze January 2015 (http://www.usaco.org/index.php?page=viewproblem2&cpid=510)
// This problem was completed as homework for the USACO Silver 1 Class on 3/21/21.

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class L7_meeting {
    static class Field {
        int id;
        ArrayList<Field> adjList;

        Field(int id) {
            this.id = id;
            adjList = new ArrayList<>();
        }
    }

    static int numFields;
    static int numPaths;
    static Field[] fields;
    static int[][] pathID;
    static int[] bessieTimes;
    static int[] elsieTimes;

    static HashSet<Integer> bessieTravelTimes = new HashSet<>();
    static HashSet<Integer> elsieTravelTimes = new HashSet<>();

    public static int findNewTime(int origin, int destination, int[] times) {
        for (int i=0; i<numPaths; i++) {
            if (pathID[i][0] == origin && pathID[i][1] == destination) {
                return times[i];
            }
        }

        return 0; // avoid compilation error
    }

    public static void meetingDFS(Field field, int bessieTime, int elsieTime) {
        // base case
        if (field.id == numFields-1) {
            bessieTravelTimes.add(bessieTime);
            elsieTravelTimes.add(elsieTime);
            return;
        }

        for (Field neighbor: field.adjList) {
            meetingDFS(neighbor, bessieTime+findNewTime(field.id, neighbor.id, bessieTimes),
                    elsieTime+findNewTime(field.id, neighbor.id, elsieTimes));
        }
    }

    public static void main(String[] args) throws IOException {
        // input
        String problemName = "meeting";
        Scanner sc = new Scanner(new File(problemName + ".in"));

        numFields = sc.nextInt();
        numPaths = sc.nextInt();
        fields = new Field[numFields];
        pathID = new int[numPaths][2];
        bessieTimes = new int[numPaths];
        elsieTimes = new int[numPaths];
        for (int i=0; i<numFields; i++) {
            fields[i] = new Field(i);
        }
        for (int i=0; i<numPaths; i++) {
            int origin = sc.nextInt()-1;
            int destination = sc.nextInt()-1;

            fields[origin].adjList.add(fields[destination]);
            pathID[i][0] = origin;
            pathID[i][1] = destination;

            bessieTimes[i] = sc.nextInt();
            elsieTimes[i] = sc.nextInt();
        }

        // algorithm
        meetingDFS(fields[0], 0, 0);

        int minTime = Integer.MAX_VALUE;
        for (int bessieTime: bessieTravelTimes) {
            if (elsieTravelTimes.contains(bessieTime)) {
                minTime = Math.min(minTime, bessieTime);
            }
        }

        // output
        PrintWriter out = new PrintWriter(new FileWriter(problemName + ".out"));
        if (minTime == Integer.MAX_VALUE) {
            out.println("IMPOSSIBLE");
        } else {
            out.println(minTime);
        }
        out.close();
    }
}