// Measuring Traffic - USACO Bronze February 2019 (http://www.usaco.org/index.php?page=viewproblem2&cpid=917)
// This problem was not completed on November 1, 2020, in 1 hour and 14 minutes, with 2/10 test cases passed (first try)

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class traffic_feb2019 {
    static int highwayLength;
    static String[] sensorType;
    static int[][] flowRange;

    public static int[][] traffic() {
        // determine the location of the first "none" and the last "none"
        int firstNone = -1;
        for (int i=0; i<highwayLength; i++) {
            if (sensorType[i].equals("none")) {
                firstNone = i;
                break;
            }
        }

        int lastNone = -1;
        for (int i=highwayLength-1; i>=0; i--) {
            if (sensorType[i].equals("none")) {
                lastNone = i;
                break;
            }
        }

        // are there "on" are "off" from first "none" to last "none"
        boolean onlyNone = true;
        for (int i=firstNone; i<=lastNone; i++) {
            if (!sensorType[i].equals("none")) {
                onlyNone = false;
                break;
            }
        }

        // determine the flow of traffic from the first none to the last none
        int trafficFlowSmall = flowRange[firstNone][0];
        int trafficFlowLarge = flowRange[firstNone][1];

        int beforeMile1RangeSmall = -1;
        int beforeMile1RangeLarge = -1;
        boolean continueSearching = true;

        for (int i=firstNone+1; i<=lastNone; i++) {
            if (continueSearching && !onlyNone && !sensorType[i].equals("none")) {
                beforeMile1RangeSmall = trafficFlowSmall;
                beforeMile1RangeLarge = trafficFlowLarge;
                continueSearching = false;
            }

            if (sensorType[i].equals("none")) {
                trafficFlowSmall = Math.max(trafficFlowSmall, flowRange[i][0]);
                trafficFlowLarge = Math.min(trafficFlowLarge, flowRange[i][1]);
            } else if (sensorType[i].equals("on")) {
                trafficFlowSmall += flowRange[i][0];
                trafficFlowLarge += flowRange[i][1];
            } else {
                trafficFlowSmall -= flowRange[i][1];
                trafficFlowLarge -= flowRange[i][0];
            }

             if (onlyNone) {
                 beforeMile1RangeSmall = trafficFlowSmall;
                 beforeMile1RangeLarge = trafficFlowLarge;
             }
        }

        System.out.println(trafficFlowSmall + " - " + trafficFlowLarge);

        // now work backwards from this to before the first mile
        for (int i=firstNone-1; i>=0; i--) {
            if (sensorType[i].equals("on")) {
                beforeMile1RangeSmall -= flowRange[i][0];
                beforeMile1RangeLarge -= flowRange[i][1];
            } else {
                beforeMile1RangeSmall += flowRange[i][1];
                beforeMile1RangeLarge += flowRange[i][0];
            }
        }

        // work forwards from the last "none" mile to the end
        int afterLastMileRangeSmall = trafficFlowSmall;
        int afterLastMileRangeLarge = trafficFlowLarge;

        for (int i=lastNone+1; i<highwayLength; i++) {
            if (sensorType[i].equals("on")) {
                afterLastMileRangeSmall += flowRange[i][0];
                afterLastMileRangeLarge += flowRange[i][1];
            } else {
                afterLastMileRangeSmall -= flowRange[i][1];
                afterLastMileRangeLarge -= flowRange[i][0];
            }
        }

        return new int[][] {{beforeMile1RangeSmall, beforeMile1RangeLarge}, {afterLastMileRangeSmall, afterLastMileRangeLarge}};
    }

    public static void main(String[] args) throws IOException {
        // input
        String problemName = "traffic";
        Scanner sc = new Scanner(new File(problemName + ".in"));

        highwayLength = sc.nextInt();
        sensorType = new String[highwayLength];
        flowRange = new int[highwayLength][2];
        for (int i=0; i<highwayLength; i++) {
            sensorType[i] = sc.next();
            flowRange[i][0] = sc.nextInt();
            flowRange[i][1] = sc.nextInt();
        }

        // algorithm
        int[][] trafficFlow = traffic();

        // output
        PrintWriter out = new PrintWriter(new FileWriter(problemName + ".out"));
        out.println(trafficFlow[0][0] + " " + trafficFlow[0][1]);
        out.println(trafficFlow[1][0] + " " + trafficFlow[1][1]);
        out.close();
    }
}
