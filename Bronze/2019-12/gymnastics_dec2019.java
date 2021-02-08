// Cow Gymnastics - USACO Bronze December 2019 (http://www.usaco.org/index.php?page=viewproblem2&cpid=963)
// This problem was completed on November 15, 2020, in 18 minutes, with all 10/10 test cases passed (second try)

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class gymnastics_dec2019 {
    static int numPractices;
    static int numCows;
    static int[][] rankings;

    public static int gymnastics() {
        int consistentPairCnt = 0;

        for (int i=1; i<=numCows; i++) {
            for (int j=1; j<=numCows; j++) {
                if (i==j) {
                    continue;
                }

                if (isConsistentPair(i, j)) {
                    consistentPairCnt++;
                }
            }
        }

        return consistentPairCnt;
    }

    public static boolean isConsistentPair(int cow1, int cow2) {
        for (int i=0; i<numPractices; i++) {
            int cow1Pos = -1;
            int cow2Pos = -1;

            for (int j=0; j<numCows; j++) {
                if (rankings[i][j] == cow1) {
                    cow1Pos = j;
                } else if (rankings[i][j] == cow2) {
                    cow2Pos = j;
                }
            }

            if (cow1Pos > cow2Pos) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) throws IOException {
        // input
        String problemName = "gymnastics";
        Scanner sc = new Scanner(new File(problemName + ".in"));

        numPractices = sc.nextInt();
        numCows = sc.nextInt();
        rankings = new int[numPractices][numCows];
        for (int i=0; i<numPractices; i++) {
            for (int j=0; j<numCows; j++) {
                rankings[i][j] = sc.nextInt();
            }
        }

        // algorithm
        int numConsistentPairs = gymnastics();

        // output
        PrintWriter out = new PrintWriter(new FileWriter(problemName + ".out"));
        out.println(numConsistentPairs);
        out.close();
    }
}
