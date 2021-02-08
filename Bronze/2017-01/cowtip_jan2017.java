// Cow Tipping - USACO Bronze January 2017 (http://www.usaco.org/index.php?page=viewproblem2&cpid=689)

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class L9_cowtip {
    public static int cowtip(int size, char[][] cows) {
        int flipCnt = 0;

        while (true) {
            int maxDist = -1;
            int maxRow = -1;
            int maxCol = -1;

            // traverse through matrix and find 1
            for (int row=0; row<size; row++) {
                for (int column=0; column<size; column++) {
                    // if 1 is found and distance is farther than old, replace
                    if ((cows[row][column] == '1') && ((row + column) > maxDist)) {
                        maxRow = row;
                        maxCol = column;
                        maxDist = (row + column);
                    }
                }
            }

            if (maxDist == -1) { // if the maxDist never changes that means there are no 0, so we are done
                break;
            }

            System.out.println((flipCnt+1) + ": (" + (maxRow+1) + ", " + (maxCol+1) + ")");

            flip(cows, maxRow, maxCol);

            flipCnt++;
        }

        return flipCnt;
    }
    public static void flip(char[][] cows, int flipRow, int flipColumn) {
        for (int row=0; row<=flipRow; row++) {
            for (int column=0; column<=flipColumn; column++) {
                if (cows[row][column] == '1') {
                    cows[row][column] = '0';
                } else {
                    cows[row][column] = '1';
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        // input
        String problemName = "cowtip";
        Scanner sc = new Scanner(new File(problemName + ".in"));
        int size = sc.nextInt();
        char[][] cows = new char[size][size];
        sc.nextLine(); // move cursor to next line
        for (int i=0; i<size; i++) {
            cows[i] = sc.nextLine().toCharArray();
        }

        // algorithm
        int flipCnt = cowtip(size, cows);

        // output
        PrintWriter out = new PrintWriter(new FileWriter(problemName + ".out"));
        out.println(flipCnt);
        out.close();
    }
}
