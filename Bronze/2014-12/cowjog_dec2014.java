// Cow Jog - USACO Bronze December 2014 (http://www.usaco.org/index.php?page=viewproblem2&cpid=489)
// This problem was completed on December 5, 2020, 14 minutes, with all 10/10 test cases passed (first try)

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class cowjog_dec2014 {
    static int numCows;
    static int[] position;
    static int[] speed;

    public static int cowjog() {
        int numGroups = 1;

        for (int i=numCows-1; i>0; i--)  {
            if (speed[i-1] > speed[i]) {
                speed[i-1] = speed[i];
            } else {
                numGroups++;
            }
        }

        return numGroups;
    }

    public static void main(String[] args) throws IOException {
        // input
        String problemName = "cowjog";
        Scanner sc = new Scanner(new File(problemName + ".in"));

        numCows = sc.nextInt();
        position = new int[numCows];
        speed = new int[numCows];
        for (int i=0; i<numCows; i++) {
            position[i] = sc.nextInt();
            speed[i] = sc.nextInt();
        }

        // algorithm
        int numGroups = cowjog();

        // output
        PrintWriter out = new PrintWriter(new FileWriter(problemName + ".out"));
        out.println(numGroups);
        out.close();
    }
}
