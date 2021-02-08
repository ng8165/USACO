// Farmer John has no Large Brown Cow - USACO Bronze November 2013 (http://www.usaco.org/index.php?page=viewproblem2&cpid=342)

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class L10_nocow {
    public static String nocow(ArrayList<String>[] adjGroups, String[] missingCows, int outputCowIndex) {
        ArrayList<String> cows;

        cows = generateTotalCows(adjGroups[0], adjGroups[1]);

        for (int i=2; i<adjGroups.length; i++) {
            ArrayList<String> tempCows = generateTotalCows(cows, adjGroups[i]);
            cows = tempCows;
        }

        for (int i=0; i<missingCows.length; i++) {
            for (int j=0; j<cows.size(); j++) {
                if (missingCows[i].equals(cows.get(j))) {
                    cows.remove(j);
                    break;
                }
            }
        }

        return cows.get(outputCowIndex-1);
    }
    public static ArrayList<String> generateTotalCows(ArrayList<String> adjGroup1, ArrayList<String> adjGroup2) {
        ArrayList<String> cows = new ArrayList<>();

        for (String adj1: adjGroup1) {
            for (String adj2: adjGroup2) {
                cows.add(adj1 + " " + adj2);
            }
        }

        return cows;
    }

    public static void main(String[] args) throws IOException {
        // input
        String problemName = "nocow";
        Scanner sc = new Scanner(new File(problemName + ".in"));
        int numMissingCows = sc.nextInt();
        int outputCowIndex = sc.nextInt();
        sc.nextLine();

        // find num of adjectives
        String[] sentence = sc.nextLine().split(" ");
        int numAdjGroups = sentence.length-5;

        // initialize an array of arraylists
        ArrayList<String>[] adjGroups = new ArrayList[numAdjGroups];
        for (int i=0; i<numAdjGroups; i++) {
            adjGroups[i] = new ArrayList<>();
        }

        // initialize a missingCows array
        String[] missingCows = new String[numMissingCows];
        for (int i=0; i<numMissingCows; i++) {
            missingCows[i] = "";
        }

        // add the already scanned sentence into the array of arraylists
        for (int i=4, cnt=0; i<sentence.length-1; i++, cnt++) {
            adjGroups[cnt].add(sentence[i]);

            if (i == sentence.length-2) {
                missingCows[0] += (sentence[i]);
            } else {
                missingCows[0] += (sentence[i] + " ");
            }
        }

        // scan the rest of the sentences into the array of arraylists
        for (int i=1; i<numMissingCows; i++) {
            sentence = sc.nextLine().split(" ");

            for (int j=4, cnt=0; j<sentence.length-1; j++, cnt++) {
                // is the adjective already inside
                boolean found = false;
                for (int k=0; k<adjGroups[cnt].size(); k++) {
                    if (sentence[j].equals(adjGroups[cnt].get(k))) {
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    adjGroups[cnt].add(sentence[j]);
                }

                if (j == sentence.length-2) {
                    missingCows[i] += (sentence[j]);
                } else {
                    missingCows[i] += (sentence[j] + " ");
                }
            }
        }

        // sorting
        Arrays.sort(missingCows);
        for (int i=0; i<numAdjGroups; i++) {
            Collections.sort(adjGroups[i]);
        }

        // algorithm
        String outputcow = nocow(adjGroups, missingCows, outputCowIndex);

        // output
        PrintWriter out = new PrintWriter(new FileWriter(problemName + ".out"));
        out.println(outputcow);
        out.close();
    }
}
