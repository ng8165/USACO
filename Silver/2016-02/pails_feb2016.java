// Milk Pails - USACO Silver February 2016 (http://www.usaco.org/index.php?page=viewproblem2&cpid=620)
// This problem was completed as classwork and homework for the USACO Silver 1 Class on 4/11/21.

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class L10_pails {
    static int xCapacity;
    static int yCapacity;
    static int maxOperations;
    static int desiredFill;

    static int M = Integer.MAX_VALUE;
    static boolean[][] visited;

    public static void pailsDFS(int xFill, int yFill, int currOperations) {
        // base cases
        if (currOperations > maxOperations) {
            return;
        }
        if (visited[xFill][yFill]) {
            return;
        }

        M = Math.min(M, Math.abs(desiredFill-xFill-yFill));
        if (M == 0) {
            // this is as low as M is going to get
            return;
        }

        visited[xFill][yFill] = true;

        pailsDFS(xCapacity, yFill, currOperations+1); // fill x to top
        pailsDFS(xFill, yCapacity, currOperations+1); // fill y to top
        pailsDFS(0, yFill, currOperations+1); // empty x
        pailsDFS(xFill, 0, currOperations+1); // empty y
        int xToY = Math.min(xFill, yCapacity-yFill);
        pailsDFS(xFill-xToY, yFill+xToY, currOperations+1); // pour x into y
        int yToX = Math.min(xCapacity-xFill, yFill);
        pailsDFS(xFill+yToX, yFill-yToX, currOperations+1); // pour y into x

        visited[xFill][yFill] = false; // backtracking
    }

    public static void pailsBFS() {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {0, 0, 0}); // xFill, yFill, currOperations

        while (!queue.isEmpty()) {
            // dequeue
            int[] pails = queue.poll();
            int xFill = pails[0], yFill = pails[1], currOperations = pails[2];

            if (visited[xFill][yFill]) {
                continue;
            }

            M = Math.min(M, Math.abs(desiredFill-xFill-yFill));
            if (M == 0) {
                // this is as low as M is going to get
                return;
            }
            visited[xFill][yFill] = true;

            // enqueue
            if (currOperations < maxOperations) {
                queue.offer(new int[] {xCapacity, yFill, currOperations+1}); // fill x to top
                queue.offer(new int[] {xFill, yCapacity, currOperations+1}); // fill y to top
                queue.offer(new int[] {0, yFill, currOperations+1}); // empty x
                queue.offer(new int[] {xFill, 0, currOperations+1}); // empty y
                int xToY = Math.min(xFill, yCapacity-yFill);
                queue.offer(new int[] {xFill-xToY, yFill+xToY, currOperations+1}); // pour x into y
                int yToX = Math.min(xCapacity-xFill, yFill);
                queue.offer(new int[] {xFill+yToX, yFill-yToX, currOperations+1}); // pour y into x
            }
        }
    }

    public static void main(String[] args) throws IOException {
        // input
        String problemName = "pails";
        BufferedReader br = new BufferedReader(new FileReader(problemName + ".in"));
        StringTokenizer st = new StringTokenizer(br.readLine());

        xCapacity = Integer.parseInt(st.nextToken());
        yCapacity = Integer.parseInt(st.nextToken());
        maxOperations = Integer.parseInt(st.nextToken());
        desiredFill = Integer.parseInt(st.nextToken());
        visited = new boolean[xCapacity+1][yCapacity+1];

        // algorithm
        //pailsDFS(0, 0, 0);
        pailsBFS();

        // output
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(problemName + ".out")));
        pw.println(M);

        br.close();
        pw.close();
    }
}
