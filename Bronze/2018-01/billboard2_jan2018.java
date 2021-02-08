// Blocked Billboard II - USACO Bronze January 2018 (http://www.usaco.org/index.php?page=viewproblem2&cpid=783)

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class L2_billboard2 {
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
    public static int overlapSide(int a, int b, int c, int d) {
        int span = Math.max(b, d) - Math.min(a, c);
        int sum = (b-a) + (d-c);
        int overlapSide=0;

        if (span < sum) {
            overlapSide = sum - span;
        }

        return overlapSide;
    }
    public static int billboard(int b1_x1, int b1_y1, int b1_x2, int b1_y2, int b2_x1, int b2_y1, int b2_x2, int b2_y2) {
        boolean bX = false;
        boolean bY = false;
        int areaBad = ((b2_x2 - b2_x1) * (b2_y2 - b2_y1));

        if ((b1_x1 <= b2_x1) && (b1_x2 >= b2_x2)) {
            bX = true;
        }
        if ((b1_y1 <= b2_y1) && (b1_y2 >= b2_y2)) {
            bY = true;
        }

        if (bX && bY) {
            return 0;
        }

        if (!bX && !bY) {
            return areaBad;
        }

        if ((b2_y2 > b1_y2) && (b2_y1 < b1_y1)) {
            return areaBad;
        }

        if ((b2_x2 > b1_x2) && (b2_x1 < b1_x1)) {
            return areaBad;
        }

        return areaBad - overlapArea(b1_x1, b1_y1, b1_x2, b1_y2, b2_x1, b2_y1, b2_x2, b2_y2);

    }
    public static int billboard2(int b1_x1, int b1_y1, int b1_x2, int b1_y2, int b2_x1, int b2_y1, int b2_x2, int b2_y2) {
        int badL = b2_x2 - b2_x1;
        int badW = b2_y2 - b2_y1;
        int tarpL = badL;
        int tarpW = badW;
        int overlapL = overlapSide(b1_x1, b1_x2, b2_x1, b2_x2);
        int overlapW = overlapSide(b1_y1, b1_y2, b2_y1, b2_y2);

        if (overlapL < badL) {
            tarpW = badW;
        } else {
            if ((b2_y2 > b1_y2) && (b2_y1 < b1_y1)) {
                tarpW = badW;
            } else {
                tarpW = badW - overlapW;
            }
        }

        if (overlapW < badW) {
            tarpL = badL;
        } else {
            if ((b2_x2 > b1_x2) && (b2_x1 < b1_x1)) {
                tarpL = badL;
            } else {
                tarpL = badL - overlapL;
            }
        }

        return tarpL * tarpW;
    }

    public static void main(String[] args) throws IOException {
        // input
        String problemName = "billboard";
        Scanner sc =  new Scanner(new File(problemName + ".in"));
        int b2_x1 = sc.nextInt();
        int b2_y1 = sc.nextInt();
        int b2_x2 = sc.nextInt();
        int b2_y2 = sc.nextInt();
        int b1_x1 = sc.nextInt();
        int b1_y1 = sc.nextInt();
        int b1_x2 = sc.nextInt();
        int b1_y2 = sc.nextInt();

        // algorithm
        int area = billboard2(b1_x1, b1_y1, b1_x2, b1_y2, b2_x1, b2_y1, b2_x2, b2_y2);

        //output
        PrintWriter out = new PrintWriter(new FileWriter(problemName + ".out"));
        out.println(area);
        out.close();
    }
}

/*
No Code Pseudocode: (b1 is good, b2 is bad)

boolean X is if the bad billboard is between the good one in terms of the X axis ((b1_x1 <= b2_x1) && (b1_x2 >= b2_x2))
boolean Y is if the bad billboard is between the good one in terms of the Y axis ((b1_y1 <= b2_y1) && (b1_y2 >= b2_y2))

bX  bY  Return
____________
T   T   0
F   F   Area_Bad ((b2_x2 - b2_x1) * (b2_y2 - b2_y1))
T   F   (see lines 67-69)
F   T   (see lines 67-69)

we now have to determine if the bad billboard is partially hidden behind the good billboard
if overlapArea == 0 then return Area_Bad ((b2_x2 - b2_x1) * (b2_y2 - b2_y1))
else return overlapArea

Code Pseudocode:
bX = false;
bY = false;
AreaBad = ((b2_x2 - b2_x1) * (b2_y2 - b2_y1));

if ((b1_x1 <= b2_x1) && (b1_x2 >= b2_x2))
   bX = true;
if ((b1_y1 <= b2_y1) && (b1_y2 >= b2_y2))
   bY = true;

if (bX && bY)
   return 0;

if ((bX==false && bY==false))
   return AreaBad;

return AreaBad - overlapArea(stuff);

 */ // Pseudocode
/*
-6 1 -8 2
-1 3 -5 6
2

-0 2 -2 4
-1 3 -5 6
4

-2 4 -3 5
-1 3 -5 6
0

-2 4 -4 7
-1 3 -5 6
2

-4 3 -8 4
-1 3 -5 6
3

-3 2 -4 3
-1 3 -5 6
1

-8 7 0 2
-5 6 -1 3
40

-2 1 -1 2
-3 1 -1 3
0

-3 0 -2 6
-3 1 -1 3
6
*/ // Test Cases