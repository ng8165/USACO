// Cow Baseball - USACO Bronze December 2013 (http://www.usaco.org/index.php?page=viewproblem2&cpid=359)

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Arrays;

public class L6_baseball {
    public static int baseball(int numCows, int[] cowPos) {
        int numTriplets = 0;

        Arrays.sort(cowPos);
        /*
        for (int i=0; i<numCows; i++) {
            System.out.print(cowPos[i] + " ");
        }
        */

        for (int X=0; X<numCows-2; X++) { // index of the X position (from first cow to third to last cow)
            for (int Y=X+1; Y<numCows-1; Y++) { // index of the Y position (from after the X cow to second to last cow)
                for (int Z=Y+1; Z<numCows; Z++) { // index of the Z position (from after the Y cow to last cow)
                    // see if last cow falls into parameters for what john saw

                    if ((cowPos[Z] - cowPos[Y]) >= (cowPos[Y] - cowPos[X]) && (cowPos[Z] - cowPos[Y]) <= 2*(cowPos[Y] - cowPos[X])) {
                        //System.out.println(cowPos[X] + "-" + cowPos[Y] + "-" + cowPos[Z]);
                        numTriplets++;
                    }
                }
            }
        }

        return numTriplets;
    }

    public static void main(String[] args) throws IOException {
        // input
        String problemName = "baseball";
        Scanner sc = new Scanner(new File(problemName + ".in"));
        int numCows = sc.nextInt();
        int[] cowPos = new int[numCows];
        for (int i=0; i<numCows; i++) {
            cowPos[i] = sc.nextInt();
        }
        /*
        System.out.println(numCows);
        for (int i=0; i<numCows; i++) {
            System.out.print(cowPos[i] + " ");
        }
        */

        // algorithm
        int numTriplets = baseball(numCows, cowPos);

        //output
        PrintWriter out = new PrintWriter(new FileWriter(problemName + ".out"));
        out.println(numTriplets);
        out.close();
    }

}
