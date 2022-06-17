// Square Pasture - USACO Bronze December 2016 (http://www.usaco.org/index.php?page=viewproblem2&cpid=663)
// This problem was completed on November 20, 2020, in 13 minutes, with all 10/10 test cases passed (second try)

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class square_dec2016 {
    static int[][] pasture1 = new int[2][2];
    static int[][] pasture2 = new int[2][2];

    public static int square() {
        int minX = Math.min(pasture1[0][0], pasture2[0][0]);
        int maxX = Math.max(pasture1[1][0], pasture2[1][0]);
        int minY = Math.min(pasture1[0][1], pasture2[0][1]);
        int maxY = Math.max(pasture1[1][1], pasture2[1][1]);

        int maxLength = Math.max((maxX-minX), (maxY-minY));

        return maxLength*maxLength;
    }

    public static void main(String[] args) throws IOException {
        // input
        String problemName = "square";
        Scanner sc = new Scanner(new File(problemName + ".in"));

        pasture1[0][0] = sc.nextInt();
        pasture1[0][1] = sc.nextInt();
        pasture1[1][0] = sc.nextInt();
        pasture1[1][1] = sc.nextInt();
        pasture2[0][0] = sc.nextInt();
        pasture2[0][1] = sc.nextInt();
        pasture2[1][0] = sc.nextInt();
        pasture2[1][1] = sc.nextInt();

        // algorithm
        int maxArea = square();

        // output
        PrintWriter out = new PrintWriter(new FileWriter(problemName + ".out"));
        out.println(maxArea);
        out.close();
    }
}
