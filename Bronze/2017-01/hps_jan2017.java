// Hoof, Paper, Scissors - USACO Bronze January 2017 (http://www.usaco.org/index.php?page=viewproblem2&cpid=688)

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class L4_hps {
    public static int hps(int numGames, int[] cow1, int[] cow2) {
        int cow1MaxWins = Integer.MIN_VALUE;

        // num1, num2, num3 are for comparing the plays for each cow
        // num1[i], num2[i], num3[i] are combinations (6 combos in total)
        // num1 beats num2, num2 beats num3, num3 beats num1
        int[] num1 = {0, 0, 1, 1, 2, 2};
        int[] num2 = {1, 2, 0, 2, 0, 1};
        int[] num3 = {2, 1, 2, 0, 1, 0};
        /*
        int cnt = 0;
        for (int i=0; i<3; i++) {
            for (int j=0; j<3; j++) {
                for (int k=0; k<3; k++) {
                    if (i!=j && j!=k && i!=k) {
                        num1[cnt] = i;
                        num2[cnt] = j;
                        num3[cnt] = k;
                        cnt++;
                        //System.out.println(i + " " + j + " " + k);
                    }
                }
            }
        }
        */

        // loops 6 times b/c num of distinct triplets is 3*2*1=6
        for (int i=0; i<6; i++) {
            int cow1Wins = 0;

            for (int j=0; j<numGames; j++) {
                // num1 beats num2, num2 beats num3, num3 beats num1
                // above will always be true, and num1, num2, num3 keeps changing which allows us to find the max

                // determine how many wins (we only need to count cow1 wins)
                if (cow1[j] == num1[i] && cow2[j] == num2[i]) {
                    cow1Wins++;
                } else if (cow1[j] == num2[i] && cow2[j] == num3[i]) {
                    cow1Wins++;
                } else if (cow1[j] == num3[i] && cow2[j] == num1[i]) {
                    cow1Wins++;
                }
            }

            cow1MaxWins = Math.max(cow1Wins, cow1MaxWins);
        }

        // Complexity: O(6*numGames) = O(numGames)

        return cow1MaxWins;
    }

    public static void main(String[] args) throws IOException {
        // input
        String problemName = "hps";
        Scanner sc = new Scanner(new File(problemName + ".in"));
        int numGames = sc.nextInt();
        int[] cow1 = new int[numGames];
        int[] cow2 = new int[numGames];
        for (int i=0; i<numGames; i++) {
            // subtracting 1 so more convenient for array indexing
            cow1[i] = sc.nextInt() - 1;
            cow2[i] = sc.nextInt() - 1;
        }

        // algorithm
        int maxWins = hps(numGames, cow1, cow2);

        //output
        PrintWriter out = new PrintWriter(new FileWriter(problemName + ".out"));
        out.println(maxWins);
        out.close();
    }
}
