// Hoofball - USACO Bronze February 2018 (http://www.usaco.org/index.php?page=viewproblem2&cpid=808)

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class L11_hoofball {
    public static int hoofball(int[] cowPositions, int numCows) {
        Arrays.sort(cowPositions);

        int[] cowsThrowTo = new int[numCows+1];
        int[] receivedBallsCnt = new int[numCows+1];

        for (int i=1; i<=numCows; i++) {
            // special cases: index 1 and index numCows
            if (i == 1) {
                cowsThrowTo[i] = i+1;
                receivedBallsCnt[i+1]++;
            } else if (i == numCows) {
                cowsThrowTo[i] = i-1;
                receivedBallsCnt[i-1]++;
            } else {
                int leftDistance = cowPositions[i] - cowPositions[i-1];
                int rightDistance = cowPositions[i+1] - cowPositions[i];

                if (rightDistance < leftDistance) {
                    // if rightDistance is smaller then we pass to the right
                    cowsThrowTo[i] = i+1;
                    receivedBallsCnt[i+1]++;
                } else {
                    // if leftDistance is smaller or leftDistance is equal to rightDistance then we pass to the left
                    cowsThrowTo[i] = i-1;
                    receivedBallsCnt[i-1]++;
                }
            }
        }

        int numBallsFromFJ = 0;

        for (int i=1; i<=numCows; i++) {
            if (receivedBallsCnt[i] == 0) {
                numBallsFromFJ++;
            } else if (receivedBallsCnt[i] == 1 && i < numCows) {
                // is it an independent pair loop?
                if (receivedBallsCnt[i+1] == 1 && cowsThrowTo[i] == i+1 && cowsThrowTo[i+1] == i) {
                    numBallsFromFJ++;
                }
            }
        }

        return numBallsFromFJ;
    }

    public static void main(String[] args) throws IOException {
        // input
        String problemName = "hoofball";
        Scanner sc = new Scanner(new File(problemName + ".in"));
        int numCows = sc.nextInt();
        int[] cowPositions = new int[numCows+1];
        for (int i=1; i<=numCows; i++) {
            cowPositions[i] = sc.nextInt();
        }

        // algorithm
        int numBallsFromFJ = hoofball(cowPositions, numCows);

        // output
        PrintWriter out = new PrintWriter(new FileWriter(problemName + ".out"));
        out.println(numBallsFromFJ);
        out.close();
    }
}
