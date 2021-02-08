// Fence Painting - USACO Bronze December 2015 (http://www.usaco.org/index.php?page=viewproblem2&cpid=567)
// This problem was completed on November 25, 2020, in 9 minutes, with all 10/10 test cases passed (second try)

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class paint_dec2015 {
    static int[] FJ = new int[2];
    static int[] bessie = new int[2];

    public static int paint() {
        int span = Math.max(FJ[1], bessie[1]) - Math.min(FJ[0], bessie[0]);
        int lengthSum = (FJ[1] - FJ[0]) + (bessie[1] - bessie[0]);

        return Math.min(span, lengthSum);
    }

    public static void main(String[] args) throws IOException {
        // input
        String problemName = "paint";
        Scanner sc = new Scanner(new File(problemName + ".in"));

        FJ[0] = sc.nextInt();
        FJ[1] = sc.nextInt();
        bessie[0] = sc.nextInt();
        bessie[1] = sc.nextInt();

        // algorithm
        int paintedFence = paint();

        // output
        PrintWriter out = new PrintWriter(new FileWriter(problemName + ".out"));
        out.println(paintedFence);
        out.close();
    }
}
