// Sleepy Cow Sorting - USACO Bronze January 2019 (http://www.usaco.org/index.php?page=viewproblem2&cpid=892)
// This problem was mostly completed on October 25, 2020, in 13 minutes, with 11/12 test cases passed (first try)
// This problem was completed on October 26, 2020, during review, with all 12/12 test cases passed (during review)

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class sleepy_jan2019 {
    static int numCows;
    static ArrayList<Integer> cows = new ArrayList<>();
    static int[] cows2;

    public static int sleepy() {
        // sanity check
        if (numCows == 1 || isArrayListSorted(cows)) {
            return 0;
        }

        for (int i=0; i<numCows; i++) {
            // remove the first cow
            cows.remove(0);

            // see if the remaining cow order is sorted
            if (isArrayListSorted(cows)) {
                return (i+1);
            }
        }

        return 0; // this should never happen, avoid compilation errors
    }
    public static boolean isArrayListSorted(ArrayList<Integer> arrList) {
        ArrayList<Integer> sortedArrList = (ArrayList<Integer>) arrList.clone();
        Collections.sort(sortedArrList);

        if (sortedArrList.equals(arrList)) {
            return true;
        } else {
            return false;
        }
    }

    public static int sleepy2() {
        for (int i=numCows-1; i>0; i--) {
            if (cows2[i] < cows2[i-1]) {
                return i;
            }
        }

        return 0; // if the cows are already sorted
    }

    public static void main(String[] args) throws IOException {
        // input
        String problemName = "sleepy";
        Scanner sc = new Scanner(new File(problemName + ".in"));

        numCows = sc.nextInt();
        cows2 = new int[numCows];
        for (int i=0; i<numCows; i++) {
            int input = sc.nextInt();
            cows.add(input);
            cows2[i] = input;
        }

        // algorithm
        int minSwaps = sleepy2();

        // output
        PrintWriter out = new PrintWriter(new FileWriter(problemName + ".out"));
        out.println(minSwaps);
        out.close();
    }
}
