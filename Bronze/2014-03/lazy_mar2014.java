// The Lazy Cow - USACO Bronze March 2014 (http://www.usaco.org/index.php?page=viewproblem2&cpid=413)
// This problem was completed on December 13, 2020, in 17 minutes, with 2/10 test cases passed (first try)

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class lazy_mar2014 {
    static int N, K;
    static int[] g, x;

    public static int lazy(int minRange, int maxRange) {
        int maxGrass = 0;

        for (int i=minRange; i<=maxRange; i++) {
            int currGrass = 0;

            for (int j=0; j<N; j++) {
                if (x[j] >= i-K && x[j] <= i+K) {
                    currGrass += g[j];
                }
            }

            maxGrass = Math.max(maxGrass, currGrass);
        }

        return maxGrass;
    }

    public static void main(String[] args) throws IOException {
        // input
        String problemName = "lazy";
        Scanner sc = new Scanner(new File(problemName + ".in"));

        N = sc.nextInt();
        K = sc.nextInt();
        g = new int[N];
        x = new int[N];
        int minRange = Integer.MAX_VALUE;
        int maxRange = 0;
        for (int i=0; i<N; i++) {
            g[i] = sc.nextInt();
            x[i] = sc.nextInt();
            minRange = Math.min(minRange, x[i]);
            maxRange = Math.max(maxRange, x[i]);
        }

        // algorithm
        int maxGrass = lazy(minRange, maxRange);

        // output
        PrintWriter out = new PrintWriter(new FileWriter(problemName + ".out"));
        out.println(maxGrass);
        out.close();
    }
}
