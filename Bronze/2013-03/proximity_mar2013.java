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
            return breed - another.breed;
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
            // identify start and end of a new section of cow breeds
            int currBreed = cows[idx].breed;
            int endIdx = idx+1;
            while (endIdx < numCows && cows[endIdx].breed == cows[idx].breed) {
                endIdx++;
            }

            // see if these cows are "crowded"
            for (int i=idx; i<endIdx-1; i++) {
                if (cows[i+1].idx - cows[i].idx <= K) {
                    maxBreed = Math.max(maxBreed, currBreed);
                    break;
                }
            }

            idx = endIdx;
        }

        // output
        PrintWriter out = new PrintWriter(new FileWriter(problemName + ".out"));
        out.println(maxBreed);
        out.close();
    }
}
