// Teleportation - USACO Bronze February 2018 (http://www.usaco.org/index.php?page=viewproblem2&cpid=807)

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.io.File;

public class L1_teleport {

    public static int teleport(int startLoc, int endLoc, int teleport1, int teleport2) {
        /*
        3 ways:
        1) John tractors the manure from start to end
        2) John tractors to teleporter 1 then teleports it to teleporter2 and tractors to the end
        3) John tractors to teleporter 1 then teleports it to teleporter2 and tractors to the end
        */

        int[] tractorDistance = new int[3];
        // index 0 is tractoring all the way, index 1 is tractoring to teleporter 1, index 2 is tractoring to teleporter 2
        tractorDistance[0] = Math.abs(endLoc-startLoc);
        tractorDistance[1] = Math.abs(teleport1-startLoc) + Math.abs(endLoc-teleport2);
        tractorDistance[2] = Math.abs(teleport2-startLoc) + Math.abs(endLoc-teleport1);

        int shortestPath=Integer.MAX_VALUE;
        for (int i=0; i<3; i++) {
            if (tractorDistance[i] < shortestPath) {
                shortestPath = tractorDistance[i];
            }
        }

        return shortestPath;
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("teleport.in"));

        int startLoc = sc.nextInt();
        int endLoc = sc.nextInt();
        int teleport1 = sc.nextInt();
        int teleport2 = sc.nextInt();

        int tractorDistance = teleport(startLoc, endLoc, teleport1, teleport2);

        PrintWriter out = new PrintWriter(new FileWriter("teleport.out"));
        out.println(tractorDistance);
        out.close();
    }
}
