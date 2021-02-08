// Why Did the Cow Cross the Road II - USACO Bronze February 2017 (http://www.usaco.org/index.php?page=viewproblem2&cpid=712)

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class L14_circlecross {
    public static int circlecross1(char[] crossingPoints) {
        int overlapCnt = 0;

        for (char c1 = 'A'; c1 < 'Z'; c1++) {
            for (char c2 = c1; c2 < 'Z'; c2++) {
                if (c1 == c2) {
                    continue;
                }

                // identify the two enter/exit points of c1 and c2
                int[] c1_points = new int[2];
                int[] c2_points = new int[2];
                for (int i=0, cnt1=0, cnt2=0; i<crossingPoints.length; i++) {
                    if (crossingPoints[i] == c1) {
                        c1_points[cnt1] = i;
                        cnt1++;
                    } else if (crossingPoints[i] == c2) {
                        c2_points[cnt2] = i;
                        cnt2++;
                    }
                }

                // see if the points of c1 and c2 partial-overlap (complete-overlap is bad)
                if (c1_points[0] < c2_points[0] && c1_points[1] > c2_points[0] && c1_points[1] < c2_points[1]) {
                    overlapCnt++;
                } else if (c1_points[0] > c2_points[0] && c1_points[0] < c2_points[1] && c1_points[1] > c2_points[1]) {
                    overlapCnt++;
                }
            }
        }

        return overlapCnt;
    }
    public static int circlecross2(char[] crossingPoints) {
        int totalCrossings = 0;
        for (int i=0; i<crossingPoints.length; i++) { // i is index for
            char cow = crossingPoints[i];

            // first find the range for the letter you are using
            int startRange = i;
            int endRange = -1;

            for (int j=i+1; j<crossingPoints.length; j++) {
                if (crossingPoints[j] == cow) {
                    endRange = j;
                    break;
                }
            }

            // check if we can even find the letter a second time (this is the case for the end char)
            if (endRange == -1) {
                continue;
            }

            // now we will go through the sectioned array in between the start and end points
            for (int j=startRange+1; j<endRange; j++) {
                char currCow = crossingPoints[j];
                int currCrossings = 0;

                for (int k=startRange+1; k<endRange; k++) {
                    if (crossingPoints[k] == currCow) {
                        currCrossings++;
                    }
                }

                if (currCrossings == 1) {
                    totalCrossings++;
                }
            }
        }

        totalCrossings /= 2; // we double-counted, so divide by 2 to single-count

        return totalCrossings;
    }

    public static void main(String[] args) throws IOException {
        // input
        String problemName = "circlecross";
        Scanner sc = new Scanner(new File(problemName + ".in"));

        String crossingPoints = sc.next();

        // algorithm
        int numCrossingPairs = circlecross2(crossingPoints.toCharArray());

        // output
        PrintWriter out = new PrintWriter(new FileWriter(problemName + ".out"));
        out.println(numCrossingPairs);
        out.close();
    }
}
