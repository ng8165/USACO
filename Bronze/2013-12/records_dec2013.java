// Record Keeping - USACO Bronze December 2013 (http://www.usaco.org/index.php?page=viewproblem2&cpid=358)
// This problem was completed as classwork for the USACO Silver 1 Class on 3/6/21.

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class L5_records {
    public static void main(String[] args) throws IOException {
        // input
        String problemName = "records";
        Scanner sc = new Scanner(new File(problemName + ".in"));

        // algorithm
        HashMap<String, Integer> entryFreq = new HashMap<>();
        int numGroups = sc.nextInt();
        int maxFrequency = 0;

        for (int i=0; i<numGroups; i++) {
            String[] group = new String[] {sc.next(), sc.next(), sc.next()};
            Arrays.sort(group);
            String groupString = Arrays.toString(group);

            entryFreq.put(groupString, entryFreq.getOrDefault(groupString, 0)+1);

            maxFrequency = Math.max(maxFrequency, entryFreq.get(groupString));
        }

        // output
        PrintWriter out = new PrintWriter(new FileWriter(problemName + ".out"));
        out.println(maxFrequency);
        out.close();
    }
}
