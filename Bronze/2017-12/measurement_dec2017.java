// Milk Measurement - USACO Bronze December 2017 (http://www.usaco.org/index.php?page=viewproblem2&cpid=761)
// This problem was completed on Sunday, September 20, 2020, in 56 minutes, with 10/10 test cases passed (first try)

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class measurement_dec2017 {
    static int numObservations;
    static int[] days;
    static String[] names;
    static int[] milkChange;

    public static int measurement() {
        int numLeaderboardChanges = 0;

        // first sort the observations by date
        int[] days_sorted = days.clone();
        String[] names_sorted = new String[numObservations];
        int[] milkChange_sorted = new int[numObservations];
        Arrays.sort(days_sorted);

        // match the unsorted data to the sorted date data
        for (int i=0; i<numObservations; i++) {
            int idx = -1;

            for (int j=0; j<numObservations; j++) {
                if (days[j] == days_sorted[i]) {
                    idx = j;
                    break;
                }
            }

            names_sorted[i] = names[idx];
            milkChange_sorted[i] = milkChange[idx];
        }

        // start the simulation: iterate through the observations
        int[] milkAmounts = new int[3]; // idx 0 is Bessie, idx 1 is Elsie, idx 2 is Mildred
        Arrays.fill(milkAmounts, 7);

        ArrayList<String> leaderboard = new ArrayList<>();
        leaderboard.add("Bessie");
        leaderboard.add("Elsie");
        leaderboard.add("Mildred");

        for (int i=0; i<numObservations; i++) {
            // update the milk amount according to the data
            if (names_sorted[i].equals("Bessie")) {
                milkAmounts[0] += milkChange_sorted[i];
            } else if (names_sorted[i].equals("Elsie")) {
                milkAmounts[1] += milkChange_sorted[i];
            } else {
                milkAmounts[2] += milkChange_sorted[i];
            }

            // update the leaderboard
            int newTopMilkAmount = Math.max(milkAmounts[0], Math.max(milkAmounts[1], milkAmounts[2]));
            ArrayList<String> newLeaderboard = new ArrayList<>();
            for (int j=0; j<3; j++) {
                if (milkAmounts[j] == newTopMilkAmount) {
                    if (j==0) {
                        newLeaderboard.add("Bessie");
                    } else if (j==1) {
                        newLeaderboard.add("Elsie");
                    } else {
                        newLeaderboard.add("Mildred");
                    }
                }
            }
            Collections.sort(leaderboard);
            Collections.sort(newLeaderboard);
            if (!leaderboard.equals(newLeaderboard)) {
                numLeaderboardChanges++;
                leaderboard = newLeaderboard;
            }
        }

        return numLeaderboardChanges;
    }

    public static void main(String[] args) throws IOException {
        // input
        String problemName = "measurement";
        Scanner sc = new Scanner(new File(problemName + ".in"));

        numObservations = sc.nextInt();
        days = new int[numObservations];
        names = new String[numObservations];
        milkChange = new int[numObservations];
        for (int i=0; i<numObservations; i++) {
            days[i] = sc.nextInt();
            names[i] = sc.next();
            milkChange[i] = sc.nextInt();
        }

        // algorithm
        int numLeaderboardChanges = measurement();

        // output
        PrintWriter out = new PrintWriter(new FileWriter(problemName + ".out"));
        out.println(numLeaderboardChanges);
        out.close();
    }
}
