// Milk Factory - USACO Bronze US Open 2019 (http://www.usaco.org/index.php?page=viewproblem2&cpid=940)
// This problem was completed on November 8, 2020, in 37 minutes, with all 10/10 test cases passed (first try)

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class factory_open2019 {
    static int numStations;
    static int[][] conveyorBelts;

    public static int factory() {
        for (int i=1; i<=numStations; i++) {
            boolean connected = true;

            for (int j=1; j<=numStations; j++) {
                if (j == i) {
                    continue;
                }

                if (!isConnected(j, i)) {
                    connected = false;
                    break;
                }
            }

            if (connected) {
                return i;
            }
        }

        return -1;
    }

    public static boolean isConnected(int from, int to) {
        while (true) {
            boolean shouldContinue = false;

            for (int i=0; i<numStations-1; i++) {
                if (conveyorBelts[i][0] == from) {
                    from = conveyorBelts[i][1];
                    shouldContinue = true;
                    break;
                }
            }

            if (from == to) {
                return true;
            }
            if (!shouldContinue) {
                return false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        // input
        String problemName = "factory";
        Scanner sc = new Scanner(new File(problemName + ".in"));

        numStations = sc.nextInt();
        conveyorBelts = new int[numStations-1][2];
        for (int i=0; i<numStations-1; i++) {
            conveyorBelts[i][0] = sc.nextInt();
            conveyorBelts[i][1] = sc.nextInt();
        }

        // algorithm
        int station = factory();

        // output
        PrintWriter out = new PrintWriter(new FileWriter(problemName + ".out"));
        out.println(station);
        out.close();
    }
}
