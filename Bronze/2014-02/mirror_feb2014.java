// Mirror Field - USACO Bronze February 2014 (http://www.usaco.org/index.php?page=viewproblem2&cpid=394)
// This problem was completed on December 13, 2020, in 44 minutes, with 10/10 test cases passed (first try)

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class mirror_feb2014 {
    static int row, col;
    static char[][] mirrorField;

    public static int mirror() {
        int maxMirrors = 0;

        // laser from west (from left)
        for (int i=0; i<col; i++) {
            int[] coordinates = new int[] {i, 0};
            char direction = 'W';
            int numMirrors = 0;

            while (isInBounds(coordinates)) {
                direction = newDirection(mirrorField[coordinates[0]][coordinates[1]], direction);

                if (direction == 'N') {
                    coordinates[0]--;
                } else if (direction == 'S') {
                    coordinates[0]++;
                } else if (direction == 'E') {
                    coordinates[1]++;
                } else {
                    coordinates[1]--;
                }

                direction = flipDirection(direction);

                numMirrors++;
            }

            maxMirrors = Math.max(maxMirrors, numMirrors);
        }

        // laser from east (from right)
        for (int i=0; i<col; i++) {
            int[] coordinates = new int[] {i, row -1};
            char direction = 'E';
            int currMirrors = 0;

            while (isInBounds(coordinates)) {
                direction = newDirection(mirrorField[coordinates[0]][coordinates[1]], direction);
                if (direction == 'N') {
                    coordinates[0]--;
                } else if (direction == 'S') {
                    coordinates[0]++;
                } else if (direction == 'E') {
                    coordinates[1]++;
                } else {
                    coordinates[1]--;
                }

                direction = flipDirection(direction);
                currMirrors++;
            }

            maxMirrors = Math.max(maxMirrors, currMirrors);
        }

        // laser from north (from up)
        for (int i=0; i<row; i++) {
            int[] coordinates = new int[] {0, i};
            char direction = 'N';
            int currMirrors = 0;

            while (isInBounds(coordinates)) {
                direction = newDirection(mirrorField[coordinates[0]][coordinates[1]], direction);
                if (direction == 'N') {
                    coordinates[0]--;
                } else if (direction == 'S') {
                    coordinates[0]++;
                } else if (direction == 'E') {
                    coordinates[1]++;
                } else {
                    coordinates[1]--;
                }

                direction = flipDirection(direction);
                currMirrors++;
            }

            maxMirrors = Math.max(maxMirrors, currMirrors);
        }

        // laser from south (from down)
        for (int i=0; i<row; i++) {
            int[] coordinates = new int[] {col -1, i};
            char direction = 'S';
            int currMirrors = 0;

            while (isInBounds(coordinates)) {
                direction = newDirection(mirrorField[coordinates[0]][coordinates[1]], direction);
                if (direction == 'N') {
                    coordinates[0]--;
                } else if (direction == 'S') {
                    coordinates[0]++;
                } else if (direction == 'E') {
                    coordinates[1]++;
                } else {
                    coordinates[1]--;
                }

                direction = flipDirection(direction);
                currMirrors++;
            }

            maxMirrors = Math.max(maxMirrors, currMirrors);
        }

        return maxMirrors;
    }

    public static char newDirection(char mirror, char direction) {
        if (mirror == '/' && direction == 'N') {
            return 'W';
        } else if (mirror == '/' && direction == 'S') {
            return 'E';
        } else if (mirror == '/' && direction == 'E') {
            return 'S';
        } else if (mirror == '/' && direction == 'W') {
            return 'N';
        } else if (mirror == '\\' && direction == 'N') {
            return 'E';
        } else if (mirror == '\\' && direction == 'S') {
            return 'W';
        } else if (mirror == '\\' && direction == 'E') {
            return 'N';
        } else if (mirror == '\\' && direction == 'W') {
            return 'S';
        }

        return '!'; // avoid compilation errors
    }
    public static boolean isInBounds(int[] coordinates) {
        if (coordinates[0] < 0 || coordinates[1] < 0) {
            return false;
        } else if (coordinates[0] >= col || coordinates[1] >= row) {
            return false;
        }

        return true;
    }
    public static char flipDirection(char direction) {
        if (direction == 'N') {
            return 'S';
        } else if (direction == 'S') {
            return 'N';
        } else if (direction == 'E') {
            return 'W';
        } else if (direction == 'W') {
            return 'E';
        }

        return '!'; // avoid compilation errors
    }

    public static void main(String[] args) throws IOException {
        // input
        String problemName = "mirror";
        Scanner sc = new Scanner(new File(problemName + ".in"));

        col = sc.nextInt();
        row = sc.nextInt();
        mirrorField = new char[col][row];
        for (int i = 0; i< col; i++) {
            mirrorField[i] = sc.next().toCharArray();
        }

        // algorithm
        int maxMirrors = mirror();

        // output
        PrintWriter out = new PrintWriter(new FileWriter(problemName + ".out"));
        out.println(maxMirrors);
        out.close();
    }
}
