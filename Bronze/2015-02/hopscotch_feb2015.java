// Cow Hopscotch (Bronze) - USACO Bronze February 2015 (http://www.usaco.org/index.php?page=viewproblem2&cpid=528)
// This problem was completed as classwork for the USACO Silver 1 Class on 3/20/21.

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class L7_hopscotch {
    static int length;
    static int width;
    static char[][] grid;

    static int jumpMethods = 0;

    public static void hopscotchDFS(int row, int col) {
        // base case
        if (row == length-1 && col == width-1) {
            jumpMethods++;
            return;
        }

        for (int i=row+1; i<length; i++) {
            for (int j=col+1; j<width; j++) {
                if (grid[i][j] != grid[row][col]) {
                    hopscotchDFS(i, j);
                }
            }
        }
    }

    public static int hopscotchDFS2(int row, int col) {
        // base case
        if (row == length-1 && col == width-1) {
            return 1;
        }

        int jumpMethods = 0;

        for (int i=row+1; i<length; i++) {
            for (int j=col+1; j<width; j++) {
                if (grid[i][j] != grid[row][col]) {
                    jumpMethods += hopscotchDFS2(i, j);
                }
            }
        }

        return jumpMethods;
    }

    public static void main(String[] args) throws IOException {
        // input
        String problemName = "hopscotch";
        Scanner sc = new Scanner(new File(problemName + ".in"));

        length = sc.nextInt();
        width = sc.nextInt();
        grid = new char[length][width];
        for (int i=0; i<length; i++) {
            grid[i] = sc.next().toCharArray();
        }

        // algorithm
        hopscotchDFS(0, 0);
        //int jumpMethods = hopscotchDFS2(0, 0);

        // output
        PrintWriter out = new PrintWriter(new FileWriter(problemName + ".out"));
        out.println(jumpMethods);
        out.close();
    }
}
