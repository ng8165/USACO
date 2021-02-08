// The Cow-Signal - USACO Bronze December 2016 (http://www.usaco.org/index.php?page=viewproblem2&cpid=665)
// This problem was completed on November 20, 2020, in 26 minutes, with all 10/10 test cases passed (second try)

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class cowsignal_dec2016 {
    static int length;
    static int width;
    static int multiplier;
    static char[][] signal;

    public static char[][] cowsignal() {
        char[][] modifiedSignal = new char[length*multiplier][width*multiplier];

        for (int i=0, idx1=0; i<length; i++) {
            char[] modifiedLine = new char[width*multiplier];

            for (int j=0, idx2=0; j<width; j++) {
                for (int k=0; k<multiplier; k++) {
                    modifiedLine[idx2++] = signal[i][j];
                }
            }

            for (int j=0; j<multiplier; j++) {
                modifiedSignal[idx1++] = modifiedLine;
            }
        }

        return modifiedSignal;
    }

    public static void main(String[] args) throws IOException {
        // input
        String problemName = "cowsignal";
        Scanner sc = new Scanner(new File(problemName + ".in"));

        length = sc.nextInt();
        width = sc.nextInt();
        multiplier = sc.nextInt();
        signal = new char[length][width];
        sc.nextLine();
        for (int i=0; i<length; i++) {
            signal[i] = sc.nextLine().toCharArray();
        }

        // algorithm
        char[][] modifiedSignal = cowsignal();

        // output
        PrintWriter out = new PrintWriter(new FileWriter(problemName + ".out"));
        for (int i=0; i<length*multiplier; i++) {
            for (int j=0; j<width*multiplier; j++) {
                out.print(modifiedSignal[i][j]);
            }
            out.println();
        }
        out.close();
    }
}
