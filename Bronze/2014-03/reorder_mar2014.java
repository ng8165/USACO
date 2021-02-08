// Reordering the Cows - USACO Bronze March 2014 (http://www.usaco.org/index.php?page=viewproblem2&cpid=412)
// This problem was completed on December 13, 2020, in 52 minutes (with Mom's help), with 10/10 test cases passed (first try)
// Do again!

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class reorder_mar2014 {
    static int numCows;
    static int[] startingOrder;
    static int[] endingOrder;

    public static int[] reorder(int[] finalIdxs) {
        if (Arrays.equals(startingOrder, endingOrder)) {
            return new int[] {0, -1};
        }

        int numShifts = 0;
        int maxShiftedCows = -1;

        for (int i=0; i<numCows; i++) {
            // should we try a simulation starting from this cow?
            if (startingOrder[i] == -1 || startingOrder[i] == endingOrder[i]) {
                continue;
            }

            int currCow = startingOrder[i];
            int currShiftedCows = 0;
            int newIdx = i;

            while (true) {
                startingOrder[newIdx] = -1;

                newIdx = finalIdxs[currCow-1];

                // update the counts
                currShiftedCows++;

                if (newIdx == i) {
                    break;
                } else {
                    // update current cow
                    currCow = startingOrder[newIdx];
                }
            }

            numShifts++;
            maxShiftedCows = Math.max(maxShiftedCows, currShiftedCows);
        }

        return new int[] {numShifts, maxShiftedCows};
    }

    public static int searchNewIdx(int currCow) {
        for (int i=0; i<numCows; i++) {
            if (currCow == endingOrder[i]) {
                return i;
            }
        }

        return -1; // avoid compilation error
    }

    public static void main(String[] args) throws IOException {
        // input
        String problemName = "reorder";
        Scanner sc = new Scanner(new File(problemName + ".in"));

        numCows = sc.nextInt();
        startingOrder = new int[numCows];
        endingOrder = new int[numCows];
        int[] finalIdxs = new int[numCows]; // Jimmy's idea
        for (int i=0; i<numCows; i++) {
            startingOrder[i] = sc.nextInt();
        }
        for (int i=0; i<numCows; i++) {
            endingOrder[i] = sc.nextInt();
            finalIdxs[endingOrder[i]-1] = i;
        }

        // algorithm
        int[] shiftResults = reorder(finalIdxs);

        // output
        PrintWriter out = new PrintWriter(new FileWriter(problemName + ".out"));
        out.println(shiftResults[0] + " " + shiftResults[1]);
        out.close();
    }
}
