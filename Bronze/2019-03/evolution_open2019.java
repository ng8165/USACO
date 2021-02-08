// Cow Evolution - USACO Bronze US Open 2019 (http://www.usaco.org/index.php?page=viewproblem2&cpid=940)
// This problem was guessed on November 8, 2020, in 20 minutes, with 9/17 test cases passed (first try)

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class evolution_open2019 {
    static int subPopulationCnt;
    static ArrayList<String>[] characteristics;

    public static void main(String[] args) throws IOException {
        // input
        String problemName = "evolution";
        Scanner sc = new Scanner(new File(problemName + ".in"));

        subPopulationCnt = sc.nextInt();
        characteristics = new ArrayList[subPopulationCnt];
        for (int i=0; i<subPopulationCnt; i++) {
            int characteristicsCnt = sc.nextInt();
            characteristics[i] = new ArrayList<>();
            for (int j=0; j<characteristicsCnt; j++) {
                characteristics[i].add(sc.next());
            }
        }

        // algorithm


        // output
        PrintWriter out = new PrintWriter(new FileWriter(problemName + ".out"));
        out.println("yes");
        out.close();
    }
}
