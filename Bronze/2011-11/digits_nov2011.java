// Awkward Digits - USACO Bronze November 2011 (http://www.usaco.org/index.php?page=viewproblem2&cpid=85)

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class L13_digits {
    public static int digits(String binaryString, String base3String) {
        char[] binary = binaryString.toCharArray();
        char[] base3 = base3String.toCharArray();

        // find all binary combos
        char[][] binaryCombos = new char[binary.length][binary.length];

        for (int i=0; i<binary.length; i++) {
            binaryCombos[i] = binary.clone();

            if (binaryCombos[i][i] == '0') {
                binaryCombos[i][i] = '1';
            } else {
                binaryCombos[i][i] = '0';
            }
        }

        // find all base 3 combos
        char[][] base3Combos = new char[base3.length*2][base3.length];
        int nextAvailable = 0;

        for (int i=0; i<base3.length*2; i+=2) {
            for (int j=0; j<=2; j++) {
                char digit = (char)(j+'0');
                int base3Index = i/2;

                if (digit == base3[base3Index]) {
                    continue;
                }

                base3Combos[nextAvailable] = base3.clone();
                base3Combos[nextAvailable][base3Index] = digit;
                nextAvailable++;
            }
        }

        // convert all possible combos (binary and base 3) into decimal
        int[] decimalBinaryCombos = new int[binaryCombos.length];
        int[] decimalBase3Combos = new int[base3Combos.length];

        for (int i=0; i<decimalBinaryCombos.length; i++) {
            decimalBinaryCombos[i] = binaryToDecimal(new String(binaryCombos[i]));
            nextAvailable++;
        }

        for (int i=0; i<decimalBase3Combos.length; i++) {
            decimalBase3Combos[i] = base3ToDecimal(new String(base3Combos[i]));
            nextAvailable++;
        }

        // check for any decimal values that are the same
        for (int decimalBinary: decimalBinaryCombos) {
            for (int decimalBase3: decimalBase3Combos) {
                if (decimalBinary == decimalBase3) {
                    return decimalBinary;
                }
            }
        }

        return 0; // just so program runs without compile error, this will never happen
    }
    public static int binaryToDecimal(String binary) {
        int length = binary.length();
        int lastValue = binary.charAt(length-1) - '0';

        if (length == 1) {
            return lastValue;
        } else {
            return binaryToDecimal(binary.substring(0, length-1))*2+lastValue;
        }
    }
    public static int base3ToDecimal(String base3) {
        int length = base3.length();
        int lastValue = base3.charAt(length-1) - '0';

        if (length == 1) {
            return lastValue;
        } else {
            return base3ToDecimal(base3.substring(0, length-1))*3+lastValue;
        }
    }

    public static void solve(String inFile, String outFile) throws IOException {
        // input
        Scanner sc = new Scanner(new File(inFile));

        String binary = sc.next();
        String base3 = sc.next();

        // algorithm
        int decimal = digits(binary, base3);

        // output
        PrintWriter out = new PrintWriter(new FileWriter(outFile));
        out.println(decimal);
        out.close();
    }

    public static void main(String[] args) throws IOException {
        int passedNum = 0;
        int failedNum = 0;

        for (int i=1; i<=10; i++) {
            long beginTime = System.currentTimeMillis();
            solve(("digits/I." + i), ("digits/MO." + i));
            long endTime = System.currentTimeMillis();

            Scanner sc_my = new Scanner(new File("digits/MO." + i));
            Scanner sc_out = new Scanner(new File("digits/O." + i));

            int myOutput = sc_my.nextInt();
            int correctOutput = sc_out.nextInt();

            if (myOutput == correctOutput) {
                System.out.println("#" + i + ": Passed in " + (endTime-beginTime) + " ms (My Output: " + myOutput + ", Correct Output: " + correctOutput + ")");
                passedNum++;
            } else {
                System.out.println("#" + i + ": Failed in " + (endTime-beginTime) + " ms (My Output: " + myOutput + ", Correct Output: " + correctOutput + ")");
                failedNum++;
            }
        }

        System.out.println("\n" + passedNum + " cases passed, " + failedNum + " cases failed");
    }
}
