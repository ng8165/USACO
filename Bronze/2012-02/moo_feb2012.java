// Moo - USACO Bronze February 2012 (http://www.usaco.org/index.php?page=viewproblem2&cpid=114)
// This problem was completed as classwork for the USACO Silver 1 Class on 2/20/21.

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class L3_moo {
    static int idx;
    static String directoryName = "moo";

    public static char moo() {
        // find value of k
        int k = 0;
        int length = 3;
        while (length < idx) {
            k++;
            length = length+k+3+length;
        }

        // recursively find character
        return findMoo(k, idx, length);
    }
    public static char findMoo(int k, int idx, int length) {
        if (k==0) {
            if (idx == 1) {
                return 'm';
            } else {
                return 'o';
            }
        }

        int prevMooLength = (length-k-3)/2;

        if (idx <= prevMooLength) {
            return findMoo(k-1, idx, prevMooLength);
        } else if (idx == prevMooLength+1) {
            return 'm';
        } else if (idx > prevMooLength+1 && idx <= prevMooLength+k+3) {
            return 'o';
        } else {
            return findMoo(k-1, idx-prevMooLength-k-3, prevMooLength);
        }
    }

    public static char solve(int i) throws IOException {
        // read input
        Scanner scin = new Scanner(new File(directoryName + "/" + i + ".in"));
        idx = scin.nextInt();

        // algorithm and return output
        return moo();
    }
    public static boolean isCorrect(int i) throws IOException{
        // read correct output
        Scanner scout = new Scanner(new File(directoryName + "/" + i + ".out"));
        char correctResult = scout.next().charAt(0);

        // check
        System.out.print("Test Case " + i);

        long before = System.currentTimeMillis();
        char result = solve(i);
        long after = System.currentTimeMillis();

        if (result == correctResult) {
            System.out.println(": Correct - took " + (after-before) + " milliseconds (Expected and Received " + correctResult +")");
            return true;
        } else {
            System.out.println(": Incorrect - took " + (after-before) + " milliseconds (Expected " + correctResult + ", Received " + result + ")");
            return false;
        }
    }

    public static void main(String[] args) throws IOException {
        int numCorrect = 0;

        for (int i=1; i<=10; i++) {
            if (isCorrect(i)) {
                numCorrect++;
            }
        }

        System.out.println("\n" + numCorrect + " correct, " + (10-numCorrect) + " incorrect");
    }
}
