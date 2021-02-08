// Contaminated Milk - USACO Bronze December 2015 (http://www.usaco.org/index.php?page=viewproblem2&cpid=569)
// This problem was mostly completed on November 25, 2020, in 57 minutes, with 9/10 test cases passed (first try)

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class badmilk_dec2015 {
    static int numFriends;
    static int numMilks;
    static int numEvents;
    static int numSick;
    static int[][] events;
    static int[][] sick;

    public static int badmilk() {
        ArrayList<Integer>[] potentialBadMilks = new ArrayList[numSick];

        for (int i=0; i<numSick; i++) {
            potentialBadMilks[i] = new ArrayList<>();

            // search for what milk the person drank before getting sick
            for (int j=0; j<numEvents; j++) {
                if (events[j][0] == sick[i][0] && events[j][2] <= sick[i][1]) {
                    potentialBadMilks[i].add(events[j][1]);
                }
            }

            System.out.println("potential: " + potentialBadMilks[i]);
        }

        ArrayList<Integer> badMilks = findCommonMilks2(potentialBadMilks);
        System.out.println("\nbad: " + badMilks);

        int numMedicines = 0;

        for (int badMilk: badMilks) {
            ArrayList<Integer> affectedPeople = new ArrayList<>();

            for (int i=0; i<numEvents; i++) {
                if (badMilk == events[i][1]) {
                    addUniqueValue(events[i][0], affectedPeople);
                }
            }

            numMedicines = Math.max(numMedicines, affectedPeople.size());
        }

        return numMedicines;
    }

    public static ArrayList<Integer> findCommonMilks(ArrayList<Integer>[] potentialBadMilks) {
        // find all unique milks
        ArrayList<Integer> uniqueMilks = new ArrayList<>();

        for (int i=0; i<numSick; i++) {
            for (int potentialBadMilk: potentialBadMilks[i]) {
                addUniqueValue(potentialBadMilk, uniqueMilks);
            }
        }

        // find common milks that all sick people drank
        ArrayList<Integer> badMilks = new ArrayList<>();

        for (int uniqueMilk: uniqueMilks) {
            boolean isUniqueMilk = true;

            for (int i=0; i<numSick; i++) {
                boolean found = false;

                for (int potentialBadMilk: potentialBadMilks[i]) {
                    if (uniqueMilk == potentialBadMilk) {
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    isUniqueMilk = false;
                    break;
                }
            }

            if (isUniqueMilk) {
                badMilks.add(uniqueMilk);
            }
        }

        return badMilks;
    }
    public static ArrayList<Integer> findCommonMilks2(ArrayList<Integer>[] potentialBadMilks) {
        // find the smallest ArrayList
        ArrayList<Integer> smallestBadMilk = new ArrayList<>();
        int minSize = Integer.MAX_VALUE;

        for (int i=0; i<numSick; i++) {
            if (potentialBadMilks[i].size() < minSize) {
                smallestBadMilk = potentialBadMilks[i];
                minSize = potentialBadMilks[i].size();
            }
        }

        // find common milks that all sick people drank
        ArrayList<Integer> badMilks = new ArrayList<>();

        for (int smallBadMilk: smallestBadMilk) {
            boolean found = true;

            for (ArrayList<Integer> potentialBadMilk: potentialBadMilks) {
                if (!potentialBadMilk.contains(smallBadMilk)) {
                    found = false;
                    break;
                }
            }

            if (found) {
                badMilks.add(smallBadMilk);
            }
        }

        return badMilks;
    }

    public static void addUniqueValue(int val, ArrayList<Integer> arrList) {
        for (int arrListInt: arrList) {
            if (arrListInt == val) {
                return;
            }
        }

        arrList.add(val);
    }

    public static void main(String[] args) throws IOException {
        // input
        String problemName = "badmilk";
        Scanner sc = new Scanner(new File(problemName + ".in"));

        numFriends = sc.nextInt();
        numMilks = sc.nextInt();
        numEvents = sc.nextInt();
        numSick = sc.nextInt();
        events = new int[numEvents][3];
        sick = new int[numSick][2];
        for (int i=0; i<numEvents; i++) {
            events[i][0] = sc.nextInt();
            events[i][1] = sc.nextInt();
            events[i][2] = sc.nextInt();
        }
        for (int i=0; i<numSick; i++) {
            sick[i][0] = sc.nextInt();
            sick[i][1] = sc.nextInt();
        }

        // algorithm
        int numMedicines = badmilk();

        // output
        PrintWriter out = new PrintWriter(new FileWriter(problemName + ".out"));
        out.println(numMedicines);
        out.close();
    }
}
