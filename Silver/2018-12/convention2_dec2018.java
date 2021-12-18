// Convention II - USACO Silver December 2018 (http://usaco.org/index.php?page=viewproblem2&cpid=859)
// This problem was completed as homework for the USACO Silver 2 Class on 5/1/21.

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class L13_convention2 {
    static class Cow implements Comparable<Cow> {
        int arrival;
        int duration;
        int seniority;

        Cow(int arrival, int duration, int seniority) {
            this.arrival = arrival;
            this.duration = duration;
            this.seniority = seniority;
        }

        public int compareTo(Cow other) {
            return arrival - other.arrival;
        }
    }

    public static void main(String[] args) throws IOException {
        // input
        String problemName = "convention2";
        BufferedReader br = new BufferedReader(new FileReader(problemName + ".in"));
        StringTokenizer st;

        int numCows = Integer.parseInt(br.readLine());

        Cow[] cows = new Cow[numCows];
        for (int i=0; i<numCows; i++) {
            st = new StringTokenizer(br.readLine());
            cows[i] = new Cow(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), i); // smaller seniority goes first
        }

        // algorithm
        Arrays.sort(cows);

        PriorityQueue<Cow> waitingCows = new PriorityQueue<>(new Comparator<Cow>() {
            @Override
            public int compare(Cow o1, Cow o2) {
                return o1.seniority - o2.seniority;
            }
        });

        int maxWait = 0;
        int idx = 0;
        int time = 0;

        while (idx < numCows) {
            if (waitingCows.isEmpty()) {
                waitingCows.add(cows[idx]);
                time = cows[idx++].arrival;
            }

            // dequeue
            Cow currCow = waitingCows.remove();

            maxWait = Math.max(maxWait, time - currCow.arrival);
            time += currCow.duration;

            // enqueue
            while (idx < numCows && cows[idx].arrival <= time) {
                waitingCows.add(cows[idx++]);
            }
        }

        // output
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(problemName + ".out")));
        pw.println(maxWait);

        br.close();
        pw.close();
    }
}
