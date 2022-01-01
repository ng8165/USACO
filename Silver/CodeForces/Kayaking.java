// CodeForces: Kayaking, all 14/14 test cases passed (https://codeforces.com/contest/863/problem/B)

import java.util.Arrays;
import java.util.Scanner;

public class CF-863B {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        Integer[] weights = new Integer[2*n];
        for (int i=0; i<2*n; i++) {
            weights[i] = sc.nextInt();
        }

        Arrays.sort(weights);

        int minInstability = 0;
        for (int i=0; i<2*n; i++) {
            for (int j=i+1; j<2*n; j++) {
                for (int k=0; k<2*n; k++) {
                    if (k==i || k==j) {
                        continue;
                    }

                    
                }
            }
        }

        System.out.println(minInstability);
    }
}
