// Angry Cows - USACO Bronze January 2016 (http://www.usaco.org/index.php?page=viewproblem2&cpid=592)
// This problem was completed on November 23, 2020, in 53 minutes, with all 10/10 test cases passed (second try)
// do again

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class angry_jan2016 {
    static int numHayBales;
    static int[] hayBaleLocations;

    public static int angry() {
        Arrays.sort(hayBaleLocations);

        int maxExplodedHayBales = 0;

        for (int i=0; i<numHayBales; i++) {
            int startingHayBaleLoc = hayBaleLocations[i];

            // simulate exploding to the left
            int furthestLeftHay = startingHayBaleLoc;
            int furthestLeftHayIdx = i;

            for (int j=i, radius=1; j>=0; j--, radius++) {
                int oldFurthestLeftHay = furthestLeftHay;
                int explosionRange = furthestLeftHay-radius;

                for (int k=j-1; k>=0; k--) {
                    if (hayBaleLocations[k] < explosionRange) {
                        furthestLeftHay = hayBaleLocations[k+1];
                        furthestLeftHayIdx = k+1;
                        break;
                    } else if (hayBaleLocations[k] == explosionRange) {
                        furthestLeftHay = hayBaleLocations[k];
                        furthestLeftHayIdx = k;
                        break;
                    } else if (k==0 && explosionRange < hayBaleLocations[k]) {
                        furthestLeftHay = hayBaleLocations[k];
                        furthestLeftHayIdx = k;
                        break;
                    }
                }

                if (oldFurthestLeftHay == furthestLeftHay) {
                    break;
                }
            }

            // simulate exploding to the right
            int furthestRightHay = startingHayBaleLoc;
            int furthestRightHayIdx = i;

            for (int j=i, radius=1; j<numHayBales; j++, radius++) {
                int oldFurthestRightHay = furthestRightHay;
                int explosionRange = furthestRightHay+radius;

                for (int k=j+1; k<numHayBales; k++) {
                    if (hayBaleLocations[k] > explosionRange) {
                        furthestRightHay = hayBaleLocations[k-1];
                        furthestRightHayIdx = k-1;
                        break;
                    } else if (hayBaleLocations[k] == explosionRange) {
                        furthestRightHay = hayBaleLocations[k];
                        furthestRightHayIdx = k;
                        break;
                    } else if (k == numHayBales-1 && explosionRange > hayBaleLocations[k]) {
                        furthestRightHay = hayBaleLocations[k];
                        furthestRightHayIdx = k;
                        break;
                    }
                }

                if (oldFurthestRightHay == furthestRightHay) {
                    break;
                }
            }

            int explodedHayBales = furthestRightHayIdx-furthestLeftHayIdx+1;

            System.out.println("Shoot " + hayBaleLocations[i] + " first, explosions reach from " + furthestLeftHay + " to " + furthestRightHay);

            maxExplodedHayBales = Math.max(maxExplodedHayBales, explodedHayBales);
        }

        return maxExplodedHayBales;
    }

    public static int angry2() {
        Arrays.sort(hayBaleLocations);

        int maxExplodedHayBales = 0;

        for (int i=0; i<numHayBales; i++) {
            System.out.println("\nshoot " + i);

            // simulate exploding to the left
            int lBound = i;
            int radius = 1;
            boolean shouldContinue = true;

            while (shouldContinue && lBound > 0) {
               shouldContinue = false;

               int newLBound = findLeftmostIndex(lBound, radius);
               System.out.println("new leftbound: " + newLBound);

                if (newLBound != lBound) {
                   lBound = newLBound;
                   shouldContinue = true;
               }

               radius++;
            }

            // simulate exploding to the right
            int rBound = i;
            radius = 1;
            shouldContinue = true;

            while (shouldContinue && rBound < numHayBales-1) {
                shouldContinue = false;

                int newRBound = findRightmostIndex(rBound, radius);
                System.out.println("new rightbound: " + newRBound);

                if (newRBound != rBound) {
                    rBound = newRBound;
                    shouldContinue = true;
                }

                radius++;
            }

            maxExplodedHayBales = Math.max(maxExplodedHayBales, (rBound-lBound+1));
        }



        return maxExplodedHayBales;
    }
    public static int findLeftmostIndex(int lBound, int radius) {
        int leftmostIndex = lBound;
        int leftSpan = hayBaleLocations[lBound] - radius;

        while (hayBaleLocations[leftmostIndex] > leftSpan && leftmostIndex > 0) {
            if (hayBaleLocations[leftmostIndex-1] >= leftSpan) {
                leftmostIndex--;
            } else {
                break;
            }
        }

        return leftmostIndex;
    }
    public static int findRightmostIndex(int rBound, int radius) {
        int rightmostIndex = rBound;
        int rightSpan = hayBaleLocations[rBound] + radius;

        while (hayBaleLocations[rightmostIndex] < rightSpan && rightmostIndex < numHayBales-1) {
            if (hayBaleLocations[rightmostIndex+1] <= rightSpan) {
                rightmostIndex++;
            } else {
                break;
            }
        }

        return rightmostIndex;
    }

    public static void main(String[] args) throws IOException {
        // input
        String problemName = "angry";
        Scanner sc = new Scanner(new File(problemName + ".in"));

        numHayBales = sc.nextInt();
        hayBaleLocations = new int[numHayBales];
        for (int i=0; i<numHayBales; i++) {
            hayBaleLocations[i] = sc.nextInt();
        }

        // algorithm
        int maxExplodedHayBales = angry2();

        // output
        PrintWriter out = new PrintWriter(new FileWriter(problemName + ".out"));
        out.println(maxExplodedHayBales);
        out.close();
    }
}
