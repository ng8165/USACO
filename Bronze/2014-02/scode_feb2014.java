// Secret Code - USACO Bronze February 2014 (http://www.usaco.org/index.php?page=viewproblem2&cpid=396)
// This problem was completed as classwork for the USACO Silver 1 Class.

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class L3_scode {
    public static int scode(String encrypted) {
        int decryptionMethods = 0;

        if (encrypted.length() % 2 == 0) {
            return 0;
        }

        // division type 1: left longer than right
        String left = encrypted.substring(0, encrypted.length()/2+1);
        String right = encrypted.substring(encrypted.length()/2+1);
        if (left.startsWith(right)) {
            decryptionMethods++;
            decryptionMethods += scode(left);
        }
        if (left.endsWith(right)) {
            decryptionMethods++;
            decryptionMethods += scode(left);
        }

        // division type 2: right longer than left
        left = encrypted.substring(0, encrypted.length()/2);
        right = encrypted.substring(encrypted.length()/2);
        if (right.startsWith(left)) {
            decryptionMethods++;
            decryptionMethods += scode(right);
        }
        if (right.endsWith(left)) {
            decryptionMethods++;
            decryptionMethods += scode(right);
        }

        return decryptionMethods;
    }

    public static void main(String[] args) throws IOException {
        // input
        String problemName = "scode";
        Scanner sc = new Scanner(new File(problemName + ".in"));

        String encrypted = sc.next();

        // algorithm
        int decryptionMethods = scode(encrypted);

        // output
        PrintWriter out = new PrintWriter(new FileWriter(problemName + ".out"));
        out.println(decryptionMethods);
        out.close();
    }
}
