// Don't Be Last! - USACO Bronze January 2017 (http://www.usaco.org/index.php?page=viewproblem2&cpid=687)
// This problem was done on Sunday, August 30, 2020, in 52 minutes, with all 11 test cases passed (first try)

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class notlast_jan2017 {
    static String[] cowNames = {"Bessie", "Elsie", "Daisy", "Gertie", "Annabelle", "Maggie", "Henrietta"};

    public static String notlast(int[] milkAmounts_unsorted) {
        int numCows = 7;

        // find the cow that produces the smallest amount of milk
        int[] milkAmounts = milkAmounts_unsorted.clone();
        Arrays.sort(milkAmounts);

        int minMilk = milkAmounts[0];

        // find the cow that produces the second-smallest amount of milk

        for (int i=0; i<numCows; i++) {
            int cowMilk = milkAmounts[i];
            if (cowMilk > minMilk) {
                // see if there is a tie (as in more than one optimal cow)
                if (i<numCows-1 && cowMilk == milkAmounts[i+1]) {
                    return "Tie";
                }

                // otherwise there is only one optimal cow
                return findCowName(cowMilk, milkAmounts_unsorted);
            }
        }

        // the only other case is that there is no optimal cow
        return "Tie";
    }

    public static int findCowIndex(String cow) {
        for (int i=0; i<cowNames.length; i++) {
            if (cow.equals(cowNames[i])) {
                return i;
            }
        }

        return 0; // this should never happen, just so program runs
    }
    public static String findCowName(int milkAmount, int[] milkAmounts) {
        for (int i=0; i<milkAmounts.length; i++) {
            if (milkAmount == milkAmounts[i]) {
                return cowNames[i];
            }
        }

        return ""; // this should never happen, just so program runs
    }

    public static void main(String[] args) throws IOException {
        // input
        String problemName = "notlast";
        Scanner sc = new Scanner(new File(problemName + ".in"));

        int numEntries = sc.nextInt();
        int[] milkAmounts = new int[7];
        for (int i=0; i<numEntries; i++) {
            String name = sc.next();
            int cowIndex = findCowIndex(name);
            milkAmounts[cowIndex] += sc.nextInt();
        }

        // algorithm
        String bestCow = notlast(milkAmounts);

        // output
        PrintWriter out = new PrintWriter(new FileWriter(problemName + ".out"));
        out.println(bestCow);
        out.close();
    }
}
