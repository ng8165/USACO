// Word Processor - USACO Bronze January 2020 (http://www.usaco.org/index.php?page=viewproblem2&cpid=987)
// This problem was completed on November 22, 2020, in 22 minutes, with all 10/10 test cases passed (second try)

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class word_jan2020 {
    static int numWords;
    static int maxWordsPerLine;
    static String[] essay;

    public static void main(String[] args) throws IOException {
        // input
        String problemName = "word";
        Scanner sc = new Scanner(new File(problemName + ".in"));
        PrintWriter out = new PrintWriter(new FileWriter(problemName + ".out"));

        numWords = sc.nextInt();
        maxWordsPerLine = sc.nextInt();
        essay = new String[numWords];
        Arrays.fill(essay, "");
        for (int i=0; i<numWords; i++) {
            essay[i] = sc.next();
        }

        // algorithm
        int wordsPerLine = 0;

        for (int i=0; i<numWords; i++) {
            String word = essay[i];
            int wordLength = essay[i].length();

            if (i == 0) {
                out.print(word);
                wordsPerLine += wordLength;
            } else if (essay[i].length() + wordsPerLine <= maxWordsPerLine) {
                out.print(" ");
                out.print(word);
                wordsPerLine += wordLength;
            } else {
                out.println();
                out.print(word);
                wordsPerLine = wordLength;
            }
        }

        // output
        out.close();
    }
}
