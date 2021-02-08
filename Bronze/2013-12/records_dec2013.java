// Record Keeping - USACO Bronze December 2013 (http://www.usaco.org/index.php?page=viewproblem2&cpid=358)

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Arrays;

public class L6_records {
    public static void main(String[] args) throws IOException {
        // input
        String problemName = "records";
        Scanner sc = new Scanner(new File(problemName + ".in"));
        int numGroups = sc.nextInt();
        sc.nextLine(); // to move cursor to next line
        String[] cowGroups = new String[numGroups];

        // algorithm
        for (int i=0; i<numGroups; i++) {
            String[] orderedGroup = new String[3];

            for (int j=0; j<3; j++) {
                orderedGroup[j] = sc.next();
            }

            Arrays.sort(orderedGroup);

            cowGroups[i] = orderedGroup[0] + " " + orderedGroup[1] + " " + orderedGroup[2];
        }

        Arrays.sort(cowGroups);

        int mode = Integer.MIN_VALUE;
        int cnt=1;

        for (int i=0; i<numGroups-1; i++) {
            if (cowGroups[i].equals(cowGroups[i+1])) {
                cnt++;
            } else {
                mode = Math.max(cnt, mode);
                cnt = 1;
            }
        }

        mode = Math.max(cnt, mode);

        //output
        PrintWriter out = new PrintWriter(new FileWriter(problemName + ".out"));
        out.println(mode);
        out.close();
    }
}