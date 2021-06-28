// Load Balancing - USACO Bronze February 2016 (http://www.usaco.org/index.php?page=viewproblem2&cpid=617)
// This problem was completed as classwork for the USACO Silver 2 Class on 6/27/21.

import java.io.*;
import java.util.*;

public class L16_balancing {
    static class Cow {
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
    }

    public static void main(String[] args) throws IOException {
        // input
        String problemName = "balancing";
        BufferedReader br = new BufferedReader(new FileReader(problemName + ".in"));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int numCows = Integer.parseInt(st.nextToken());

        int[] x = new int[numCows];
        int[] y = new int[numCows];
        Cow[] cowsX = new Cow[numCows];
        Cow[] cowsY = new Cow[numCows];

        PriorityQueue<Cow> pqX = new PriorityQueue<>(new Comparator<Cow>() {
            @Override
            public int compare(Cow o1, Cow o2) {
                return o1.x - o2.x;
            }
        });
        PriorityQueue<Cow> pqY = new PriorityQueue<>(new Comparator<Cow>() {
            @Override
            public int compare(Cow o1, Cow o2) {
                return o1.y - o2.y;
            }
        });

        for (int i=0; i<numCows; i++) {
            st = new StringTokenizer(br.readLine());
            x[i] = Integer.parseInt(st.nextToken());
            y[i] = Integer.parseInt(st.nextToken());

            pqX.add(new Cow(x[i], y[i]));
            pqY.add(new Cow(x[i], y[i]));
        }

        for (int i=0; i<numCows; i++) {
            cowsX[i] = pqX.remove();
            cowsY[i] = pqY.remove();
        }

        //System.out.println(Arrays.toString(cowsX));
        //System.out.println(Arrays.toString(cowsY));

        // algorithm
        int minM = Integer.MAX_VALUE;

        for (Cow cowX: cowsX) {
            int xWall = cowX.x+1;
            for (Cow cowY: cowsY) {
                int yWall = cowY.y+1;
                int q1 = 0, q2 = 0, q3 = 0, q4 = 0;

                for (int i=0; i<numCows; i++) {
                    if (x[i] > xWall && y[i] > yWall) {
                        q1++;
                    } else if (x[i] < xWall && y[i] > yWall) {
                        q2++;
                    } else if (x[i] < xWall && y[i] < yWall) {
                        q3++;
                    } else {
                        q4++;
                    }
                }

                int M = Math.max(q1, Math.max(q2, Math.max(q3, q4)));
                minM = Math.min(minM, M);
            }
        }

        // output
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(problemName + ".out")));

        pw.println(minM);

        br.close();
        pw.close();
    }
}
