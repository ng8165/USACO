// Where Am I? - USACO Bronze December 2019 (http://www.usaco.org/index.php?page=viewproblem2&cpid=964)
// This problem was completed on November 15, 2020, in 23 minutes, with all 10/10 test cases passed (second try)

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class whereami_dec2019 {
    static int mailboxLength;
    static String mailboxes;

    public static int whereami() {
        for (int i=1; i<=mailboxLength; i++) {
            // generate all consecutive sets
            String[] consecutiveSets = new String[mailboxLength-i+1];
            for (int j=0; j<consecutiveSets.length; j++) {
                consecutiveSets[j] = mailboxes.substring(j, j+i);
            }

            // identify if any of the consecutive sets are the same
            boolean shouldContinueSearching = true;
            for (int j=0; j<consecutiveSets.length; j++) {
                for (int k=0; k<consecutiveSets.length; k++) {
                    if (j == k) {
                        continue;
                    }

                    if (consecutiveSets[j].equals(consecutiveSets[k])) {
                        shouldContinueSearching = false;
                        break;
                    }
                }

                if (!shouldContinueSearching) {
                    break;
                }
            }

            if (shouldContinueSearching) {
                return i;
            }
        }

        return 0; // avoid compilation errors
    }

    public static void main(String[] args) throws IOException {
        // input
        String problemName = "whereami";
        Scanner sc = new Scanner(new File(problemName + ".in"));

        mailboxLength = sc.nextInt();
        mailboxes = sc.next();

        // algorithm
        int minConsecutiveSet = whereami();

        // output
        PrintWriter out = new PrintWriter(new FileWriter(problemName + ".out"));
        out.println(minConsecutiveSet);
        out.close();
    }
}
