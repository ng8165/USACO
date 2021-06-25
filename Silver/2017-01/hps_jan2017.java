// Hoof, Paper, Scissors - USACO Silver January 2017 (http://www.usaco.org/index.php?page=viewproblem2&cpid=691)
// This problem was completed as classwork for the USACO Silver 2 Class on 6/24/21.

import java.io.*;

public class L15_hps {
    public static void main(String[] args) throws IOException {
        // input
        String problemName = "hps";
        BufferedReader br = new BufferedReader(new FileReader(problemName + ".in"));

        int numGames = Integer.parseInt(br.readLine());

        // algorithm

        // calculate prefix sums for wins of each move and finish input
        int[] HWins = new int[numGames+1];
        int[] PWins = new int[numGames+1];
        int[] SWins = new int[numGames+1];
        String[] FJMoves = new String[numGames+1];

        for (int i=1; i<=numGames; i++) {
            FJMoves[i] = br.readLine();

            if (FJMoves[i].equals("H")) {
                HWins[i] = HWins[i-1];
                PWins[i] = PWins[i-1]+1;
                SWins[i] = SWins[i-1];
            } else if (FJMoves[i].equals("P")) {
                HWins[i] = HWins[i-1];
                PWins[i] = PWins[i-1];
                SWins[i] = SWins[i-1]+1;
            } else {
                HWins[i] = HWins[i-1]+1;
                PWins[i] = PWins[i-1];
                SWins[i] = SWins[i-1];
            }
        }


        // base case is no switches
        int maxWins = Math.max(HWins[numGames], Math.max(PWins[numGames], SWins[numGames]));

        // brute force the gesture switches
        for (int i=1; i<numGames; i++) {
            int HtoP = HWins[i] + PWins[numGames] - PWins[i-1];
            int HtoS = HWins[i] + SWins[numGames] - SWins[i-1];
            int PtoH = PWins[i] + HWins[numGames] - HWins[i-1];
            int PtoS = PWins[i] + SWins[numGames] - SWins[i-1];
            int StoH = SWins[i] + HWins[numGames] - HWins[i-1];
            int StoP = SWins[i] + PWins[numGames] - PWins[i-1];

            int tempMax = Math.max(HtoP, HtoS);
            tempMax = Math.max(tempMax, PtoH);
            tempMax = Math.max(tempMax, PtoS);
            tempMax = Math.max(tempMax, StoH);
            tempMax = Math.max(tempMax, StoP);

            maxWins = Math.max(maxWins, tempMax);
        }

        // output
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(problemName + ".out")));

        pw.print(maxWins);

        br.close();
        pw.close();
    }
}
