import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class milkorder_bronzebooster {
    static int numCows;
    static int numPriority;
    static int numDemanding;
    static int[] priority;
    static int[][] demanding;

    public static int milkorder() {
        int[] order = new int[numCows];

        // place demanding cows into the array first
        for (int i=0; i<numDemanding; i++) {
            order[demanding[i][1]-1] = demanding[i][0];
        }

        boolean is1InPriority = false;
        for (int i=0; i<numPriority; i++) {
            if (priority[i] == 1) {
                is1InPriority = true;
                break;
            }
        }

        if (is1InPriority) {
            // next start placing priority cows
            int priorityPos = 0;
            priorityFor: for (int i=0; i<numPriority; i++) {
                // check to make sure the current priority cow is not also demanding
                for (int j=0; j<numCows; j++) {
                    if (order[j] == priority[i]) {
                        priorityPos = j;
                        continue priorityFor;
                    }
                }

                // find the next empty spot to place the current priority cow
                if (order[priorityPos] == 0) {
                    order[priorityPos] = priority[i];
                } else {
                    while (order[priorityPos] != 0) {
                        priorityPos++;
                    }
                    order[priorityPos] = priority[i];
                }
                priorityPos++;
            }

            // find the location of the 1
            for (int i=0; i<numCows; i++) {
                if (order[i] == 1) {
                    return i+1;
                }
            }
        } else {
            // next start placing priority cows backwards
            int priorityPos = numCows-1;
            priorityFor: for (int i=numPriority-1; i>=0; i--) {
                // check to make sure the current priority cow is not also demanding
                for (int j=0; j<numCows; j++) {
                    if (order[j] == priority[i]) {
                        priorityPos = j;
                        continue priorityFor;
                    }
                }

                // find the next empty spot to place the current priority cow
                if (order[priorityPos] == 0) {
                    order[priorityPos] = priority[i];
                } else {
                    while (order[priorityPos] != 0) {
                        priorityPos--;
                    }
                    order[priorityPos] = priority[i];
                }
                priorityPos--;
            }

            // find the closest empty space, where cow 1 will go
            for (int i=0; i<numCows; i++) {
                if (order[i] == 0) {
                    return i+1;
                }
            }
        }

        return 0; // avoid compilation errors
    }

    public static void main(String[] args) throws IOException {
        // input
        Scanner sc = new Scanner(new File("milkorder.in"));

        numCows = sc.nextInt();
        numPriority = sc.nextInt();
        numDemanding = sc.nextInt();
        priority = new int[numPriority];
        demanding = new int[numDemanding][2];
        for (int i=0; i<numPriority; i++) {
            priority[i] = sc.nextInt();
        }
        for (int i=0; i<numDemanding; i++) {
            demanding[i][0] = sc.nextInt();
            demanding[i][1] = sc.nextInt();
        }

        // algorithm
        int min1Loc = milkorder();

        // output
        PrintWriter out = new PrintWriter(new FileWriter("milkorder.out"));
        out.println(min1Loc);
        out.close();
    }
}
