// Bovine Genomics - USACO Bronze US Open 2017 (http://www.usaco.org/index.php?page=viewproblem2&cpid=736)
// This problem was completed as classwork for the USACO Silver 1 Class on 2/25/21.

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
            boolean found = false;

            for (char genome: plain[i]) {
                if (spotty[i].contains(genome)) {
                    found = true;
                    break;
                }
            }

            if (!found) {
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
        plain = new HashSet[numGenomes];
        spotty = new HashSet[numGenomes];
        for (int i=0; i<numCows; i++) {
            char[] genomes = sc.next().toCharArray();
            for (int j=0; j<numGenomes; j++) {
                if (spotty[j] == null) {
                    spotty[j] = new HashSet<>();
                }
                spotty[j].add(genomes[j]);
            }
        }
        for (int i=0; i<numCows; i++) {
            char[] genomes = sc.next().toCharArray();
            for (int j=0; j<numGenomes; j++) {
                if (plain[j] == null) {
                    plain[j] = new HashSet<>();
                }
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
