// Cow Gymnastics - USACO Bronze December 2019 (http://www.usaco.org/index.php?page=viewproblem2&cpid=963)

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class L9_gymnastics {
    public static int gymnastics(int[][] cowRankings, int numPractices, int numCows) {
        int numPairs = 0;

        for (int cow1=1; cow1<=numCows; cow1++) {
            for (int cow2=1; cow2<=numCows; cow2++) {
                if (cow1 == cow2) {
                    continue;
                }

                boolean consistent = true; // to see if cow1 is consistently better than cow2

                for (int row=0; row<numPractices; row++) {
                    for (int column=0; column<numCows; column++) {
                        if (cowRankings[row][column] == cow1) {
                            // if cow1 comes first, it is correct, so we move onto next practice
                            break;
                        }

                        if (cowRankings[row][column] == cow2) {
                            // if cow2 comes first, then it is incorrect, consistent is false and we stop searching completely
                            consistent = false;
                            break;
                        }
                    }

                    if (!consistent) { // double break
                        break;
                    }
                }

                if (consistent) {
                    numPairs++;
                }
            }
        }

        return numPairs;
    }

    public static void main(String[] args) throws IOException {
        // input
        String problemName = "gymnastics";
        Scanner sc = new Scanner(new File(problemName + ".in"));
        int numPractices = sc.nextInt();
        int numCows = sc.nextInt();
        int[][] cowRankings = new int[numPractices][numCows];
        for (int i=0; i<numPractices; i++) {
            for (int j=0; j<numCows; j++) {
                cowRankings[i][j] = sc.nextInt();
            }
        }

        // algorithm
        int numPairs = gymnastics(cowRankings, numPractices, numCows);

        // output
        PrintWriter out = new PrintWriter(new FileWriter(problemName + ".out"));
        out.println(numPairs);
        out.close();
    }
}
