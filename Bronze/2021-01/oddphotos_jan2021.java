import java.util.Scanner;

public class oddphotos_jan2021 {
    public static void main(String[] args) {
        // input
        Scanner sc = new Scanner(System.in);

        int numBreeds = sc.nextInt();
        int evenCnt = 0;
        int oddCnt = 0;
        for (int i=0; i<numBreeds; i++) {
            if (sc.nextInt()%2 == 0) {
                evenCnt++;
            } else {
                oddCnt++;
            }
        }

        // algorithm
        if (oddCnt > evenCnt) {
            // too many odd groups, merge two odd groups which form one even group
            while (oddCnt > evenCnt) {
                evenCnt++;
                oddCnt-=2;
            }
        }

        // output
        if (evenCnt > oddCnt+1) {
            System.out.println(2*oddCnt+1);
        } else {
            System.out.println(evenCnt+oddCnt);
        }
    }
}
