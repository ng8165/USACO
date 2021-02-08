// Ski Course Design - USACO Bronze January 2014 (http://www.usaco.org/index.php?page=viewproblem2&cpid=376)

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class L5_skidesign {
    public static int skidesign(int numHills, int[] hillHeights) {
        int minCost = Integer.MAX_VALUE;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i=0; i<numHills; i++) {
            min = Math.min(min, hillHeights[i]);
            max = Math.max(max, hillHeights[i]);
        }

        // looping through the max hill heights and comparing the costs that change
        for (int i=min+17; i<=max; i++) {
            int currCost = 0;
            // there is a range as we change the highest hill height: from i-17 --> i
            int maxRange = i;
            int minRange = i-17;
            //System.out.println(minRange + " " + maxRange);

            for (int j=0; j<numHills; j++) {
                if (hillHeights[j] < minRange) { // if the hill height is too short, then we make it taller
                    int extraHeight = minRange - hillHeights[j];
                    currCost += (extraHeight * extraHeight);
                    //System.out.println("Extra Height: " + extraHeight + " (from " + minRange + " to " + hillHeights[j] + "), the current minCost for the hill is $" + currCost);
                } else if (hillHeights[j] > maxRange) { // if the hill height is too tall, then we make it shorter
                    int extraHeight = hillHeights[j] - maxRange;
                    currCost += (extraHeight * extraHeight);
                    //System.out.println("Extra Height: " + extraHeight + " (from " + hillHeights[j] + " to " + maxRange + "), the current minCost for the hill is $" + currCost);
                }
            }

            minCost = Math.min(minCost, currCost); // is the minCost for this max smaller than the current minCost?
        }

        return minCost;
    }

    public static void main(String[] args) throws IOException {
        // input
        String problemName = "skidesign";
        Scanner sc = new Scanner(new File(problemName + ".in"));
        int numHills = sc.nextInt();
        int[] hillHeights = new int[numHills];
        for (int i=0; i<numHills; i++) {
            hillHeights[i] = sc.nextInt();
        }
        /*
        System.out.println(numHills);
        for (int i=0; i<numHills; i++) {
            System.out.print(hillHeights[i] + " ");
        }
        */

        // algorithm
        int cost = skidesign(numHills, hillHeights);

        //output
        PrintWriter out = new PrintWriter(new FileWriter(problemName + ".out"));
        out.println(cost);
        out.close();
    }
}
