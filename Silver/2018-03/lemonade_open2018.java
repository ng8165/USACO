// Lemonade Line - USACO Silver US Open 2018 (http://www.usaco.org/index.php?page=viewproblem2&cpid=835)
// This problem was completed as classwork for the USACO Silver 2 Class on 7/8/21.

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class L19_lemonade {
    public static void main(String[] args) throws IOException {
        // input
        String problemName = "lemonade";
        BufferedReader br = new BufferedReader(new FileReader(problemName + ".in"));

        int numCows = Integer.parseInt(br.readLine());
        int[] waitTimes = new int[numCows];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=0; i<numCows; i++) {
            waitTimes[i] = Integer.parseInt(st.nextToken());
        }

        // algorithm
        Arrays.sort(waitTimes);

        // greedy algorithm
        int cowsInLine = 0;
        for (int i=numCows-1; i>=0; i--) {
            if (cowsInLine <= waitTimes[i]) {
                cowsInLine++;
            } else {
                // all other cows wait less, so all following cows won't go in line
                break;
            }
        }


        // output
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(problemName + ".out")));

        pw.println(cowsInLine);

        br.close();
        pw.close();
    }
}
