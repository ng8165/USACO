// The Bovine Shuffle - USACO Bronze December 2017 (http://www.usaco.org/index.php?page=viewproblem2&cpid=760)
// This problem was completed on Sunday, September 20, 2020, in 24 minutes, with 10/10 test cases passed (second try)

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class shuffle_dec2017 {
    static int numCows;
    static int[] shuffle;
    static String[] finalCowPos;
    
    public static String[] shuffle() {
        // generate a de-shuffle
        int[] deShuffle = new int[numCows+1];
        for (int i=1; i<=numCows; i++) {
            deShuffle[shuffle[i]] = i;
        }

        // start de-shuffling into the starting position
        String[] currCowPos = finalCowPos.clone();
        for (int i=0; i<3; i++) {
            currCowPos = startDeshuffle(deShuffle, currCowPos);
        }

        return currCowPos;
    }

    public static String[] startDeshuffle(int[] deShuffle, String[] currCowPos) {
        String[] nextCowPos = new String[numCows+1];
        for (int i=1; i<=numCows; i++) {
            nextCowPos[deShuffle[i]] = currCowPos[i];
        }
        return nextCowPos;
    }

    public static void main(String[] args) throws IOException {
        // input
        String problemName = "shuffle";
        Scanner sc = new Scanner(new File(problemName + ".in"));

        numCows = sc.nextInt();
        shuffle = new int[numCows+1];
        finalCowPos = new String[numCows+1];
        for (int i=1; i<=numCows; i++) {
            shuffle[i] = sc.nextInt();
        }
        for (int i=1; i<=numCows; i++) {
            finalCowPos[i] = sc.next();
        }

        // algorithm
        String[] currCowPos = shuffle();

        // output
        PrintWriter out = new PrintWriter(new FileWriter(problemName + ".out"));
        for (int i=1; i<=numCows; i++) {
            out.println(currCowPos[i]);
        }
        out.close();
    }
}
