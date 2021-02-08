// Family Tree - USACO Bronze US Open 2018 (http://www.usaco.org/index.php?page=viewproblem2&cpid=833)
// This problem was partially completed on October 11, 2020, in 1 hour, with 9/15 test cases passed (first try)

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class family_open2018 {
    static int numRelationships;
    static String cow1;
    static String cow2;
    static String[][] relationships;

    public static String family() {
        String cow1_mother = "";
        for (int i=0; i<numRelationships; i++) {
            if (relationships[i][1].equals(cow1)) {
                cow1_mother = relationships[i][0];
            }
        }
        String cow1_sister = "";
        for (int i=0; i<numRelationships; i++) {
            if (relationships[i][0].equals(cow1_mother) && (!relationships[i][1].equals(cow1))) {
                cow1_sister = relationships[i][1];
            }
        }
        String cow1_grandmother = "";
        for (int i=0; i<numRelationships; i++) {
            if (relationships[i][1].equals(cow1_mother)) {
                cow1_grandmother = relationships[i][0];
            }
        }
        String cow1_aunt = "";
        for (int i=0; i<numRelationships; i++) {
            if (relationships[i][0].equals(cow1_grandmother) && (!relationships[i][1].equals(cow1))) {
                cow1_aunt = relationships[i][1];
            }
        }
        String cow1_greatgrandmother = "";
        for (int i=0; i<numRelationships; i++) {
            if (relationships[i][1].equals(cow1_grandmother)) {
                cow1_greatgrandmother = relationships[i][0];
            }
        }
        String cow1_greataunt = "";
        for (int i=0; i<numRelationships; i++) {
            if (relationships[i][0].equals(cow1_greatgrandmother) && (!relationships[i][1].equals(cow1_grandmother))) {
                cow1_greataunt = relationships[i][1];
            }
        }
        String cow1_greatgreatgrandmother = "";
        for (int i=0; i<numRelationships; i++) {
            if (relationships[i][1].equals(cow1_greatgrandmother)) {
                cow1_greatgreatgrandmother = relationships[i][0];
            }
        }
        String cow1_greatgreataunt = "";
        for (int i=0; i<numRelationships; i++) {
            if (relationships[i][0].equals(cow1_greatgreatgrandmother) && (!relationships[i][1].equals(cow1_greatgrandmother))) {
                cow1_greatgreataunt = relationships[i][1];
            }
        }
        if (cow1_sister.equals(cow2)) {
            return "SIBLINGS";
        } else if (cow1_mother.equals(cow2)) {
            return cow2 + " is the mother of " + cow1;
        } else if (cow1_aunt.equals(cow2)) {
            return cow2 + " is the aunt of " + cow1;
        } else if (cow1_grandmother.equals(cow2)) {
            return cow2 + " is the grand-mother of " + cow1;
        } else if (cow1_greataunt.equals(cow2)) {
            return cow2 + " is the great-aunt of " + cow1;
        } else if (cow1_greatgrandmother.equals(cow2)) {
            return cow2 + " is the great-grand-mother of " + cow1;
        } else if (cow1_greatgreataunt.equals(cow2)) {
            return cow2 + " is the great-great-aunt of " + cow1;
        } else if (cow1_greatgreatgrandmother.equals(cow2)) {
            return cow2 + " is the great-great-grand-mother of " + cow1;
        } else {
            return "NOT RELATED";
        }
    }

    public static void main(String[] args) throws IOException {
        // input
        String problemName = "family";
        Scanner sc = new Scanner(new File(problemName + ".in"));

        numRelationships = sc.nextInt();
        cow1 = sc.next();
        cow2 = sc.next();
        relationships = new String[numRelationships][2];
        for (int i=0; i<numRelationships; i++) {
            relationships[i][0] = sc.next();
            relationships[i][1] = sc.next();
        }

        // algorithm
        String relationship = family();

        // output
        PrintWriter out = new PrintWriter(new FileWriter(problemName + ".out"));
        out.println(relationship);
        out.close();
    }
}
