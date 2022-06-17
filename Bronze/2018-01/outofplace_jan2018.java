// Out of Place - USACO Bronze January 2018 (http://www.usaco.org/index.php?page=viewproblem2&cpid=785)
// This problem was slightly completed on September 27, 2020, in 1 hour, with 4/10 test cases passed (second try)
// Ths Problem was completed on September 28, 2020, during review, with all 10/10 test cases passed (review)

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class outofplace_jan2018 {
    static int numCows;
    static int[] cowOrder;

    public static int outofplace() {
        // find the correct cow order
        int[] correctCowOrder = cowOrder.clone();
        Arrays.sort(correctCowOrder);

        if (Arrays.equals(cowOrder, correctCowOrder)) {
            return 0;
        }

        // find bessie's height and position
        int bessieHeight = 0;
        int bessieCorrectPos = 0;
        for (int i=0; i<numCows; i++) {
            if (correctCowOrder[i] != cowOrder[i]) {
                bessieHeight = correctCowOrder[i];
                bessieCorrectPos = i;
                break;
            }
        }

        int bessieCurrPos = 0;
        for (int i=0; i<numCows; i++) {
            if (cowOrder[i] == bessieHeight) {
                bessieCurrPos = i;
                break;
            }
        }

        // now we know the range of the swaps, so we can find the num of unique numbers
        int numSwaps = 0;
        if (bessieCurrPos < bessieCorrectPos) {
            for (int i=bessieCurrPos+1; i<bessieCorrectPos; i++) {
                for (int j=i+1; j<bessieCorrectPos; j++) {
                    if (cowOrder[j] != cowOrder[i]) {
                        i=j;
                        numSwaps++;
                        break;
                    }
                }
            }
        } else {
            for (int i=bessieCurrPos-1; i>bessieCorrectPos; i--) {
                for (int j=i-1; j>bessieCorrectPos; j--) {
                    if (cowOrder[j] != cowOrder[i]) {
                        i=j;
                        numSwaps++;
                        break;
                    }
                }
            }
        }

        return numSwaps;
    }

    public static int outofplace2() {
        // first generate the correct cow order (before bessie messed it up)
        int[] correctCowOrder = cowOrder.clone();
        Arrays.sort(correctCowOrder);

        // now determine bessie's height and location in the order
        int bessiePos = -1;
        int bessieHeight = -1;
        for (int i=1; i<numCows; i++) {
            if (cowOrder[i] < cowOrder[i-1]) {
                // there is a decrease in heights, but we do not know which cow is bessie, so test both
                if (isCowBessie(i-1)) {
                    bessiePos = i-1;
                    bessieHeight = cowOrder[i-1];
                } else {
                    bessiePos = i;
                    bessieHeight = cowOrder[i];
                }
                break;
            }
        }

        System.out.println("bessie pos " + bessiePos + ", bessie height " + bessieHeight);

        // now determine bessie's location in the correct cow order
        int bessieCorrectPos = -1;
        for (int i=0; i<numCows; i++) {
            if (bessieHeight == correctCowOrder[i]) {
                bessieCorrectPos = i;
                break;
            }
        }

        if (bessieCorrectPos < bessiePos) {
            for (int i=numCows-1; i>=0; i--) {
                if (bessieHeight == correctCowOrder[i]) {
                    bessieCorrectPos = i;
                    break;
                }
            }
        }

        System.out.println("bessie correct pos " + bessieCorrectPos);

        // now find the start and end ranges for the swap
        int numSwaps = 0;

        if (bessieCorrectPos < bessiePos) {
            for (int i=bessiePos; i>bessieCorrectPos; i--) {
                if (cowOrder[i] != cowOrder[i-1]) { // we don't want duplicates, we can skip over all of that
                    numSwaps++;
                }
            }
        } else {
            for (int i=bessiePos; i<bessieCorrectPos; i++) {
                if (cowOrder[i] != cowOrder[i+1]) { // we don't want duplicates, we can skip over all of that
                    numSwaps++;
                }
            }
        }

        System.out.println(numSwaps);

        return numSwaps;
    }
    public static boolean isCowBessie(int cowPos) {
        // first generate a cow order without the cow
        int[] tempCowOrder = new int[numCows-1];
        for (int j=0, idx=0; j<numCows; j++) {
            if (j == cowPos) {
                continue;
            }
            tempCowOrder[idx++] = cowOrder[j];
        }

        // sort this new cow order
        int[] tempSortedCowOrder = tempCowOrder.clone();
        Arrays.sort(tempSortedCowOrder);

        // if the new cow order is equal to the sorted cow order, then the cow is bessie!
        if (Arrays.equals(tempCowOrder, tempSortedCowOrder)) {
            return true;
        } else {
            return false;
        }
    }

    public static int outofplace3() {
        // create the correct cow order (before bessie messed the order up)
        int[] correctCowOrder = cowOrder.clone();
        Arrays.sort(correctCowOrder);

        int numSwaps = 0;

        for (int i=0; i<numCows; i++) {
            // for every inconsistency in the two orders, we need to swap
            if (cowOrder[i] != correctCowOrder[i]) {
                numSwaps++;
            }
        }

        return (numSwaps-1);
    }

    public static void main(String[] args) throws IOException {
        // input
        String problemName = "outofplace";
        Scanner sc = new Scanner(new File(problemName + ".in"));

        numCows = sc.nextInt();
        cowOrder = new int[numCows];
        for (int i=0; i<numCows; i++) {
            cowOrder[i] = sc.nextInt();
        }

        // algorithm
        int numSwaps = outofplace2();

        // output
        PrintWriter out = new PrintWriter(new FileWriter(problemName + ".out"));
        out.println(numSwaps);
        out.close();
    }
}
