// Painting the Fence - USACO Bronze January 2013 (http://www.usaco.org/index.php?page=viewproblem2&cpid=224)
// This problem completed for the USACO Silver Udemy Course, passing all 10/10 test cases.

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class paint_jan2013 {
    static class Move implements Comparable<Move>{
        int pos;
        boolean startLayer;

        Move(int pos, boolean startLayer) {
            this.pos = pos;
            this.startLayer = startLayer;
        }

        public int compareTo(Move another) {
            return pos - another.pos;
        }
    }

    public static void main(String[] args) throws IOException {
        // input
        String problemName = "paint";
        Scanner sc = new Scanner(new File(problemName + ".in"));

        int numMoves = sc.nextInt();
        int[] moveList = new int[numMoves];
        for (int i=0; i<numMoves; i++) {
            moveList[i] = sc.nextInt();

            if (sc.next().equals("L")) {
                moveList[i] *= -1;
            }
        }

        // algorithm
        Move[] moves = new Move[2*numMoves];
        int currPos = 0;

        for (int i=0; i<numMoves; i++) {
            moves[2*i] = new Move(Math.min(currPos, (currPos+moveList[i])), true);
            moves[2*i+1] = new Move(Math.max(currPos, (currPos+=moveList[i])), false);
        }

        Arrays.sort(moves);

        int currLayers = 0;
        int paintedWell = 0;
        for (int i=0; i<2*numMoves; i++) {
            if (i>0 && currLayers >= 2) {
                paintedWell += (moves[i].pos - moves[i-1].pos);
            }

            if (moves[i].startLayer) {
                currLayers++;
            } else {
                currLayers--;
            }
        }

        // output
        PrintWriter out = new PrintWriter(new FileWriter(problemName + ".out"));
        out.println(paintedWell);
        out.close();
    }
}
