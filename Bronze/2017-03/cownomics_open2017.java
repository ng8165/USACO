// Bovine Genomics - USACO Bronze US Open 2017 (http://www.usaco.org/index.php?page=viewproblem2&cpid=736)
// This problem was completed as classwork for the USACO Silver 1 Class on 2/25/21.

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;

public class L4_cownomics {
    static int numCows;
    static int numGenomes;
    static HashSet<Character>[] spotty;
    static HashSet<Character>[] plain;

    public static int cownomics() {
        int numSpottyPos = 0;

        for (int i=0; i<numGenomes; i++) {
            if (Collections.disjoint(spotty[i], plain[i])) {
                numSpottyPos++;
            }
        }

        return numSpottyPos;
    }

    public static void main(String[] args) throws IOException {
        // input
        String problemName = "cownomics";
        Scanner sc = new Scanner(new File(problemName + ".in"));

        numCows = sc.nextInt();
        numGenomes = sc.nextInt();
        spotty = new HashSet[numGenomes];
        plain = new HashSet[numGenomes];
        for (int i=0; i<numGenomes; i++) {
            spotty[i] = new HashSet<>();
            plain[i] = new HashSet<>();
        }
        for (int i=0; i<numCows; i++) {
            char[] genomes = sc.next().toCharArray();
            for (int j=0; j<numGenomes; j++) {
                spotty[j].add(genomes[j]);
            }
        }
        for (int i=0; i<numCows; i++) {
            char[] genomes = sc.next().toCharArray();
            for (int j=0; j<numGenomes; j++) {
                plain[j].add(genomes[j]);
            }
        }

        // algorithm
        int numSpotPos = cownomics();

        // output
        PrintWriter out = new PrintWriter(new FileWriter(problemName + ".out"));
        out.println(numSpotPos);
        out.close();
    }
}
