// Don't Be Last! - USACO Bronze January 2017 (http://www.usaco.org/index.php?page=viewproblem2&cpid=687)
// This problem was completed as homework for the USACO Silver 1 Class on 3/6/21.

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class L5_notlast {
    public static void main(String[] args) throws IOException {
        // input
        String problemName = "notlast";
        Scanner sc = new Scanner(new File(problemName + ".in"));

        int numEntries = sc.nextInt();

        // algorithm
        HashMap<String, Integer> milkLog = new HashMap<>();
        milkLog.put("Bessie", 0);
        milkLog.put("Elsie", 0);
        milkLog.put("Daisy", 0);
        milkLog.put("Gertie", 0);
        milkLog.put("Annabelle", 0);
        milkLog.put("Maggie", 0);
        milkLog.put("Henrietta", 0);

        // add all log entries into HashMap
        for (int i=0; i<numEntries; i++) {
            String cow = sc.next();
            milkLog.put(cow, milkLog.get(cow)+sc.nextInt());
        }

        // find second smallest milk production
        int minMilk = Integer.MAX_VALUE, secondMinMilk = Integer.MAX_VALUE;
        for (int milk: milkLog.values()) {
            if (milk < minMilk) {
                secondMinMilk = minMilk;
                minMilk = milk;
            } else if (milk > minMilk && milk < secondMinMilk) {
                secondMinMilk = milk;
            }
        }

        // find cows with second smallest milk production
        ArrayList<String> secondMinCows = new ArrayList<>();
        for (Map.Entry<String, Integer> entry: milkLog.entrySet()) {
            if (entry.getValue() == secondMinMilk) {
                secondMinCows.add(entry.getKey());
            }
        }

        // output
        PrintWriter out = new PrintWriter(new FileWriter(problemName + ".out"));
        if (secondMinCows.size() == 1) {
            out.println(secondMinCows.get(0));
        } else {
            out.println("Tie");
        }
        out.close();
    }
}
