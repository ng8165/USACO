import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class socdist1_bronzebooster {
    static int numStalls;
    static char[] stalls;

    public static int socdist1() {
        int cnt=0;
        int case1D = 0;
        int maxFreeLength = 0;
        int maxFreeStart = -1;
        int maxFreeEnd = -1;
        maxWhile: while (cnt < numStalls) {
            if (stalls[cnt] == '0') {
                for (int i=cnt+1; i<numStalls; i++) {
                    if (stalls[i] == '1') {
                        if ((i-cnt) > maxFreeLength) {
                            maxFreeLength = i-cnt;
                            maxFreeStart = cnt;
                            maxFreeEnd = i-1;
                        }

                        cnt = i;
                        break;
                    }

                    if (i == numStalls-1) {
                        maxFreeLength = i-cnt+1;
                        maxFreeStart = cnt;
                        maxFreeEnd = i;
                        break maxWhile;
                    }
                }
            }
            cnt++;
        }

        cnt=0;
        int case2D = 0;
        int max2FreeLength = 0;
        int max2FreeStart = -1;
        int max2FreeEnd = -1;
        while (cnt < numStalls) {
            if (stalls[cnt] == '0') {
                for (int i=cnt+1; i<numStalls; i++) {
                    if (stalls[i] == '1') {
                        if ((i-cnt) > max2FreeLength && (cnt != maxFreeStart) && (i-1 != maxFreeEnd)) {
                            max2FreeLength = i-cnt;
                            max2FreeStart = cnt;
                            max2FreeEnd = i-1;
                        }

                        cnt = i;
                        break;
                    }
                }
            }
            cnt++;
        }

        if (maxFreeStart == 0 ^ maxFreeEnd == numStalls-1) {
            case1D = maxFreeLength/2;
        } else if (maxFreeStart == 0 && maxFreeEnd == numStalls-1) {
            case1D = maxFreeLength-1;
        } else {
            if (maxFreeLength%3 == 2) {
                case1D = maxFreeLength/3+1;
            } else {
                case1D = maxFreeLength/3;
            }
        }

        if (max2FreeLength > 0) {
            if (maxFreeStart == 0 || maxFreeEnd == numStalls-1) {
                case2D = maxFreeLength;
            } else {
                if (maxFreeLength%2 == 1) {
                    case2D = maxFreeLength/2+1;
                } else {
                    case2D = maxFreeLength/2;
                }
            }

            if (max2FreeStart == 0 || max2FreeEnd == numStalls-1) {
                case2D = Math.min(case2D, max2FreeLength);
            } else {
                if (max2FreeLength%2 == 1) {
                    case2D = Math.min(case2D, max2FreeLength/2+1);
                } else {
                    case2D = Math.min(case2D, max2FreeLength/2);
                }
            }
        }

        return Math.max(case1D, case2D);
    }

    public static void main(String[] args) throws IOException {
        // input
        Scanner sc = new Scanner(new File("socdist1.in"));

        numStalls = sc.nextInt();
        stalls = sc.next().toCharArray();

        // algorithm
        int closestDistance = socdist1();

        // output
        PrintWriter out = new PrintWriter(new FileWriter("socdist1.out"));
        out.println(closestDistance);
        out.close();
    }
}
