// Mad Scientist - USACO Bronze February 2020 (http://www.usaco.org/index.php?page=viewproblem2&cpid=1012)

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class L3_breedflip {
    public static int breedflip(int cowCount, String expectedCows, String receivedCows) {
        int[] diff = new int[cowCount];
        int currPos = 0;
        int flipCount = 0;

        for (int i=0; i<cowCount; i++) {
            if (expectedCows.charAt(i) == receivedCows.charAt(i)) {
                diff[i] = 0;
            } else {
                diff[i] = 1;
            }
        }

        while (currPos < cowCount) {
            if (diff[currPos] == 1) {
                while (true) {
                    int nextValue;
                    if (currPos == cowCount-1) {
                        flipCount++;
                        break;
                    } else {
                        nextValue = diff[currPos+1];
                    }

                    if (nextValue == 0) {
                        flipCount++;
                        break;
                    } else {
                        currPos++;
                    }
                }
            }
            currPos++;
        }

        return flipCount;
    }

    public static void main(String[] args) throws IOException {
        // input
        String problemName = "breedflip";
        Scanner sc = new Scanner(new File(problemName + ".in"));
        int cowNum = sc.nextInt();
        String expectedCows = sc.next();
        String receivedCows = sc.next();

        // algorithm
        int flipCount = breedflip(cowNum, expectedCows, receivedCows);

        //output
        PrintWriter out = new PrintWriter(new FileWriter(problemName + ".out"));
        out.println(flipCount);
        out.close();
    }
}
