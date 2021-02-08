// Word Processor - USACO Bronze January 2020 (http://www.usaco.org/index.php?page=viewproblem2&cpid=987)


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class L3_word {
    public static void main(String[] args) throws IOException {
        // input
        String problemName = "word";
        Scanner sc = new Scanner(new File(problemName + ".in"));
        PrintWriter out = new PrintWriter(new FileWriter(problemName + ".out"));

        int wordCount = sc.nextInt();
        int charPerLine = sc.nextInt();
        String[] essay = new String[wordCount];

        for (int i = 0; i < wordCount; i++) {
            essay[i] = sc.next();
        }

        // algorithm
        int charsLeft = charPerLine;
        for (int i = 0; i < essay.length; i++) {
            String word = essay[i];
            String nextWord;
            if (i == essay.length - 1) {
                nextWord = "";
            } else {
                nextWord = essay[i + 1];
            }

            if (word.length() > charsLeft) {
                // new line, print word, update charsLeft
                out.println();
                out.print(word);
                charsLeft = charPerLine - word.length();
                // print space or not?
                if ((nextWord.length() <= charsLeft) && (i < essay.length-1)) {
                    out.print(" ");
                }
            } else {
                // print word, update charsLeft
                out.print(word);
                charsLeft -= word.length();
                // print space or not?
                if ((nextWord.length() <= charsLeft) && (i < essay.length-1)) {
                    out.print(" ");
                }
            }
        }
        //output
        out.close();

    }
}
