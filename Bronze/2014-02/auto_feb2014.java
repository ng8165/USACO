// Auto-Complete - USACO Bronze February 2014 (http://www.usaco.org/index.php?page=viewproblem2&cpid=395)
// This problem was finished on 1/30/21 as homework for the USACO Silver 1 Class.

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class L1_auto {
    static int numDictEntries;
    static int numRequests;
    static DictEntry[] dictionary;

    static class DictEntry implements Comparable<DictEntry> {
        String word;
        int idx;

        DictEntry(String word, int idx) {
            this.word = word;
            this.idx = idx;
        }

        public int compareTo(DictEntry another) {
            return word.compareTo(another.word);
        }
    }

    public static void main(String[] args) throws IOException {
        // input
        String problemName = "auto";
        Scanner sc = new Scanner(new File(problemName + ".in"));

        numDictEntries = sc.nextInt();
        numRequests = sc.nextInt();
        dictionary = new DictEntry[numDictEntries];
        for (int i=0; i<numDictEntries; i++) {
            dictionary[i] = new DictEntry(sc.next(), (i+1));
        }

        // algorithm and output
        PrintWriter out = new PrintWriter(new FileWriter(problemName + ".out"));

        Arrays.sort(dictionary);

        for (int i=0; i<numRequests; i++) {
            int idx = sc.nextInt();
            String partialWord = sc.next();

            int output = -1;

            for (int j=0; j<numDictEntries; j++) {
                if (dictionary[j].word.startsWith(partialWord)) {
                    int newIdx = j+idx-1;

                    if (newIdx < numDictEntries && dictionary[newIdx].word.startsWith(partialWord)) {
                        output = dictionary[newIdx].idx;
                    }

                    break;
                }
            }

            out.println(output);
        }

        out.close();
    }
}
