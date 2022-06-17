// Bucket Brigade - USACO Bronze US Open 2019 (http://www.usaco.org/index.php?page=viewproblem2&cpid=939)
// This problem was completed on November 8, 2020, in 39 minutes, with all 10/10 test cases passed (second try)

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class buckets_open2019 {
    static char[][] map = new char[10][10];

    public static int buckets() {
        // search for B, L, and R
        int[] barnLoc = new int[2];
        int[] lakeLoc = new int[2];
        int[] rockLoc = new int[2];

        for (int i=0; i<10; i++) {
            for (int j=0; j<10; j++) {
                if (map[i][j] == 'B') {
                    barnLoc[0] = i;
                    barnLoc[1] = j;
                } else if (map[i][j] == 'L') {
                    lakeLoc[0] = i;
                    lakeLoc[1] = j;
                } else if (map[i][j] == 'R') {
                    rockLoc[0] = i;
                    rockLoc[1] = j;
                }
            }
        }

        if ((barnLoc[0] == lakeLoc[0]) && rockLoc[0] == barnLoc[0] && isBetween(barnLoc[1], lakeLoc[1], rockLoc[1])) {
            return Math.abs(lakeLoc[1] - barnLoc[1]) + 1;
        } else if ((barnLoc[1] == lakeLoc[1]) && rockLoc[1] == barnLoc[1] && isBetween(barnLoc[0], lakeLoc[0], rockLoc[0])) {
            return Math.abs(lakeLoc[0] - barnLoc[0]) + 1;
        }

        return Math.abs(lakeLoc[0] - barnLoc[0]) + Math.abs(lakeLoc[1] - barnLoc[1]) - 1;
    }
    public static boolean isBetween(int pos1, int pos2, int position) {
        if (pos1 < pos2) {
            if (pos1 < position && pos2 > position) {
                return true;
            }
        } else {
            if (pos1 > position && pos2 < position) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        // input
        String problemName = "buckets";
        Scanner sc = new Scanner(new File(problemName + ".in"));

        for (int i=0; i<10; i++) {
            map[i] = sc.nextLine().toCharArray();
        }

        // algorithm
        int bucketBrigade = buckets();

        // output
        PrintWriter out = new PrintWriter(new FileWriter(problemName + ".out"));
        out.println(bucketBrigade);
        out.close();
    }
}
