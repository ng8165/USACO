// The Great Revegetation - USACO Bronze February 2019 (http://www.usaco.org/index.php?page=viewproblem2&cpid=916)
// This problem was completed on December 20, 2020, in 40 minutes, with 10/10 test cases passed (second try)

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class revegetate_feb2019 {
    static int numPastures;
    static int numCows;
    static int[][] pasturePreferences;

    static int[] pastures;

    public static void revegetate() {
        pastures[1] = 1;

        for (int i=2; i<=numPastures; i++) {
            pastures[i] = findSmallestPasture(i);
        }
    }

    public static int findSmallestPasture(int pasture) {
        int[] badGrass = new int[4+1];

        for (int i=1; i<pasture; i++) {
            int[] grassCombo = new int[] {i, pasture};
            for (int j=0; j<numCows; j++) {
                if (Arrays.equals(pasturePreferences[j], grassCombo)) {
                    badGrass[i] = -1;
                }
            }
        }

        for (int i=1; i<=4; i++) {
            if (badGrass[i] == 0) {
                return i;
            }
        }

        return 0; // avoid compilation errors
    }

    public static void main(String[] args) throws IOException {
        // input
        String problemName = "revegetate";
        Scanner sc = new Scanner(new File(problemName + ".in"));
        //Scanner sc = new Scanner(System.in);

        numPastures = sc.nextInt();
        numCows = sc.nextInt();
        pasturePreferences = new int[numCows][2];
        pastures = new int[numPastures+1];
        for (int i=0; i<numCows; i++) {
            int p1 = sc.nextInt();
            int p2 = sc.nextInt();

            pasturePreferences[i][0] = Math.min(p1, p2);
            pasturePreferences[i][1] = Math.max(p1, p2);
        }

        // algorithm
        revegetate();

        // output
        PrintWriter out = new PrintWriter(new FileWriter(problemName + ".out"));
        for (int i=1; i<=numPastures; i++) {
            out.print(pastures[i]);
        }
        out.close();
        //System.out.println();
    }
}
