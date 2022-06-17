// Blocked Billboard - USACO Bronze December 2017 (http://www.usaco.org/index.php?page=viewproblem2&cpid=759)
// This problem was partially completed on Sunday, September 20, 2020, in 1 hour, with 7/10 test cases passed (second try)
// This problem was beautifully completed on Saturday, September 26, 2020, during review, with all 10/10 test cases passed

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class billboard_dec2017 {
    static int[] b1_ll = new int[2];
    static int[] b1_ur = new int[2];
    static int[] b2_ll = new int[2];
    static int[] b2_ur = new int[2];
    static int[] tr_ll = new int[2];
    static int[] tr_ur = new int[2];

    public static int billboard() {
        int remainingArea = 0;

        // find b1 and b2 areas
        int b1_area = (b1_ur[0] - b1_ll[0]) * (b1_ur[1] - b1_ll[1]);
        int b2_area = (b2_ur[0] - b2_ll[0]) * (b2_ur[1] - b2_ll[1]);

        // determine if b1 and tr overlap
        remainingArea += (b1_area - bill_tr_overlap(b1_ll, b1_ur));

        // determine if b2 and tr overlap
        remainingArea += (b2_area - bill_tr_overlap(b2_ll, b2_ur));

        return remainingArea;

    }

    public static int bill_tr_overlap(int[] bill_ll, int[] bill_ur) {
        int bill_length = (bill_ur[0] - bill_ll[0]);
        int bill_width = (bill_ur[1] - bill_ll[1]);
        int tr_length = (tr_ur[0] - tr_ll[0]);
        int tr_width = (tr_ur[1] - tr_ll[1]);

        int horizontalSpan = (Math.max(bill_ur[0], tr_ur[0]) - Math.min(bill_ll[0], tr_ll[0]));
        int verticalSpan = (Math.max(bill_ur[1], tr_ur[1]) - Math.min(bill_ll[1], tr_ll[1]));
        int horizontalOverlap = 0;
        int verticalOverlap = 0;

        if (horizontalSpan < (bill_length + tr_length)) {
            horizontalOverlap = (bill_length + tr_length) - horizontalSpan;
        }

        if (verticalSpan < (bill_width + tr_width)) {
            verticalOverlap = (bill_width + tr_width) - verticalSpan;
        }

        return horizontalOverlap * verticalOverlap;
    }

    public static void main(String[] args) throws IOException {
        // input
        String problemName = "billboard";
        Scanner sc = new Scanner(new File(problemName + ".in"));

        b1_ll[0] = sc.nextInt();
        b1_ll[1] = sc.nextInt();
        b1_ur[0] = sc.nextInt();
        b1_ur[1] = sc.nextInt();
        b2_ll[0] = sc.nextInt();
        b2_ll[1] = sc.nextInt();
        b2_ur[0] = sc.nextInt();
        b2_ur[1] = sc.nextInt();
        tr_ll[0] = sc.nextInt();
        tr_ll[1] = sc.nextInt();
        tr_ur[0] = sc.nextInt();
        tr_ur[1] = sc.nextInt();

        // algorithm
        int billboardArea = billboard();

        // output
        PrintWriter out = new PrintWriter(new FileWriter(problemName + ".out"));
        out.println(billboardArea);
        out.close();
    }
}
