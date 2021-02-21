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

        // base case
        if (encrypted.length() < 2 || encrypted.length() % 2 == 0) {
            return 0;
        }

        // division type 1: left longer than right (long + short)
        String left = encrypted.substring(0, encrypted.length()/2+1);
        String right = encrypted.substring(encrypted.length()/2+1);
        if (left.startsWith(right)) {
            decryptionMethods++; // this decryption method works
            decryptionMethods += scode(left); // possibly to able to decrypt more
        }
        if (left.endsWith(right)) {
            decryptionMethods++;
            decryptionMethods += scode(left);
        }

        // division type 2: right longer than left (short + long)
        left = encrypted.substring(0, encrypted.length()/2);
        right = encrypted.substring(encrypted.length()/2);
        if (right.startsWith(left)) {
            decryptionMethods++; // this decryption method works
            decryptionMethods += scode(right); // possibly to able to decrypt more
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
