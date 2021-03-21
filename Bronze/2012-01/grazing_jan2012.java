// Grazing Patterns - USACO Bronze January 2012 (http://www.usaco.org/index.php?page=viewproblem2&cpid=105)
// This problem was completed as homework (with automation mode) for the USACO Silver 1 Class on 3/20/21.

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class L7_grazing {
    static String directoryName = "grazing";

    static int numBarrenSquares;
    static boolean[][] isSquareBarren;

    static int eatenSquares;
    static int grazingMethods;

    public static void grazingDFS(int row, int col) {
        // base case
        if (row == 4 && col == 4) {
            if (eatenSquares == 25-numBarrenSquares) {
                // cow ate everything but the original barren squares
                grazingMethods++;
            }
        }

        // north (one row up)
        if (row > 0 && !isSquareBarren[row-1][col]) {
            isSquareBarren[row-1][col] = true;
            eatenSquares++;

            grazingDFS(row-1, col);

            // backtracking
            isSquareBarren[row-1][col] = false;
            eatenSquares--;
        }

        // south (one row down)
        if (row < 4 && !isSquareBarren[row+1][col]) {
            isSquareBarren[row+1][col] = true;
            eatenSquares++;

            grazingDFS(row+1, col);

            // backtracking
            isSquareBarren[row+1][col] = false;
            eatenSquares--;
        }

        // west (one col left)
        if (col > 0 && !isSquareBarren[row][col-1]) {
            isSquareBarren[row][col-1] = true;
            eatenSquares++;

            grazingDFS(row, col-1);

            // backtracking
            isSquareBarren[row][col-1] = false;
            eatenSquares--;
        }

        // east (one col right)
        if (col < 4 && !isSquareBarren[row][col+1]) {
            isSquareBarren[row][col+1] = true;
            eatenSquares++;

            grazingDFS(row, col+1);

            // backtracking
            isSquareBarren[row][col+1] = false;
            eatenSquares--;
        }
    }

    public static int solve(int i) throws IOException {
        // input
        Scanner scin = new Scanner(new File(directoryName + "/" + i + ".in"));

        // reset static variables for next test in automation mode
        isSquareBarren = new boolean[5][5];
        eatenSquares = 0;
        grazingMethods = 0;

        numBarrenSquares = scin.nextInt();
        for (int j=0; j<numBarrenSquares; j++) {
            isSquareBarren[scin.nextInt()-1][scin.nextInt()-1] = true;
        }

        // algorithm
        // essentially: DFS for how many ways one cow can eat the whole field and reach bottom right corner
        isSquareBarren[0][0] = true; // eat starting square
        eatenSquares++;

        grazingDFS(0, 0);

        // output
        return grazingMethods;
    }

    public static boolean isCorrect(int i) throws IOException {
        // read correct output
        Scanner scout = new Scanner(new File(directoryName + "/" + i + ".out"));
        int correctResult = scout.nextInt();

        // check
        System.out.print("Test Case " + i);

        long before = System.currentTimeMillis();
        int result = solve(i);
        long after = System.currentTimeMillis();

        if (result == correctResult) {
            System.out.println(": Correct - took " + (after-before) + " milliseconds (Expected and Received " + correctResult +")");
            return true;
        } else {
            System.out.println(": Incorrect - took " + (after-before) + " milliseconds (Expected " + correctResult + ", Received " + result + ")");
            return false;
        }
    }

    public static void main(String[] args) throws IOException {
        int numCorrect = 0;

        for (int i=1; i<=10; i++) {
            if (isCorrect(i)) {
                numCorrect++;
            }
        }

        System.out.println("\n" + numCorrect + " correct, " + (10-numCorrect) + " incorrect");
    }
}