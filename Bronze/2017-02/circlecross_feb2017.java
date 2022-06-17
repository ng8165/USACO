// Why Did the Cow Cross the Road II - USACO Bronze February 2017 (http://www.usaco.org/index.php?page=viewproblem2&cpid=712)
// This problem was done on Sunday, September 6, 2020, in 43 minutes, with all 10 test cases passed (second try)

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class circlecross_feb2017 {
    public static int circlecross(char[] crossingPoints) {
        int totalCrossings = crossingPoints.length;
        int numCrossings = 0;
        
        for (int i=0; i<totalCrossings; i++) {
            char currCow = crossingPoints[i];
            
            // find start and end index for the current cow range
            int startIndex = i+1;
            int endIndex = -1;
            
            for (int j=startIndex; j<totalCrossings; j++) {
                if (crossingPoints[j] == currCow) {
                    endIndex = j;
                    break;
                }
            }
            
            // if cow was not found
            if (endIndex == -1) {
                continue;
            }

            // now that we have identified start and end index, we can start looking for crossings
            for (int j=startIndex; j<endIndex; j++) {
                int numCurrCrossingPoints = 0;

                // find number of crossing points for current cow
                for (int k=startIndex; k<endIndex; k++) {
                    if (crossingPoints[k] == crossingPoints[j]) {
                        numCurrCrossingPoints++;
                    }
                }

                // if only one crossing point then the two cows cross!
                if (numCurrCrossingPoints == 1) {
                    numCrossings++;
                }
            }
        }

        return numCrossings/2; // we double-counted (A-B and B-A) so we need to divide by 2
    }
    public static void main(String[] args) throws IOException {
        // input
        String problemName = "circlecross";
        Scanner sc = new Scanner(new File(problemName + ".in"));

        char[] crossingPoints = sc.next().toCharArray();

        // algorithm
        int numCrossings = circlecross(crossingPoints);

        // output
        PrintWriter out = new PrintWriter(new FileWriter(problemName + ".out"));
        out.println(numCrossings);
        out.close();
    }
}

