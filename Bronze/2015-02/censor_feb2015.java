// Censoring (Bronze) - USACO Bronze February 2015 (http://www.usaco.org/index.php?page=viewproblem2&cpid=526)
// This problem was completed as classwork for the USACO Silver 1 Class on 4/22/21.

import java.io.*;

public class L12_censor {
    public static void main(String[] args) throws IOException {
        // input
        String problemName = "censor";
        BufferedReader br = new BufferedReader(new FileReader(problemName + ".in"));

        char[] charArr = br.readLine().toCharArray();
        String str = br.readLine();

        // algorithm
        StringBuilder sb = new StringBuilder();

        for (char ch: charArr) {
            sb.append(ch);

            int length = sb.length();
            if (length > str.length() && sb.substring(length-str.length()).equals(str)) {
                sb.delete(length-str.length(), length);
            }
        }

        // output
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(problemName + ".out")));
        pw.println(sb);

        br.close();
        pw.close();
    }
}
