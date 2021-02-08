//Square Pasture - USACO Bronze December 2016 (http://www.usaco.org/index.php?page=viewproblem2&cpid=663)

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class L1_square {
    public static int square(int[] x, int[] y) {
        int x_max = Integer.MIN_VALUE;
        int y_max = Integer.MIN_VALUE;
        int[] xTest = new int[3];
        int[] yTest = new int[3];

        for (int i=0; i<4; i++) {
            for (int j=0; j<4; j++) {
                if (i!=j) {
                    if (x[i] - x[j] > x_max) {
                        x_max = x[i] - x[j];
                    }
                }
            }
        }

        for (int i=0; i<4; i++) {
            for (int j=0; j<4; j++) {
                if (i!=j) {
                    if (y[i] - y[j] > y_max) {
                        y_max = y[i] - y[j];
                    }
                }
            }
        }

        if (x_max >= y_max) {
            return x_max * x_max;
        } else {
            return y_max * y_max;
        }
    }
    public static int square2(int[] x, int[] y) {
        int x_min=Integer.MAX_VALUE;
        int x_max=Integer.MIN_VALUE;
        int y_min=Integer.MAX_VALUE;
        int y_max=Integer.MIN_VALUE;

        for (int i=0; i<x.length; i++) {
            if (x[i] < x_min) x_min = x[i];
            if (x[i] > x_max) x_max = x[i];
        }
        int length = x_max - x_min;

        for (int i=0; i<y.length; i++) {
            if (y[i] < y_min) y_min = y[i];
            if (y[i] > y_max) y_max = y[i];
        }
        int width = y_max - y_min;

        if (length >= width) {
            return length*length;
        } else {
            return width*width;
        }
    }
    public static int square3(int[] x, int[] y) {
        int x1 = x[0];
        int y1 = y[0];
        int x2 = x[1];
        int y2 = y[1];
        int x3 = x[2];
        int y3 = y[2];
        int x4 = x[3];
        int y4 = y[3];

        int left_x = Math.min(x1, x3);
        int left_y = Math.min(y1, y3);
        int right_x = Math.max(x2, x4);
        int right_y = Math.max(y2, y4);

        int squareSide = Math.max((right_x-left_x), (right_y-left_y));
        return squareSide*squareSide;
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("square.in"));
        int[] x = new int[4];
        int[] y = new int[4];

        for (int i=0; i<8; i++) {
            if (i % 2 == 0) {
                x[i/2] = sc.nextInt();
            } else {
                y[i/2] = sc.nextInt();
            }
        }

        int squareArea = square3(x, y);

        PrintWriter out = new PrintWriter(new FileWriter("square.out"));
        out.println(squareArea);
        out.close();
    }
}

/*
Test Cases:
6 6 8 8
1 8 4 9
49

2 3 5 4
1 5 2 7
16

2 3 5 4
6 4 10 7
64

2 3 5 4
5 1 6 2
16

2 3 5 4
0 0 2 2
25
 */ // Test Cases
/*
int leftx = min (x1, x3)
int lefty = min(y1, y3)
int rightx = max(x2, x4)
int righty = max(y2, y4)
int edge = max((rightx-leftx), (righty-lefty))
return edge*edge;
 */ // Pseudocode
