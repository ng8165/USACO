import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class photo_bronzebooster {
    static int numCows;
    static int[] bessieOrder;

    public static int[] photo() {
        int[] currOrder = new int[numCows];
        startLoop: for (int i=1; i<numCows; i++) {
            // generate a potential order
            currOrder[0] = i;
            int[] temp = new int[numCows];
            temp[i-1]++;

            for (int j=1; j<numCows; j++) {
                currOrder[j] = bessieOrder[j-1] - currOrder[j-1];
                if (currOrder[j] < 1 || currOrder[j] > numCows) {
                    continue startLoop;
                }
                temp[currOrder[j]-1]++;
            }

            // see if the order is valid
            boolean noZero = true;
            for (int j=0; j<numCows; j++) {
                if (temp[j] == 0) {
                    noZero = false;
                    break;
                }
            }
            if (noZero) {
                return currOrder;
            }
        }

        return new int[] {0}; // avoid compilation errors
    }

    public static void main(String[] args) throws IOException {
        // input
        Scanner sc = new Scanner(new File("photo.in"));

        numCows = sc.nextInt();
        bessieOrder = new int[numCows-1];
        for (int i=0; i<numCows-1; i++) {
            bessieOrder[i] = sc.nextInt();
        }

        // algorithm
        int[] order = photo();

        // output
        PrintWriter out = new PrintWriter(new FileWriter("photo.out"));
        for (int i=0; i<numCows-1; i++) {
            out.print(order[i] + " ");
        }
        out.println(order[numCows-1]);
        out.close();
    }
}
