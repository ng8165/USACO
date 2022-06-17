import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class swap_bronzebooster {
    static int numCows;
    static int numRepeats;
    static int[][] swaps = new int[2][2];

    public static int[] swap() {
        int[] cows = new int[numCows];

        for (int i=1; i<=numCows; i++) {
            int[] cowsSwap = new int[numCows];
            for (int j=1; j<=numCows; j++) {
                cowsSwap[j-1] = j;
            }

            // find the number of cycles before it is the same
            ArrayList<Integer> values = new ArrayList<>();
            values.add(i);
            int val;
            int cycleCnt = 0;

            do {
                // try the two swaps
                calculateNewValue(swaps[0][0]-1, swaps[0][1]-1, i-1, cowsSwap);
                val = calculateNewValue(swaps[1][0]-1, swaps[1][1]-1, i-1, cowsSwap);

                values.add(val);
                cycleCnt++;
            } while (val != i);
            values.remove(values.size()-1);

            // take number of repeats mod cycle count
            cows[i-1] = values.get(numRepeats%cycleCnt);
        }

        return cows;
    }
    public static int calculateNewValue(int left, int right, int idx, int[] cows) {
        while (left<right) {
            int temp = cows[left];
            cows[left] = cows[right];
            cows[right] = temp;

            left++;
            right--;
        }

        return cows[idx];
    }

    public static int[] swap2() {
        int[] cows = new int[numCows];
        for (int i=1; i<=numCows; i++) {
            cows[i-1] = i;
        }

        System.out.println(Arrays.toString(cows) + "\n\n");


        for (int i=0; i<numRepeats; i++) {
            reverse(swaps[0][0]-1, swaps[0][1]-1, cows);
            System.out.println(Arrays.toString(cows));
            reverse(swaps[1][0]-1, swaps[1][1]-1, cows);
            System.out.println(Arrays.toString(cows) + "\n");
        }

        return cows;
    }
    public static void reverse(int left, int right, int[] cows) {
        while (left<right) {
            int temp = cows[left];
            cows[left] = cows[right];
            cows[right] = temp;

            left++;
            right--;
        }
    }

    public static void main(String[] args) throws IOException {
        // input
        Scanner sc = new Scanner(new File("swap.in"));

        numCows = sc.nextInt();
        numRepeats = sc.nextInt();
        swaps[0][0] = sc.nextInt();
        swaps[0][1] = sc.nextInt();
        swaps[1][0] = sc.nextInt();
        swaps[1][1] = sc.nextInt();

        // algorithm
        int[] cows = swap();

        // output
        PrintWriter out = new PrintWriter(new FileWriter("swap.out"));
        for (int i=0; i<numCows; i++) {
            out.println(cows[i]);
        }
        out.close();
    }
}
