// Guess the Animal - USACO Bronze January 2019 (http://www.usaco.org/index.php?page=viewproblem2&cpid=893)
// This problem was partially completed on October 25, 2020, in 1 hour and 23 minutes, with 3/10 test cases passed (first try)

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class guess_jan2019 {
    static int numAnimals;
    static String[] animals;
    static int[] numCharacteristics;
    static ArrayList<String>[] characteristics;

    public static int guess() {
        // find cow with most characteristics
        int maxCharacteristics = 0;
        int maxCharacterID = -1;
        for (int i=0; i<numAnimals; i++) {
            if (numCharacteristics[i] > maxCharacteristics) {
                maxCharacterID = i;
            }
        }

        int[] characteristicCnt = new int[numCharacteristics[maxCharacterID]];
        for (int i=0; i<characteristicCnt.length; i++) {
            characteristicCnt[i] = countCharacteristics(characteristics[maxCharacterID].get(i));
        }

        int maxYes = 0;

        for (int i=0; i<characteristicCnt.length; i++) {
            if (characteristicCnt[i] > 1) {
                maxYes++;
            }
        }

        return maxYes+1;
    }

    public static boolean searchCharacteristics(String characteristic, ArrayList<String> uniqueCharacteristics) {
        for (String uniqueCharacteristic: uniqueCharacteristics) {
            if (characteristic.equals(uniqueCharacteristic)) {
                return false;
            }
        }

        return true;
    }
    public static int countCharacteristics(String characteristic) {
        int characteristicCnt = 0;

        for (int i=0; i<numAnimals; i++) {
            for (int j=0; j<numCharacteristics[i]; j++) {
                if (characteristics[i].get(j).equals(characteristic)) {
                    characteristicCnt++;
                }
            }
        }

        return characteristicCnt;
    }

    public static void main(String[] args) throws IOException {
        // input
        String problemName = "guess";
        Scanner sc = new Scanner(new File(problemName + ".in"));

        numAnimals = sc.nextInt();
        animals = new String[numAnimals];
        numCharacteristics = new int[numAnimals];
        characteristics = new ArrayList[numAnimals];
        for (int i=0; i<numAnimals; i++) {
            animals[i] = sc.next();
            numCharacteristics[i] = sc.nextInt();
            characteristics[i] = new ArrayList<>();
            for (int j=0; j<numCharacteristics[i]; j++) {
                characteristics[i].add(sc.next());
            }
        }

        // algorithm
        int maxYes = guess();

        // output
        PrintWriter out = new PrintWriter(new FileWriter(problemName + ".out"));
        out.println(maxYes);
        out.close();
    }
}
