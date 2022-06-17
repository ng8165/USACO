// The Cow-Signal - USACO Bronze December 2016 (http://www.usaco.org/index.php?page=viewproblem2&cpid=665)
// This problem was done as practice on Monday, October 5, 2020

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class cowsignal_dec2016 {
    static int rows;
    static int columns;
    static int sizeEnlarge;
    static char[][] signal;

    public static String[] cowsignal() {
        String[] newSignal = new String[rows*sizeEnlarge];
        Arrays.fill(newSignal, "");

        for (int row=0; row<rows; row++) {
            for (int i=0; i<sizeEnlarge; i++) {
                for (int col=0; col<columns; col++) {
                    for (int j=0; j<sizeEnlarge; j++) {
                        newSignal[row*sizeEnlarge+i] += signal[row][col];
                    }
                }
            }
        }

        return newSignal;
    }

    public static void main(String[] args) throws IOException {
        // input
        String problemName = "cowsignal";
        Scanner sc = new Scanner(new File(problemName + ".in"));

        rows = sc.nextInt();
        columns = sc.nextInt();
        sizeEnlarge = sc.nextInt();
        signal = new char[rows][columns];
        sc.nextLine();
        for (int i=0; i<rows; i++) {
            signal[i] = sc.nextLine().toCharArray();
        }

        // algorithm
        String[] newSignal = cowsignal();

        // output
        PrintWriter out = new PrintWriter(new FileWriter(problemName + ".out"));
        for (int i=0; i<rows*sizeEnlarge; i++) {
            out.println(newSignal[i]);
        }
        out.close();
    }
}
