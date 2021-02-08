// Lost Cow - USACO Bronze US Open 2017 (http://www.usaco.org/index.php?page=viewproblem2&cpid=735)

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class L3_lostcow {
    public static int lostcow(int x, int y) {
        int oldPos = x;
        int newPos;
        int move = 1;
        int direction = 1;
        int distance = 0;

        while (true) {
            newPos = x + move*direction;
            if (y >= Math.min(oldPos, newPos) && y <= Math.max(oldPos, newPos)) {
                distance += Math.abs(y - oldPos);
                break;
            } else {
                distance += Math.abs(newPos - oldPos);
            }

            oldPos = newPos;
            move *= 2;
            direction *= -1;
        }

        return distance;
    }

    public static void main(String[] args) throws IOException {
        // input
        String problemName = "lostcow";
        Scanner sc = new Scanner(new File(problemName + ".in"));
        int x = sc.nextInt(); // John position
        int y = sc.nextInt(); // Bessie position

        // algorithm
        int distance = lostcow(x, y);

        //output
        PrintWriter out = new PrintWriter(new FileWriter(problemName + ".out"));
        out.println(distance);
        out.close();
    }
}
