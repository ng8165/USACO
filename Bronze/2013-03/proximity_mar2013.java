// Breed Proximity - USACO Bronze March 2013 (http://www.usaco.org/index.php?page=viewproblem2&cpid=260)
// This problem completed for the USACO Silver Udemy Course, passing all 10/10 test cases.

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class proximity_mar2013 {
    public static void main(String[] args) throws IOException {
        // input
        String problemName = "proximity";
        Scanner sc = new Scanner(new File(problemName + ".in"));

        int numCows = sc.nextInt();
        int K = sc.nextInt();
        int[] cowBreed = new int[numCows];
        for (int i=0; i<numCows; i++) {
            cowBreed[i] = sc.nextInt();
        }

        // algorithm
        int[] breedCnt = new int[1000001];
        int maxBreed = -1;

        for (int i=0; i<numCows; i++) {
            // check if we need to remove any cows
            if (i > K) {
                breedCnt[cowBreed[i-K-1]]--;
            }

            // add new cows
            breedCnt[cowBreed[i]]++;

            if (breedCnt[cowBreed[i]] > 1) {
                maxBreed = Math.max(maxBreed, cowBreed[i]);
            }
        }

        // output
        PrintWriter out = new PrintWriter(new FileWriter(problemName + ".out"));
        out.println(maxBreed);
        out.close();
    }
}
