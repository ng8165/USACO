// Circular Barn - USACO Bronze February 2016 (http://www.usaco.org/index.php?page=viewproblem2&cpid=616)
// This problem was completed on November 23, 2020, in 17 minutes, with all 10/10 test cases passed (second try)

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class cbarn_feb2016 {
    static int numRooms;
    static int[] roomSizes;
    static int numCows = 0;

    public static int cbarn() {
        int minWalkingDistance = Integer.MAX_VALUE;

        for (int startingDoorIdx=0; startingDoorIdx<numRooms; startingDoorIdx++) {
            int remainingCows = numCows;
            int walkingDistance = 0;

            for (int i=startingDoorIdx; i<numRooms+startingDoorIdx; i++) {
                int currRoomSize = roomSizes[i%numRooms];
                remainingCows -= currRoomSize;
                walkingDistance += remainingCows;
            }

            minWalkingDistance = Math.min(minWalkingDistance, walkingDistance);
        }

        return minWalkingDistance;
    }

    public static void main(String[] args) throws IOException {
        // input
        String problemName = "cbarn";
        Scanner sc = new Scanner(new File(problemName + ".in"));

        numRooms = sc.nextInt();
        roomSizes = new int[numRooms];
        for (int i=0; i<numRooms; i++) {
            roomSizes[i] = sc.nextInt();
            numCows += roomSizes[i];
        }

        // algorithm
        int minWalkingDistance = cbarn();

        // output
        PrintWriter out = new PrintWriter(new FileWriter(problemName + ".out"));
        out.println(minWalkingDistance);
        out.close();
    }
}
