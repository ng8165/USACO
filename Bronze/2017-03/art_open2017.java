// Modern Art - USACO Bronze Open 2017 (http://www.usaco.org/index.php?page=viewproblem2&cpid=737)
// This problem was not completed on Sunday, September 13, 2020, in 1 hour, with 2/10 test cases passed (first try)
// This problem was completed on Saturday, September 19, 2020, in review, with 10/10 test cases passed (review)

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class art_open2017 {
    static int canvasSize;
    static char[][] canvas;

    public static int art() {
        ArrayList<Character> colors = new ArrayList<>();

        // find all colors
        for (int i=0; i<canvasSize; i++) {
            for (int j=0; j<canvasSize; j++) {
                if (canvas[i][j] != '0' && isUniqueColor(canvas[i][j], colors)) {
                    colors.add(canvas[i][j]);
                }
            }
        }

        return 1;
    }
    public static boolean isUniqueColor(char color, ArrayList<Character> colors) {
        for (Character currColor: colors) {
            if (color == currColor) {
                return false;
            }
        }
        return true;
    }

    public static int art2() {
        ArrayList<Character> colors = new ArrayList<>();
        ArrayList<Character> paintedAfterwards = new ArrayList<>();

        // find all colors on the canvas
        for (int row=0; row<canvasSize; row++) {
            for (int col=0; col<canvasSize; col++) {
                if (canvas[row][col] != '0' && isUniqueColor2(canvas[row][col], colors)) {
                    colors.add(canvas[row][col]);
                }
            }
        }

        // iterate through these colors and find their minimum original shapes
        for (char currColor: colors) {
            // find the coordinates of the current color's shape
            int minRow = Integer.MAX_VALUE, maxRow = 0, minCol = Integer.MAX_VALUE, maxCol = 0;

            for (int row=0; row<canvasSize; row++) {
                for (int col=0; col<canvasSize; col++) {
                    if (currColor == canvas[row][col]) {
                        minCol = Math.min(minCol, col);
                        maxCol = Math.max(maxCol, col);
                        minRow = Math.min(minRow, row);
                        maxRow = Math.max(maxRow, row);
                    }
                }
            }

            // scan in the coordinates of the shape and see if there is anything that is not the current color
            for (int row=minRow; row<=maxRow; row++) {
                for (int col=minCol; col<=maxCol; col++) {
                    if (canvas[row][col] != currColor) {
                        if (isUniqueColor3(canvas[row][col], paintedAfterwards)) {
                            paintedAfterwards.add(canvas[row][col]);
                        }
                    }
                }
            }
        }

        return (colors.size() - paintedAfterwards.size());
    }
    public static boolean isUniqueColor2(char color, ArrayList<Character> colors) {
        for (Character currColor: colors) {
            if (color == currColor) {
                return false;
            }
        }
        return true;
    }
    public static boolean isUniqueColor3(char color, ArrayList<Character> paintedAfterwards) {
        for (Character currColor: paintedAfterwards) {
            if (color == currColor) {
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) throws IOException{
        // input
        String problemName = "art";
        Scanner sc = new Scanner(new File(problemName + ".in"));

        canvasSize = sc.nextInt();
        canvas = new char[canvasSize][canvasSize];
        sc.nextLine();
        for (int i=0; i<canvasSize; i++) {
            canvas[i] = sc.nextLine().toCharArray();
        }

        // algorithm
        int numBaseColors = art2();

        // output
        PrintWriter out = new PrintWriter(new FileWriter(problemName + ".out"));
        out.println(numBaseColors);
        out.close();
    }
}

