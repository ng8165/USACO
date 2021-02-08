// Milking Order - USACO Bronze US Open 2018 (http://www.usaco.org/index.php?page=viewproblem2&cpid=832)
// This problem was partially completed on October 11, 2020, in 1 hour, with 4/10 test cases passed (first try)

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class milkorder_open2018 {
    static int numCows;
    static int hierarchyCnt;
    static int[] hierarchy;
    static int specificCnt;
    static int[][] specific;

    public static int milkorder() {
        int[] milkingOrder = new int[numCows+1];
        // first place the specific position cows into the milking order
        for (int i = 0; i< specificCnt; i++) {
            milkingOrder[specific[i][1]] = specific[i][0];
        }

        System.out.println(Arrays.toString(milkingOrder));

        // now determine where to place the hierarchy cows
        // the tricky situation is when a cow is in both hierarchy and specificPos
        int hPos;
        int spPos;
        for (int i=0; i<hierarchyCnt; i++) {
            for (int j = 0; j< specificCnt; j++) {
                if (hierarchy[i] == specific[j][0]) {
                    hPos = i;
                    spPos = specific[j][1];
                    if (hPos > 0) {
                        // we have to place the higher status cow before this cow
                        for (int k=spPos-1; k>0; k--) {
                            if (milkingOrder[k] == 0) {
                                milkingOrder[k] = hierarchy[hPos-1];
                                System.out.println(Arrays.toString(milkingOrder));
                                break;
                            }
                        }
                    }
                }
            }
        }
        for (int i=1; i<=numCows; i++) {
            if (milkingOrder[i] == 0) {
                return i;
            }
        }

        return 0; // this should never happen, avoid compilation errors

    }

    public static int milkorder2() {
        int[] milkingOrder = new int[numCows+1];

        // first place the specific cows into the milking order
        for (int i=0; i<specificCnt; i++) {
            milkingOrder[specific[i][1]] = specific[i][0];
        }

        System.out.println(Arrays.toString(milkingOrder));

        // try to place the sick cow in closest empty spaces until the milking order is valid
        for (int i=1; i<=numCows; i++) {
            int[] tempMilkingOrder = milkingOrder.clone();

            // place the sick cow
            if (tempMilkingOrder[i] != 0) {
                continue;
            }
            tempMilkingOrder[i] = 1;
            System.out.println("sick cow at " + i + "\n" + Arrays.toString(tempMilkingOrder));

            // start placing hierarchy cows
            boolean areAllCowsPlaced = true;

            for (int j=0; j<hierarchyCnt; j++) {
                int currCow = hierarchy[j];

                int currCowPos = findCow(currCow, tempMilkingOrder);

                if (j==0) {
                    if (currCow != 1) {
                        currCowPos = placeCow(currCow, 1, tempMilkingOrder);
                    }
                    continue;
                }

                int prevCow = hierarchy[j-1];
                int prevCowPos = findCow(prevCow, tempMilkingOrder);

                if (currCowPos == -1) {
                    currCowPos = placeCow(currCow, prevCowPos, tempMilkingOrder);
                }

                if (prevCowPos > currCowPos) {
                    areAllCowsPlaced = false;
                    break;
                }

                System.out.println(hierarchy[j] + ": " + Arrays.toString(tempMilkingOrder));
            } // for (j)

            if (areAllCowsPlaced) {
                return i;
            }
        } // for (i)

        return 0; // this should never happen, avoid compilation errors
    }

    public static int findCow(int currCow, int[] tempMilkingOrder) {
        for (int i=1; i<=numCows; i++) {
            if (currCow == tempMilkingOrder[i]) {
                return i;
            }
        }

        return -1;
    }
    public static int placeCow(int currCow, int startIdx, int[] tempMilkingOrder) {
        for (int i=startIdx; i<=numCows; i++) {
            if (tempMilkingOrder[i] == 0) {
                tempMilkingOrder[i] = currCow;
                return i;
            }
        }

        return 0; // this should never happen, avoid compilation errors
    }
    
    public static void main(String[] args) throws IOException {
        // input
        String problemName = "milkorder";
        Scanner sc = new Scanner(new File(problemName + ".in"));

        numCows = sc.nextInt();
        hierarchyCnt = sc.nextInt();
        specificCnt = sc.nextInt();
        hierarchy = new int[hierarchyCnt];
        specific = new int[specificCnt][2];
        for (int i=0; i<hierarchyCnt; i++) {
            hierarchy[i] = sc.nextInt();
        }
        for (int i=0; i<specificCnt; i++) {
            specific[i][0] = sc.nextInt();
            specific[i][1] = sc.nextInt();
        }

        // algorithm
        int cow1Pos = milkorder2();

        // output
        PrintWriter out = new PrintWriter(new FileWriter(problemName + ".out"));
        out.println(cow1Pos);
        out.close();
    }
}
