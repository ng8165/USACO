// Blocked Billboard II - USACO Bronze January 2018 (http://www.usaco.org/index.php?page=viewproblem2&cpid=783)
// This problem was mostly completed on September 27, 2020, in 35 minutes, with 9/10 test cases passed (second try)

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class billboard_jan2018 {
    static int[] goodBill_ll = new int[2];
    static int[] goodBill_ur = new int[2];
    static int[] badBill_ll = new int[2];
    static int[] badBill_ur = new int[2];

    public static int billboard() {
        // find basic measurements of the billboards
        int goodBill_width = goodBill_ur[0] - goodBill_ll[0];
        int goodBill_height = goodBill_ur[1] - goodBill_ll[1];
        int badBill_width = badBill_ur[0] - badBill_ll[0];
        int badBill_height = badBill_ur[1] - badBill_ll[1];
        int badBill_area = (badBill_ur[0] - badBill_ll[0]) * (badBill_ur[1] - badBill_ll[1]);
        int widthSpan = Math.max(goodBill_ur[0], badBill_ur[0]) - Math.min(goodBill_ll[0], badBill_ll[0]);
        int heightSpan = Math.max(goodBill_ur[1], badBill_ur[1]) - Math.min(goodBill_ll[1], badBill_ll[1]);

        // find the overlap lengths
        boolean isWidthCompleteOverlap = false;
        int width_overlap = 0;
        if (goodBill_ll[0] < badBill_ll[0] && goodBill_ur[0] > badBill_ur[0]) {
            isWidthCompleteOverlap = true;
            width_overlap = badBill_width;
        }

        boolean isHeightCompleteOverlap = false;
        int height_overlap = 0;
        if (goodBill_ll[1] < badBill_ll[1] && goodBill_ur[1] > badBill_ur[1]) {
            isHeightCompleteOverlap = true;
            height_overlap = badBill_height;
        }

        int tarpWidth;
        int tarpHeight;
        if (isWidthCompleteOverlap && !isHeightCompleteOverlap) {
            // width is a complete overlap but height is a partial overlap
            int heightPartialOverlap = goodBill_height + badBill_height - heightSpan;

            tarpWidth = width_overlap;
            if (badBill_ll[1] < goodBill_ll[1] && badBill_ur[1] > goodBill_ur[1]) {
                tarpHeight = badBill_height;
            } else {
                tarpHeight = badBill_height - heightPartialOverlap;
            }
        } else if (isHeightCompleteOverlap && !isWidthCompleteOverlap) {
            // height is a complete overlap but width is a partial overlap
            int widthPartialOverlap = goodBill_width + badBill_width - widthSpan;

            tarpHeight = height_overlap;
            if (badBill_ll[0] < goodBill_ll[0] && badBill_ur[0] > goodBill_ur[0]) {
                tarpWidth = badBill_width;
            } else {
                tarpWidth = badBill_width - widthPartialOverlap;
            }
        } else {
            // both width and height are overlapped or only partial overlaps or no overlap at all
            tarpWidth = badBill_width - width_overlap;
            tarpHeight = badBill_height - height_overlap;
        }

        return tarpWidth*tarpHeight;
    }

    public static void main(String[] args) throws IOException {
        // input
        String problemName = "billboard";
        Scanner sc = new Scanner(new File(problemName + ".in"));

        badBill_ll[0] = sc.nextInt();
        badBill_ll[1] = sc.nextInt();
        badBill_ur[0] = sc.nextInt();
        badBill_ur[1] = sc.nextInt();
        goodBill_ll[0] = sc.nextInt();
        goodBill_ll[1] = sc.nextInt();
        goodBill_ur[0] = sc.nextInt();
        goodBill_ur[1] = sc.nextInt();

        // algorithm
        int tarpArea = billboard();

        // output
        PrintWriter out = new PrintWriter(new FileWriter(problemName + ".out"));
        out.println(tarpArea);
        out.close();
    }
}
