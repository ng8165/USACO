// Rental Service - USACO Silver January 2018 (http://www.usaco.org/index.php?page=viewproblem2&cpid=787)
// This problem was completed as homework for the USACO Silver 2 Class on 7/10/21.

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class L19_rental {
    static class Store implements Comparable<Store> {
        int numGallons;
        int cost;

        Store(int numGallons, int cost) {
            this.numGallons = numGallons;
            this.cost = cost;
        }

        @Override
        public int compareTo(Store o) {
            return cost - o.cost;
        }
    }

    public static void main(String[] args) throws IOException {
        // input
        String problemName = "rental";
        BufferedReader br = new BufferedReader(new FileReader(problemName + ".in"));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int numCows = Integer.parseInt(st.nextToken());
        int numStores = Integer.parseInt(st.nextToken());
        int numRenters = Integer.parseInt(st.nextToken());
        int[] cows = new int[numCows];
        Store[] stores = new Store[numStores];
        int[] renters = new int[numRenters];
        for (int i=0; i<numCows; i++) {
            cows[i] = Integer.parseInt(br.readLine());
        }
        for (int i=0; i<numStores; i++) {
            st = new StringTokenizer(br.readLine());
            stores[i] = new Store(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        for (int i=0; i<numRenters; i++) {
            renters[i] = Integer.parseInt(br.readLine());
        }

        // algorithm
        Arrays.sort(cows);
        Arrays.sort(stores);
        Arrays.sort(renters); // traverse all backwards

        // try to sell all cows to store (sell high cow first)
        int[] milkProfits = new int[numCows];
        int storePtr = numStores-1;
        long totalProfit = 0;

        for (int cowPtr=numCows-1; cowPtr>=0; cowPtr--) {
            int milkProfit = 0;
            int milkAmt = cows[cowPtr];

            while (storePtr >= 0 && milkAmt > 0) {
                if (milkAmt <= stores[storePtr].numGallons) {
                    // current store will buy all of my milk
                    milkProfit += stores[storePtr].cost * milkAmt;
                    stores[storePtr].numGallons -= milkAmt;

                    if (stores[storePtr].numGallons == 0) {
                        // store won't buy more milk
                        storePtr--;
                    }

                    break;
                } else {
                    // have more milk than store wants to buy
                    milkProfit += stores[storePtr].cost * stores[storePtr].numGallons;
                    stores[storePtr].numGallons = 0;
                    milkAmt -= stores[storePtr--].numGallons;
                }
            }

            milkProfits[cowPtr] = milkProfit;
            totalProfit += milkProfit;
        }

        // switch out all cows with renting (rent small cow first)
        long newProfit = totalProfit;
        long maxProfit = totalProfit;
        int cowPtr = 0;
        int rentPtr = numRenters-1;

        while (cowPtr < numCows && rentPtr >= 0) {
            newProfit -= milkProfits[cowPtr++];
            newProfit += renters[rentPtr--];

            maxProfit = Math.max(maxProfit, newProfit);
        }

        // output
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(problemName + ".out")));

        pw.println(maxProfit);

        br.close();
        pw.close();
    }
}
