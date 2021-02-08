// Find the Cow! - USACO Bronze November 2012 (http://www.usaco.org/index.php?page=viewproblem2&cpid=187)

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class L7_cowfind {
    public static int cowfind(String grass_string) {
        char[] grass = grass_string.toCharArray();
        int bessieCnt = 0;

        for (int i=0; i<grass.length-1; i++) { // searches for ((
            if (grass[i] == '(' && grass[i+1] == '(') {

                for (int j=i+2; j<grass.length-1; j++) { // when (( is found, searches for )) after it and adds to cnt
                    if (grass[j] == ')' && grass[j+1] == ')') {
                        bessieCnt++;
                    }
                }
            }
        }

        return bessieCnt;
    }

    public static void main(String[] args) throws IOException {
        // input
        String problemName = "cowfind";
        Scanner sc = new Scanner(new File(problemName + ".in"));
        String grass = sc.nextLine();

        // algorithm
        int bessiePossibleLoc = cowfind(grass);

        //output
        PrintWriter out = new PrintWriter(new FileWriter(problemName + ".out"));
        out.println(bessiePossibleLoc);
        out.close();
    }
}
