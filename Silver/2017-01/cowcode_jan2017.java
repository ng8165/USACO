// Secret Cow Code - USACO Silver January 2017 (http://www.usaco.org/index.php?page=viewproblem2&cpid=692)
// This problem was completed as homework for the USACO Silver 1 Class on 2/21/21.

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class L3_cowcode {
    static String word;
    static long idx;

    public static char cowcode(int level, long idx, long length) {
        // base case
        if (level == 0) {
            return word.charAt((int) idx-1);
        }

        length /= 2;
        if (idx > length) {
            idx -= length;
            if (idx == 1) {
                idx = length; // first char becomes last char
            } else {
                idx--; // all other chars move to left by 1
            }
        }

        return cowcode(level-1, idx, length);
    }

    public static void main(String[] args) throws IOException {
        // input
        String problemName = "cowcode";
        Scanner sc = new Scanner(new File(problemName + ".in"));

        word = sc.next();
        idx = sc.nextLong();

        // algorithm
        int level = 0; // find the number of necessary encryptions
        long length = word.length();

        while (length < idx) {
            length *= 2;
            level++;
        }

        // find the character recursively
        char result = cowcode(level, idx, length);

        // output
        PrintWriter out = new PrintWriter(new FileWriter(problemName + ".out"));
        out.println(result);
        out.close();
    }
}
