// Marathon - USACO Bronze December 2014 (http://www.usaco.org/index.php?page=viewproblem2&cpid=487)

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class L8_marathon {
    public static int marathon(int numCheckpoints, int[] xCoordinates, int[] yCoordinates) {
        int minDistance = Integer.MAX_VALUE;
        int totalDistance = 0;

        // find total distance without skipping checkpoints
        for (int i=0; i<numCheckpoints-1; i++) {
            totalDistance += (Math.abs(xCoordinates[i] - xCoordinates[i+1]) + Math.abs(yCoordinates[i] - yCoordinates[i+1]));
        }

        // with i as index for skipped checkpoint, we subtract old distance and add new distance
        for (int i=1; i<numCheckpoints-1; i++) {
            int currDistance = totalDistance;

            int distance1 = Math.abs(xCoordinates[i-1] - xCoordinates[i]) + Math.abs(yCoordinates[i-1] - yCoordinates[i]);
            int distance2 = Math.abs(xCoordinates[i] - xCoordinates[i+1]) + Math.abs(yCoordinates[i] - yCoordinates[i+1]);
            int distance3 = Math.abs(xCoordinates[i-1] - xCoordinates[i+1]) + Math.abs(yCoordinates[i-1] - yCoordinates[i+1]);

            currDistance -= distance1;
            currDistance -= distance2;
            currDistance += distance3;

            minDistance = Math.min(currDistance, minDistance);
        }

        return minDistance;
    }

    public static void main(String[] args) throws IOException {
        // input
        String problemName = "marathon";
        Scanner sc = new Scanner(new File(problemName + ".in"));
        int numCheckpoints = sc.nextInt();
        int[] xCoordinates = new int[numCheckpoints];
        int[] yCoordinates = new int[numCheckpoints];
        for (int i=0; i<numCheckpoints; i++) {
            xCoordinates[i] = sc.nextInt();
            yCoordinates[i] = sc.nextInt();
        }

        // algorithm
        int minDistance = marathon(numCheckpoints, xCoordinates, yCoordinates);

        // output
        PrintWriter out = new PrintWriter(new FileWriter(problemName + ".out"));
        out.println(minDistance);
        out.close();
    }
}
