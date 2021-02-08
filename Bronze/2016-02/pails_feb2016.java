// Milk Pails - USACO Bronze February 2016 (http://www.usaco.org/index.php?page=viewproblem2&cpid=615)
// This problem was completed on November 23, 2020, in 20 minutes, with all 10/10 test cases passed (second try)

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class pails_feb2016 {
    static int smallPail;
    static int mediumPail;
    static int largePail;

    public static int pails() {
        int maxPailAmount = 0;

        for (int smallPailAmount=0; smallPailAmount<=(largePail/smallPail); smallPailAmount++) {
            // find how many medium pails can fit in the remaining space
            int remainingAmount = largePail - (smallPail * smallPailAmount);
            int mediumPailAmount = remainingAmount / mediumPail;

            int finalAmount = (smallPailAmount*smallPail) + (mediumPailAmount*mediumPail);
            maxPailAmount = Math.max(maxPailAmount, finalAmount);
            //System.out.println(smallPailAmount + "*" + smallPail + " + " + mediumPailAmount + "*" + mediumPail + " = " + finalAmount);
        }

        return maxPailAmount;
    }

    public static void main(String[] args) throws IOException {
        // input
        String problemName = "pails";
        Scanner sc = new Scanner(new File(problemName + ".in"));

        smallPail = sc.nextInt();
        mediumPail = sc.nextInt();
        largePail = sc.nextInt();

        // algorithm
        int maxFillAmount = pails();

        // output
        PrintWriter out = new PrintWriter(new FileWriter(problemName + ".out"));
        out.println(maxFillAmount);
        out.close();
    }
}
