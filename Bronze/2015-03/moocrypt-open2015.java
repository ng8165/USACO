// Moocryption - USACO Bronze US Open 2015 (http://www.usaco.org/index.php?page=viewproblem2&cpid=545)

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class L9_moocrypt {
    public static int moocrypt(char[][] puzzle, int rows, int columns) {
        int maxMOO = 0;
        for (int i=0; i<26; i++) {
            for (int j=0; j<26; j++) {
                char c1 = (char)(i+'A');
                char c2 = (char)(j+'A');
                if (c1 == 'M' || c2 == 'O' || c1 == c2) {
                    continue;
                }
                int MOOcnt = search2(puzzle, rows, columns, c1, c2);
                maxMOO = Math.max(MOOcnt, maxMOO);
            }
        }
        return maxMOO;
    }
    public static int search(char[][] puzzle, int numRows, int numColumns, char c1, char c2) {
        int numMOO = 0;

        // horizontal (left to right)
        for (int row=0; row<numRows; row++) {
            for (int column=0; column<numColumns-2; column++) {
                if (puzzle[row][column] == c1 && puzzle[row][column+1] == c2 && puzzle[row][column+2] == c2) {
                    numMOO++;
                    System.out.println("Horizontal Left to Right: " + c1 + "" + c2 + "" + c2 + " - " + row + " " + column);
                }
            }
        }

        // horizontal (right to left)
        for (int row=0; row<numRows; row++) {
            for (int column=numColumns-1; column>=2; column--) {
                if (puzzle[row][column] == c1 && puzzle[row][column-1] == c2 && puzzle[row][column-2] == c2) {
                    numMOO++;
                    System.out.println("Horizontal Right to Left: " + c1 + "" + c2 + "" + c2 + "" + " - " + row + " " + column);
                }
            }
        }

        // vertical (top to bottom)
        for (int column=0; column<numColumns; column++) {
            for (int row=0; row<numRows-2; row++) {
                if (puzzle[row][column] == c1 && puzzle[row+1][column] == c2 && puzzle[row+2][column] == c2) {
                    numMOO++;
                    System.out.println("Vertical Top to Bottom: " + c1 + "" + c2 + "" + c2 + "" + " - " + row + " " + column);
                }
            }
        }

        // vertical (bottom to top)
        for (int column=0; column<numColumns; column++) {
            for (int row=numRows-1; row>=2; row--) {
                if (puzzle[row][column] == c1 && puzzle[row-1][column] == c2 && puzzle[row-2][column] == c2) {
                    numMOO++;
                    System.out.println("Vertical Bottom to Top: " + c1 + "" + c2 + "" + c2 + "" + " - " + row + " " + column);
                }
            }
        }

        // diagonal (top left corner to top right corner)
        for (int row = 0; row < numRows-2; row++) {
            int column = 0;
            int loopCnt = Math.min(numRows, numColumns);
            for (int i=0; i<loopCnt-2; i++) {
                if (puzzle[row+i][column+i] == c1 && puzzle[row+i+1][column+i+1] == c2 && puzzle[row+i+2][column+i+2] == c2) {
                    numMOO++;
                    System.out.println("Diagonal Top Left to Bottom Right: " + c1 + "" + c2 + "" + c2 + "" + " - " + (row+i) + " " + (column+i));
                }
            }
        }
        for (int column = 1; column < numColumns-2; column++) {
            int row = 0;
            int loopCnt = Math.min(numRows, numColumns);
            for (int i=0; i<loopCnt-3; i++) {
                if (puzzle[row+i][column+i] == c1 && puzzle[row+i+1][column+i+1] == c2 && puzzle[row+i+2][column+i+2] == c2) {
                    numMOO++;
                    System.out.println("Diagonal Top Left to Bottom Right: " + c1 + "" + c2 + "" + c2 + "" + " - " + (row+i) + " " + (column+i));
                }
            }
        }

        return numMOO;
    }
    public static int search2(char[][] puzzle, int numRows, int numColumns, char c1, char c2) {
        int numMOO = 0;
        for (int row=0; row<numRows; row++) {
            for (int column=0; column<numColumns; column++) {
                if (puzzle[row][column] == c1) {
                    // north of c1
                    if (row >= 2 && puzzle[row-1][column] == c2 && puzzle[row-2][column] == c2) {
                        numMOO++;
                    }

                    // south of c1
                    if (row < numRows-2 && puzzle[row+1][column] == c2 && puzzle[row+2][column] == c2) {
                        numMOO++;
                    }

                    // west of c1
                    if (column >= 2 && puzzle[row][column-1] == c2 && puzzle[row][column-2] == c2) {
                        numMOO++;
                    }

                    // east of c1
                    if (column < numColumns-2 && puzzle[row][column+1] == c2 && puzzle[row][column+2] == c2) {
                        numMOO++;
                    }

                    // northwest of c1
                    if (row >= 2 && column >= 2 && puzzle[row-1][column-1] == c2 && puzzle[row-2][column-2] == c2) {
                        numMOO++;
                    }

                    // northeast of c1
                    if (row >= 2 && column < numColumns-2 && puzzle[row-1][column+1] == c2 && puzzle[row-2][column+2] == c2) {
                        numMOO++;
                    }

                    // southwest of c1
                    if (row < numRows-2 && column >= 2 && puzzle[row+1][column-1] == c2 && puzzle[row+2][column-2] == c2) {
                        numMOO++;
                    }

                    // southeast of c1
                    if (row < numRows-2 && column < numColumns-2 && puzzle[row+1][column+1] == c2 && puzzle[row+2][column+2] == c2) {
                        numMOO++;
                    }
                }
            }
        }
        if (numMOO>0) {
            System.out.println(c1 + "" + c2 + ": " + numMOO);
        }
        return numMOO;
    }

    public static void main(String[] args) throws IOException {
        // input
        String problemName = "moocrypt";
        Scanner sc = new Scanner(new File(problemName + ".in"));
        int rows = sc.nextInt();
        int columns = sc.nextInt();
        char[][] puzzle = new char[rows][columns];
        sc.nextLine(); // move cursor to next line from the first line to second line
        for (int i=0; i<rows; i++) {
            puzzle[i] = sc.nextLine().toCharArray();
        }

        // algorithm
        int maxMOO = moocrypt(puzzle, rows, columns);

        // output
        PrintWriter out = new PrintWriter(new FileWriter(problemName + ".out"));
        out.println(maxMOO);
        out.close();
    }
}
