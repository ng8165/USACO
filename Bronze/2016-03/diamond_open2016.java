// Diamond Collector - USACO Bronze US Open 2016 (http://www.usaco.org/index.php?page=viewproblem2&cpid=639)
// This problem was completed on November 21, 2020, in 27 minutes, with all 10/10 test cases passed (second try)

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class diamond_open2016 {
    static int numDiamonds;
    static int sizeDifference;
    static int[] diamondSizes;

    public static int diamond() {
        Arrays.sort(diamondSizes);
        int maxDiamonds = 0;

        for (int i=0; i<numDiamonds; i++) {
            int startingDiamond = diamondSizes[i];
            int currDiamonds = 1;

            for (int j=i+1; j<numDiamonds; j++) {
                int endingDiamond = diamondSizes[j];

                if ((endingDiamond - startingDiamond) > sizeDifference) {
                    break;
                } else {
                    currDiamonds++;
                }
            }

            maxDiamonds = Math.max(maxDiamonds, currDiamonds);
        }

        return maxDiamonds;
    }

    public static void main(String[] args) throws IOException {
        // input
        String problemName = "diamond";
        Scanner sc = new Scanner(new File(problemName + ".in"));

        numDiamonds = sc.nextInt();
        sizeDifference = sc.nextInt();
        diamondSizes = new int[numDiamonds];
        for (int i=0; i<numDiamonds; i++) {
            diamondSizes[i] = sc.nextInt();
        }

        // algorithm
        int maxDiamonds = diamond();

        // output
        PrintWriter out = new PrintWriter(new FileWriter(problemName + ".out"));
        out.println(maxDiamonds);
        out.close();
    }
}
