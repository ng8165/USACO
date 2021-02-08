// Bessie Slows Down - USACO Bronze January 2014 (http://www.usaco.org/index.php?page=viewproblem2&cpid=377)

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;

public class L10_slowdown {
    public static int slowdown(ArrayList<Integer> T_slowdowns, ArrayList<Integer> D_slowdowns) {
        double currTime = 0;
        double currDistance = 0;
        int cnt_speed = 1;
        double currSpeed;

        Collections.sort(T_slowdowns);
        Collections.sort(D_slowdowns);

        while (true) {
            currSpeed = 1.0/cnt_speed; // update speed

            if (T_slowdowns.size() == 0 && D_slowdowns.size() == 0) {
                // exit condition
                currTime += (1000-currDistance)/currSpeed;
                break;
            }

            if (D_slowdowns.size() == 0) {
                // no more D slowdowns so there are T slowdowns
                int stopTime = T_slowdowns.remove(0);
                currDistance += ((stopTime - currTime) * currSpeed);
                currTime = stopTime;
                cnt_speed++;
            } else if (T_slowdowns.size() == 0) {
                // no more T slowdowns so there are D slowdowns
                int stopDistance = D_slowdowns.remove(0);
                currTime += (stopDistance - currDistance)/currSpeed;
                currDistance = stopDistance;
                cnt_speed++;
            } else { // we still have both slowdowns so we must check which one comes first
                int stopTime = T_slowdowns.get(0);
                int stopDistance = D_slowdowns.get(0);

                // calculating travel time and distance and converting both
                double travelTime= stopTime - currTime;
                double travelDistance = stopDistance - currDistance;
                double travelTimeToDistance = travelTime*currSpeed;

                // judgement, check which event comes first
                if (travelTimeToDistance < travelDistance) {
                    // time comes first
                    currTime = T_slowdowns.remove(0);
                    currDistance += travelTimeToDistance;
                    cnt_speed++;
                } else if (travelTimeToDistance == travelDistance) {
                    // comes at same time
                    currTime = T_slowdowns.remove(0);
                    currDistance = D_slowdowns.remove(0);
                    cnt_speed+=2;
                } else {
                    // distance comes first
                    currTime += travelDistance/currSpeed;
                    currDistance = D_slowdowns.remove(0);
                    cnt_speed++;
                }
            }
        }

        // rounding
        int intCurrTime = (int)(currTime);
        return ((currTime - intCurrTime) >= 0.5) ? (intCurrTime+1) : intCurrTime;
    }
    public static void insertInOrder(ArrayList<Integer> slowdowns, int slowdown) {
        boolean added = false;

        for (int j=0; j<slowdowns.size(); j++) {
            if (slowdown < slowdowns.get(j)) {
                slowdowns.add(j, slowdown);
                added = true;
                break;
            }
        }

        if (!added) { // if the event is greater than everything, add to end also
            slowdowns.add(slowdown);
        }
    }

    public static void main(String[] args) throws IOException {
        // input
        String problemName = "slowdown";
        Scanner sc = new Scanner(new File(problemName + ".in"));
        int totalSlowdowns = sc.nextInt();
        ArrayList<Integer> T_slowdowns = new ArrayList<>();
        ArrayList<Integer> D_slowdowns = new ArrayList<>();
        for (int i=0; i<totalSlowdowns; i++) {
            String slowdownType = sc.next();
            if (slowdownType.equals("T")) {
                T_slowdowns.add(sc.nextInt());
                //insertInOrder(T_slowdowns, sc.nextInt());
            } else if (slowdownType.equals("D")) {
                D_slowdowns.add(sc.nextInt());
                //insertInOrder(D_slowdowns, sc.nextInt());
            }
        }

        // algorithm
        int totalTime = slowdown(T_slowdowns, D_slowdowns);

        // output
        PrintWriter out = new PrintWriter(new FileWriter(problemName + ".out"));
        out.println(totalTime);
        out.close();
    }
}