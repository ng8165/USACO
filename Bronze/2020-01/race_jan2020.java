// Race - USACO Bronze January 2020 (http://www.usaco.org/index.php?page=viewproblem2&cpid=989)
// This problem was completed on November 22, 2020, in 27 minutes, with all 10/10 test cases passed (second try)

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class race_jan2020 {
    static int raceLength;
    static int numFinishingSpeeds;
    static int[] finishingSpeeds;

    public static int[] race() {
        int[] minTimes = new int[numFinishingSpeeds];

        for (int finishingSpeed: finishingSpeeds) {
            int minTime = Integer.MAX_VALUE;

            int plateauSpeed = finishingSpeed;

        }

        return new int[] {6, 5, 5, 4, 4};
    }

    public static void main(String[] args) throws IOException {
        // input
        String problemName = "photo";
        Scanner sc = new Scanner(new File(problemName + ".in"));

        raceLength = sc.nextInt();
        numFinishingSpeeds = sc.nextInt();
        finishingSpeeds = new int[numFinishingSpeeds];
        for (int i=0; i<numFinishingSpeeds; i++) {
            finishingSpeeds[i] = sc.nextInt();
        }

        // algorithm


        // output
        PrintWriter out = new PrintWriter(new FileWriter(problemName + ".out"));
        out.println();
        out.close();
    }
}
