// Cow Dance Show - USACO Silver January 2017 (http://www.usaco.org/index.php?page=viewproblem2&cpid=690)
// This problem was completed as homework for the USACO Silver 2 Class on 7/5/21.

import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class L18_cowdance {
    static int numCows;
    static int maxTime;
    static int[] cowDance;

    public static int calculateDanceTime(int K) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int time = 0;
        int idx = 0;

        // add first K cows (all start at time 0)
        while (idx < K) {
            pq.add(cowDance[idx++]);
        }

        while (!pq.isEmpty()) {
            time = pq.remove();

            if (idx < numCows) {
                pq.add(time + cowDance[idx++]); // add exit time
            }
        }

        return time;
    }

    public static void main(String[] args) throws IOException {
        // input
        String problemName = "cowdance";
        BufferedReader br = new BufferedReader(new FileReader(problemName + ".in"));
        StringTokenizer st = new StringTokenizer(br.readLine());

        numCows = Integer.parseInt(st.nextToken());
        maxTime = Integer.parseInt(st.nextToken());
        cowDance = new int[numCows];
        for (int i=0; i<numCows; i++) {
            cowDance[i] = Integer.parseInt(br.readLine());
        }

        // algorithm
        int left = 1;
        int right = numCows;

        // binary search
        while (left < right) {
            int mid = left + (right-left)/2;

            if (calculateDanceTime(mid) <= maxTime) {
                right = mid;
            } else {
                left = mid+1;
            }
        }

        // output
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(problemName + ".out")));

        pw.println(left); // left is the same as right

        br.close();
        pw.close();
    }
}
