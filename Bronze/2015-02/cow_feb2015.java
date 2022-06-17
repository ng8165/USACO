// COW - USACO Bronze February 2015 (http://www.usaco.org/index.php?page=viewproblem2&cpid=527)
// This problem was partially completed on November 27, 2020, in 24 minutes, with 6/10 test cases passed (first try)
// This problem was completed on November 28, 2020, during review, with all 10/10 test cases passed (review)
// REMEMBER THIS PROBLEM!!! cow2 solution is from USACO solution: http://www.usaco.org/current/data/sol_cow_bronze.html

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class cow_feb2015 {
    static int length;
    static char[] str;

    public static int cow() {
        int numCOW = 0;

        for (int i=0; i<length; i++) {
            if (str[i] == 'C') {
                for (int j=i+1; j<length; j++) {
                    if (str[j] == 'O') {
                        int numW = 0;

                        for (int k=j+1; k<length; k++) {
                            if (str[k] == 'W') {
                                numW++;
                            }
                        }

                        numCOW += numW;
                    }
                }
            }
        }

        return numCOW;
    }

    public static long cow2() {
        long numC = 0;
        long numCO = 0;
        long numCOW = 0;

        for (char letter: str) {
            if (letter == 'C') {
                numC++;
            } else if (letter == 'O') {
                numCO += numC;
            } else if (letter == 'W') {
                numCOW += numCO;
            }
        }

        return numCOW;
    }


    public static void main(String[] args) throws IOException {
        // input
        String problemName = "cow";
        Scanner sc = new Scanner(new File(problemName + ".in"));

        length = sc.nextInt();
        str = sc.next().toCharArray();

        // algorithm
        long numCOW = cow2();

        // output
        PrintWriter out = new PrintWriter(new FileWriter(problemName + ".out"));
        out.println(numCOW);
        out.close();
    }
}
