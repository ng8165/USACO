// Lifeguards - USACO Silver January 2018 (http://www.usaco.org/index.php?page=viewproblem2&cpid=786)
// This problem was completed as homework for the USACO Silver 1 Class on 2/28/21.

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class L4_lifeguards {
    static int numLifeguards;
    static Event[] events;

    static class Event implements Comparable<Event> {
        int time;
        int idx;
        boolean isStart;

        Event(int time, int idx, boolean isStart) {
            this.time = time;
            this.idx = idx;
            this.isStart = isStart;
        }

        public int compareTo(Event another) {
            return time - another.time;
        }
    }

    public static int lifeguards() {
        // sort events by time
        Arrays.sort(events);

        HashSet<Integer> currLifeguards = new HashSet<>();
        int[] aloneTime = new int[numLifeguards];
        int totalTime = 0;

        for (int i=0; i<numLifeguards*2; i++) {
            if (currLifeguards.size() == 1) {
                // lifeguard is alone, add to their alone time
                int aloneIdx = -1;
                for (int idx: currLifeguards) {
                    // get idx of alone lifeguard (runs only once)
                    aloneIdx = idx;
                }

                aloneTime[aloneIdx] += (events[i].time - events[i-1].time);
            }

            if (currLifeguards.size() > 0) {
                // finding the total time of lifeguards if no firing
                totalTime += (events[i].time - events[i-1].time);
            }

            // lifeguard start/end points
            if (events[i].isStart) {
                currLifeguards.add(events[i].idx);
            } else {
                currLifeguards.remove(events[i].idx);
            }
        }

        // find minimum alone time to maximize lifeguard time
        int minAlone = Integer.MAX_VALUE;

        for (int alone: aloneTime) {
            minAlone = Math.min(minAlone, alone);
        }

        return totalTime - minAlone;
    }

    public static void main(String[] args) throws IOException {
        // input
        String problemName = "lifeguards";
        Scanner sc = new Scanner(new File(problemName + ".in"));

        numLifeguards = sc.nextInt();
        events = new Event[numLifeguards*2];
        for (int i=0; i<numLifeguards; i++) {
            events[i*2] = new Event(sc.nextInt(), i, true);
            events[i*2+1] = new Event(sc.nextInt(), i, false);
        }

        // algorithm
        int maxLifeguardTime = lifeguards();

        // output
        PrintWriter out = new PrintWriter(new FileWriter(problemName + ".out"));
        out.println(maxLifeguardTime);
        out.close();
    }
}
