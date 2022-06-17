// Haywire - USACO Bronze US Open 2013 (http://www.usaco.org/index.php?page=viewproblem2&cpid=281)
// This problem was completed as homework for the USACO Silver 2 Class on 7/18/21.

import java.io.*;
import java.util.HashMap;
import java.util.StringTokenizer;

public class L20_haywire {
    static int numCows;
    static int[][] friends;

    static int[] permutation;
    static boolean[] visited;
    static int minHayCnt = Integer.MAX_VALUE;

    public static void permute(int currCow) {
        // base case
        if (currCow >= numCows) {
            minHayCnt = Math.min(minHayCnt, calculateHayCnt(permutation));
            return;
        }

        for (int i=0; i<numCows; i++) {
            if (!visited[i]) {
                visited[i] = true;
                permutation[currCow] = i+1;

                permute(currCow+1);

                // backtracking
                visited[i] = false;
                permutation[currCow] = 0;
            }
        }
    }

    public static int calculateHayCnt(int[] permutation) {
        // convert permutation into a HashMap (key is cow, value is idx/pos in the barn)
        HashMap<Integer, Integer> cows = new HashMap<>();
        for (int i=0; i<numCows; i++) {
            cows.put(permutation[i], i);
        }

        int totalHay = 0;
        for (int i=0; i<numCows; i++) {
            int cow = i+1;
            for (int j=0; j<3; j++) {
                totalHay += Math.abs(cows.get(friends[i][j]) - cows.get(cow));
            }
        }

        // every friendship is mutual and is therefore counted twice
        return totalHay/2;
    }

    public static void main(String[] args) throws IOException {
        // input
        String problemName = "haywire";
        BufferedReader br = new BufferedReader(new FileReader(problemName + ".in"));
        StringTokenizer st;

        numCows = Integer.parseInt(br.readLine());
        friends = new int[numCows][3];
        for (int i=0; i<numCows; i++) {
            st = new StringTokenizer(br.readLine());

            friends[i][0] = Integer.parseInt(st.nextToken());
            friends[i][1] = Integer.parseInt(st.nextToken());
            friends[i][2] = Integer.parseInt(st.nextToken());
        }

        // algorithm
        permutation = new int[numCows];
        visited = new boolean[numCows];

        permute(0);

        // output
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(problemName + ".out")));

        pw.println(minHayCnt);

        br.close();
        pw.close();
    }
}
