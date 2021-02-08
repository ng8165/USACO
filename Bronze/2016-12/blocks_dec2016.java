// Block Game - USACO Bronze December 2016 (http://www.usaco.org/index.php?page=viewproblem2&cpid=664)
// This problem was completed on November 20, 2020, in 1 hour 17 minutes, with all 10/10 test cases passed (second try)

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class blocks_dec2016 {
    static int numBoards;
    static String[][] boardWords;

    public static int[] boards() {
        int[] requiredLetters = new int[26];

        for (int i=0; i<numBoards; i++) {
            char[] word1 = boardWords[i][0].toCharArray();
            char[] word2 = boardWords[i][1].toCharArray();

            ArrayList<Character> uniqueLetters = determineUniqueLetters(boardWords[i][0] + boardWords[i][1]);

            for (char currLetter: uniqueLetters) {
                int word1LetterCnt = 0;
                int word2LetterCnt = 0;

                for (char word1Letter: word1) {
                    if (word1Letter == currLetter) {
                        word1LetterCnt++;
                    }
                }

                for (char word2Letter: word2) {
                    if (word2Letter == currLetter) {
                        word2LetterCnt++;
                    }
                }

                int maxLetterCnt = Math.max(word1LetterCnt, word2LetterCnt);

                requiredLetters[currLetter - 'a'] += maxLetterCnt;
            }
        }

        return requiredLetters;
    }

    public static ArrayList<Character> determineUniqueLetters(String combinedString) {
        char[] combinedWord = combinedString.toCharArray();

        ArrayList<Character> uniqueLetters = new ArrayList<>();

        for (char letter: combinedWord) {
            boolean found = false;

            for (char uniqueLetter: uniqueLetters) {
                if (letter == uniqueLetter) {
                    found = true;
                    break;
                }
            }

            if (!found) {
                uniqueLetters.add(letter);
            }
        }

        return uniqueLetters;
    }

    public static void main(String[] args) throws IOException {
        // input
        String problemName = "blocks";
        Scanner sc = new Scanner(new File(problemName + ".in"));

        numBoards = sc.nextInt();
        boardWords = new String[numBoards][2];
        for (int i=0; i<numBoards; i++) {
            boardWords[i][0] = sc.next();
            boardWords[i][1] = sc.next();
        }

        // algorithm
        int[] requiredLetters = boards();

        // output
        PrintWriter out = new PrintWriter(new FileWriter(problemName + ".out"));
        for (int i=0; i<26; i++) {
            out.println(requiredLetters[i]);
        }
        out.close();
    }
}
