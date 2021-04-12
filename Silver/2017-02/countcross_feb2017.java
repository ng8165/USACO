// Why Did the Cow Cross the Road III - USACO Silver February 2017 (http://www.usaco.org/index.php?page=viewproblem2&cpid=716)
// This problem was completed as classwork and homework for the USACO Silver 1 Class on 4/11/21.

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class L10_countcross {
    static class Field {
        int row;
        int col;
        boolean hasCow;
        ArrayList<Field> roadList;

        Field(int row, int col) {
            this.row = row;
            this.col = col;
            hasCow = false;
            this.roadList = new ArrayList<>();
        }
    }

    static int farmSize;
    static int numCows;
    static int numRoads;
    static Field[][] farm;

    static boolean[][] visited;
    static int encounteredCows;

    public static void countcrossBFS(int startRow, int startCol) {
        visited = new boolean[farmSize][farmSize];
        Queue<Field> queue = new LinkedList<>();
        encounteredCows = 0;

        queue.offer(farm[startRow][startCol]);
        visited[startRow][startCol] = true;

        while (!queue.isEmpty()) {
            // dequeue
            Field curr = queue.poll();
            int row = curr.row, col = curr.col;

            if (farm[row][col].hasCow) {
                encounteredCows++;
            }

            // enqueue
            if (row > 0 && !visited[row-1][col] && isNotInArrayList(curr.roadList, row-1, col)) {
                queue.offer(farm[row-1][col]);
                visited[row-1][col] = true;
            }
            if (row < farmSize-1 && !visited[row+1][col] && isNotInArrayList(curr.roadList, row+1, col)) {
                queue.offer(farm[row+1][col]);
                visited[row+1][col] = true;
            }
            if (col > 0 && !visited[row][col-1] && isNotInArrayList(curr.roadList, row, col-1)) {
                queue.offer(farm[row][col-1]);
                visited[row][col-1] = true;
            }
            if (col < farmSize-1 && !visited[row][col+1] && isNotInArrayList(curr.roadList, row, col+1)) {
                queue.offer(farm[row][col+1]);
                visited[row][col+1] = true;
            }
        }
    }

    public static void countcrossDFS(int row, int col) {
        visited[row][col] = true;

        if (farm[row][col].hasCow) {
            encounteredCows++;
        }

        if (row > 0 && !visited[row-1][col] && isNotInArrayList(farm[row][col].roadList, row-1, col)) {
            countcrossDFS(row-1, col);
        }
        if (row < farmSize-1 && !visited[row+1][col] && isNotInArrayList(farm[row][col].roadList, row+1, col)) {
            countcrossDFS(row+1, col);
        }
        if (col > 0 && !visited[row][col-1] && isNotInArrayList(farm[row][col].roadList, row, col-1)) {
            countcrossDFS(row, col-1);
        }
        if (col < farmSize-1 && !visited[row][col+1] && isNotInArrayList(farm[row][col].roadList, row, col+1)) {
            countcrossDFS(row, col+1);
        }
    }

    public static boolean isNotInArrayList(ArrayList<Field> arrList, int row, int col) {
        for (Field curr: arrList) {
            if (curr.row == row && curr.col == col) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) throws IOException {
        // input
        String problemName = "countcross";
        BufferedReader br = new BufferedReader(new FileReader(problemName + ".in"));
        StringTokenizer st = new StringTokenizer(br.readLine());

        farmSize = Integer.parseInt(st.nextToken());
        numCows = Integer.parseInt(st.nextToken());
        numRoads = Integer.parseInt(st.nextToken());
        farm = new Field[farmSize][farmSize];

        // initialize all fields (currently null)
        for (int i=0; i<farmSize; i++) {
            for (int j=0; j<farmSize; j++) {
                farm[i][j] = new Field(i, j);
            }
        }

        // add roads to roadList for the fields
        for (int i=0; i<numRoads; i++) {
            st = new StringTokenizer(br.readLine());

            int r1 = Integer.parseInt(st.nextToken())-1, c1 = Integer.parseInt(st.nextToken())-1;
            int r2 = Integer.parseInt(st.nextToken())-1, c2 = Integer.parseInt(st.nextToken())-1;
            farm[r1][c1].roadList.add(farm[r2][c2]);
            farm[r2][c2].roadList.add(farm[r1][c1]);
        }

        // place cows into fields
        for (int i=0; i<numCows; i++) {
            st = new StringTokenizer(br.readLine());

            farm[Integer.parseInt(st.nextToken())-1][Integer.parseInt(st.nextToken())-1].hasCow = true;
        }

        // algorithm

        // BFS
        /*
        int distantCows = 0;
        for (int i=0; i<farmSize; i++) {
            for (int j=0; j<farmSize; j++) {
                if (farm[i][j].hasCow) {
                    countcrossBFS(i, j);
                    distantCows += (numCows-encounteredCows);
                }
            }
        }
         */

        // DFS
        int distantCows = 0;
        for (int i=0; i<farmSize; i++) {
            for (int j=0; j<farmSize; j++) {
                if (farm[i][j].hasCow) {
                    visited = new boolean[farmSize][farmSize];
                    encounteredCows = 0;
                    countcrossDFS(i, j);
                    distantCows += (numCows-encounteredCows);
                }
            }
        }

        // output
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(problemName + ".out")));
        pw.println(distantCows/2);

        br.close();
        pw.close();
    }
}
