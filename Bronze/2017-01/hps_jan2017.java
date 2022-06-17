// Hoof, Paper, Scissors - USACO Bronze January 2017 (http://www.usaco.org/index.php?page=viewproblem2&cpid=688)
// This problem was done on Sunday, August 30, 2020, in 43 minutes, with all 10 test cases passed (second try)

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class hps_jan2017 {
    static int[][] rules = {{1, 2, 3}, {1, 3, 2}, {2, 1, 3}, {2, 3, 1}, {3, 1, 2}, {3, 2, 1}};

    public static int hps(int[] cow1Moves, int[] cow2Moves) {
        int numGames = cow1Moves.length;
        int maxWins = 0;

        for (int i=0; i<rules.length; i++) {
            int currWins = 0;

            for (int j=0; j<numGames; j++) {
                if (cow1Wins(cow1Moves[j], cow2Moves[j], rules[i])) {
                    currWins++;
                }
            }

            maxWins = Math.max(maxWins, currWins);
        }

        return maxWins;
    }

    public static boolean cow1Wins(int play1, int play2, int[] rule) {
        if (play1 == rule[0] && play2 == rule[1]) {
            return true;
        } else if (play1 == rule[1] && play2 == rule[2]) {
            return true;
        } else if (play1 == rule[2] && play2 == rule[0]) {
            return true;
        } else {
            return false;
        }
    }
    public static void main(String[] args) throws IOException {
        // input
        String problemName = "hps";
        Scanner sc = new Scanner(new File(problemName + ".in"));

        int numGames = sc.nextInt();
        int[] cow1Moves = new int[numGames];
        int[] cow2Moves = new int[numGames];
        for (int i=0; i<numGames; i++) {
            cow1Moves[i] = sc.nextInt();
            cow2Moves[i] = sc.nextInt();
        }

        // algorithm
        int maxWins = hps(cow1Moves, cow2Moves);

        // output
        PrintWriter out = new PrintWriter(new FileWriter(problemName + ".out"));
        out.println(maxWins);
        out.close();
    }
}
