// Cow Art - USACO Bronze March 2014 (http://www.usaco.org/index.php?page=viewproblem2&cpid=414)
// This problem was completed as homework for the USACO Silver 1 Class on 3/28/21.

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class L8_cowart {
    static int paintingSize;
    static char[][] painting;

    static boolean[][] visited;

    public static void cowart_humanDFS(int row, int col) {
        visited[row][col] = true;

        if (row > 0 && !visited[row-1][col] && painting[row][col] == painting[row-1][col]) {
            cowart_humanDFS(row-1, col);
        }

        if (row < paintingSize-1 && !visited[row+1][col] && painting[row][col] == painting[row+1][col]) {
            cowart_humanDFS(row+1, col);
        }

        if (col > 0 && !visited[row][col-1] && painting[row][col] == painting[row][col-1]) {
            cowart_humanDFS(row, col-1);
        }

        if (col < paintingSize-1 && !visited[row][col+1] && painting[row][col] == painting[row][col+1]) {
            cowart_humanDFS(row, col+1);
        }
    }

    public static void cowart_cowDFS(int row, int col) {
        visited[row][col] = true;

        if (row > 0 && !visited[row-1][col]) {
            if (painting[row][col] == painting[row-1][col] || (painting[row][col] == 'R' && painting[row-1][col] == 'G')
                    || (painting[row][col] == 'G' && painting[row-1][col] == 'R')) {
                cowart_cowDFS(row-1, col);
            }
        }

        if (row < paintingSize-1 && !visited[row+1][col]) {
            if (painting[row][col] == painting[row+1][col] || (painting[row][col] == 'R' && painting[row+1][col] == 'G')
                    || (painting[row][col] == 'G' && painting[row+1][col] == 'R')) {
                cowart_cowDFS(row+1, col);
            }
        }

        if (col > 0 && !visited[row][col-1]) {
            if (painting[row][col] == painting[row][col-1] || (painting[row][col] == 'R' && painting[row][col-1] == 'G')
                    || (painting[row][col] == 'G' && painting[row][col-1] == 'R')) {
                cowart_cowDFS(row, col-1);
            }
        }

        if (col < paintingSize-1 && !visited[row][col+1]) {
            if (painting[row][col] == painting[row][col+1] || (painting[row][col] == 'R' && painting[row][col+1] == 'G')
                    || (painting[row][col] == 'G' && painting[row][col+1] == 'R')) {
                cowart_cowDFS(row, col+1);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        // input
        String problemName = "cowart";
        Scanner sc = new Scanner(new File(problemName + ".in"));

        paintingSize = sc.nextInt();
        painting = new char[paintingSize][paintingSize];
        for (int i=0; i<paintingSize; i++) {
            painting[i] = sc.next().toCharArray();
        }

        // algorithm
        int humanRegions = 0;
        visited = new boolean[paintingSize][paintingSize];
        for (int i=0; i<paintingSize; i++) {
            for (int j=0; j<paintingSize; j++) {
                if (!visited[i][j]) {
                    cowart_humanDFS(i, j);
                    humanRegions++;
                }
            }
        }

        int cowRegions = 0;
        visited = new boolean[paintingSize][paintingSize];
        for (int i=0; i<paintingSize; i++) {
            for (int j=0; j<paintingSize; j++) {
                if (!visited[i][j]) {
                    cowart_cowDFS(i, j);
                    cowRegions++;
                }
            }
        }

        // output
        PrintWriter out = new PrintWriter(new FileWriter(problemName + ".out"));
        out.println(humanRegions + " " + cowRegions);
        out.close();
    }
}
