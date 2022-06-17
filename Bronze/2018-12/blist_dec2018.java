// The Bucket List - USACO Bronze December 2018 (http://www.usaco.org/index.php?page=viewproblem2&cpid=856)
// This problem was completed on October 18, 2020, in 33 minutes, with all 10/10 test cases passed (second try)

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class blist_dec2018 {
    static int numCows;
    static int[][] schedule;
    static int[] buckets;

    public static int blist() {
        // create a timeline
        int[] timeline = new int[numCows*2];
        for (int i=0, j=0; i<numCows; i++) {
            timeline[j++] = schedule[i][0];
            timeline[j++] = schedule[i][1];
        }
        Arrays.sort(timeline);

        // start simulations
        int inUseBuckets = 0;
        int availableBuckets = 0;

        for (int timeEvent: timeline) {
            // search for the current time event in the schedule
            boolean isStartingEvent = true;
            int bucketsNeeded = 0;
            for (int j=0; j<numCows; j++) {
                if (schedule[j][0] == timeEvent) {
                    isStartingEvent = true;
                    bucketsNeeded = buckets[j];
                    break;
                } else if (schedule[j][1] == timeEvent) {
                    isStartingEvent = false;
                    bucketsNeeded = buckets[j];
                    break;
                }
            }

            if (isStartingEvent) {
                // allocate buckets
                if (availableBuckets == 0) {
                    // get more buckets from storage
                    inUseBuckets += bucketsNeeded;
                } else if (availableBuckets >= bucketsNeeded) {
                    // enough available buckets, no need to go to storage
                    availableBuckets -= bucketsNeeded;
                    inUseBuckets += bucketsNeeded;
                } else {
                    // some available buckets, but still need to go to storage
                    inUseBuckets += bucketsNeeded;
                    availableBuckets = 0;
                }
            } else {
                // return buckets
                inUseBuckets -= bucketsNeeded;
                availableBuckets += bucketsNeeded;
            }
        }

        return availableBuckets;
    }

    public static void main(String[] args) throws IOException {
        // input
        String problemName = "blist";
        Scanner sc = new Scanner(new File(problemName + ".in"));

        numCows = sc.nextInt();
        schedule = new int[numCows][2];
        buckets = new int[numCows];
        for (int i=0; i<numCows; i++) {
            schedule[i][0] = sc.nextInt();
            schedule[i][1] = sc.nextInt();
            buckets[i] = sc.nextInt();
        }

        // algorithm
        int bucketsNeeded = blist();

        // output
        PrintWriter out = new PrintWriter(new FileWriter(problemName + ".out"));
        out.println(bucketsNeeded);
        out.close();
    }
}
