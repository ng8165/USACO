// Social Distancing - USACO Silver US Open 2020 (http://www.usaco.org/index.php?page=viewproblem2&cpid=1038)
// This problem was completed as homework for the USACO Silver 2 Class on 7/5/21.

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class L18_socdist {
    static int numCows;
    static int numGrass;
    static Grass[] grass;

    static class Grass implements Comparable<Grass> {
        long left;
        long right;

        Grass(long left, long right) {
            this.left = left;
            this.right = right;
        }

        public int compareTo(Grass o) {
            return (int) (left - o.left);
        }
    }

    public static boolean isValidD(long D) {
        long prevLoc = grass[0].left;
        int cowCnt = 1;

        for (int i=0; i<numGrass; i++) {
            // add as many cows as possible into current grass
            while (cowCnt <= numCows) {
                long newLoc = prevLoc + D;

                if (newLoc < grass[i].left) {
                    // before current grass starts
                    prevLoc = grass[i].left;
                    cowCnt++;
                } else if (newLoc <= grass[i].right) {
                    // in current grass
                    prevLoc = newLoc;
                    cowCnt++;
                } else {
                    // move to next grass
                    break;
                }
            }
        }

        return cowCnt >= numCows;
    }

    public static void main(String[] args) throws IOException {
        // input
        String problemName = "socdist";
        BufferedReader br = new BufferedReader(new FileReader(problemName + ".in"));
        StringTokenizer st = new StringTokenizer(br.readLine());

        numCows = Integer.parseInt(st.nextToken());
        numGrass = Integer.parseInt(st.nextToken());
        grass = new Grass[numGrass];
        long maxRight = 0;
        for (int i=0; i<numGrass; i++) {
            st = new StringTokenizer(br.readLine());
            grass[i] = new Grass(Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken()));
            maxRight = Math.max(maxRight, grass[i].right);
        }

        // algorithm
        Arrays.sort(grass); // sort by left

        long left = 1;
        long right = maxRight-grass[0].left+1;

        // binary search
        while (left < right-1) {
            long mid = left + (right-left)/2;

            if (isValidD(mid)) {
                left = mid;
            } else {
                right = mid-1;
            }
        }

        // check last case
        long maxD;
        if (isValidD(right)) { // left is equal to right-1
            maxD = right;
        } else {
            maxD = left;
        }

        // output
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(problemName + ".out")));

        pw.println(maxD);

        br.close();
        pw.close();
    }
}
