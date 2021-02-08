// Promotion Counting - USACO Bronze January 2016 (http://www.usaco.org/index.php?page=viewproblem2&cpid=591)
// This problem was completed on November 24, 2020, in 22 minutes, with all 10/10 test cases passed (second try)

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class promote_jan2016 {
    static int[] bronze = new int[2];
    static int[] silver = new int[2];
    static int[] gold = new int[2];
    static int[] platinum = new int[2];

    public static int[] promote() {
        int[] promotions = new int[3];

        promotions[2] = platinum[1] - platinum[0];
        promotions[1] = gold[1] - (gold[0] - promotions[2]);
        promotions[0] = silver[1] - (silver[0] - promotions[1]);

        return promotions;
    }

    public static void main(String[] args) throws IOException {
        // input
        String problemName = "promote";
        Scanner sc = new Scanner(new File(problemName + ".in"));

        bronze[0] = sc.nextInt();
        bronze[1] = sc.nextInt();
        silver[0] = sc.nextInt();
        silver[1] = sc.nextInt();
        gold[0] = sc.nextInt();
        gold[1] = sc.nextInt();
        platinum[0] = sc.nextInt();
        platinum[1] = sc.nextInt();

        // algorithm
        int[] promotions = promote();

        // output
        PrintWriter out = new PrintWriter(new FileWriter(problemName + ".out"));
        for (int i=0; i<3; i++) {
            out.println(promotions[i]);
        }
        out.close();
    }
}
