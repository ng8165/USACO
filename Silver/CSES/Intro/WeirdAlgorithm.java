// CSES: Weird Algorithm (Introductory Problem), all 14/14 test cases passed (https://cses.fi/problemset/task/1068/)

import java.util.Scanner;

public class CSES-WeirdAlgorithm { // change class name to "Main" before submitting to CSES
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        long num = sc.nextInt();

        while (num != 1) {
            System.out.print(num + " ");

            if (num%2 == 0) {
                num/=2;
            } else {
                num*=3;
                num++;
            }

        }

        System.out.println(num);
    }
}
