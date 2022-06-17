// Why Did the Cow Cross the Road III - USACO Bronze February 2017 (http://www.usaco.org/index.php?page=viewproblem2&cpid=713)
// This problem was finished on 1/30/21 during the USACO Silver 1 Class.

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class L1_cowqueue {
    static int numCows;
    static Cow[] cows;

    static class Cow implements Comparable<Cow> {
        int arrivalTime;
        int questioningTime;

        Cow(int arrivalTime, int questioningTime) {
            this.arrivalTime = arrivalTime;
            this.questioningTime = questioningTime;
        }

        public int compareTo(Cow another) {
            return arrivalTime - another.arrivalTime;
        }
    }

    public static int cowqueue() {
        Arrays.sort(cows);

        for (int i=0; i<numCows-1; i++) {
            int cowEnterTime = cows[i].arrivalTime + cows[i].questioningTime;

            if (cowEnterTime > cows[i+1].arrivalTime) {
                cows[i+1].arrivalTime = cowEnterTime;
            }
        }

        return (cows[numCows-1].arrivalTime + cows[numCows-1].questioningTime);
    }

    public static void main(String[] args) throws IOException {
        // input
        String problemName = "cowqueue";
        Scanner sc = new Scanner(new File(problemName + ".in"));

        numCows = sc.nextInt();
        cows = new Cow[numCows];
        for (int i=0; i<numCows; i++) {
            cows[i] = new Cow(sc.nextInt(), sc.nextInt());
        }

        // algorithm
        int time = cowqueue();

        // output
        PrintWriter out = new PrintWriter(new FileWriter(problemName + ".out"));
        out.println(time);
        out.close();
    }
}
