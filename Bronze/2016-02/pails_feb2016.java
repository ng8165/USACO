// Milk Pails - USACO Bronze February 2016 (http://www.usaco.org/index.php?page=viewproblem2&cpid=615)

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class L4_pails {
    public static int pails(int x, int y, int m) {
        int maxMilk = 0;
        for (int i=0; i<=(m/x); i++) {
            int yPails = (m - x * i)/y;
            int milkAmount = x * i + y * yPails;
            maxMilk = Math.max(maxMilk, milkAmount);
        }
        return maxMilk;
    }

    public static void main(String[] args) throws IOException{
        // input
        String problemName = "pails";
        Scanner sc = new Scanner(new File(problemName + ".in"));
        int x = sc.nextInt(); // X pail capacity
        int y = sc.nextInt(); // Y pail capacity
        int m = sc.nextInt(); // what the person wants

        // algorithm
        int maxMilk = pails(x, y, m);

        //output
        PrintWriter out = new PrintWriter(new FileWriter(problemName + ".out"));
        out.println(maxMilk);
        out.close();
    }
}
