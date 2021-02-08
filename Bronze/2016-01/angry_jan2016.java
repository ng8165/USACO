// Angry Cows - USACO Bronze January 2016 (http://www.usaco.org/index.php?page=viewproblem2&cpid=592)

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class L12_angry {
    public static int angry(int[] hayBaleLocations) {
        int numHayBales = hayBaleLocations.length;
        int maxHayBale = 0;

        Arrays.sort(hayBaleLocations);

        for (int i=0; i<numHayBales; i++) {
            int minIndex = i;
            int maxIndex = i;
            int radius = 1;

            while (true) {
                int newMinIndex = minIndex;
                for (int j = minIndex - 1; j >= 0; j--) {
                    if (hayBaleLocations[minIndex] - hayBaleLocations[j] <= radius) {
                        newMinIndex = j;
                    } else {
                        break;
                    }
                }

                if (minIndex == newMinIndex) {
                    break;
                } else {
                    minIndex = newMinIndex;
                    radius++;
                }
            }

            radius = 1;
            while (true) {
                int newMaxIndex = maxIndex;
                for (int j=maxIndex+1; j<numHayBales; j++) {
                    if (hayBaleLocations[j] - hayBaleLocations[maxIndex] <= radius) {
                        newMaxIndex = j;
                    } else {
                        break;
                    }
                }

                if (maxIndex == newMaxIndex) {
                    break;
                } else {
                    maxIndex = newMaxIndex;
                    radius++;
                }
            }

            maxHayBale = Math.max(maxHayBale, (maxIndex - minIndex + 1));
        }

        return maxHayBale;
    }

    public static void main(String[] args) throws IOException {
        // input
        String problemName = "angry";
        Scanner sc = new Scanner(new File(problemName + ".in"));

        int numHayBales = sc.nextInt();
        int[] hayBaleLocations = new int[numHayBales];
        for (int i=0; i<numHayBales; i++) {
            hayBaleLocations[i] = sc.nextInt();
        }

        // algorithm
        int maxHayBaleNum = angry(hayBaleLocations);

        // output
        PrintWriter out = new PrintWriter(new FileWriter(problemName + ".out"));
        out.println(maxHayBaleNum);
        out.close();
    }
}
