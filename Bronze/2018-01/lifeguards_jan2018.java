// Lifeguards - USACO Bronze January 2018 (http://www.usaco.org/index.php?page=viewproblem2&cpid=784)

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class L14_lifeguards {
    static int[][] cowShifts;

    public static int lifeguards() {
        int numCows = cowShifts.length;
        int maxTime = 0;

        for (int i=0; i<numCows; i++) { // i will be the index for which cow to fire
            // generate the time line where the cow shifts are
            int[] timeEvents = new int[numCows*2-2];
            for (int j=0, cnt=0; j<numCows; j++) {
                if (j == i) {
                    continue;
                }
                timeEvents[cnt] = cowShifts[j][0];
                cnt++;
                timeEvents[cnt] = cowShifts[j][1];
                cnt++;
            }
            Arrays.sort(timeEvents);

            int lifeguardNum = 0;
            int lifeguardTime = 0;
            for (int j=0; j<timeEvents.length; j++) {
                if (lifeguardNum > 0) {
                    lifeguardTime += (timeEvents[j] - timeEvents[j-1]);
                }

                boolean isStarting = findShift(timeEvents[j]);
                if (isStarting) {
                    lifeguardNum++;
                } else {
                    lifeguardNum--;
                }
            }

            maxTime = Math.max(maxTime, lifeguardTime);
        }

        return maxTime;
    }
    public static boolean findShift(int timeEvent) {
        for (int row=0; row<cowShifts.length; row++) {
            for (int column=0; column<2; column++) {
                if (timeEvent == cowShifts[row][column]) {
                    if (column == 0) {
                        return true;
                    } else {
                        return false;
                    }
                }
            }
        }

        return true; // this should never happen, just so program runs
    }

    public static void main(String[] args) throws IOException {
        // input
        String problemName = "lifeguards";
        Scanner sc = new Scanner(new File(problemName + ".in"));

        int numCows = sc.nextInt();
        cowShifts = new int[numCows][2];
        for (int i=0; i<numCows; i++) {
            for (int j=0; j<2; j++) {
                cowShifts[i][j] = sc.nextInt();
            }
        }

        // algorithm
        int maxTime = lifeguards();

        // output
        PrintWriter out = new PrintWriter(new FileWriter(problemName + ".out"));
        out.println(maxTime);
        out.close();
    }
}
