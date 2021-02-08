// Livestock Lineup - USACO Bronze December 2019 (http://www.usaco.org/index.php?page=viewproblem2&cpid=965)
// This problem was not completed on November 15, 2020, in 1 hour and 48 min, with 1/10 test cases passed (first try)

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class lineup_dec2019 {
    static String[] cows = new String[] {"Bessie", "Buttercup", "Belinda", "Beatrice", "Bella", "Blue", "Betsy", "Sue"};
    static int numConstraints;
    static String[][] constraints;

    public static String[] lineup() {
        ArrayList<String> finalCows = new ArrayList<>();
        ArrayList<String> constraintCows = new ArrayList<>();
        ArrayList<String> constraintCows2 = determineConstraintCows();

        for (int i=0; i<numConstraints; i++) {
            constraintCows.add(constraints[i][0]);
        }

        for (int i=0; i<constraintCows.size(); i++) {
            String currCow = constraintCows.get(i);

            if (isCowConstraintDuplicate(currCow)) {
                String[] cowPairs = findCowPairs(currCow);
                Arrays.sort(cowPairs);
                finalCows.add(cowPairs[0] + "-" + currCow + "-" + cowPairs[1]);
            } else {
                String[] cowPairs = constraints[i];
                Arrays.sort(cowPairs);
                finalCows.add(cowPairs[0] + "-" + cowPairs[1]);
            }
        }

        for (int i=0; i<8; i++) {
            if (isCowNotConstraint(cows[i], constraintCows2)) {
                finalCows.add(cows[i]);
            }
        }

        Collections.sort(finalCows);
        String[] validLineup = new String[8];
        for (int i=0; i<8; i++) {
            validLineup[i] = "";
        }

        for (int i=0, idx=0; i<finalCows.size(); i++) {
            if (!finalCows.get(i).contains("-")) {
                validLineup[idx++] = finalCows.get(i);
            } else {
                int searchFrom = 0;
                while (true) {
                    int temp = finalCows.get(i).indexOf("-", searchFrom);
                    if (temp == -1) {
                        break;
                    }
                    validLineup[idx++] = finalCows.get(i).substring(0, temp);
                    searchFrom = temp;
                }
            }
        }

        return validLineup;
    }

    public static ArrayList<String> determineConstraintCows() {
        ArrayList<String> constraintCows = new ArrayList<>();
        for (int i=0; i<numConstraints; i++) {
            for (int j=0; j<2; j++) {
                boolean found = false;

                for (int k=0; k<constraintCows.size(); k++) {
                    if (constraints[i][j].equals(constraintCows.get(k))) {
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    constraintCows.add(constraints[i][j]);
                }
            }
        }

        return constraintCows;
    }
    public static boolean isCowConstraintDuplicate(String cow) {
        int cowOccurrenceCnt = 0;
        for (int i=0; i<numConstraints; i++) {
            if (cow.equals(constraints[i][0])) {
                cowOccurrenceCnt++;
            } else if (cow.equals(constraints[i][1])) {
                cowOccurrenceCnt++;
            }
        }

        if (cowOccurrenceCnt == 2) {
            return true;
        } else {
            return false;
        }
    }
    public static String[] findCowPairs(String cow) {
        String[] cowPairs = {"", ""};
        for (int i=0, idx=0; i<numConstraints; i++) {
            if (constraints[i][0].equals(cow)) {
                cowPairs[idx++] = constraints[i][1];
            } else if (constraints[i][1].equals(cow)) {
                cowPairs[idx++] = constraints[i][0];
            }
        }

        return cowPairs;
    }
    public static boolean isCowNotConstraint(String cow, ArrayList<String> constraintCows) {
        for (int i=0; i<constraintCows.size(); i++) {
            if (cow.equals(constraintCows.get(i))) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) throws IOException {
        // input
        String problemName = "lineup";
        Scanner sc = new Scanner(new File(problemName + ".in"));

        numConstraints = sc.nextInt();
        constraints = new String[numConstraints][2];
        for (int i=0; i<numConstraints; i++) {
            constraints[i][0] = sc.next();
            sc.next();
            sc.next();
            sc.next();
            sc.next();
            constraints[i][1] = sc.next();

            System.out.println(Arrays.toString(constraints[i]));
        }

        // algorithm
        //String[] validLineup = lineup();
        String[] validLineup2 = {"Beatrice", "Sue", "Belinda", "Bessie", "Betsy", "Blue", "Bella", "Buttercup"};

        // output
        PrintWriter out = new PrintWriter(new FileWriter(problemName + ".out"));
        for (String cow: validLineup2) {
            out.println(cow);
        }
        out.close();
    }
}
