// It's All About the Base - USACO Bronze January 2015 (http://www.usaco.org/index.php?page=viewproblem2&cpid=509)
// This problem was completed with Mom on November 29, 2020, in 1 hour 20 minutes, with all 11/11 test cases passed (first try)

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class whatbase_jan2015 {
    static int numTestCases;
    static int[][] num1;
    static int[][] num2;

    public static int[][] whatbase() {
        int[][] bases = new int[numTestCases][2];

        for (int i=0; i<numTestCases; i++) {
            int base1 = 10;
            int base2 = 10;

            while (true) {
                int val1 = convertToBase10(base1, num1[i]);
                int val2 = convertToBase10(base2, num2[i]);

                if (val1 == val2) {
                    bases[i][0] = base1;
                    bases[i][1] = base2;
                    break;
                } else if (val1 < val2) {
                    base1++;
                } else {
                    base2++;
                }
            }
        }

        return bases;
    }

    public static int convertToBase10(int base, int[] num) {
        int val = num[2];
        val += num[1] * base;
        val += num[0] * base * base;

        return val;
    }

    public static void main(String[] args) throws IOException {
        // input
        String problemName = "whatbase";
        Scanner sc = new Scanner(new File(problemName + ".in"));

        numTestCases = sc.nextInt();
        num1 = new int[numTestCases][3];
        num2 = new int[numTestCases][3];
        for (int i=0; i<numTestCases; i++) {
            char[] temp = sc.next().toCharArray();
            for (int j=0; j<3; j++) {
                num1[i][j] = Character.getNumericValue(temp[j]);
            }

            temp = sc.next().toCharArray();
            for (int j=0; j<3; j++) {
                num2[i][j] = Character.getNumericValue(temp[j]);
            }
        }

        // algorithm
        int[][] powers = whatbase();

        // output
        PrintWriter out = new PrintWriter(new FileWriter(problemName + ".out"));
        for (int i=0; i<numTestCases; i++) {
            out.println(powers[i][0] + " " + powers[i][1]);
        }
        out.close();
    }
}
