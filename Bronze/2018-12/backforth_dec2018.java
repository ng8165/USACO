// Back and Forth - USACO Bronze December 2018 (http://www.usaco.org/index.php?page=viewproblem2&cpid=857)
// This problem was not completed on October 18, 2020, in 1 hour 20 minutes, with only 1/10 test cases passed (first try)

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class backforth_dec2018 {
    static ArrayList<Integer> barn1Buckets = new ArrayList<>();
    static ArrayList<Integer> barn2Buckets = new ArrayList<>();

    public static int backforth() {
        ArrayList<Integer> barn1DistinctMilk = new ArrayList<>();

        int barn1Milk = 1000; // monday
        int barn2Milk = 1000;

        for (int i=0; i<10; i++) { // tuesday
            System.out.println("\ni: " + i);
            System.out.println("barn1Buckets: " + barn1Buckets);
            System.out.println("barn2Buckets: " + barn2Buckets);

            int tuesdayBucket = barn1Buckets.get(i);

            barn1Milk -= tuesdayBucket;
            barn2Milk += tuesdayBucket;

            barn1Buckets.remove(i);
            barn2Buckets.add(tuesdayBucket);

            for (int j=0; j<11; j++) { // wednesday
                int wednesdayBucket = barn2Buckets.get(j);

                barn2Milk -= wednesdayBucket;
                barn1Milk += wednesdayBucket;

                barn2Buckets.remove(j);
                barn1Buckets.add(wednesdayBucket);

                for (int k=0; k<10; k++) { // thursday
                    int thursdayBucket = barn1Buckets.get(k);

                    barn1Milk -= thursdayBucket;
                    barn2Milk += thursdayBucket;

                    barn1Buckets.remove(k);
                    barn2Buckets.add(thursdayBucket);

                    for (int l=0; l<11; l++) { // friday
                        int fridayBucket = barn2Buckets.get(l);

                        barn2Milk -= fridayBucket;
                        barn1Milk += fridayBucket;

                        // check barn 1 milk
                        if (searchBarn1DistinctMilk(barn1Milk, barn1DistinctMilk)) {
                            barn1DistinctMilk.add(barn1Milk);
                        }

                        barn2Milk += fridayBucket;
                        barn1Milk -= fridayBucket;
                    }

                    barn1Buckets.add(k, thursdayBucket);
                    barn2Buckets.remove(barn2Buckets.size()-1);

                    barn1Milk += thursdayBucket;
                    barn2Milk -= thursdayBucket;
                }

                barn2Buckets.add(j, wednesdayBucket);
                barn1Buckets.remove(barn1Buckets.size()-1);

                barn2Milk += wednesdayBucket;
                barn1Milk -= wednesdayBucket;
            }

            barn1Buckets.add(i, tuesdayBucket);
            barn2Buckets.remove(barn2Buckets.size()-1);

            barn1Milk += tuesdayBucket;
            barn2Milk -= tuesdayBucket;
        }

        System.out.println("\n\nbarn1DistinctMilk: " + barn1DistinctMilk);

        return barn1DistinctMilk.size();
    }

    public static boolean searchBarn1DistinctMilk(int milkValue, ArrayList<Integer> barn1DistinctMilk) {
        for (int distinctMilkValue: barn1DistinctMilk) {
            if (milkValue == distinctMilkValue) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) throws IOException {
        // input
        String problemName = "backforth";
        Scanner sc = new Scanner(new File(problemName + ".in"));

        for (int i=0; i<10; i++) {
            barn1Buckets.add(sc.nextInt());
        }
        for (int i=0; i<10; i++) {
            barn2Buckets.add(sc.nextInt());
        }

        // algorithm
        int numReadings = backforth();

        // output
        PrintWriter out = new PrintWriter(new FileWriter(problemName + ".out"));
        out.println(numReadings);
        out.close();
    }
}
