// Block Game - USACO Bronze December 2016 (http://www.usaco.org/index.php?page=viewproblem2&cpid=664)

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Arrays;

public class L7_blocks {
    public static int[] blocks(int numBoards, String[] wordStrings) {
        int[] totalBlocks = new int[26];

        for (int i=0; i<numBoards; i++) {
            int[] frontBoardLetters = new int[26];
            int[] backBoardLetters = new int[26];
            int[] boardLetters = new int[26];
            char[] frontBoard = wordStrings[2*i].toCharArray();
            char[] backBoard = wordStrings[2*i+1].toCharArray();

            for (int j=0; j<frontBoard.length; j++) {
                frontBoardLetters[frontBoard[j]-'a']++;
            }

            for (int j=0; j<backBoard.length; j++) {
                backBoardLetters[backBoard[j] -'a']++;
            }

            for (int j=0; j<26; j++) {
                boardLetters[j] = Math.max(frontBoardLetters[j], backBoardLetters[j]);
            }

            for (int j=0; j<26; j++) {
                totalBlocks[j] += boardLetters[j];
            }
        }

        return totalBlocks;
    }

    public static void main(String[] args) throws IOException {
        // input
        String problemName = "blocks";
        Scanner sc = new Scanner(new File(problemName + ".in"));
        int numBoards = sc.nextInt();
        String[] words = new String[numBoards*2];
        for (int i=0; i<words.length; i++) {
            words[i] = sc.next();
        }

        // algorithm
        int[] blocks = blocks(numBoards, words);

        //output
        PrintWriter out = new PrintWriter(new FileWriter(problemName + ".out"));
        for (int i=0; i<26; i++) {
            out.println(blocks[i]);
        }
        out.close();
    }
}
