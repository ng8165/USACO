// Mowing the Field - USACO Bronze January 2016 (http://www.usaco.org/index.php?page=viewproblem2&cpid=593)
// This problem was not completed on November 24, 2020, in 1 hour 7 minutes, with 1/10 test cases passed (first try)
// This problem was completed on November 28, 2020, in around 3 hours, with all 10/10 test cases passed (review)

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class mowing_jan2016 {
    static int numMoves;
    static String[] direction;
    static int[] distance;

    static int xPos = 0;
    static int yPos = 0;

    public static int mowing() {
        // find min/max values of x and y
        int minX = 0, minY = 0, maxX = 0, maxY = 0;

        for (int i=0; i<numMoves; i++) {
            calculateNewPosition(i);

            minX = Math.min(minX, xPos);
            maxX = Math.max(maxX, xPos);
            minY = Math.min(minY, yPos);
            maxY = Math.max(maxY, yPos);
        }

        System.out.println("x: " + minX + ", " + maxX);
        System.out.println("y: " + minY + ", " + maxY);

        // dimensions
        int yDimension = maxY-minY+3;
        int xDimension = maxX-minX+3;

        System.out.println("dimensions: " + yDimension + ", " + xDimension);

        // create the field
        int[][] field = new int[yDimension][xDimension];
        for (int i=0; i<yDimension; i++) {
            Arrays.fill(field[i], -1);
        }

        // start simulating FJ mowing his field
        int time = 1;
        int minGrassGrowTime = Integer.MAX_VALUE;

        // calculating starting point
        xPos = maxY+1;
        yPos = Math.abs(minX)+1;
        System.out.println("starting point: " + xPos + ", " + yPos + "\n");

        field[xPos][yPos] = 0; // set the starting coordinates as time=0

        for (int i=0; i<numMoves; i++) {
            for (int j=0; j<distance[i]; j++) {
                calculateNewPosition2(i);

                // place time into the cell
                int cellTime = field[xPos][yPos];

                if (cellTime != -1) {
                    minGrassGrowTime = Math.min(minGrassGrowTime, (time-cellTime));
                }
                field[xPos][yPos] = time++;

                printField(field);
            }
        }

        return minGrassGrowTime;
    }
    public static void calculateNewPosition2(int idx) {
        if (direction[idx].equals("N")) {
            xPos--;
        } else if (direction[idx].equals("S")) {
            xPos++;
        } else if (direction[idx].equals("E")) {
            yPos++;
        } else {
            yPos--;
        }
    }
    public static void printField(int[][] field) {
        for (int[] row: field) {
            for (int time: row) {
                if (time == -1) {
                    System.out.print(".  ");
                } else {
                    if (time < 10) {
                        System.out.print(time + "  ");
                    } else {
                        System.out.print(time + " ");
                    }
                }
            }
            System.out.println();
        }
        System.out.println("\n");
    }

    public static int mowing2() {
        // calculate min/max values of FJ's mowing area
        int minX = 0, maxX = 0, minY = 0, maxY = 0;

        for (int i=0; i<numMoves; i++) {
            calculateNewPosition(i);

            minX = Math.min(minX, xPos);
            maxX = Math.max(maxX, xPos);
            minY = Math.min(minY, yPos);
            maxY = Math.max(maxY, yPos);
        }

        System.out.println("x: " + minX + ", " + maxX);
        System.out.println("y: " + minY + ", " + maxY);

        // starting point
        xPos = Math.abs(minX)+1;
        yPos = Math.abs(minY)+1;

        System.out.println("starting point: " + xPos + ", " + yPos);

        // dimensions
        int yDimension = maxY-minY+3;
        int xDimension = maxX-minX+3;

        System.out.println("dimensions: " + yDimension + ", " + xDimension + "\n");

        // create the field
        int[][] field = new int[yDimension][xDimension];
        for (int i=0; i<yDimension; i++) {
            Arrays.fill(field[i], -1);
        }

        field[yPos][xPos] = 0; // set the starting coordinates as time=0

        // start simulating FJ mowing his field
        int currTime = 1;
        int minGrassGrowTime = Integer.MAX_VALUE;

        for (int i=0; i<numMoves; i++) {
            System.out.println("i = " + i + ",  move: " + direction[i] + " " + distance[i] + ", position: " + yPos + ", " + xPos + ", min: " + minGrassGrowTime);

            for (int j=0; j<distance[i]; j++) {
                calculateNextPosition(i);

                // place time into the cell
                int oldTime = field[yPos][xPos];

                if (oldTime != -1) {
                    minGrassGrowTime = Math.min(minGrassGrowTime, (currTime-oldTime));
                }
                field[yPos][xPos] = currTime++;

                printField2(field);
            }
        }

        if (minGrassGrowTime == Integer.MAX_VALUE) {
            return -1;
        } else {
            return minGrassGrowTime;
        }
    }
    public static void calculateNextPosition(int idx) {
        if (direction[idx].equals("N")) {
            yPos++;
        } else if (direction[idx].equals("S")) {
            yPos--;
        } else if (direction[idx].equals("E")) {
            xPos++;
        } else {
            xPos--;
        }
    }
    public static void printField2(int[][] field) {
        for (int i=field.length-1; i>=0; i--) {
            for (int time: field[i]) {
                if (time == -1) {
                    System.out.print(".  ");
                } else {
                    if (time < 10) {
                        System.out.print(time + "  ");
                    } else {
                        System.out.print(time + " ");
                    }
                }
            }
            System.out.println();
        }
        System.out.println("\n");
    }

    public static void calculateNewPosition(int idx) {
        if (direction[idx].equals("N")) {
            yPos += distance[idx];
        } else if (direction[idx].equals("S")) {
            yPos -= distance[idx];
        } else if (direction[idx].equals("E")) {
            xPos += distance[idx];
        } else {
            xPos -= distance[idx];
        }
    }

    public static void main(String[] args) throws IOException {
        // input
        String problemName = "mowing";
        Scanner sc = new Scanner(new File(problemName + ".in"));

        numMoves = sc.nextInt();
        direction = new String[numMoves];
        distance = new int[numMoves];
        for (int i=0; i<numMoves; i++) {
            direction[i] = sc.next();
            distance[i] = sc.nextInt();
        }

        // algorithm
        int minGrassGrowTime = mowing2();

        // output
        PrintWriter out = new PrintWriter(new FileWriter(problemName + ".out"));
        out.println(minGrassGrowTime);
        out.close();
    }
}

