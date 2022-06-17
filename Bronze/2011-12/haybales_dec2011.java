// Hay Bales - USACO Bronze December 2011 (http://www.usaco.org/index.php?page=viewproblem2&cpid=94)

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class L8_haybales {
    public static int haybales(int numPiles, int[] haySizes) {
        // find the average
        int average = 0;
        for (int i=0; i<numPiles; i++) {
            average += haySizes[i];
        }
        average /= numPiles;

        int movedHay = 0;

        // the minimum number of bales to move is always whatever the big bales have left over from being subtracted by the average
        for (int i=0; i<numPiles; i++) {
            if (haySizes[i] > average) {
                movedHay += (haySizes[i] - average);
            }
        }

        return movedHay;
    }

    public static void main(String[] args) throws IOException {
        for (int i=1; i<=10; i++) {
            // input
            String testDataName = "haybales/";
            Scanner sc_in = new Scanner(new File(testDataName + "I." + i));
            int numPiles = sc_in.nextInt();
            int[] haySizes = new int[numPiles];
            for (int j=0; j<numPiles; j++) {
                haySizes[j] = sc_in.nextInt();
            }

            // algorithm
            long startTime = System.currentTimeMillis();
            int movedHay = haybales(numPiles, haySizes);
            long endTime = System.currentTimeMillis();
            long totalTime = endTime - startTime;

            // output
            PrintWriter pw_myout = new PrintWriter(new FileWriter(testDataName + "MO." + i));
            pw_myout.println(movedHay);
            pw_myout.close();

            // compare
            Scanner sc_out = new Scanner(new File(testDataName + "O." + i));
            Scanner sc_myout = new Scanner(new File(testDataName + "MO." + i));

            boolean failed = false;

            while (sc_out.hasNextLine()) {
                String outLine = sc_out.nextLine();
                if (!(sc_myout.hasNextLine())) {
                    failed = true;
                    break;
                }
                String myoutLine = sc_myout.nextLine();

                if (!(outLine.equals(myoutLine))) {
                    failed = true;
                    break;
                }

            }

            if (failed || sc_myout.hasNextLine()) {
                System.out.println("Test #" + i + ": Failed in " + totalTime + " ms");
            } else {
                System.out.println("Test #" + i + ": Passed in " + totalTime + " ms");
            }
        }
    }
}
