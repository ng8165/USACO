// Stuck in a Rut - USACO Bronze December 2020 (http://www.usaco.org/index.php?page=viewproblem&cpid=1049)
// This problem was partially completed on December 21, 2020, in 1 hour and 47 minutes, with 6/10 test cases passed

import java.util.Arrays;
import java.util.Scanner;

public class stuck_3 {
    static int numCows;
    static String[] direction;
    static int[][] coordinates;
    static int[][] farm = new int[2000][2000];

    static int maxX = -1, maxY = -1;
    static int[] grassEaten;
    static boolean[] shouldMoveMore;

    public static void stuck() {
        grassEaten = new int[numCows];
        shouldMoveMore = new boolean[numCows];
        Arrays.fill(grassEaten, 1);
        Arrays.fill(shouldMoveMore, true);

        while (areCowsInBounds()) {
            fillGrassCells();
            updateCoordinates();
            checkStuckCows();
        }
    }

    public static void updateCoordinates() {
        for (int i=0; i<numCows; i++) {
            if (shouldMoveMore[i]) {
                if (direction[i].equals("N")) {
                    coordinates[i][1]++;
                } else {
                    coordinates[i][0]++;
                }
            }
        }
    }
    public static void fillGrassCells() {
        for (int i=0; i<numCows; i++) {
            farm[coordinates[i][0]][coordinates[i][1]] = -1;
        }
    }
    public static void checkStuckCows() {
        for (int i=0; i<numCows; i++) {
            if (farm[coordinates[i][0]][coordinates[i][1]] == -1) {
                shouldMoveMore[i] = false;
            } else {
                grassEaten[i]++;
            }
        }
    }
    public static boolean areCowsInBounds() {
        for (int i=0; i<numCows; i++) {
            if (shouldMoveMore[i]) {
                if (coordinates[i][0] < maxX && coordinates[i][1] < maxY) {
                    return true;
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {
        // input
        Scanner sc = new Scanner(System.in);

        numCows = sc.nextInt();
        direction = new String[numCows];
        coordinates = new int[numCows][2];
        for (int i=0; i<numCows; i++) {
            direction[i] = sc.next();
            coordinates[i][0] = sc.nextInt();
            coordinates[i][1] = sc.nextInt();

            maxX = Math.max(maxX, coordinates[i][0]);
            maxY = Math.max(maxY, coordinates[i][1]);
        }

        // algorithm
        stuck();

        // output
        for (int i=0; i<numCows; i++) {
            if (!shouldMoveMore[i]) {
                System.out.println(grassEaten[i]);
            } else {
                System.out.println("Infinity");
            }
        }
    }
}
