// Teleportation - USACO Bronze February 2018 (http://www.usaco.org/index.php?page=viewproblem2&cpid=807)
// This problem was completed on October 4, 2020, in 19 minutes, with all 10/10 test cases passed (second try)

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class teleport_feb2018 {
    static int manureStartPos;
    static int manureEndPos;
    static int teleporterPos1;
    static int teleporterPos2;

    public static int teleporter() {
        // there are three scenarios: don't teleport, teleport 1 to 2, or teleport 2 to 1

        // scenario 1: don't teleport, only tractors
        int tractorDist1 = Math.abs(manureEndPos - manureStartPos);

        // scenario 2: tractor to 1, teleport to 2, then tractor from 2
        int tractorDist2 = Math.abs(teleporterPos1 - manureStartPos) + Math.abs(manureEndPos - teleporterPos2);

        // scenario 3: tractor to 2, teleport to 1, then tractor from 1
        int tractorDist3 = Math.abs(teleporterPos2 - manureStartPos) + Math.abs(manureEndPos - teleporterPos1);

        return Math.min(tractorDist1, Math.min(tractorDist2, tractorDist3));
    }

    public static void main(String[] args) throws IOException {
        // input
        String problemName = "teleport";
        Scanner sc = new Scanner(new File(problemName + ".in"));

        manureStartPos = sc.nextInt();
        manureEndPos = sc.nextInt();
        teleporterPos1 = sc.nextInt();
        teleporterPos2 = sc.nextInt();

        // algorithm
        int tractorDistance = teleporter();

        // output
        PrintWriter out = new PrintWriter(new FileWriter(problemName + ".out"));
        out.println(tractorDistance);
        out.close();
    }
}
