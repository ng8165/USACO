// Sleepy Cow Herding - USACO Bronze February 2019 (http://www.usaco.org/index.php?page=viewproblem2&cpid=915)
// This problem was mostly completed on November 1, 2020, in 24 minutes, with 9/10 test cases passed (second try)
// This problem was completed on November 7, 2020, during review, with all 10/10 test cases passed (review)

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class herding_feb2019 {
    static int[] cows = new int[3];

    public static int[] herding() {
        Arrays.sort(cows);

        if ((cows[2] - cows[1] == 1) && (cows[1] - cows[0] == 1)) {
            return new int[] {0, 0};
        }

        int maxMoves = Math.max(cows[2] - cows[1], cows[1] - cows[0]) - 1;

        if ((cows[2] - cows[1] == 2) || (cows[1] - cows[0] == 2)) {
            return new int[] {1, maxMoves};
        } else {
            return new int[] {2, maxMoves};
        }
    }

    public static void main(String[] args) throws IOException {
        // input
        String problemName = "herding";
        Scanner sc = new Scanner(new File(problemName + ".in"));

        cows[0] = sc.nextInt();
        cows[1] = sc.nextInt();
        cows[2] = sc.nextInt();

        // algorithm
        int[] cowMoves = herding();

        // output
        PrintWriter out = new PrintWriter(new FileWriter(problemName + ".out"));
        out.println(cowMoves[0]);
        out.println(cowMoves[1]);
        out.close();
    }
}
