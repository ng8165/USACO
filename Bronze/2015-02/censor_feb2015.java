// Censoring (Bronze) - USACO Bronze February 2015 (http://www.usaco.org/index.php?page=viewproblem2&cpid=526)
// This problem was partially completed on November 27, 2020, in 34 minutes, with 6/15 test cases passed (first try)
// This problem was partially completed on November 28, 2020, during review, with 9/15 test cases passed (review)
// Attempted with String manipulation and StringBuilder, Solution requires Stack (see here: http://www.cs.ucf.edu/~dmarino/progcontests/mysols/highschool/usaco/2014/bronze/censor.java)

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class censor_feb2015 {
    static String magazine;
    static String badWord;

    public static String censor() {
        char[] magazineArr = magazine.toCharArray();

        StringBuilder goodMagazine = new StringBuilder(magazineArr.length);
        for (char letter: magazineArr) {
            goodMagazine.append(letter);
            System.out.println(goodMagazine.toString());

            if (goodMagazine.length() > badWord.length()) {
                int idx = goodMagazine.lastIndexOf(badWord);

                if (idx != -1) {
                    goodMagazine.delete(idx, goodMagazine.length());
                }
            }
        }

        return goodMagazine.toString();
    }
    public static String censor2() {
        StringBuilder goodMagazine = new StringBuilder(magazine);

        while (true) {
            int idx = goodMagazine.indexOf(badWord);

            if (idx == -1) {
                break;
            }
            System.out.println("idx: " + idx);

            goodMagazine.delete(idx, idx+badWord.length());
        }

        return goodMagazine.toString();
    }

    public static void main(String[] args) throws IOException {
        // input
        String problemName = "censor";
        Scanner sc = new Scanner(new File(problemName + ".in"));

        magazine = sc.next();
        badWord = sc.next();

        // algorithm
        String goodMagazine = censor2();

        // output
        PrintWriter out = new PrintWriter(new FileWriter(problemName + ".out"));
        out.println(goodMagazine);
        out.close();
    }
}
