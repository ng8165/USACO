// Load Balancing - USACO Bronze February 2016 (http://www.usaco.org/index.php?page=viewproblem2&cpid=617)
// This problem was completed as classwork for the USACO Silver 2 Class on 6/28/21.
// This implementation was influenced by Cararra's video on YouTube (https://www.youtube.com/watch?v=bEEbslngvxI).

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class L16_balancing {
    static class Cow implements Comparable<Cow>{
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
            return x-o.x;
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
        Cow[] cows = new Cow[numCows];

        for (int i=0; i<numCows; i++) {
            st = new StringTokenizer(br.readLine());
            cows[i] = new Cow(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        // algorithm
        Arrays.sort(cows);
        //System.out.println(Arrays.toString(cows));

        int minM = Integer.MAX_VALUE;

        for (int i=0; i<numCows-1; i++) {
            int yWall = cows[i].y+1;

            // count cows while xWall is at the very left
            int q1 = 0, q2 = 0, q3 = 0, q4 = 0;
            for (int j=0; j<numCows; j++) {
                if (cows[j].y > yWall) {
                    q1++;
                } else {
                    q4++;
                }
            }

            for (int j=0; j<numCows-1; j++) {
                while (true) {
                    if (cows[j].y > yWall) {
                        q1--;
                        q2++;
                    } else {
                        q4--;
                        q3++;
                    }

                    if (cows[j].x != cows[j+1].x) {
                        break;
                    } else {
                        j++;
                    }
                }
                int M = Math.max(Math.max(q1, q2), Math.max(q3, q4));
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
