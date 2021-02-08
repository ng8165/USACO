// Bovine Genomics - USACO Bronze Open 2017 (http://www.usaco.org/index.php?page=viewproblem2&cpid=736)
// This problem was mostly completed on Sunday, September 13, 2020, in 45 minutes, with 9/10 test cases passed (first try)

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class cownomics_open2017 {
    static int numCows;
    static int genomeLength;
    static char[][] spottedCows;
    static char[][] plainCows;

    public static int cownomics() {
        int numMutations = 0;

        for (int pos=0; pos<genomeLength; pos++) {
            boolean uniqueGenome = true;

            for (int cowIdx=0; cowIdx<numCows; cowIdx++) {
                if (!isUniqueGenome(pos, spottedCows[cowIdx][pos])) {
                    uniqueGenome = false;
                    break;
                }
            }

            if (uniqueGenome) {
                numMutations++;
            }
        }

        return numMutations;
    }

    public static boolean isUniqueGenome(int genomePosition, char genome) {
        for (int i=0; i<numCows; i++) {
            if (plainCows[i][genomePosition] == genome) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) throws IOException{
        // input
        String problemName = "cownomics";
        Scanner sc = new Scanner(new File(problemName + ".in"));

        numCows = sc.nextInt();
        genomeLength = sc.nextInt();
        sc.nextLine();
        spottedCows = new char[numCows][genomeLength];
        plainCows = new char[numCows][genomeLength];
        for (int i=0; i<numCows; i++) {
            spottedCows[i] = sc.nextLine().toCharArray();
        }
        for (int i=0; i<numCows; i++) {
            plainCows[i] = sc.nextLine().toCharArray();
        }

        // algorithm
        int numMutations = cownomics();

        // output
        PrintWriter out = new PrintWriter(new FileWriter(problemName + ".out"));
        out.println(numMutations);
        out.close();
    }
}

