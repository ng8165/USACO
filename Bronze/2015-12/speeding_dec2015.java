// Speeding Ticket - USACO Bronze December 2015 (http://www.usaco.org/index.php?page=viewproblem2&cpid=568)
// This problem was completed on November 25, 2020, in 24 minutes, with all 10/10 test cases passed (second try)

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class speeding_dec2015 {
    static int numRoadSegments;
    static int numBessieSegments;
    static int[][] road;
    static int[][] bessie;

    public static int speeding() {
        int maxSpeeding = 0;

        for (int i=0, j=0; i<numBessieSegments;) {
            // is bessie speeding
            if (bessie[i][1] > road[j][1]) {
                int speeding = bessie[i][1] - road[j][1];
                maxSpeeding = Math.max(maxSpeeding, speeding);
            }

            // modify bessie/road lengths or index
            if (bessie[i][0] > road[j][0]) {
                bessie[i][0] -= road[j][0];
                j++;
            } else if (bessie[i][0] < road[j][0]) {
                road[j][0] -= bessie[i][0];
                i++;
            } else {
                i++;
                j++;
            }
        }

        return maxSpeeding;
    }

    public static int speeding2() {
        int maxSpeeding = 0;
        int bIdx=0, rIdx=0;

        while (bIdx<numBessieSegments && rIdx<numRoadSegments) {
            // is bessie speeding
            if (bessie[bIdx][1] > road[rIdx][1]) {
                int speeding = bessie[bIdx][1] - road[rIdx][1];
                maxSpeeding = Math.max(maxSpeeding, speeding);
            }

            // modify bessie/road lengths or index
            if (bessie[bIdx][0] > road[rIdx][0]) {
                bessie[bIdx][0] -= road[rIdx][0];
                rIdx++;
            } else if (bessie[bIdx][0] < road[rIdx][0]) {
                road[rIdx][0] -= bessie[bIdx][0];
                bIdx++;
            } else {
                bIdx++;
                rIdx++;
            }
        }

        return maxSpeeding;
    }

    public static void main(String[] args) throws IOException {
        // input
        String problemName = "speeding";
        Scanner sc = new Scanner(new File(problemName + ".in"));

        numRoadSegments = sc.nextInt();
        numBessieSegments = sc.nextInt();
        road = new int[numRoadSegments][2];
        bessie = new int[numBessieSegments][2];
        for (int i=0; i<numRoadSegments; i++) {
            road[i][0] = sc.nextInt();
            road[i][1] = sc.nextInt();
        }
        for (int i=0; i<numBessieSegments; i++) {
            bessie[i][0] = sc.nextInt();
            bessie[i][1] = sc.nextInt();
        }

        // algorithm
        int maxSpeeding = speeding2();

        // output
        PrintWriter out = new PrintWriter(new FileWriter(problemName + ".out"));
        out.println(maxSpeeding);
        out.close();
    }
}
