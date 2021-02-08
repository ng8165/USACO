// Blocked Billboard - USACO Bronze December 2017 (http://www.usaco.org/index.php?page=viewproblem2&cpid=759)

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.io.File;

public class L2_billboard {

    public static int overlapArea(int r1_x1, int r1_y1, int r1_x2, int r1_y2, int r2_x1, int r2_y1, int r2_x2 ,int r2_y2) {
        int xSpan = Math.max(r1_x2, r2_x2) - Math.min(r1_x1, r2_x1);
        int xSum = (r1_x2-r1_x1) + (r2_x2-r2_x1);
        int xOverlapSide=0;

        if (xSpan < xSum) {
            xOverlapSide = xSum - xSpan;
        }

        int ySpan = Math.max(r1_y2, r2_y2) - Math.min(r1_y1, r2_y1);
        int ySum = (r1_y2-r1_y1) + (r2_y2-r2_y1);
        int yOverlapSide=0;
        if (ySpan < ySum) {
            yOverlapSide = ySum - ySpan;
        }

        return xOverlapSide * yOverlapSide;
    }
    public static int billboard(int b1_x1, int b1_y1, int b1_x2, int b1_y2, int b2_x1, int b2_y1, int b2_x2, int b2_y2, int tr_x1, int tr_y1, int tr_x2, int tr_y2) {
        int b1_area = (b1_x2 - b1_x1) * (b1_y2 - b1_y1) - overlapArea(b1_x1, b1_y1, b1_x2, b1_y2, tr_x1, tr_y1, tr_x2, tr_y2);
        int b2_area = (b2_x2 - b2_x1) * (b2_y2 - b2_y1) - overlapArea(b2_x1, b2_y1, b2_x2, b2_y2, tr_x1, tr_y1, tr_x2, tr_y2);
        return b1_area + b2_area;
    }

    public static void main(String[] args) throws IOException {
        // input
        String problemName = "billboard"; // change the name of billboard
        Scanner sc =  new Scanner(new File(problemName + ".in"));
        int b1_x1 = sc.nextInt();
        int b1_y1 = sc.nextInt();
        int b1_x2 = sc.nextInt();
        int b1_y2 = sc.nextInt();
        int b2_x1 = sc.nextInt();
        int b2_y1 = sc.nextInt();
        int b2_x2 = sc.nextInt();
        int b2_y2 = sc.nextInt();
        int tr_x1 = sc.nextInt();
        int tr_y1 = sc.nextInt();
        int tr_x2 = sc.nextInt();
        int tr_y2 = sc.nextInt();

        // algorithm
        int area = billboard(b1_x1, b1_y1, b1_x2, b1_y2, b2_x1, b2_y1, b2_x2, b2_y2, tr_x1, tr_y1, tr_x2, tr_y2);

        //output
        PrintWriter out = new PrintWriter(new FileWriter(problemName + ".out"));
        out.println(area);
        out.close();
    }
}

/*
boolean overlap_b1_x = false;
boolean overlap_b1_y = false;
boolean overlap_b2_x = false;
boolean overlap_b2_y = false;
boolean overlap_b1 = false;
boolean overlap_b2 = false;


if ((max(b1_x2, tr_x2)-min(b1_x1, tr_x1)) < (b1_x2-b1_x1+tr_x2-tr_x1))
    overlap_b1_x = true;
if ((max(b1_y2, tr_y2)-min(b1_y1, tr_y1)) < (b1_y2-b1_y1+tr_y2-tr_y1))
    overlap_b1_y = true;
if ((max(b2_x2, tr_x2)-min(b2_x1, tr_x1)) < (b2_x2-b2_x1+tr_x2-tr_x1))
    overlap_b2_x = true;
if ((max(b2_y2, tr_y2)-min(b2_y1, tr_y1)) < (b2_y2-b2_y1+tr_y2-tr_y1))
    overlap_b2_y = true;
if (overlap_b1_x == true && overlap_b1_y == true)
    overlap_b1 = true;
if (overlap_b2_x == true && overlap_b2_y == true)
    overlap_b2 = true;

 */ // Pseudocode
/*
-5,2 ,-3, 3,-2, 1, -1, 5
0

2,2,4,3,3,1,4,4
1

-4,-5, 3, -1,1, -7, 4, -3
4
 */ // Test Cases for overlapArea