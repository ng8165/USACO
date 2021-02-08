import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class guess_bronzebooster {
    static int numAnimals;
    static String[] animals;
    static ArrayList<ArrayList<String>> characteristics;

    public static int guess() {
        int maxCommonCharacteristics = -1;
        for (int i=0; i<numAnimals; i++) {
            for (int j=i+1; j<numAnimals; j++) {
                // compare the two animals to find the maximum number of common characteristics
                int currCommonCharacteristics = 0;

                for (int k=0; k<characteristics.get(i).size(); k++) {
                    for (int l=0; l<characteristics.get(j).size(); l++) {
                        if (characteristics.get(i).get(k).equals(characteristics.get(j).get(l))) {
                            currCommonCharacteristics++;
                        }
                    }
                }

                maxCommonCharacteristics = Math.max(maxCommonCharacteristics, currCommonCharacteristics);
            }
        }

        maxCommonCharacteristics++;

        return maxCommonCharacteristics;
    }

    public static void main(String[] args) throws IOException {
        // input
        Scanner sc = new Scanner(new File("guess.in"));

        numAnimals = sc.nextInt();
        animals = new String[numAnimals];
        characteristics = new ArrayList<>();
        for (int i=0; i<numAnimals; i++) {
            characteristics.add(new ArrayList<>());

            animals[i] = sc.next();
            int numCharacteristics = sc.nextInt();

            for (int j=0; j<numCharacteristics; j++) {
                characteristics.get(i).add(sc.next());
            }
        }

        // algorithm
        int maxYes = guess();

        // output
        PrintWriter out = new PrintWriter(new FileWriter("guess.out"));
        out.println(maxYes);
        out.close();
    }
}
