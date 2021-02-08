// Auto-Complete - USACO Bronze February 2014 (http://www.usaco.org/index.php?page=viewproblem2&cpid=395)

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class L10_auto {
    public static ArrayList<Integer> auto(String[] words, String[] partialWords, int[] lookupIndexes, int numWords, int numPartialWords) {
        ArrayList<Integer> outputLines = new ArrayList<>();

        Arrays.sort(words);

        for (int i=0; i<numPartialWords; i++) {
            String partialWord = partialWords[i];

            ArrayList<String> startWithPartialWord = new ArrayList<>();
            for (int j=0; j<numWords; j++) {
                if (words[j].startsWith(partialWord)) {
                    startWithPartialWord.add(words[j]);
                }
            }

            if (lookupIndexes[i] > startWithPartialWord.size() || startWithPartialWord.size() == 0) {
                outputLines.add(-1);
                continue;
            }

            Collections.sort(startWithPartialWord);

            String requestedWord = startWithPartialWord.get(lookupIndexes[i]-1);

            for (int j=0; j<numWords; j++) {
                if (words[j].equals(requestedWord)) {
                    outputLines.add(j+1);
                    break;
                }
            }
        }

        return outputLines;
    }

    public static void main(String[] args) throws IOException {
        // input
        String problemName = "auto";
        Scanner sc = new Scanner(new File(problemName + ".in"));
        int numWords = sc.nextInt();
        int numPartialWords = sc.nextInt();
        String[] words = new String[numWords];
        String[] partialWords = new String[numPartialWords];
        int[] lookupIndexes = new int[numPartialWords];
        for (int i=0; i<numWords; i++) {
            words[i] = sc.next();
        }
        for (int i=0; i<numPartialWords; i++) {
            lookupIndexes[i] = sc.nextInt();
            partialWords[i] = sc.next();
        }

        // algorithm
        ArrayList<Integer> outputLines = auto(words, partialWords, lookupIndexes, numWords, numPartialWords);

        // output
        PrintWriter out = new PrintWriter(new FileWriter(problemName + ".out"));
        for (int i=0; i<numPartialWords; i++) {
            out.println(outputLines.get(i));
        }
        out.close();
    }
}
