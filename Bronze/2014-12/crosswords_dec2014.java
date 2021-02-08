// Crosswords - USACO Bronze December 2014 (http://www.usaco.org/index.php?page=viewproblem2&cpid=488)

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.ArrayList;

public class L10_crosswords {
    public static void crosswords(char[][] crossword, ArrayList<int[]> clues) {
        for (int row=0; row<crossword.length; row++) {
            for (int column=0; column<crossword[0].length; column++) {
                if (crossword[row][column] == '#') {
                    continue;
                }

                // check if the char can be a horizontal clue beginning
                if (column == 0 || crossword[row][column-1] == '#') {
                    // check if the cell to the left is outside of grid or blocked
                    if (column < crossword[0].length-2 && crossword[row][column+1] == '.' && crossword[row][column+2] == '.') {
                        // check if the two cells to the right are empty
                        clues.add(new int[] {row+1, column+1});
                        continue;
                    }
                }

                // now check if char can be a vertical clue beginning
                if (row == 0 || crossword[row-1][column] == '#') {
                    // check if the cell above is outside of grid or blocked
                    if (row<crossword.length-2 && crossword[row+1][column] == '.' && crossword[row+2][column] == '.') {
                        // check if the two cells below are empty
                        clues.add(new int[] {row+1, column+1});
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        // input
        String problemName = "crosswords";
        Scanner sc = new Scanner(new File(problemName + ".in"));
        int row = sc.nextInt();
        int column = sc.nextInt();
        sc.nextLine();
        ArrayList<int[]> clues = new ArrayList<>();
        char[][] crossword = new char[row][column];
        for (int i=0; i<row; i++) {
            crossword[i] = sc.nextLine().toCharArray();
        }

        // algorithm
        crosswords(crossword, clues); // clues is the output ArrayList

        // output
        PrintWriter out = new PrintWriter(new FileWriter(problemName + ".out"));
        out.println(clues.size());
        for (int[] clue: clues) {
            out.println(clue[0] + " " + clue[1]);
        }
        out.close();
    }
}
