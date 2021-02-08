import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class traffic_bronzebooster {
    static int numSensors;
    static String[] sensorType;
    static int[][] sensorRange;

    public static int[][] traffic() {
        int[][] output = new int[2][2];

        boolean start = false;
        int startRange = -1;
        int endRange = -1;

        for (int i=numSensors-1; i>=0; i--) {
            if (!start) {
                if (sensorType[i].equals("none")) {
                    start = true;
                    startRange = sensorRange[i][0];
                    endRange = sensorRange[i][1];
                }

                continue;
            }

            if (sensorType[i].equals("none")) {
                startRange = Math.max(startRange, sensorRange[i][0]);
                endRange = Math.min(endRange, sensorRange[i][1]);
            } else if (sensorType[i].equals("on")) {
                if (startRange - sensorRange[i][1] < 0) {
                    startRange = 0;
                } else {
                    startRange -= sensorRange[i][1];
                }

                if (endRange - sensorRange[i][0] < 0) {
                    endRange = 0;
                } else {
                    endRange -= sensorRange[i][0];
                }
            } else {
                startRange += sensorRange[i][0];
                endRange += sensorRange[i][1];
            }
        }

        output[0][0] = startRange;
        output[0][1] = endRange;

        start = false;
        startRange = -1;
        endRange = -1;

        for (int i=0; i<numSensors; i++) {
            if (!start) {
                if (sensorType[i].equals("none")) {
                    start = true;
                    startRange = sensorRange[i][0];
                    endRange = sensorRange[i][1];
                }

                continue;
            }

            if (sensorType[i].equals("none")) {
                startRange = Math.max(startRange, sensorRange[i][0]);
                endRange = Math.min(endRange, sensorRange[i][1]);
            } else if (sensorType[i].equals("on")) {
                startRange += sensorRange[i][0];
                endRange += sensorRange[i][1];
            } else {
                if (startRange - sensorRange[i][1] < 0) {
                    startRange = 0;
                } else {
                    startRange -= sensorRange[i][1];
                }

                if (endRange - sensorRange[i][0] < 0) {
                    endRange = 0;
                } else {
                    endRange -= sensorRange[i][0];
                }
            }
        }

        output[1][0] = startRange;
        output[1][1] = endRange;

        return output;
    }

    public static void main(String[] args) throws IOException {
        // input
        Scanner sc = new Scanner(new File("traffic.in"));

        numSensors = sc.nextInt();
        sensorType = new String[numSensors];
        sensorRange = new int[numSensors][2];
        for (int i=0; i<numSensors; i++) {
            sensorType[i] = sc.next();
            sensorRange[i][0] = sc.nextInt();
            sensorRange[i][1] = sc.nextInt();
        }

        // algorithm
        int[][] output = traffic();

        // output
        PrintWriter out = new PrintWriter(new FileWriter("traffic.out"));
        out.println(output[0][0] + " " + output[0][1]);
        out.println(output[1][0] + " " + output[1][1]);
        out.close();
    }
}
