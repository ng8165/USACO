import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class socdist2_bronzebooster {
    static int numCows;
    static int[] cowPosition;
    static boolean[] isSick;

    static boolean[] hasCowBeenInfected;

    public static int socdist2(int[] cowPositionUnsorted, boolean[] isSickUnsorted) {
        // first sort this cow position array
        cowPosition = cowPositionUnsorted.clone();
        Arrays.sort(cowPosition);
        for (int i=0; i<numCows; i++) {
            for (int j=0; j<numCows; j++) {
                if (cowPosition[i] == cowPositionUnsorted[j]) {
                    isSick[i] = isSickUnsorted[j];
                    break;
                }
            }
        }

        // first find the maximum R
        int R = Integer.MAX_VALUE;
        for (int i=0; i<numCows-1; i++) {
            if (isSick[i] && !isSick[i+1] || !isSick[i] && isSick[i+1]) {
                R = Math.min(R, cowPosition[i+1]-cowPosition[i]);
            }
        }
        R--;

        // start counting the number of sick cows
        int minSickCows = 0;
        for (int i=0; i<numCows; i++) {
            if (isSick[i] && !hasCowBeenInfected[i]) {
                // this cow should be sick and has not been infected by another cow, so it must have the infection
                infectCows(i, R);
                minSickCows++;
            }
        }

        return minSickCows;
    }

    public static void infectCows(int cowPos, int R) { // this is DFS (Depth First Search)
        if (hasCowBeenInfected[cowPos]) {
            // this cow has already been infected (DFS stop)
            return;
        }

        // this cow was not infected, but now it is
        hasCowBeenInfected[cowPos] = true;

        // this cow will infect more cows, so find those too
        for (int i=0; i<numCows; i++) {
            if (Math.abs(cowPosition[i]-cowPosition[cowPos]) <= R && !hasCowBeenInfected[i]) {
                infectCows(i, R); // recursion
            }
        }
    }

    public static void main(String[] args) throws IOException {
        // input
        Scanner sc = new Scanner(new File("socdist2.in"));

        numCows = sc.nextInt();
        int[] cowPositionUnsorted = new int[numCows];
        cowPosition = new int[numCows];
        boolean[] isSickUnsorted = new boolean[numCows];
        isSick = new boolean[numCows];
        hasCowBeenInfected = new boolean[numCows];
        for (int i=0; i<numCows; i++) {
            cowPositionUnsorted[i] = sc.nextInt();

            if (sc.nextInt() == 1) {
                isSickUnsorted[i] = true;
            }
        }

        // algorithm
        int minSickCows = socdist2(cowPositionUnsorted, isSickUnsorted);

        // output
        PrintWriter out = new PrintWriter(new FileWriter("socdist2.out"));
        out.println(minSickCows);
        out.close();
    }
}
