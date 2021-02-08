// Why Did the Cow Cross the Road III - USACO Bronze February 2017 (http://www.usaco.org/index.php?page=viewproblem2&cpid=713)
// This problem was partially completed on Sunday, September 6, 2020, in 1 hour, with 5/10 test cases passed (first try)
// This problem was fully completed during review on Saturday, September 12, 2020, all 10/10 test cases passed (review)
/*
postmortem
1) don't overthink, do the problem the simple way
2) choose correct data structure (if there are two separate lines of data, don't merge them together)
 */

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class cowqueue_feb2017 {
    static int numCows;
    static int[] cowEntryTimes;
    static int[] durations;

    public static int cowqueue() {
        // sort the cow times (sort entry times, match durations)
        int[] cowEntryTimes_sorted = cowEntryTimes.clone();
        Arrays.sort(cowEntryTimes_sorted);
        int[] durations_sorted = new int[numCows];

        for (int i=0; i<numCows; i++) {
            for (int j=0; j<numCows; j++) {
                if (cowEntryTimes_sorted[i] == cowEntryTimes[j]) {
                    durations_sorted[i] = durations[j];
                    break;
                }
            }
        }

        // simulate cows being processed
        for (int i=0; i<numCows; i++) {
            int startTime = cowEntryTimes_sorted[i];
            int endTime = (startTime + durations_sorted[i]);

            if (i == numCows-1) {
                return endTime;
            }

            if (endTime > cowEntryTimes_sorted[i+1]) {
                // the next cow has to wait
                cowEntryTimes_sorted[i+1] = endTime;
            }
        }

        return 0; // this should never happen, avoid compilation error
    }

    public static void main(String[] args) throws IOException{
        // input
        String problemName = "cowqueue";
        Scanner sc = new Scanner(new File(problemName + ".in"));

        numCows = sc.nextInt();
        cowEntryTimes = new int[numCows];
        durations = new int[numCows];
        for (int i=0; i<numCows; i++) {
            cowEntryTimes[i] = sc.nextInt();
            durations[i] = sc.nextInt();
        }

        // algorithm
        int completeProcessingTime = cowqueue();

        // output
        PrintWriter out = new PrintWriter(new FileWriter(problemName + ".out"));
        out.println(completeProcessingTime);
        out.close();
    }
}

