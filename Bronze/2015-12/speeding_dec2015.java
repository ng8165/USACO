// Speeding Ticket - USACO Bronze December 2015 (http://www.usaco.org/index.php?page=viewproblem2&cpid=568)

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class L12_speeding {
    public static int speeding(int[][] road, int[][] bessie) {
        int bessieIndex = 0;
        int maxSpeeding = 0;

        for (int i=0; i<road.length; i++) {
            int distanceElapsed = 0;
            int currRoadDist = road[i][0];
            int currRoadSpeedLimit = road[i][1];

            while (distanceElapsed < currRoadDist) {
                int bessieEventDist = bessie[bessieIndex][0];
                int bessieEventSpeed = bessie[bessieIndex][1];
                int distanceRemaining = currRoadDist - distanceElapsed;

                if (bessieEventDist <= distanceRemaining) {
                    distanceElapsed += bessieEventDist;
                    bessieIndex++;
                    maxSpeeding = Math.max(maxSpeeding, (bessieEventSpeed - currRoadSpeedLimit));
                } else {
                    bessie[bessieIndex][0] = bessieEventDist - currRoadDist + distanceElapsed;
                    maxSpeeding = Math.max(maxSpeeding, (bessieEventSpeed - currRoadSpeedLimit));
                    break;
                }
            }
        }
        return maxSpeeding;
    }

    public static void main(String[] args) throws IOException {
        // input
        String problemName = "speeding";
        Scanner sc = new Scanner(new File(problemName + ".in"));

        int numRoadSegments = sc.nextInt();
        int numBessieSegments = sc.nextInt();
        int[][] road = new int[numRoadSegments][2];
        int[][] bessie = new int[numBessieSegments][2];
        for (int i=0; i<numRoadSegments; i++) {
            for (int j=0; j<2; j++) {
                road[i][j] = sc.nextInt();
            }
        }
        for (int i=0; i<numBessieSegments; i++) {
            for (int j=0; j<2; j++) {
                bessie[i][j] = sc.nextInt();
            }
        }

        // algorithm
        int maxSpeeding = speeding(road, bessie);

        // output
        PrintWriter out = new PrintWriter(new FileWriter(problemName + ".out"));
        out.println(maxSpeeding);
        out.close();
    }
}
