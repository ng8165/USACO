// Taming the Herd - USACO Bronze February 2018 (http://www.usaco.org/index.php?page=viewproblem2&cpid=809)
// This problem was mostly completed on October 4, 2020, in 31 minutes, with 9/10 test cases passed (first try)
// This problem was completed on October 10, 2020, during review, with all 10/10 test cases passed (review)

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class taming_feb2018 {
    static int numEntries;
    static int[] entries;

    public static int[] taming() {
        // first reconstruct the entries as much as we can
        entries[0] = 0;
        for (int i=0; i<numEntries; i++) {
            int cnt = entries[i];
            if (cnt > 0) {
                for (int j=1; j<=entries[i]; j++) {
                    if (i-j < 0) {
                        return new int[] {-1};
                    }

                    cnt--;
                    if (entries[i-j] == -1) {
                        entries[i-j] = cnt;
                    } else if (entries[i-j] != cnt) {
                        return new int[] {-1};
                    }
                }
            }
        }

        // now find the number of breakouts
        // to find min breakout num, assume anything unknown is not a breakout
        // to find max breakout num, assume anything unknown is a breakout
        int minBreakoutNum = 0;
        int maxBreakoutNum = 0;

        for (int i=0; i<numEntries; i++) {
            if (entries[i] == 0) {
                minBreakoutNum++;
                maxBreakoutNum++;
            } else if (entries[i] == -1) {
                maxBreakoutNum++;
            }
        }

        return new int[] {minBreakoutNum, maxBreakoutNum};
    }

    public static void main(String[] args) throws IOException {
        // input
        String problemName = "taming";
        Scanner sc = new Scanner(new File(problemName + ".in"));

        numEntries = sc.nextInt();
        entries = new int[numEntries];
        for (int i=0; i<numEntries; i++) {
            entries[i] = sc.nextInt();
        }

        // algorithm
        int[] minmaxBreakouts = taming();

        // output
        PrintWriter out = new PrintWriter(new FileWriter(problemName + ".out"));
        if (minmaxBreakouts.length == 1) {
            out.println(minmaxBreakouts[0]);
        } else {
            out.println(minmaxBreakouts[0] + " " + minmaxBreakouts[1]);
        }
        out.close();
    }
}
