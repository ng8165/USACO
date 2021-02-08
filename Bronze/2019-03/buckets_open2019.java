// Bucket Brigade - USACO Bronze US Open 2019 (http://www.usaco.org/index.php?page=viewproblem2&cpid=939)

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class L7_buckets {
    public static boolean isBetween (int barn, int lake, int rock) {
        int largerCoordinate = Math.max(barn, lake);
        int smallerCoordinate = Math.min(barn, lake);

        if (rock < largerCoordinate && rock > smallerCoordinate) {
            return true;
        } else {
            return false;
        }
    }

    public static int buckets(String[] farmMap) {
        int B_x = 0, B_y = 0, R_x = 0, R_y = 0, L_x = 0, L_y = 0;

        for (int i=0; i<10; i++) {        // i is row
            for (int j=0; j<10; j++) {    // j is column
                // find the location of the barn, rock, and lake

                char currChar = farmMap[i].charAt(j);
                if (currChar != '.') {
                    if (currChar == 'B') {
                        B_x = i;
                        B_y = j;
                    } else if (currChar == 'R') {
                        R_x = i;
                        R_y = j;
                    } else {
                        L_x = i;
                        L_y = j;
                    }
                }
            }
        }
        /*
        System.out.println("B: (" + B_x + ", " + B_y + ")");
        System.out.println("R: (" + R_x + ", " + R_y + ")");
        System.out.println("L: (" + L_x + ", " + L_y + ")");
        */

        int horizontalDiff = Math.abs(L_x-B_x);
        int verticalDiff = Math.abs(L_y-B_y);

        // most of the time the shortest distance is simply the horizontalDiff + verticalDiff - 1
        // however there are some circumstances where two extra cows are necessary
        // example of special circumstance: barn and lake are on a horizontal or vertical line and rock is in between

        // determine if the locations fall into the special circumstance
        if (horizontalDiff == 0 && R_x == L_x) {
            if (isBetween(B_y, L_y, R_y)) {
                return horizontalDiff + verticalDiff + 1;
            } else {
                return horizontalDiff + verticalDiff - 1;
            }
        } else if (verticalDiff == 0 && R_y == L_y) {
            if (isBetween(B_x, L_x, R_x)) {
                return horizontalDiff + verticalDiff + 1;
            } else {
                return horizontalDiff + verticalDiff - 1;
            }
        } else {
            return horizontalDiff + verticalDiff - 1;
        }
    }

    public static void main(String[] args) throws IOException {
        // input
        String problemName = "buckets";
        Scanner sc = new Scanner(new File(problemName + ".in"));
        String[] farmMap = new String[10];
        for (int i=0; i<10; i++) {
            farmMap[i] = sc.nextLine();
        }

        // algorithm
        int numCows = buckets(farmMap);

        //output
        PrintWriter out = new PrintWriter(new FileWriter(problemName + ".out"));
        out.println(numCows);
        out.close();
    }
}
