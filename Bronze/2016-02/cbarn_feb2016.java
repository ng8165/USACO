// Circular Barn - USACO Bronze February 2016 (http://www.usaco.org/index.php?page=viewproblem2&cpid=616)

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class L5_cbarn {
    public static int cbarn(int numRooms, int[] roomCapacities) {
        // the plan: brute force - try from all the rooms, find the min distance
        int minDistance = Integer.MAX_VALUE;
        int totalCows = 0;
        for (int i=0; i<numRooms; i++) {
            totalCows += roomCapacities[i];
        }

        for (int startRoom=0; startRoom<numRooms; startRoom++) {
            int currDistance = 0;
            int cowsStayingInRooms = 0;
            int currRoom;

            for (int i=0; i<numRooms; i++) {
                currRoom = (startRoom+i) % numRooms; // update current room

                cowsStayingInRooms += roomCapacities[currRoom];
                currDistance += (totalCows - cowsStayingInRooms);
            }

            minDistance = Math.min(minDistance, currDistance);
        }

        return minDistance;
    }

    public static void main(String[] args) throws IOException {
        // input
        String problemName = "cbarn";
        Scanner sc = new Scanner(new File(problemName + ".in"));
        int numRooms = sc.nextInt();
        int[] roomCapacities = new int[numRooms];
        for (int i=0; i<numRooms; i++) {
            roomCapacities[i] = sc.nextInt();
        }
        /*
        System.out.println(numRooms);
        for (int i=0; i<numRooms; i++) {
            System.out.print(roomCapacities[i] + " ");
        }
        */

        // algorithm
        int distance = cbarn(numRooms, roomCapacities);

        //output
        PrintWriter out = new PrintWriter(new FileWriter(problemName + ".out"));
        out.println(distance);
        out.close();
    }
}
