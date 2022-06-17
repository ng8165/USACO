// Social Distancing II - USACO Bronze US Open 2020 (http://usaco.org/index.php?page=viewproblem2&cpid=1036)
// This problem was finished on 1/31/21 as homework for the USACO Silver 1 Class.

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class L1_socdist2 {
    static int numCows;
    static Cow[] cows;

    static class Cow implements Comparable<Cow>{
        int pos;
        boolean sick;

        Cow(int pos, int sick) {
            this.pos = pos;

            if (sick == 1) {
                this.sick = true;
            } else {
                this.sick = false;
            }
        }

        public int compareTo(Cow another) {
            return pos - another.pos;
        }
    }

    public static int socdist2() {
        Arrays.sort(cows);

        // first find the maximum R: minimum distance between a healthy and sick cow
        int R = Integer.MAX_VALUE;
        for (int i=0; i<numCows-1; i++) {
            if (cows[i].sick ^ cows[i+1].sick) {
                R = Math.min(R, cows[i+1].pos - cows[i].pos);
            }
        }
        R--;

        // now find the possible number of sick cows
        int numSickCows = 0;

        for (int i=0; i<numCows-1; i++) {
            if (cows[i].sick && cows[i+1].pos - cows[i].pos > R) {
                numSickCows++;
            }
        }

        if (cows[numCows-1].sick) {
            numSickCows++; // special case: last cow not checked
        }

        return numSickCows;
    }

    public static void main(String[] args) throws IOException {
        // input
        String problemName = "socdist2";
        Scanner sc = new Scanner(new File(problemName + ".in"));

        numCows = sc.nextInt();
        cows = new Cow[numCows];
        for (int i=0; i<numCows; i++) {
            cows[i] = new Cow(sc.nextInt(), sc.nextInt());
        }

        // algorithm
        int numSickCows = socdist2();

        // output
        PrintWriter out = new PrintWriter(new FileWriter(problemName + ".out"));
        out.println(numSickCows);
        out.close();
    }
}
