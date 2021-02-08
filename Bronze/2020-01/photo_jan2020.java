// Photoshoot - USACO Bronze January 2020 (http://www.usaco.org/index.php?page=viewproblem2&cpid=988)
// This problem was not completed on November 22, 2020, in 27 minutes, with 1/10 test cases passed (first try)

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class photo_jan2020 {
    static int numCows;
    static int[] bessieOrder;

    public static int[] photo() {
        int[][] potentialOrders = new int[bessieOrder[0]-1][numCows];
        for (int i=1; i<bessieOrder[0]; i++) {
            potentialOrders[i-1][0] = i;
            for (int j=1; j<numCows; j++) {
                potentialOrders[i-1][j] = bessieOrder[j-1] - potentialOrders[i-1][j-1];
            }
        }

        for (int i=0; i<3; i++) {
            System.out.println(Arrays.toString(potentialOrders[i]));
        }

        return potentialOrders[potentialOrders.length-1];
    }

    public static void main(String[] args) throws IOException {
        // input
        String problemName = "photo";
        Scanner sc = new Scanner(new File(problemName + ".in"));

        numCows = sc.nextInt();
        bessieOrder = new int[numCows-1];
        for (int i=0; i<numCows-1; i++) {
            bessieOrder[i] = sc.nextInt();
        }

        // algorithm
        int[] originalOrder = photo();

        // output
        PrintWriter out = new PrintWriter(new FileWriter(problemName + ".out"));
        for (int i=0; i<numCows-1; i++) {
            out.print(originalOrder[i] + " ");
        }
        out.print(originalOrder[originalOrder.length-1]);
        out.close();
    }
}
