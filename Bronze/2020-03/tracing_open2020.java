import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class tracing_bronzebooster {
    static int numCows;
    static int numEntries;
    static boolean[] infected;
    static int[] time;
    static int[][] interactions;

    public static int[] tracing(int[] timeUnsorted, int[][] interactionsUnsorted) {
        // sort the cow interaction times
        boolean[] visited = new boolean[numEntries];
        time = timeUnsorted.clone();
        Arrays.sort(time);
        for (int i=0; i<numEntries; i++) {
            for (int j=0; j<numEntries; j++) {
                if (time[i] == timeUnsorted[j] && !visited[i]) {
                    interactions[i] = interactionsUnsorted[j];
                    visited[i] = true;
                    //System.out.println(time[i] + ": " + interactions[i][0] + " " + interactions[i][1]);
                    break;
                }
            }
        }

        // start the brute force search
        int numPatientZero = 0;
        int minK = Integer.MAX_VALUE;
        int maxK = -1;

        for (int i=0; i<numCows; i++) { // patient 0
            //System.out.println("\n\npatient0: " + i);
            boolean isPatientZero = false;

            for (int K=0; K<=numEntries; K++) { // K
                //System.out.println("\nK = " + K);
                // simulate the infection
                boolean[] currInfected = new boolean[numCows];
                currInfected[i] = true; // patient zero is infected
                int[] infectedPower = new int[numCows];
                infectedPower[i] = K;
                //System.out.println("Original: " + Arrays.toString(currInfected) + ", " + Arrays.toString(infectedPower));

                for (int j=0; j<numEntries; j++) {
                    //System.out.print("j = " + j + ": ");

                    int cow1 = interactions[j][0];
                    int cow2 = interactions[j][1];

                    if (infectedPower[cow1] > 0 && infectedPower[cow2] == 0) {
                        infectedPower[cow1]--;
                        if (!currInfected[cow2]) {
                            currInfected[cow2] = true;
                            infectedPower[cow2] = K;
                        }
                        //System.out.print(cow1 + " -> " + cow2 + ", ");
                    } else if (infectedPower[cow2] > 0 && infectedPower[cow1] == 0) {
                        infectedPower[cow2]--;
                        if (!currInfected[cow1]) {
                            currInfected[cow1] = true;
                            infectedPower[cow1] = K;
                        }
                        //System.out.print(cow2 + " -> " + cow1 + ", ");
                    } else if (infectedPower[cow1] > 0 && infectedPower[cow2] > 0) {
                        infectedPower[cow1]--;
                        infectedPower[cow2]--;
                        //System.out.print(cow1 + " <-> " + cow2 + ", ");
                    }

                    //System.out.println(Arrays.toString(currInfected) + ", " + Arrays.toString(infectedPower));
                }

                if (Arrays.equals(infected, currInfected)) {
                    isPatientZero = true;
                    minK = Math.min(minK, K);
                    maxK = Math.max(maxK, K);
                    //System.out.println("\nUpdating K: min = " + minK + " and max = " + maxK);
                }
            } // K

            if (isPatientZero) {
                numPatientZero++;
                //System.out.println("\nUpdating numPatientZero: " + numPatientZero);
            }
        } // patient 0

        return new int[] {numPatientZero, minK, maxK};
    }

    public static void main(String[] args) throws IOException {
        // input
        Scanner sc = new Scanner(new File("tracing.in"));

        numCows = sc.nextInt();
        numEntries = sc.nextInt();
        char[] cowSick = sc.next().toCharArray();
        infected = new boolean[numCows];
        for (int i=0; i<numCows; i++) {
            if (cowSick[i] == '1') {
                infected[i] = true;
            }
        }
        int[][] interactionsUnsorted = new int[numEntries][2];
        int[] timeUnsorted = new int[numEntries];
        interactions = new int[numEntries][2];
        time = new int[numEntries];
        for (int i=0; i<numEntries; i++) {
            timeUnsorted[i] = sc.nextInt();
            interactionsUnsorted[i][0] = sc.nextInt()-1;
            interactionsUnsorted[i][1] = sc.nextInt()-1;
        }

        // algorithm
        int[] result = tracing(timeUnsorted, interactionsUnsorted);

        // output
        PrintWriter out = new PrintWriter(new FileWriter("tracing.out"));
        out.print(result[0] + " " + result[1] + " ");
        if (result[2] == numEntries) {
            out.println("Infinity");
        } else {
            out.println(result[2]);
        }
        out.close();
    }
}
