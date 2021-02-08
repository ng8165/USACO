// Shell Game - USACO Bronze January 2019 (http://www.usaco.org/index.php?page=viewproblem2&cpid=891)
// This problem was completed on October 25, 2020, in 36 minutes, with all 10/10 test cases passed (first try)
// This problem was improved on October 26, 2020, during review

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class shell_jan2019 {
    static int numSwaps;
    static int[][] swaps;
    static int[] guesses;

    public static int shell() {
        // first determine what the game will look like
        int[][] game = new int[numSwaps][3];

        for (int i=0; i<numSwaps; i++) {
            if (i==0) {
                game[i] = swap(swaps[i][0]-1, swaps[i][0]-1, new int[] {1, 2, 3});
            } else {
                game[i] = swap(swaps[i][0]-1, swaps[i][1]-1, game[i-1].clone());
            }
        }

        for (int i=0; i<numSwaps; i++) {
            System.out.println(game[i][0] + " " + game[i][1] + " " + game[i][2]);
        }

        // now start trying each possibility (under 1, under 2, and under 3) and calculating wins
        int maxWins = 0;

        for (int i=1; i<=3; i++) {
            System.out.println("\nloc: " + i);
            int currWins = 0;

            for (int j=0; j<numSwaps; j++) {
                if (game[j][guesses[j]-1] == i) {
                    currWins++;
                    System.out.print(j + " ");
                }
            }

            maxWins = Math.max(maxWins, currWins);
        }

        return maxWins;
    }
    public static int[] swap(int idx1, int idx2, int[] arr) {
        int temp = arr[idx1];
        arr[idx1] = arr[idx2];
        arr[idx2] = temp;

        return arr;
    }

    public static int shell2() {
        int[] game = {1, 2, 3};
        int[] wins = new int[3];

        for (int i=0; i<numSwaps; i++) {
            // simulate the swapping
            swap2(swaps[i][0]-1, swaps[i][1]-1, game);
            System.out.println("Game: " + Arrays.toString(game));

            // see where pebble has to be for cow to win
            wins[game[guesses[i]-1]-1]++;
            System.out.println("Wins: " + Arrays.toString(wins) + "\n");
        }

        return Math.max(wins[0], Math.max(wins[1], wins[2]));
    }
    public static void swap2(int idx1, int idx2, int[] arr) {
        int temp = arr[idx1];
        arr[idx1] = arr[idx2];
        arr[idx2] = temp;
    }


    public static void main(String[] args) throws IOException {
        // input
        String problemName = "shell";
        Scanner sc = new Scanner(new File(problemName + ".in"));

        numSwaps = sc.nextInt();
        swaps = new int[numSwaps][2];
        guesses = new int[numSwaps];
        for (int i=0; i<numSwaps; i++) {
            swaps[i][0] = sc.nextInt();
            swaps[i][1] = sc.nextInt();
            guesses[i] = sc.nextInt();
        }

        // algorithm
        int maxWins = shell2();

        // output
        PrintWriter out = new PrintWriter(new FileWriter(problemName + ".out"));
        out.println(maxWins);
        out.close();
    }
}
