// Load Balancing - USACO Bronze February 2016 (http://www.usaco.org/index.php?page=viewproblem2&cpid=619)

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class L4_balancing {
    public static int balancing(int cowNum, int maxCowLoc, int[] xCoordinates, int[] yCoordinates) {
        int result = cowNum;
        int complexityCnt = 0;

        for (int a=0; a<cowNum; a++) { // a is vertical (x=a)
            for (int b=0; b<cowNum; b++) { // b is horizontal (y=b)
                int max_cnt;
                int cnt_q1 = 0;
                int cnt_q2 = 0;
                int cnt_q3 = 0;
                int cnt_q4 = 0;

                // determine the vertical fence position and horizontal fence position
                int vFencePos = xCoordinates[a] + 1;
                int hFencePos = yCoordinates[b] + 1;
                //System.out.println("vFence: " + vFencePos + ", hFence: " + hFencePos);

                // determine which quadrant each cow is in
                for (int i=0; i<cowNum; i++) {
                    if (xCoordinates[i] > vFencePos) {
                        if (yCoordinates[i] > hFencePos) {
                            cnt_q1++;
                        } else {
                            cnt_q4++;
                        }
                    } else {
                        if (yCoordinates[i] > hFencePos) {
                            cnt_q2++;
                        } else {
                            cnt_q3++;
                        }
                    }
                    complexityCnt++;
                }

                max_cnt = Math.max(Math.max(cnt_q1, cnt_q2), Math.max(cnt_q3, cnt_q4)); // find the largest cnt
                result = Math.min(result, max_cnt); // find the smallest M
                //System.out.println("c1: " + cnt_q1 + ", c2: " + cnt_q2 + ", c3: " + cnt_q3 + ", c4: " + cnt_q4 + ", max: " + max_cnt + "\n");
            }
        }
        //System.out.println("Time Complexity: O(" + complexityCnt + ")");
        return result;
    }

    public static void main(String[] args) throws IOException{
        // input
        String problemName = "balancing";
        String testDataName = "balancing_bronze_feb16/";
        Scanner sc = new Scanner(new File(problemName + ".in"));

        int cowNum = sc.nextInt();
        int maxCowLoc = sc.nextInt();
        int[] xCoordinates = new int[cowNum];
        int[] yCoordinates = new int[cowNum];
        for (int i=0; i<cowNum; i++) {
            xCoordinates[i] = sc.nextInt();
            yCoordinates[i] = sc.nextInt();
        }
        /*
        for (int i=0; i<cowNum; i++) {
            System.out.println("#" + (i+1) + ": " + xCoordinates[i] + " " + yCoordinates[i]);
        }
        System.out.println();
        */

        // algorithm
        for (int i=1; i<=10; i++) {
            PrintWriter out2 = new PrintWriter(new FileWriter(testDataName+ i + ".myout"));
            Scanner sc1 = new Scanner(new File(testDataName + i + ".in"));
            Scanner sc2 = new Scanner(new File(testDataName + i + ".myout"));
            Scanner sc3 = new Scanner(new File(testDataName + i + ".out"));

            int cowNum2 = sc1.nextInt();
            int maxCowLoc2 = sc1.nextInt();
            int[] xCoordinates2 = new int[cowNum2];
            int[] yCoordinates2 = new int[cowNum2];
            for (int j=0; j<cowNum2; j++) {
                xCoordinates2[j] = sc1.nextInt();
                yCoordinates2[j] = sc1.nextInt();
            }

            System.out.print("Test Case #" + i + ": ");

            long startTime = System.currentTimeMillis(); // find runtime
            int output = balancing(cowNum2, maxCowLoc2, xCoordinates2, yCoordinates2);
            long endTime = System.currentTimeMillis();

            out2.print(output); // print the output into a .myout file
            out2.close();

            String result = sc2.next(); // input the outputs from .myout and the .out file into Stirngs
            String correctResult = sc3.next();

            if (result.equals(correctResult)) { // judge if they are equal
                System.out.print("Passed in ");
                System.out.println((endTime - startTime) + " ms");
            } else {
                System.out.print("Failed, took ");
                System.out.print((endTime - startTime) + " ms");
                System.out.println(" (Your Result: " + result + ", Expected Result: " + correctResult+ ")");
            }
        }
        int m = balancing(cowNum, maxCowLoc, xCoordinates, yCoordinates);

        //output
        PrintWriter out = new PrintWriter(new FileWriter(problemName + ".out"));
        out.println(m);
        out.close();
    }
}