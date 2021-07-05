// Loan Repayment - USACO Silver January 2020 (http://usaco.org/index.php?page=viewproblem2&cpid=991)
// This problem was completed as classwork for the USACO Silver 2 Class on 7/4/21.
// REDO (Thought of basic framework and one optimization, but other optimization was from the slides)

import java.io.*;
import java.util.StringTokenizer;

public class L18_loan {
    static long numGallons;
    static long numDays;
    static long minDaily;

    public static boolean isValidX(long X) {
        long gallonsLeft = numGallons;

        for (long days=0; days<numDays; days++) {
            long gallonsToday = gallonsLeft/X;

            if (gallonsToday < minDaily) {
                // all other milks will be smaller, so all M
                return (numDays-days)*minDaily >= gallonsLeft;
            } else {
                // calculate how many days with same gallon as today
                long sameGallonDays = gallonsLeft/gallonsToday - X;

                days += sameGallonDays;
                gallonsLeft -= (sameGallonDays+1)*gallonsToday;
            }
        }

        return gallonsLeft <= 0;
    }

    public static void main(String[] args) throws IOException {
        // input
        String problemName = "loan";
        BufferedReader br = new BufferedReader(new FileReader(problemName + ".in"));
        StringTokenizer st = new StringTokenizer(br.readLine());

        numGallons = Long.parseLong(st.nextToken());
        numDays = Long.parseLong(st.nextToken());
        minDaily = Long.parseLong(st.nextToken());

        // algorithm
        long left = 1L;
        long right = numGallons;

        // binary search
        while (left < right-1) { // may get stuck so end early
            long mid = left + (right-left)/2;

            if (isValidX(mid)) {
                left = mid; // inclusive (mid is good)
            } else {
                right = mid-1; // exclusive (mid is bad)
            }
        }

        // handle final step separately: left is right-1
        long maxX;
        if (isValidX(right)) {
            maxX = right;
        } else {
            maxX = left;
        }

        // output
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(problemName + ".out")));

        pw.println(maxX);

        br.close();
        pw.close();
    }
}
