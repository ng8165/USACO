// Mixing Milk - USACO Bronze December 2018 (http://www.usaco.org/index.php?page=viewproblem2&cpid=855)

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class L12_mixmilk {
    static int[] bucketSize = new int[3];
    static int[] milkAmount = new int[3];

    public static int[] mixmilk() {
        int from = 0;
        int to = 1;

        for (int i=0; i<100; i++, from++, to++) {
            from %= 3;
            to %= 3;

            pour(from, to);
        }

        return milkAmount;
    }

    public static void pour(int from, int to) {
        if (milkAmount[from] <= (bucketSize[to] - milkAmount[to])) {
            // milk in from bucket can fit in to bucket (not overflow)
            milkAmount[to] += milkAmount[from];
            milkAmount[from] = 0;
        } else {
            // milk in from bucket cannot fit in to bucket (will overflow)
            milkAmount[from] = (milkAmount[from] + milkAmount[to]) - bucketSize[to];
            milkAmount[to] = bucketSize[to];
        }
    }

    public static void main(String[] args) throws IOException {
        // input
        String problemName = "mixmilk";
        Scanner sc = new Scanner(new File(problemName + ".in"));

        for (int i=0; i<3; i++) {
            bucketSize[i] = sc.nextInt();
            milkAmount[i] = sc.nextInt();
        }

        // algorithm
        int[] finalMilkAmount = mixmilk();

        // output
        PrintWriter out = new PrintWriter(new FileWriter(problemName + ".out"));
        for (int i=0; i<3; i++) {
            out.println(finalMilkAmount[i]);
        }
        out.close();
    }
}
