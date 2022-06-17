// Triangles - USACO Bronze February 2020 (http://usaco.org/index.php?page=viewproblem2&cpid=1011)

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class L4_triangles {
    public static int triangles (int numFencePoints, int[] xCoordinates, int[] yCoordinates) {
        int twiceMaxArea = 0;

        for (int i=0; i<numFencePoints; i++) { // try all first posts (fp1_x, fp1_y)
            for (int j=i+1; j<numFencePoints; j++) { // try all second posts (fp2_x, fp2_y)
                for (int k=j+1; k<numFencePoints; k++) { // try all third posts (fp3_x, fp3_y)
                    int fp1_x = xCoordinates[i];
                    int fp1_y = yCoordinates[i];
                    int fp2_x = xCoordinates[j];
                    int fp2_y = yCoordinates[j];
                    int fp3_x = xCoordinates[k];
                    int fp3_y = yCoordinates[k];
                    int length;
                    int width;

                    // is one of the sides parallel to the x-axis
                    if (fp1_y == fp2_y) {
                        length = Math.abs(fp2_x-fp1_x);
                    } else if (fp2_y == fp3_y) {
                        length = Math.abs(fp3_x-fp2_x);
                    } else if (fp1_y == fp3_y) {
                        length = Math.abs(fp3_x-fp1_x);
                    } else {
                        continue;
                    }

                    // is one of the sides parallel to the y-axis
                    if (fp1_x == fp2_x) {
                        width = Math.abs(fp2_y-fp1_y);
                    } else if (fp2_x == fp3_x) {
                        width = Math.abs(fp3_y-fp2_y);
                    } else if (fp1_x == fp3_x) {
                        width = Math.abs(fp3_y-fp1_y);
                    } else {
                        continue;
                    }

                    // if so, then we can compare the areas and find the max
                    twiceMaxArea = Math.max(twiceMaxArea, (length*width));
                }
            }
        }

        // Complexity: O(numFencePoints^3)

        return twiceMaxArea;
    }

    public static void main(String[] args) throws IOException {
        // input
        String problemName = "triangles";
        Scanner sc = new Scanner(new File(problemName + ".in"));
        int numFencePoints = sc.nextInt();
        int[] xCoordinates = new int[numFencePoints];
        int[] yCoordinates = new int[numFencePoints];
        for (int i=0; i<numFencePoints; i++) {
            xCoordinates[i] = sc.nextInt();
            yCoordinates[i] = sc.nextInt();
        }

        // algorithm
        int twiceMaxArea = triangles(numFencePoints, xCoordinates, yCoordinates);

        //output
        PrintWriter out = new PrintWriter(new FileWriter(problemName + ".out"));
        out.println(twiceMaxArea);
        out.close();
    }
}
