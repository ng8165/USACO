// Sleepy Cow Herding - USACO Silver February 2019 (http://www.usaco.org/index.php?page=viewproblem2&cpid=918)
// This problem was completed as classwork for the USACO Silver 2 Class on 7/3/21.
// DO AGAIN (done with tons of assistance from Ms. Wen's slides)

import java.io.*;
import java.util.Arrays;

public class L17_herding {
    public static void main(String[] args) throws IOException {
        // input
        String problemName = "herding";
        BufferedReader br = new BufferedReader(new FileReader(problemName + ".in"));

        int numCows = Integer.parseInt(br.readLine());
        int[] cows = new int[numCows];
        for (int i=0; i<numCows; i++) {
            cows[i] = Integer.parseInt(br.readLine());
        }

        // algorithm
        Arrays.sort(cows);

        int minCowMoves;

        if ((cows[numCows-2]-cows[0]+1 == numCows-1 && cows[numCows-1]-cows[numCows-2]-1 > 1) ||
            (cows[numCows-1]-cows[1]+1 == numCows-1 && cows[1]-cows[0]-1 > 1)) {
            // special case: an outlier with all other cows consecutive
            minCowMoves = 2;
        } else {
            // count first window
            int cowsInWindow = 0;
            int left = 0, right = 0;

            while (right < numCows && cows[right] < cows[0]+numCows) {
                cowsInWindow++;
                right++;
            }

            int maxCowsInWindow = cowsInWindow;

            // sliding window for min
            while (left < numCows && right < numCows) {
                if (cows[right] < cows[left]+numCows) {
                    cowsInWindow++;
                    right++;
                } else {
                    cowsInWindow--;
                    left++;
                }

                maxCowsInWindow = Math.max(maxCowsInWindow, cowsInWindow);
            }

            minCowMoves = numCows-maxCowsInWindow;
        }

        // calculate max
        int shiftFromRight = cows[numCows-2] - (cows[0] + numCows - 1) + 1;
        int shiftFromLeft = (cows[numCows-1] - numCows + 1) - cows[1] + 1;

        int maxCowMoves = Math.max(shiftFromLeft, shiftFromRight);

        // output
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(problemName + ".out")));

        pw.println(minCowMoves);
        pw.println(maxCowMoves);

        br.close();
        pw.close();
    }
}
