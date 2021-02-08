// Cow Gymnastics - USACO Bronze December 2019 (http://www.usaco.org/index.php?page=viewproblem2&cpid=963)
// This problem was completed on December 20, 2020, in 9 minutes, with 10/10 test cases passed (second try)

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class gymnastics_dec2019 {
    static int numPractices;
    static int numCows;
    static int[][] performances;

    public static int gymnastics() {
        int numConsistentPairs = 0;

        for (int i=1; i<=numCows; i++) {
            for (int j=1; j<=numCows; j++) {
                if (i==j) {
                    continue;
                }

                if (isConsistentPair(i, j)) {
                    numConsistentPairs++;
                }
            }
        }

        return numConsistentPairs;
    }

    public static boolean isConsistentPair(int cow1, int cow2) {
        for (int i=0; i<numPractices; i++) {
            int cow1Idx = -1, cow2Idx = -1;

            for (int j=0; j<numCows; j++) {
                if (performances[i][j] == cow1) {
                    cow1Idx = j;
                } else if (performances[i][j] == cow2) {
                    cow2Idx = j;
                }
            }

            if (cow2Idx > cow1Idx) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) throws IOException {
        // input
        /*String problemName = "gymnastics";
        Scanner sc = new Scanner(new File(problemName + ".in"));*/
        Scanner sc = new Scanner(System.in);

        numPractices = sc.nextInt();
        numCows = sc.nextInt();
        performances = new int[numPractices][numCows];
        for (int i=0; i<numPractices; i++) {
            for (int j=0; j<numCows; j++) {
                performances[i][j] = sc.nextInt();
            }
        }

        // algorithm
        int numConsistentPairs = gymnastics();

        // output
        /*PrintWriter out = new PrintWriter(new FileWriter(problemName + ".out"));
        out.println(numConsistentPairs);
        out.close();*/
        System.out.println(numConsistentPairs);
    }
}
