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
            return o.cost - cost;
        }
    }

    static int numCows;
    static int numStores;
    static int numRenters;
    static int[] cows;
    static Store[] stores;
    static int[] renters;

    static int storePtr;

    public static int calculateMilkProfit(int milkAmt) {
        int milkProfit = 0;

        while (storePtr < numStores && milkAmt > 0) {
            if (milkAmt <= stores[storePtr].numGallons) {
                // current store will buy all of my milk
                milkProfit += stores[storePtr].cost * milkAmt;
                stores[storePtr].numGallons -= milkAmt;

                if (stores[storePtr].numGallons == 0) {
                    storePtr++;
                }

                break;
            } else {
                // need more milk than this store will buy from me
                milkProfit += stores[storePtr].cost * stores[storePtr].numGallons;
                milkAmt -= stores[storePtr++].numGallons;
            }
        }

        return milkProfit;
    }

    public static void main(String[] args) throws IOException {
        // input
        String problemName = "rental";
        BufferedReader br = new BufferedReader(new FileReader(problemName + ".in"));
        StringTokenizer st = new StringTokenizer(br.readLine());

        numCows = Integer.parseInt(st.nextToken());
        numStores = Integer.parseInt(st.nextToken());
        numRenters = Integer.parseInt(st.nextToken());
        cows = new int[numCows];
        stores = new Store[numStores];
        renters = new int[numRenters];
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
        Arrays.sort(renters);

        int cowLow = 0; // low cows are at front (rent these)
        int cowHigh = numCows-1; // high cows are at back (sell milk)

        int rentPtr = numRenters-1; // sorted normally, traverse backwards
        storePtr = 0; // sorted backwards, traverse normally

        long profit = 0;

        // greedy algorithm
        while (cowLow <= cowHigh) {
            if (rentPtr < 0 && storePtr >= numStores) {
                break;
            }

            if (rentPtr < 0) {
                // no more renters, sell to stores
                profit += calculateMilkProfit(cows[cowHigh--]);

                continue;
            }

            if (storePtr >= numStores) {
                // no more stores, sell to renters
                profit += renters[rentPtr--];
                cowLow++;

                continue;
            }

            // calculate profit of selling milk
            int milkProfit = 0;
            int milkAmt = cows[cowHigh];
            int tempStorePtr = storePtr;

            while (tempStorePtr < numStores) {
                if (milkAmt <= stores[tempStorePtr].numGallons) {
                    milkProfit += stores[tempStorePtr].cost * milkAmt; // finish profit calculation

                    if (milkProfit > renters[rentPtr]) {
                        // selling milk is more profitable
                        profit += milkProfit;
                        stores[tempStorePtr].numGallons -= milkAmt;

                        storePtr = tempStorePtr;
                        cowHigh--;
                    } else {
                        // renting is more profitable
                        profit += renters[rentPtr--];

                        cowLow++;
                    }

                    break;
                } else {
                    // need more milk than this store will buy from me
                    milkProfit += stores[tempStorePtr].cost * stores[tempStorePtr].numGallons;
                    milkAmt -= stores[tempStorePtr++].numGallons;
                }
            }

            if (tempStorePtr == numStores) {
                if (milkProfit > renters[rentPtr]) {
                    // selling milk is more profitable
                    profit += milkProfit;
                    stores[tempStorePtr].numGallons -= milkAmt;

                    storePtr = tempStorePtr;
                    cowHigh--;
                } else {
                    // renting is more profitable
                    profit += renters[rentPtr--];

                    cowLow++;
                }
            }
        }

        // output
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(problemName + ".out")));

        pw.println(profit);

        br.close();
        pw.close();
    }
}
