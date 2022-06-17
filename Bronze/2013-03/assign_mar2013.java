// Breed Assignment - USACO Bronze March 2013 (http://www.usaco.org/index.php?page=viewproblem2&cpid=261)
// This problem was completed as classwork for the USACO Silver 2 Class on 7/15/21.

import java.io.*;
import java.util.HashSet;
import java.util.StringTokenizer;

public class L20_assign {
    static int numCows;
    static int numRelationships;
    static String[][] relation;

    static char[] permutation;
    static int validAssignmentCnt = 0;

    public static void permute(int currCow) {
        // base case
        if (currCow >= numCows) {
            validAssignmentCnt++;
            return;
        }

        // find possible breeds
        HashSet<Character> breeds = new HashSet<>();
        breeds.add('H');
        breeds.add('J');
        breeds.add('G');

        for (int prevCow=0; prevCow<currCow; prevCow++) {
            if (relation[prevCow][currCow] != null) {
                if (relation[prevCow][currCow].equals("S")) {
                    if (!breeds.contains(permutation[prevCow])) {
                        // assignment is invalid
                        return;
                    }

                    // same relation
                    breeds.clear();
                    breeds.add(permutation[prevCow]);
                } else {
                    // different relation
                    breeds.remove(permutation[prevCow]);
                }
            } // else: no relation
        }

        // do recursion for the possible breeds
        for (char breed: breeds) {
            permutation[currCow] = breed;
            permute(currCow+1);
            permutation[currCow] = 0; // backtracking (not necessary, will be overwritten)
        }
    }

    public static void main(String[] args) throws IOException {
        // input
        String problemName = "assign";
        BufferedReader br = new BufferedReader(new FileReader(problemName + ".in"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(problemName + ".out")));

        numCows = Integer.parseInt(st.nextToken());
        numRelationships = Integer.parseInt(st.nextToken());

        /*
        relationships saved in 2D array, indexes are cow numbers, String contents determine relationship
        relation[i][j]: S indicates cow i and cow j are same
                        D indicates cow i and cow j are different
                        null indicates cow i and cow j have no relation
        */
        relation = new String[numCows][numCows];
        for (int i=0; i<numRelationships; i++) {
            st = new StringTokenizer(br.readLine());

            String r = st.nextToken();
            int cowTemp1 = Integer.parseInt(st.nextToken())-1;
            int cowTemp2 = Integer.parseInt(st.nextToken())-1;
            int cow1 = Math.min(cowTemp1, cowTemp2);
            int cow2 = Math.max(cowTemp1, cowTemp2);

            if (relation[cow1][cow2] != null && !relation[cow1][cow2].equals(r)) {
                // FJ gave contradictory information
                pw.println(0);

                br.close();
                pw.close();
                return;
            } else {
                relation[cow1][cow2] = r;
            }
        }

        // algorithm
        permutation = new char[numCows];
        permutation[0] = 'H';

        permute(1);

        validAssignmentCnt *= 3;

        // output
        pw.println(validAssignmentCnt);

        br.close();
        pw.close();
    }
}
