// Sleepy Cow Herding - USACO Bronze February 2019 (http://www.usaco.org/index.php?page=viewproblem2&cpid=915)

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class L14_herding {
    public static int herding_min(int[] cowLocations) {
        if ((cowLocations[1]-cowLocations[0] == 1) && (cowLocations[2]-cowLocations[1] == 1)) {
            return 0;
        } else if ((cowLocations[1]-cowLocations[0] == 2) || (cowLocations[2]-cowLocations[1] == 2)) {
            return 1;
        } else {
            return 2;
        }
    }

    public static int herding_max(int[] cowLocations) {
        int maxCowMoves = 0;
        
        for (int i=0; i<2; i++) {
            maxCowMoves = Math.max(maxCowMoves, (cowLocations[i+1]-cowLocations[i]-1));
        }

        return maxCowMoves;
    }

    public static void main(String[] args) throws IOException {
        // input
        String problemName = "herding";
        Scanner sc = new Scanner(new File(problemName + ".in"));

        int[] cowLocations = new int[3];
        for (int i=0; i<3; i++) {
            cowLocations[i] = sc.nextInt();
        }
        Arrays.sort(cowLocations);

        // algorithm and output
        PrintWriter out = new PrintWriter(new FileWriter(problemName + ".out"));
        out.println(herding_min(cowLocations));
        out.println(herding_max(cowLocations));
        out.close();
    }
}
