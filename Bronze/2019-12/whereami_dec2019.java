// Where Am I? - USACO Bronze December 2019 (http://www.usaco.org/index.php?page=viewproblem2&cpid=964)

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class L7_whereami {
    public static int whereami(int mailboxNum, String mailboxes) {
        int K = 1;

        while (true) {
            for (int i=0; i < mailboxNum-K+1; i++) {
                String mailboxSet = mailboxes.substring(i, i+K); // creating the mailbox set (A, AB, ABC, etc.)
                //System.out.println(mailboxSet);

                if (mailboxes.indexOf(mailboxSet, i+1) > -1) {
                    // if the set is in the mailbox more than once then it is not good
                    break;
                }

                if (i == mailboxNum-K) {
                    // if the for loop can run to mailboxNum-K successfully then the set is okay and K can be returned
                    return K;
                }
            }
            K++;
        }
    }

    public static void main(String[] args) throws IOException {
        // input
        String problemName = "whereami";
        Scanner sc = new Scanner(new File(problemName + ".in"));
        int mailboxNum = sc.nextInt();
        String mailboxes = sc.next();

        // algorithm
        int K = whereami(mailboxNum, mailboxes);

        //output
        PrintWriter out = new PrintWriter(new FileWriter(problemName + ".out"));
        out.println(K);
        out.close();
    }
}
