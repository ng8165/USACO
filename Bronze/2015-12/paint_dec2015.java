// Fence Painting - USACO Bronze December 2015 (http://www.usaco.org/index.php?page=viewproblem2&cpid=567)

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class L1_paint {
    public static int paint(int seg1_start, int seg1_end, int seg2_start, int seg2_end) {
        int seg_front_start;
        int seg_front_end;
        int seg_back_start;
        int seg_back_end;
        int[] segPos = new int[4];
        int maxSegPos = Integer.MIN_VALUE;
        int minSegPos = Integer.MAX_VALUE;
        boolean overlap;

        if (seg1_start <= seg2_start) {
            seg_front_start = seg1_start;
            seg_front_end = seg1_end;
            seg_back_start = seg2_start;
            seg_back_end = seg2_end;
            if (seg_front_end > seg_back_start) {
                overlap = true;
            } else {
                overlap = false;
            }
        } else {
            seg_front_start = seg2_start;
            seg_front_end = seg2_end;
            seg_back_start = seg1_start;
            seg_back_end = seg1_end;
            if (seg_front_end > seg_back_start) {
                overlap = true;
            } else {
                overlap = false;
            }
        }

        segPos[0] = seg_front_start;
        segPos[1] = seg_front_end;
        segPos[2] = seg_back_start;
        segPos[3] = seg_back_end;

        if (overlap == true) {
            for (int i = 0; i < 4; i++) {
                if (segPos[i] > maxSegPos) {
                    maxSegPos = segPos[i];
                }
                if (segPos[i] < minSegPos) {
                    minSegPos = segPos[i];
                }
            }
            return maxSegPos-minSegPos;
        } else {
            return ((seg_front_end - seg_front_start) + (seg_back_end - seg_back_start));
        }

    }
    public static int paint2(int seg1_start, int seg1_end, int seg2_start, int seg2_end) {
        int john = seg1_end - seg1_start;
        int bessie = seg2_end - seg2_start;
        int total = Math.max(seg1_end, seg2_end) - Math.min(seg1_start, seg2_start);
        return Math.min(total, (john+bessie));
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("paint.in"));

        int seg1_start = sc.nextInt();
        int seg1_end = sc.nextInt();
        int seg2_start = sc.nextInt();
        int seg2_end = sc.nextInt();

        int paintDistance = paint2(seg1_start, seg1_end, seg2_start, seg2_end);

        PrintWriter out = new PrintWriter(new FileWriter("paint.out"));
        out.println(paintDistance);
        out.close();
    }
}

/*
7 10
4 8
A: 6

42 93
63 69
A: 51

1 8
8 99
A: 98

46 53
51 76
A: 30

22 34
57 86
A: 41

57 86
22 34
A: 41
*/ // Test Cases
/*
int john = s1start - s1end
int bessie = s2start - s2end
int total = s2end - s1start
if total > john+bessie
    return (john+bessie) * (john+bessie)
else
    return total*total
 */ // Pseudocode