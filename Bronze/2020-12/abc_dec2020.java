// Do You Know Your ABCs? - USACO Bronze December 2020 (http://www.usaco.org/index.php?page=viewproblem&cpid=1047)
// This problem was completed on December 21, 2020, in 36 minutes, with 10/10 test cases passed

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class abc_1 {
    static ArrayList<Integer> numbers = new ArrayList<>();

    public static int[] findABC(int A, int Apos, int ABC, int ABCpos) {
        int BC = ABC-A;
        if (Apos < ABCpos) {
            numbers.remove(Apos);
            numbers.remove(ABCpos-1);
        } else {
            numbers.remove(ABCpos);
            numbers.remove(Apos-1);
        }
        int B = 0, C = 0;

        for (int i=0; i<5; i++) {
            for (int j=0; j<5; j++) {
                if (numbers.get(i) + numbers.get(j) == BC) {
                    B = numbers.get(i);
                    C = numbers.get(j);
                    break;
                }
            }
        }

        int[] finalABC = new int[] {A, B, C};
        Arrays.sort(finalABC);
        return finalABC;
    }

    public static void main(String[] args) {
        // input
        Scanner sc = new Scanner(System.in);
        int A = Integer.MAX_VALUE;
        int Apos = -1;
        int ABC = 0;
        int ABCpos = -1;

        for (int i=0; i<7; i++) {
            numbers.add(sc.nextInt());
            if (numbers.get(i) < A) {
                A = numbers.get(i);
                Apos = i;
            }
            if (numbers.get(i) > ABC) {
                ABC = numbers.get(i);
                ABCpos = i;
            }
        }

        // algorithm
        int[] finalABC = findABC(A, Apos, ABC, ABCpos);

        // output
        System.out.println(finalABC[0] + " " + finalABC[1] + " " + finalABC[2]);
    }
}
