// Diamond Collector - USACO Bronze US Open 2016 (http://www.usaco.org/index.php?page=viewproblem2&cpid=639)

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class L6_diamond {
    public static int diamond(int numDiamonds, int K, int[] diamondSizes) {
        Arrays.sort(diamondSizes);

        int maxCnt = Integer.MIN_VALUE;

        for (int i=0; i<numDiamonds-1; i++) { // changing start diamond
            int cnt = 1;
            int rangeEnd = diamondSizes[i] + K;

            for (int j=i+1; j<numDiamonds; j++) { // seeing compatible diamonds
                if (diamondSizes[j] <= rangeEnd) {
                    cnt++;
                }
                else {
                    break;
                }
            }

            maxCnt = Math.max(maxCnt, cnt);
        }

        return maxCnt;
    }

    public static void main(String[] args) throws IOException {
        // input
        String problemName = "diamond";
        Scanner sc = new Scanner(new File(problemName + ".in"));
        int numDiamonds = sc.nextInt();
        int K = sc.nextInt();
        int[] diamondSizes = new int[numDiamonds];
        for (int i=0; i<numDiamonds; i++) {
            diamondSizes[i] = sc.nextInt();
        }

        // algorithm
        int maxCnt = diamond(numDiamonds, K, diamondSizes);

        //output
        PrintWriter out = new PrintWriter(new FileWriter(problemName + ".out"));
        out.println(maxCnt);
        out.close();
    }

}
