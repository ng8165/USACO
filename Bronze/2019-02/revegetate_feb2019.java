// The Great Revegetation - USACO Bronze February 2019 (http://www.usaco.org/index.php?page=viewproblem2&cpid=916)
// This problem was completed on November 1, 2020, in 51 minutes, with all 10/10 test cases passed (first try)

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
    static int[][] favoritePastures;

    public static int[] revegetate() {
        // create an output array for the grass types in pastures
        int[] pastures = new int[numPastures+1];
        pastures[1] = 1; // the first one will be 1 because we want the smallest number

        for (int i=2; i<=numPastures; i++) {
            // first identify what this pasture cannot have
            ArrayList<Integer> forbiddenGrass = new ArrayList<>();

            for (int j=1; j<i; j++) {
                // see if any cow likes pasture j and pasture i
                if (isPastureComboForbidden(j, i)) {
                    forbiddenGrass.add(pastures[j]);
                }
            }

            // now set the new pasture to have the smallest available grass
            for (int j=1; j<=4; j++) {
                if (!isGrassForbidden(j, forbiddenGrass)) {
                    pastures[i] = j;
                    break;
                }
            }
        }

        return pastures;
    }
    public static boolean isPastureComboForbidden(int p1, int p2) {
        int[] pastures = {p1, p2};

        for (int i=0; i<numCows; i++) {
            if (Arrays.equals(favoritePastures[i], pastures)) {
                return true;
            }
        }

        return false;
    }
    public static boolean isGrassForbidden(int grass, ArrayList<Integer> forbiddenGrass) {
        for (int badGrass: forbiddenGrass) {
            if (grass == badGrass) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) throws IOException {
        // input
        String problemName = "revegetate";
        Scanner sc = new Scanner(new File(problemName + ".in"));

        numPastures = sc.nextInt();
        numCows = sc.nextInt();
        favoritePastures = new int[numCows][2];
        for (int i=0; i<numCows; i++) {
            int temp1 = sc.nextInt();
            int temp2 = sc.nextInt();
            favoritePastures[i][0] = Math.min(temp1, temp2);
            favoritePastures[i][1] = Math.max(temp1, temp2);
        }

        // algorithm
        int[] pastures = revegetate();

        // output
        PrintWriter out = new PrintWriter(new FileWriter(problemName + ".out"));
        for (int i=1; i<=numPastures; i++) {
            out.print(pastures[i]);
        }
        out.close();
    }
}
