// The Lost Cow - USACO Bronze Open 2017 (http://www.usaco.org/index.php?page=viewproblem2&cpid=735)
// This problem was completed on Sunday, September 13, 2020, in 37 minutes, with 10/10 test cases passed (second try)

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class lostcow_open2017 {
    static int FJ;
    static int bessie;

    public static int lostcow() {
        int moveDistance = 1;
        int direction = 1;
        int FJDistance = 0;

        int startPos = FJ;
        int endPos = FJ + (direction*moveDistance);

        while (true) {
            if ((direction == 1 && (bessie >= startPos && bessie <= endPos)) ||
                    (direction == -1 && (bessie >= endPos && bessie <= startPos))) {
                // bessie is between the start and end positions
                FJDistance += (Math.abs(bessie - startPos));
                break;
            } else {
                // bessie is not in between the stand and end positions
                FJDistance += (Math.abs(endPos - startPos));
            }

            moveDistance *= 2;
            direction *= -1;

            startPos = endPos;
            endPos = FJ + (direction*moveDistance);
        }

        return FJDistance;
    }
    public static int lostcow2() {
        int moveDistance = 1;
        int direction = 1;
        int FJDistance = 0;

        int startPos = FJ;
        int endPos = FJ + (direction*moveDistance);

        while (true) {
            if (((startPos-bessie)*(endPos-bessie)) < 0) {
                // bessie is between the start and end positions
                FJDistance += (Math.abs(bessie - startPos));
                break;
            } else {
                // bessie is not in between the stand and end positions
                FJDistance += (Math.abs(endPos - startPos));
            }

            moveDistance *= 2;
            direction *= -1;

            startPos = endPos;
            endPos = FJ + (direction*moveDistance);
        }

        return FJDistance;
    }

    public static void main(String[] args) throws IOException{
        // input
        String problemName = "lostcow";
        Scanner sc = new Scanner(new File(problemName + ".in"));

        FJ = sc.nextInt();
        bessie = sc.nextInt();

        // algorithm
        int FJDistance = lostcow2();

        // output
        PrintWriter out = new PrintWriter(new FileWriter(problemName + ".out"));
        out.println(FJDistance);
        out.close();
    }
}

