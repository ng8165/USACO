// Paired Up - USACO Silver US Open 2017 (http://www.usaco.org/index.php?page=viewproblem2&cpid=738)
// This problem was completed as classwork for the USACO Silver 2 Class on 7/10/21.

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class L19_pairup {
    static class CowGroup implements Comparable<CowGroup> {
        int unpairedCows;
        int milkTime;

        CowGroup(int unpairedCows, int milkTime) {
            this.unpairedCows = unpairedCows;
            this.milkTime = milkTime;
        }

        @Override
        public String toString() {
            return "(" + unpairedCows + ", " + milkTime + ")";
        }

        @Override
        public int compareTo(CowGroup o) {
            return milkTime - o.milkTime;
        }
    }

    public static void main(String[] args) throws IOException {
        // input
        String problemName = "pairup";
        BufferedReader br = new BufferedReader(new FileReader(problemName + ".in"));
        StringTokenizer st;

        int numLines = Integer.parseInt(br.readLine());
        CowGroup[] cows = new CowGroup[numLines];
        for (int i=0; i<numLines; i++) {
            st = new StringTokenizer(br.readLine());
            cows[i] = new CowGroup(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        // algorithm
        Arrays.sort(cows);

        int left = 0;
        int right = numLines-1;
        int maxMilkingTime = 0;

        // greedy algorithm
        while (left <= right) {
            maxMilkingTime = Math.max(maxMilkingTime, cows[left].milkTime + cows[right].milkTime);

            if (cows[right].unpairedCows > cows[left].unpairedCows) {
                // pair all left cows with as many right cows as possible
                cows[right].unpairedCows -= cows[left].unpairedCows;
                left++;
            } else if (cows[left].unpairedCows > cows[right].unpairedCows) {
                // pair all right cows with as many left cows as possible
                cows[left].unpairedCows -= cows[right].unpairedCows;
                right--;
            } else {
                // pair all left cows with all right cows
                left++;
                right--;
            }
        }

        // output
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(problemName + ".out")));

        pw.println(maxMilkingTime);

        br.close();
        pw.close();
    }
}
