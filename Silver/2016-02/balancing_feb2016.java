// Load Balancing - USACO Bronze February 2016 (http://www.usaco.org/index.php?page=viewproblem2&cpid=617)
// This problem was completed as classwork for the USACO Silver 2 Class on 6/28/21.

import java.io.*;
import java.util.Arrays;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class L16_balancing {
    static class Cow implements Comparable<Cow> {
        int x;
        int y;

        Cow(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "(" + x + ", " + y + ")";
        }

        @Override
        public int compareTo(Cow o) {
            return x - o.x;
        }
    }

    public static void main(String[] args) throws IOException {
        // input
        String problemName = "balancing";
        BufferedReader br = new BufferedReader(new FileReader(problemName + ".in"));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int numCows = Integer.parseInt(st.nextToken());

        Cow[] cowsX = new Cow[numCows];
        TreeMap<Integer, Integer> cowsY = new TreeMap<>(); // key: y coordinate, value: number of cows with same y

        for (int i=0; i<numCows; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            cowsX[i] = new Cow(x, y);
            cowsY.put(y, cowsY.getOrDefault(y,0)+1);
        }

        // algorithm
        Arrays.sort(cowsX);
        //System.out.println(Arrays.toString(cowsX));
        //System.out.println(cowsY);

        int minM = Integer.MAX_VALUE;
        int countBelow = 0; // prefix count

        for (Map.Entry<Integer, Integer> cowCount: cowsY.entrySet()) { // set horizontal fence, then try vertical fences
            countBelow += cowCount.getValue();

            // initialize quadrants to when the vertical fence is at the very left
            int q1 = numCows-countBelow;
            int q2 = 0;
            int q3 = 0;
            int q4 = countBelow;

            int hFence = cowCount.getKey()+1;

            int i=0;
            while (i<numCows-1) {
                // shift from right quadrants to left quadrants
                if (cowsX[i].y > hFence) {
                    q1--;
                    q2++;
                } else {
                    q4--;
                    q3++;
                }

                // skip M update if next cow has the same x
                if (cowsX[i].x == cowsX[i+1].x) {
                    i++;
                    continue;
                }

                int M = Math.max(Math.max(q1, q2), Math.max(q3, q4));
                minM = Math.min(minM, M);

                i++;
            }
        }

        // output
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(problemName + ".out")));

        pw.println(minM);

        br.close();
        pw.close();
    }
}
