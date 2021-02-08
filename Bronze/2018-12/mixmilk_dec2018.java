// Mixing Milk - USACO Bronze December 2018 (http://www.usaco.org/index.php?page=viewproblem2&cpid=855)
// This problem was completed on October 18, 2020, in 25 minutes, with all 10/10 test cases passed (second try)

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class mixmilk_dec2018 {
    static int[] capacity = new int[3];
    static int[] milkAmounts = new int[3];

    public static void mixmilk() {
        for (int i=1; i<=100; i++) {
            // identify which bucket is pouring to and from
            if (i%3 == 1) {
                // pour from bucket 1 to 2
                if (milkAmounts[0] + milkAmounts[1] <= capacity[1]) {
                    milkAmounts[1] += milkAmounts[0];
                    milkAmounts[0] = 0;
                } else {
                    milkAmounts[0] = milkAmounts[0] + milkAmounts[1] - capacity[1];
                    milkAmounts[1] = capacity[1];
                }
            } else if (i%3 == 2) {
                // pour from bucket 2 to 3
                if (milkAmounts[1] + milkAmounts[2] <= capacity[2]) {
                    milkAmounts[2] += milkAmounts[1];
                    milkAmounts[1] = 0;
                } else {
                    milkAmounts[1] = milkAmounts[1] + milkAmounts[2] - capacity[2];
                    milkAmounts[2] = capacity[2];
                }
            } else {
               // pour from bucket 3 to 1
                if (milkAmounts[2] + milkAmounts[0] <= capacity[0]) {
                    milkAmounts[0] += milkAmounts[2];
                    milkAmounts[2] = 0;
                } else {
                    milkAmounts[2] = milkAmounts[2] + milkAmounts[0] - capacity[0];
                    milkAmounts[0] = capacity[0];
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        // input
        String problemName = "mixmilk";
        Scanner sc = new Scanner(new File(problemName + ".in"));

        for (int i=0; i<3; i++) {
            capacity[i] = sc.nextInt();
            milkAmounts[i] = sc.nextInt();
        }

        // algorithm
        mixmilk();

        // output
        PrintWriter out = new PrintWriter(new FileWriter(problemName + ".out"));
        for (int i=0; i<3; i++) {
            out.println(milkAmounts[i]);
        }
        out.close();
    }
}
