import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class lineup_bronzebooster {
    static int numConstraints;
    static String[][] constraints;

    static String[] cows = new String[] {"Beatrice", "Belinda", "Bella", "Bessie", "Betsy", "Blue", "Buttercup", "Sue"};

    public static String[] lineup() {
        String[] minOrder = new String[8];
        Arrays.fill(minOrder, "|");

        for (int a=0; a<8; a++) {
            for (int b=0; b<8; b++) {
                if (a==b) {
                    continue;
                }
                for (int c=0; c<8; c++) {
                    if (a==c || b==c) {
                        continue;
                    }
                    for (int d=0; d<8; d++) {
                        if (a==d || b==d || c==d) {
                            continue;
                        }
                        for (int e=0; e<8; e++) {
                            if (a==e || b==e || c==e || d==e) {
                                continue;
                            }
                            for (int f=0; f<8; f++) {
                                if (a==f || b==f || c==f || d==f || e==f) {
                                    continue;
                                }
                                for (int g=0; g<8; g++) {
                                    if (a==g || b==g || c==g || d==g || e==g || f==g) {
                                        continue;
                                    }
                                    for (int h=0; h<8; h++) {
                                        if (a==h || b==h || c==h || d==h || e==h || f==h || g==h) {
                                            continue;
                                        }

                                        // generate a order based on the for loops
                                        String[] currOrder = generateOrder(a, b, c, d, e, f, g, h);

                                        // see if the order is valid
                                        if (isOrderValid(currOrder)) {
                                            // compare the order to the minOrder
                                            if (Arrays.toString(currOrder).compareTo(Arrays.toString(minOrder)) < 0) {
                                                minOrder = currOrder;
                                                //System.out.println(Arrays.toString(currOrder));
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        return minOrder;
    }

    public static String[] generateOrder(int a, int b, int c, int d, int e, int f, int g, int h) {
        String[] currOrder = new String[8];

        currOrder[0] = cows[a];
        currOrder[1] = cows[b];
        currOrder[2] = cows[c];
        currOrder[3] = cows[d];
        currOrder[4] = cows[e];
        currOrder[5] = cows[f];
        currOrder[6] = cows[g];
        currOrder[7] = cows[h];

        return currOrder;
    }
    public static boolean isOrderValid(String[] order) {
        for (int i=0; i<numConstraints; i++) {
            int cow1Idx = -1, cow2Idx = -1;

            for (int j=0; j<order.length; j++) {
                if (constraints[i][0].equals(order[j])) {
                    cow1Idx = j;
                } else if (constraints[i][1].equals(order[j])) {
                    cow2Idx = j;
                }
            }

            if (Math.abs(cow2Idx-cow1Idx) != 1) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) throws IOException {
        // input
        Scanner sc = new Scanner(new File("lineup.in"));

        numConstraints = sc.nextInt();
        constraints = new String[numConstraints][2];
        for (int i=0; i<numConstraints; i++) {
            constraints[i][0] = sc.next();
            for (int j=0; j<4; j++) {
                sc.next();
            }
            constraints[i][1] = sc.next();
        }

        // algorithm
        String[] order = lineup();

        // output
        PrintWriter out = new PrintWriter(new FileWriter("lineup.out"));
        for (String cow: order) {
            out.println(cow);
        }
        out.close();
    }
}
