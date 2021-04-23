// Censoring (Bronze) - USACO Bronze February 2015 (http://www.usaco.org/index.php?page=viewproblem2&cpid=526)
// This problem was completed as classwork for the USACO Silver 1 Class on 4/22/21.

import java.io.*;

public class L12_censor {
    public static void main(String[] args) throws IOException {
        // input
        String problemName = "censor";
        BufferedReader br = new BufferedReader(new FileReader(problemName + ".in"));

        StringBuilder str = new StringBuilder(br.readLine());
        String substr = br.readLine();

        // algorithm
        int substrIdx = 0;

        while (true) {
            substrIdx = str.indexOf(substr, substrIdx-substr.length()); // when you delete you may introduce another substr write where you were

            if (substrIdx > -1) {
                str.delete(substrIdx, substrIdx+substr.length());
            } else {
                break;
            }
        }

        // output
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(problemName + ".out")));
        pw.println(str.toString());

        br.close();
        pw.close();
    }
}
