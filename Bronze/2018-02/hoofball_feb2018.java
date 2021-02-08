// Hoofball - USACO Bronze February 2018 (http://www.usaco.org/index.php?page=viewproblem2&cpid=808)
// This problem was not completed on October 4, 2020, in 1 hour, with only 1/10 test cases passed (second try)
// This problem was completed on October 10, 2020, during review, with only all 10/10 test cases passed (review)

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class hoofball_feb2018 {
    static int numCows;
    static int[] cowPos;

    public static int hoofball() {
        int[] cowPassFrom = cowPos.clone();
        Arrays.sort(cowPassFrom);

        // first determine which cow every cow will pass to
        int[] cowPassTo = new int[numCows];
        cowPassTo[0] = cowPassFrom[1];
        cowPassTo[numCows-1] = cowPassFrom[numCows-2];

        for (int i=1; i<numCows-1; i++) {
            int leftDistance = cowPassFrom[i] - cowPassFrom[i-1];
            int rightDistance = cowPassFrom[i+1] - cowPassFrom[i];
            if (leftDistance > rightDistance) {
                cowPassTo[i] = cowPassFrom[i+1];
            } else {
                cowPassTo[i] = cowPassFrom[i-1];
            }
        }

        for (int i=0; i<numCows; i++) {
            System.out.println(cowPassFrom[i] + " --> " + cowPassTo[i]);
        }

        System.out.println("\ncowPassFrom: " + Arrays.toString(cowPassFrom));
        System.out.println("cowPassTo: " + Arrays.toString(cowPassTo) + "\n");

        ArrayList<Integer> cowsWithoutBall = new ArrayList<>();
        for (int i=0; i<numCows; i++) {
            cowsWithoutBall.add(cowPassFrom[i]);
        }

        int minBalls = 0;
        while (cowsWithoutBall.size() > 0) {
            ArrayList<Integer> maxCowsGetBall = new ArrayList<>();
            int maxCowsGetBallSize = 0;
            for (int i=0; i<numCows; i++) {
                ArrayList<Integer> currCowsGetBall = findCowsGetBall(i, cowPassFrom, cowPassTo);
                if (maxCowsGetBallSize < currCowsGetBall.size()) {
                    maxCowsGetBallSize = currCowsGetBall.size();
                    maxCowsGetBall = currCowsGetBall;
                }
            }

            for (int i=0; i<maxCowsGetBall.size(); i++) {
                for (int j=0; j<cowsWithoutBall.size(); j++) {
                    if (maxCowsGetBall.get(i).equals(cowsWithoutBall.get(j))) {
                        cowsWithoutBall.remove(j);
                    }
                }
            }

            minBalls++;
        }
        return minBalls;
    }
    public static ArrayList<Integer> findCowsGetBall(int idx, int[] cowPassFrom, int[] cowPassTo) {
        ArrayList<Integer> cowsGetBall = new ArrayList<>();
        cowsGetBall.add(cowPassFrom[idx]);
        int currCowTo = cowPassTo[idx];

        while (true) {
            // find repeats
            for (int i=0; i<cowsGetBall.size(); i++) {
                if (currCowTo == cowsGetBall.get(i)) {
                    return cowsGetBall;
                }
            }
            cowsGetBall.add(currCowTo);

            // find the next cow
            for (int i=0; i<numCows; i++) {
                if (cowPassFrom[i] == currCowTo) {
                    currCowTo = cowPassTo[i];
                    break;
                }
            }
        }
    }

    public static int hoofball2() {
        int[] cowPassFrom = cowPos.clone();
        Arrays.sort(cowPassFrom);

        int[] cowPassTo = new int[numCows];
        int[] cowPassToCnt = new int[numCows];
        cowPassTo[0] = cowPassFrom[1];
        cowPassToCnt[1]++;
        cowPassTo[numCows-1] = cowPassFrom[numCows-2];
        cowPassToCnt[numCows-2]++;

        for (int i=1; i<numCows-1; i++) {
            int leftDistance = cowPassFrom[i] - cowPassFrom[i-1];
            int rightDistance = cowPassFrom[i+1] - cowPassFrom[i];

            if (leftDistance > rightDistance) {
                cowPassTo[i] = cowPassFrom[i+1];
                cowPassToCnt[i+1]++;
            } else {
                cowPassTo[i] = cowPassFrom[i-1];
                cowPassToCnt[i-1]++;
            }
        }

        System.out.println(Arrays.toString(cowPassFrom));
        System.out.println(Arrays.toString(cowPassTo));
        System.out.println(Arrays.toString(cowPassToCnt));


        int minBalls = 0;

        for (int i=0; i<numCows; i++) {
            if (cowPassToCnt[i] == 0) {
                System.out.println(i);
                minBalls++;
            } else if (i<numCows-1 && (cowPassToCnt[i] == 1) && (cowPassToCnt[i+1] == 1)) {
                if ((cowPassFrom[i] == cowPassTo[i+1]) && (cowPassFrom[i+1] == cowPassTo[i])) {
                    System.out.println(i + " " + (i+1));
                    minBalls++;
                }
            }
        }

        return minBalls;
    }

    public static void main(String[] args) throws IOException {
        // input
        String problemName = "hoofball";
        Scanner sc = new Scanner(new File(problemName + ".in"));

        numCows = sc.nextInt();
        cowPos = new int[numCows];
        for (int i=0; i<numCows; i++) {
            cowPos[i] = sc.nextInt();
        }

        // algorithm
        int minBalls = hoofball2();

        // output
        PrintWriter out = new PrintWriter(new FileWriter(problemName + ".out"));
        out.println(minBalls);
        out.close();
    }
}
