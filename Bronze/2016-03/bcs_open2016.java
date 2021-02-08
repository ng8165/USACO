// Bull in a China Shop - USACO Bronze US Open 2016 (http://www.usaco.org/index.php?page=viewproblem2&cpid=639)
// This problem was completed on November 21, 2020, in 27 minutes, with all 10/10 test cases passed (first try)

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class bcs_open2016 {
    static int gridSize;
    static int numGrids;
    static char[][][] grids;

    public static int[] bcs() {
        for (int i=0; i<numGrids; i++) {
            for (int j=0; j<numGrids; j++) {
                if (i == j) {
                    continue;
                }

                char[][] grid1 = grids[i];
                char[][] grid2 = grids[j];

                
            }
        }
    }

    public static void main(String[] args) throws IOException {
        // input
        String problemName = "bcs";
        Scanner sc = new Scanner(new File(problemName + ".in"));

        gridSize = sc.nextInt();
        numGrids = sc.nextInt();
        sc.nextLine();
        grids = new char[numGrids][gridSize][gridSize];
        for (int i=0; i<numGrids; i++) {
            for (int j=0; j<gridSize; j++) {
                grids[i][j] = sc.nextLine().toCharArray();
            }
        }

        // algorithm
        int[] correctGrids = bcs();

        // output
        PrintWriter out = new PrintWriter(new FileWriter(problemName + ".out"));
        out.println(correctGrids[0] + " " + correctGrids[1]);
        out.close();
    }
}
