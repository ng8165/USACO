// Why Did the Cow Cross the Road - USACO Bronze February 2017 (http://www.usaco.org/index.php?page=viewproblem2&cpid=711)
// This problem was done on Sunday, September 6, 2020, in 23 minutes, with all 10 test cases passed (second try)

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class crossroad_feb2017 {
    public static int crossroad(int[] cowID, int[] cowPositions) {
        int numObservations = cowID.length;
        int numCrossings = 0;

        for (int i=0; i<numObservations; i++) {
            int currCowID = cowID[i];
            int currCowPos = cowPositions[i];

            for (int j=i-1; j>=0; j--) {
                if (cowID[j] == currCowID) {
                    if (cowPositions[j] != currCowPos) {
                        numCrossings++;
                    }
                    break;
                }
            }
        }

        return numCrossings;
    }
    public static int crossroad2(int[] cowID, int[] cowPositions) {
        int numObservations = cowID.length;
        int numCrossings = 0;

        int[] cowLastPositons = new int[10+1];
        Arrays.fill(cowLastPositons, -1);

        for (int i=0; i<numObservations; i++) {
            int currCowID = cowID[i];
            int currCowLastPosition = cowLastPositons[currCowID];

            if (currCowLastPosition != -1 && currCowLastPosition != cowPositions[i]) {
                numCrossings++;
            }

            cowLastPositons[currCowID] = cowPositions[i];
        }

        return numCrossings;
    }

    public static void main(String[] args) throws IOException {
        // input
        String problemName = "crossroad";
        Scanner sc = new Scanner(new File(problemName + ".in"));

        int numObservations = sc.nextInt();
        int[] cowID = new int[numObservations];
        int[] cowPositions = new int[numObservations];
        for (int i=0; i<numObservations; i++) {
            cowID[i] = sc.nextInt();
            cowPositions[i] = sc.nextInt();
        }

        // algorithm
        int numCrossings = crossroad2(cowID, cowPositions);

        // output
        PrintWriter out = new PrintWriter(new FileWriter(problemName + ".out"));
        out.println(numCrossings);
        out.close();
    }
}
