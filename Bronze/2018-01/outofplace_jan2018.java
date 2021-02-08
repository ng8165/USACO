// Out of Place - USACO Bronze January 2018 (http://www.usaco.org/index.php?page=viewproblem2&cpid=785)

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class L11_outofplace {
    public static int outofplace(int[] currLineup, int numCows) {
        int[] correctLineup = currLineup.clone();
        Arrays.sort(correctLineup); // make sorted lineup

        int numNotEqual = 0;

        for (int i=0; i<numCows; i++) {
            if (correctLineup[i] != currLineup[i]) {
                // for every cow that is incorrect, a flip is necessary
                numNotEqual++;
            }
        }

        return (numNotEqual-1); // there is always an extra flip
    }

    public static void main(String[] args) throws IOException {
        // input
        String problemName = "outofplace";
        Scanner sc = new Scanner(new File(problemName + ".in"));
        int numCows = sc.nextInt();
        int[] cowLineup = new int[numCows];
        for (int i=0; i<numCows; i++) {
            cowLineup[i] = sc.nextInt();
        }

        // algorithm
        int numSwaps = outofplace(cowLineup, numCows);

        // output
        PrintWriter out = new PrintWriter(new FileWriter(problemName + ".out"));
        out.println(numSwaps);
        out.close();
    }
}
