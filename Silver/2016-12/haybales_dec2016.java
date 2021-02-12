// Counting Haybales - USACO Silver December 2016 (http://www.usaco.org/index.php?page=viewproblem2&cpid=666)
// This problem was finished on 2/11/21 as homework for the USACO Silver 1 Class.

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class L2_haybales {
    public static void main(String[] args) throws IOException {
        // input
        String problemName = "haybales";
        Scanner sc = new Scanner(new File(problemName + ".in"));

        int numHaybales = sc.nextInt();
        int numQueries = sc.nextInt();
        int[] haybales = new int[numHaybales];
        for (int i=0; i<numHaybales; i++) {
            haybales[i] = sc.nextInt();
        }
        int[][] queries = new int[numQueries][2];
        for (int i=0; i<numQueries; i++) {
            queries[i][0] = sc.nextInt();
            queries[i][1] = sc.nextInt();
        }

        // algorithm and output
        PrintWriter out = new PrintWriter(new FileWriter(problemName + ".out"));

        Arrays.sort(haybales); // prepare for binary search

        for (int i=0; i<numQueries; i++) {
            int rangeLeft = queries[i][0];
            int rangeRight = queries[i][1];

            int leftPos = Arrays.binarySearch(haybales, rangeLeft);
            int rightPos = Arrays.binarySearch(haybales, rangeRight);

            boolean doesRightExist = true; // if there is no haybale at the right position, we should not add 1
            if (leftPos < 0) {
                leftPos = -leftPos-1;
            }
            if (rightPos < 0) {
                doesRightExist = false;
                rightPos = -rightPos-1;
            }

            if (doesRightExist) {
                out.println(rightPos-leftPos+1);
            } else {
                out.println(rightPos-leftPos);
            }
        }

        out.close();
    }
}
