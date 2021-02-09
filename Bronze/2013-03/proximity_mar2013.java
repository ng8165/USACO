// Breed Proximity - USACO Bronze March 2013 (http://www.usaco.org/index.php?page=viewproblem2&cpid=260)
// This problem completed for the USACO Silver Udemy Course, passing all 10/10 test cases.

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class proximity_mar2013 {
    static class Cow implements Comparable<Cow>{
        int breed;
        int idx;

        Cow(int breed, int idx) {
            this.breed = breed;
            this.idx = idx;
        }

        public int compareTo(Cow another) {
            if (breed == another.breed) {
                return idx - another.idx;
            } else {
                return another.breed - breed;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        // input
        String problemName = "proximity";
        Scanner sc = new Scanner(new File(problemName + ".in"));

        int numCows = sc.nextInt();
        int K = sc.nextInt();
        Cow[] cows = new Cow[numCows];
        for (int i=0; i<numCows; i++) {
            cows[i] = new Cow(sc.nextInt(), i);
        }

        // algorithm
        Arrays.sort(cows);

        int maxBreed = -1;
        int idx = 0;
        while (idx < numCows) {
            if (cows[idx].breed == cows[idx+1].breed && (cows[idx+1].idx-cows[idx].idx) <= K) {
                maxBreed = cows[idx].breed;
                break;
            }

            idx++;
        }

        // output
        PrintWriter out = new PrintWriter(new FileWriter(problemName + ".out"));
        out.println(maxBreed);
        out.close();
    }
}
