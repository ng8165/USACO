// Grass Planting - USACO Silver January 2019 (http://www.usaco.org/index.php?page=viewproblem2&cpid=894)
// This problem was completed as homework for the USACO Silver 1 Class on 3/14/21.

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class L6_planting {
    public static void main(String[] args) throws IOException {
        // input
        String problemName = "planting";
        Scanner sc = new Scanner(new File(problemName + ".in"));

        int numFields = sc.nextInt();

        // algorithm
        // restrictions: child node cannot be same grass as parent, grandparent, and siblings
        int[] numConnections = new int[numFields];
        for (int i=0; i<numFields-1; i++) {
            // get the number of whatever touches the node (parent and all children)
            numConnections[sc.nextInt()-1]++;
            numConnections[sc.nextInt()-1]++;
        }

        int maxConnections = 0;
        for (int connections: numConnections) {
            maxConnections = Math.max(maxConnections, connections);
        }

        // max number of grass types will be maxConnections + 1 (all my connections + current node)
        maxConnections++; // add current node

        // output
        PrintWriter out = new PrintWriter(new FileWriter(problemName + ".out"));
        out.println(maxConnections);
        out.close();
    }
}
