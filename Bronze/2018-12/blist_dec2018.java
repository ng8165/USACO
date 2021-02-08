// The Bucket List - USACO Bronze December 2018 (http://www.usaco.org/index.php?page=viewproblem2&cpid=856)

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class L11_blist {
    public static int blist(int[][] milkingSchedule, int numCows) {
        // place all of the time events on a line
        int[] timeEvents = new int[numCows*2];

        for (int i=0, cnt=0; i<numCows; i++) {
            for (int j=0; j<2; j++, cnt++) {
                timeEvents[cnt] = milkingSchedule[i][j];
            }
        }

        Arrays.sort(timeEvents);

        // now we begin the actual bucket determining process
        ArrayList<Integer> available = new ArrayList<>();
        ArrayList<Integer> unavailable = new ArrayList<>();

        for (int i=0; i<numCows*2; i++) {
            // we go through the time events and identify what the current time event is (starting or ending position?)
            boolean starting = false;
            boolean found = false;
            int bucketsNeeded = 0;

            for (int j=0; j<numCows; j++) {
                for (int k=0; k<2; k++) {
                    if (milkingSchedule[j][k] == timeEvents[i]) {
                        if (k == 0) {
                            starting = true;
                            found = true;
                            bucketsNeeded = milkingSchedule[j][2];
                            break;
                        } else {
                            bucketsNeeded = milkingSchedule[j][2];
                            found = true;
                            break;
                        }
                    }
                }

                if (found) {
                    break;
                }
            }

            if (starting) {
                if (available.size() == 0) {
                    // if there are no buckets available for the cows then we must get some extras
                    for (int j=0; j<bucketsNeeded; j++) {
                        // it can be any number, that number just says how many buckets there are
                        unavailable.add(0);
                    }
                } else {
                    // there is a bucket for the cow to use, but it may not be enough
                    if ((available.size() - bucketsNeeded) < 0) {
                        // we need some extra buckets, so remove available buckets to unavailable, then add more buckets to unavailable
                        int removeNum = available.size();
                        for (int j=0; j<removeNum; j++) {
                            available.remove(0);
                            unavailable.add(0);
                        }
                        for (int j=0; j<(bucketsNeeded - removeNum); j++) {
                            unavailable.add(0);
                        }
                    } else {
                        // there are enough buckets, FJ doesn't need to get more
                        for (int j=0; j<bucketsNeeded; j++) {
                            available.remove(0);
                            unavailable.add(0);
                        }
                    }
                }
            } else {
                for (int j=0; j<bucketsNeeded; j++) {
                    unavailable.remove(0);
                    available.add(0);
                }
            }
        }

        return available.size();
    }

    public static void main(String[] args) throws IOException {
        // input
        String problemName = "blist";
        Scanner sc = new Scanner(new File(problemName + ".in"));
        int numCows = sc.nextInt();
        int[][] milkingSchedule = new int[numCows][3];
        for (int i=0; i<numCows; i++) {
            for (int j=0; j<3; j++) {
                milkingSchedule[i][j] = sc.nextInt();
            }
        }

        // algorithm
        int numBuckets = blist(milkingSchedule, numCows);

        // output
        PrintWriter out = new PrintWriter(new FileWriter(problemName + ".out"));
        out.println(numBuckets);
        out.close();
    }
}
