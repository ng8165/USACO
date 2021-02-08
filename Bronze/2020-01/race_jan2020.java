// Race - USACO Bronze January 2020 (http://www.usaco.org/index.php?page=viewproblem2&cpid=989)

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class L12_race {
    static int distance;

    public static int race(int endingSpeed) {
        int maxSpeed = 1;
        int minTime = Integer.MAX_VALUE;

        while (true) {
            // generate starting values (speeding up section)
            int startingDistance = maxSpeed*(maxSpeed+1)/2;
            int startingTime = maxSpeed;

            // generate ending values (slowing down section)
            int endingDistance = 0;
            int endingTime = 0;

            if (maxSpeed > endingSpeed) {
                endingTime = maxSpeed-endingSpeed;
                endingDistance = (endingSpeed+(maxSpeed-1))*(maxSpeed-endingSpeed)/2;
            }

            // generate middle values (constant speed section)
            int middleDistance = distance - startingDistance - endingDistance;

            if (middleDistance < 0) {
                break;
            }

            int middleTime;
            if (middleDistance % maxSpeed == 0) {
                middleTime = middleDistance/maxSpeed;
            } else {
                middleTime = (middleDistance/maxSpeed)+1;
            }

            // find totals
            int totalTime = startingTime + middleTime + endingTime;
            minTime = Math.min(minTime, totalTime);
            maxSpeed++;
        }

        return minTime;
    }
    
    public static void main(String[] args) throws IOException {
        // input
        String problemName = "race";
        Scanner sc = new Scanner(new File(problemName + ".in"));

        distance = sc.nextInt();
        int numEndingSpeeds = sc.nextInt();
        int[] endingSpeeds = new int[numEndingSpeeds];
        for (int i=0; i<numEndingSpeeds; i++) {
            endingSpeeds[i] = sc.nextInt();
        }

        // output
        PrintWriter out = new PrintWriter(new FileWriter(problemName + ".out"));
        for (int i=0; i<numEndingSpeeds; i++) {
            out.println(race(endingSpeeds[i]));
        }
        out.close();
    }
}
