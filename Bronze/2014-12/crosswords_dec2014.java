// Crosswords - USACO Bronze December 2014 (http://www.usaco.org/index.php?page=viewproblem2&cpid=488)
// This problem was completed on December 5, 2020, 29 minutes, with all 10/10 test cases passed (second try)

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class crosswords_dec2014 {
    static int length;
    static int width;
    static char[][] crossword;

    public static ArrayList<int[]> crosswords() {
        ArrayList<int[]> clues = new ArrayList<>();

        for (int i=0; i<length; i++) {
            for (int j=0; j<width; j++) {
                if (isHorizontalClue(i, j) || isVerticalClue(i, j)) {
                    clues.add(new int[] {i+1, j+1});
                }
            }
        }

        return clues;
    }
    public static boolean isHorizontalClue(int i, int j) {
        if ((crossword[i][j] == '.') && (j==0 || crossword[i][j-1] == '#') &&
                (j < width-2 && crossword[i][j+1] == '.' && crossword[i][j+2] == '.')) {
            return true;
        }

        return false;
    }
    public static boolean isVerticalClue(int i, int j) {
        if ((crossword[i][j] == '.') && (i==0 || crossword[i-1][j] == '#') &&
                (i < length-2 && crossword[i+1][j] == '.' && crossword[i+2][j] == '.')) {
            return true;
        }

        return false;
    }

    public static void main(String[] args) throws IOException {
        // input
        String problemName = "crosswords";
        Scanner sc = new Scanner(new File(problemName + ".in"));

        length = sc.nextInt();
        width = sc.nextInt();
        sc.nextLine();
        crossword = new char[length][width];
        for (int i=0; i<length; i++) {
            crossword[i] = sc.nextLine().toCharArray();
        }

        // algorithm
        ArrayList<int[]> clues = crosswords();

        // output
        PrintWriter out = new PrintWriter(new FileWriter(problemName + ".out"));
        out.println(clues.size());
        for (int[] clue: clues) {
            out.println(clue[0] + " " + clue[1]);
        }
        out.close();
    }
}
