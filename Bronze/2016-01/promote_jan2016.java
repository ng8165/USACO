// Promotion Counting - USACO Bronze January 2016 (http://www.usaco.org/index.php?page=viewproblem2&cpid=591)

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class L1_promote {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("promote.in"));
        int bb = sc.nextInt();
        int ba = sc.nextInt();
        int sb = sc.nextInt();
        int sa = sc.nextInt();
        int gb = sc.nextInt();
        int ga = sc.nextInt();
        int pb = sc.nextInt();
        int pa = sc.nextInt();

        int g_p = pa - pb;
        int s_g = ga - gb + g_p;
        int b_s = sa - sb + s_g;

        PrintWriter out = new PrintWriter(new FileWriter("promote.out"));
        out.println(b_s);
        out.println(s_g);
        out.println(g_p);
        out.close();
    }
}
