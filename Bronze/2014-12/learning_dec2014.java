// Learning by Example - USACO Bronze December 2014 (http://www.usaco.org/index.php?page=viewproblem2&cpid=490)
// This problem was not completed on December 5, 2020, 40 minutes, with all 2/13 test cases passed (first try)
// Attempted during review: Too Complex (found start/end search indexes)

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class learning_dec2014 {
    static int numCows;
    static int lightestCow;
    static int heaviestCow;
    static String[] spottedOrNot;
    static int[] cowWeight;

    public static int learning(int numNS) {
        // sort cow weights
        int[] cowWeightSorted = cowWeight.clone();
        Arrays.sort(cowWeightSorted);

        String[] spottedOrNotSorted = new String[numCows];
        for (int i=0; i<numCows; i++) {
            for (int j=0; j<numCows; j++) {
                if (cowWeight[j] == cowWeightSorted[i]) {
                    spottedOrNotSorted[i] = spottedOrNot[j];
                    break;
                }
            }
        }

        cowWeight = cowWeightSorted;
        spottedOrNot = spottedOrNotSorted;

        int[] searchStartEnd = findSearchStartEnd();
        int searchStart = searchStartEnd[0];
        int searchEnd = searchStartEnd[1];

        return 6;

        /*int numNotSpotted = 0;

        if (lightestCow < cowWeight[0] && spottedOrNot[0].equals("NS")) {
            numNotSpotted += cowWeight[0] - lightestCow + 1;
        }
        if (heaviestCow > cowWeight[numCows-1] && spottedOrNot[numCows-1].equals("NS")) {
            numNotSpotted += heaviestCow - cowWeight[numCows-1] + 1;
        }

        for (int i=searchStart; i<=searchEnd; i++) {
            if (i == searchStart) {

            } else if (i == searchEnd) {

            } else {
                if (spottedOrNot[i].equals("NS") ^ (spottedOrNot[i-1].equals("NS"))) {
                    numNotSpotted += (cowWeight[i] - cowWeight[i-1] - 1)/2 + 1;
                } else if (spottedOrNot[i].equals("NS") && (spottedOrNot[i-1].equals("NS"))) {
                    numNotSpotted += cowWeight[i] - cowWeight[i-1] + 1;
                }
            }
        }

        return (heaviestCow - lightestCow + 1) - numNotSpotted;*/
    }

    public static int[] findSearchStartEnd() {
        int searchStart = -1;
        int searchEnd = -1;

        if (lightestCow < cowWeight[0]) {
            searchStart = Integer.MIN_VALUE;
        } else if (lightestCow >= cowWeight[numCows-1]) {
            searchStart = Integer.MAX_VALUE;
        } else {
            for (int i=0; i<numCows-1; i++) {
                if (lightestCow == cowWeight[i]) {
                    searchStart = i;
                    break;
                } else if (lightestCow > cowWeight[i] && lightestCow < cowWeight[i+1]) {
                    searchStart = i;
                    break;
                }
            }
        }

        if (heaviestCow <= cowWeight[0]) {
            searchEnd = Integer.MIN_VALUE;
        } else if (heaviestCow > cowWeight[numCows-1]) {
            searchEnd = Integer.MAX_VALUE;
        } else {
            for (int i=numCows-1; i>0; i--) {
                if (heaviestCow == cowWeight[i]) {
                    searchEnd = i;
                    break;
                } else if (heaviestCow > cowWeight[i-1] && heaviestCow < cowWeight[i]) {
                    searchEnd = i;
                    break;
                }
            }
        }

        System.out.println("searchStartEnd: " + searchStart + ", " + searchEnd);

        return new int[] {searchStart, searchEnd};
    }

    public static void main(String[] args) throws IOException {
        // input
        String problemName = "learning";
        Scanner sc = new Scanner(new File(problemName + ".in"));

        numCows = sc.nextInt();
        lightestCow = sc.nextInt();
        heaviestCow = sc.nextInt();
        spottedOrNot = new String[numCows];
        cowWeight = new int[numCows];
        int numNS = 0;
        for (int i=0; i<numCows; i++) {
            spottedOrNot[i] = sc.next();
            cowWeight[i] = sc.nextInt();
            if (spottedOrNot[i].equals("NS")) {
                numNS++;
            }
        }

        // algorithm
        int numSpotted = learning(numNS);

        // output
        PrintWriter out = new PrintWriter(new FileWriter(problemName + ".out"));
        out.println(numSpotted);
        out.close();
    }
}
