// Lifeguards - USACO Bronze January 2018 (http://www.usaco.org/index.php?page=viewproblem2&cpid=784)
// This problem was completed on September 27, 2020, in 35 minutes, with all 10/10 test cases passed (second try)

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class lifeguards_jan2018 {
    static int numLifeguards;
    static int[][] lifeguardShifts;

    public static int lifeguards() {
        int maxLifeguardTime = 0;

        for (int i=0; i<numLifeguards; i++) { // i is the index for which lifeguard to fire
            int currLifeguardTime = 0;

            // first generate a timeline
            int[] timeline = new int[numLifeguards*2-2];
            for (int j=0, idx=0; j<numLifeguards; j++) {
                if (j == i) {
                    continue;
                }
                timeline[idx++] = lifeguardShifts[j][0];
                timeline[idx++] = lifeguardShifts[j][1];
            }
            Arrays.sort(timeline);

            // iterate through the timeline: add current lifeguard time, update lifeguard num
            int currLifeguardCnt = 0;

            for (int j=0; j<timeline.length; j++) {
                if (currLifeguardCnt > 0) {
                    currLifeguardTime += (timeline[j] - timeline[j-1]);
                }

                // update lifeguard count
                if (isShiftStartTime(timeline[j])) {
                    currLifeguardCnt++;
                } else {
                    currLifeguardCnt--;
                }
            }

            maxLifeguardTime = Math.max(maxLifeguardTime, currLifeguardTime);
        }

        return maxLifeguardTime;
    }

    public static boolean isShiftStartTime(int time) {
        for (int i=0; i<numLifeguards; i++) {
            if (lifeguardShifts[i][0] == time) { // check for start time
                return true;
            } else if (lifeguardShifts[i][1] == time) { // check for end time
                return false;
            }
        }

        return true; // this should never happen, avoid compilation errors
    }

    public static void main(String[] args) throws IOException {
        // input
        String problemName = "lifeguards";
        Scanner sc = new Scanner(new File(problemName + ".in"));

        numLifeguards = sc.nextInt();
        lifeguardShifts = new int[numLifeguards][2];
        for (int i = 0; i< numLifeguards; i++) {
            lifeguardShifts[i][0] = sc.nextInt();
            lifeguardShifts[i][1] = sc.nextInt();
        }

        // algorithm
        int numLifeguardTime = lifeguards();

        // output
        PrintWriter out = new PrintWriter(new FileWriter(problemName + ".out"));
        out.println(numLifeguardTime);
        out.close();
    }
}
