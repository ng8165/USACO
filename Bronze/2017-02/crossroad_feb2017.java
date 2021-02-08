// Why Did the Cow Cross the Road - USACO Bronze February 2017 (http://www.usaco.org/index.php?page=viewproblem2&cpid=711)

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class L5_crossroad {
    public static int crossroad(int observationNum, int[] cowID, int[] position) {
        int crossings = 0;

        for (int i=0; i<observationNum; i++) {
            for (int j=i+1; j<observationNum; j++) { // find if the cow is moving from i+1 to the end
                if (cowID[j] == cowID[i]) {
                    if (position[i] != position[j]) { // if the cow never moves we don't need to count
                        crossings++;
                    }
                    break;
                }
            }
        }

        return crossings;
    }

    public static void main(String[] args) throws IOException {
        // input
        String problemName = "crossroad";
        Scanner sc = new Scanner(new File(problemName + ".in"));
        int observationNum = sc.nextInt();
        int[] cowID = new int[observationNum];
        int[] position = new int[observationNum];
        for (int i=0; i<observationNum; i++) {
            cowID[i] = sc.nextInt();
            position[i] = sc.nextInt();
        }
        /*
        System.out.println(observationNum + " observations");
        for (int i=0; i<observationNum; i++) {
            System.out.println(cowID[i] + " " + position[i]);
        }
        */

        // algorithm
        int crossings = crossroad(observationNum, cowID, position);

        //output
        PrintWriter out = new PrintWriter(new FileWriter(problemName + ".out"));
        out.println(crossings);
        out.close();
    }

}
