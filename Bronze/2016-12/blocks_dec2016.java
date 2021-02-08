// Block Game - USACO Bronze December 2016 (http://www.usaco.org/index.php?page=viewproblem2&cpid=664)
// This problem was done as practice on Monday, October 5, 2020

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class blocks_dec2016 {
    static int numBoards;
    static String[][] boards;

    public static int[] blocks() {
        int[] neededChars = new int[26];

        for (int i=0; i<numBoards; i++) {
            String word1 = boards[i][0];
            char[] word1Array = word1.toCharArray();
            int[] word1Chars = new int[26];

            String word2 = boards[i][1];
            char[] word2Array = word2.toCharArray();
            int[] word2Chars = new int[26];

            // turn the current words into characters
            for (char currChar: word1Array) {
                word1Chars[currChar - 'a']++;
            }
            for (char currChar: word2Array) {
                word2Chars[currChar - 'a']++;
            }

            // add those characters into the master list of needed chars
            for (int j=0; j<26; j++) {
                neededChars[j] += Math.max(word1Chars[j], word2Chars[j]);
            }
        }

        return neededChars;
    }

    public static void main(String[] args) throws IOException {
        // input
        String problemName = "blocks";
        Scanner sc = new Scanner(new File(problemName + ".in"));

        numBoards = sc.nextInt();
        boards = new String[numBoards][2];
        for (int i=0; i<numBoards; i++) {
            boards[i][0] = sc.next();
            boards[i][1] = sc.next();
        }

        // algorithm
        int[] neededChars = blocks();

        // output
        PrintWriter out = new PrintWriter(new FileWriter(problemName + ".out"));
        for (int i=0; i<26; i++) {
            out.println(neededChars[i]);
        }
        out.close();
    }
}
