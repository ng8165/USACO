// Cow Tipping - USACO Bronze January 2017 (http://www.usaco.org/index.php?page=viewproblem2&cpid=689)
// This problem was done on Sunday, August 30, 2020, in 50 minutes, with all 10 test cases passed (second try)

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class cowtip_jan2017 {
    static char[][] cows;

    public static int cowtip() {
        int cowLength = cows.length;
        int numFlips = 0;

        while (!areCowsRightWay()) {
            // first find the farthest tipped cow
            int farthestCow_x = 0;
            int farthestCow_y = 0;
            int farthestCow_sum = 0;

            for (int i=0; i<cowLength; i++) {
                for (int j=0; j<cowLength; j++) {
                    if (cows[i][j] == '1') {
                        int currCow_sum = i+j;
                        if (currCow_sum > farthestCow_sum) {
                            farthestCow_x = i;
                            farthestCow_y = j;
                        }
                    }
                }
            }

            flipCows(farthestCow_x, farthestCow_y);

            numFlips++;
        }

        return numFlips;
    }
    public static void flipCows(int x_flip, int y_flip) {
        for (int row=0; row<=x_flip; row++) {
            for (int column=0; column<=y_flip; column++) {
                if (cows[row][column] == '0') {
                    cows[row][column] = '1';
                } else {
                    cows[row][column] = '0';
                }
            }
        }
    }
    public static boolean areCowsRightWay() {
        for (char[] row: cows) {
            for (char cowStatus: row) {
                if (cowStatus == '1') {
                    return false;
                }
            }
        }

        return true;
    }
    public static void main(String[] args) throws IOException {
        // input
        String problemName = "cowtip";
        Scanner sc = new Scanner(new File(problemName + ".in"));

        int cowLength = sc.nextInt();
        cows = new char[cowLength][cowLength];
        for (int i=0; i<cowLength; i++) {
            cows[i] = sc.next().toCharArray();
        }

        // algorithm
        int minFlips = cowtip();

        // output
        PrintWriter out = new PrintWriter(new FileWriter(problemName + ".out"));
        out.println(minFlips);
        out.close();
    }
}
