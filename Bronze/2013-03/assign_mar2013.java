// Breed Assignment - USACO Bronze March 2013 (http://www.usaco.org/index.php?page=viewproblem2&cpid=261)
// This problem was completed as classwork for the USACO Silver 2 Class on 7/15/21.

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

public class L20_assign {
    static int numCows;
    static int numRelationships;
    static int[][] cows;
    static String[] relation;

    static final char[] breeds = {'H', 'J', 'B'};
    static ArrayList<Character> permutation;
    static int validAssignmentCnt = 0;

    public static void permute(int idx) {
        // base case
        if (idx >= numCows) {
            if (isValidAssignment(permutation)) {
                validAssignmentCnt++;
            }

            return;
        }

        for (char breed: breeds) {
            permutation.add(breed);

            permute(idx+1);

            // backtracking
            permutation.remove(permutation.size()-1);
        }
    }

    public static boolean isValidAssignment(ArrayList<Character> assignment) {
        for (int i=0; i<relation.length; i++) {
            if (relation[i].equals("S") && assignment.get(cows[i][0]-1) != assignment.get(cows[i][1]-1)) {
                return false;
            } else if (relation[i].equals("D") && assignment.get(cows[i][0]-1) == assignment.get(cows[i][1]-1)) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) throws IOException {
        // input
        String problemName = "assign";
        BufferedReader br = new BufferedReader(new FileReader(problemName + ".in"));
        StringTokenizer st = new StringTokenizer(br.readLine());

        numCows = Integer.parseInt(st.nextToken());
        numRelationships = Integer.parseInt(st.nextToken());
        permutation = new ArrayList<>();
        permutation.add('H');
        HashSet<String> uniqueRelationships = new HashSet<>();
        for (int i=0; i<numRelationships; i++) {
            uniqueRelationships.add(br.readLine());
        }
        cows = new int[uniqueRelationships.size()][2];
        relation = new String[uniqueRelationships.size()];
        int idx=0;
        for (String r: uniqueRelationships) {
            st = new StringTokenizer(r);
            relation[idx] = st.nextToken();
            cows[idx][0] = Integer.parseInt(st.nextToken());
            cows[idx++][1] = Integer.parseInt(st.nextToken());
        }

        // algorithm
        permute(1);
        validAssignmentCnt *= 3;

        // output
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(problemName + ".out")));

        pw.println(validAssignmentCnt);

        br.close();
        pw.close();
    }
}
