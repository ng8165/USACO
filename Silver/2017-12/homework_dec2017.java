// My Cow Ate My Homework - USACO Silver December 2017 (http://www.usaco.org/index.php?page=viewproblem2&cpid=762)
// This problem was completed as classwork for the USACO Silver 2 Class on 6/23/21.

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class L15_homework {
    public static void main(String[] args) throws IOException {
        // input
        String problemName = "homework";
        BufferedReader br = new BufferedReader(new FileReader(problemName + ".in"));

        int numQuestions = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] scores = new int[numQuestions];
        for (int i=0; i<numQuestions; i++) {
            scores[i] = Integer.parseInt(st.nextToken());
        }

        // algorithm

        // calculate suffix sum and suffix min
        long[] suffixSum = new long[numQuestions];
        int[] suffixMin = new int[numQuestions];
        int min = Integer.MAX_VALUE;
        for (int i=numQuestions-1; i>=0; i--) {
            // calculate suffix sum
            if (i == numQuestions-1) {
                suffixSum[i] = scores[i];
            } else {
                suffixSum[i] = scores[i] + suffixSum[i+1];
            }

            // calculate suffix min
            min = Math.min(scores[i], min);
            suffixMin[i] = min;
        }

        // brute force value of K
        ArrayList<Integer> maxKList = new ArrayList<>();
        double maxScore = 0.0;

        for (int K=1; K<=numQuestions-2; K++) {
            double finalScore = (suffixSum[K] - suffixMin[K])/(numQuestions-K-1.0);

            if (maxScore < finalScore) {
                maxKList.clear();
                maxKList.add(K);
                maxScore = finalScore;
            } else if (maxScore == finalScore) {
                maxKList.add(K);
            }
        }

        // output
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(problemName + ".out")));

        for (int K: maxKList) {
            pw.println(K);
        }

        br.close();
        pw.close();
    }
}
