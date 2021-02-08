// The Cow-Signal - USACO Bronze December 2016 (http://www.usaco.org/index.php?page=viewproblem2&cpid=665)

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class L3_cowsignal {
    public static String[] cowsignal (String[] lines, int m, int n, int k) {
        String[] bigSignal = new String[m*k];

        // line repeating
        for (int a=0; a<m; a++) {
            for (int b=0; b<k; b++) {
                String currLine = "";

                // character repeating
                for (int c=0; c<n; c++) {
                    for (int d=0; d<k; d++) {
                        currLine += lines[a].charAt(c);
                    }
                }

                bigSignal[a*k+b] = currLine;
            }
        }
        return bigSignal;
    }
    public static void main(String[] args) throws IOException {
        // input
        String problemName = "cowsignal";
        Scanner sc =  new Scanner(new File(problemName + ".in"));
        int m = sc.nextInt();
        int n = sc.nextInt();
        int k = sc.nextInt();
        String[] lines = new String[m];

        for (int i=0; i<m; i++) {
            lines[i] = sc.next();
        }

        // algorithm
        String[] bigSignal = cowsignal(lines, m, n, k);

        //output
        PrintWriter out = new PrintWriter(new FileWriter(problemName + ".out"));
        for (int i=0; i<m*k; i++) {
            out.println(bigSignal[i]);
        }
        out.close();
    }
}

/*
for (int a=0; a<m; a++)
  for (int b=0; b<k; b++)
    for (int c=0; c<n; c++)
      for (int d=0; d<k; d++)
        System.out.print(signal[i*m+j]);
      System.out.println();
*/ // Pseudocode
