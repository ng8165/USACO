import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class evolution_bronzebooster {
    static int numPopulations;
    static ArrayList<ArrayList<String>> populationsInput = new ArrayList<>();

    public static String evolution() {
        // change the inputs into which populations each characteristic appears in
        ArrayList<String> characteristics = new ArrayList<>();
        ArrayList<ArrayList<Integer>> populations = new ArrayList<>();
        for (int i=0; i<populationsInput.size(); i++) {
            for (int j=0; j<populationsInput.get(i).size(); j++) {
                boolean found = false;

                for (int k=0; k<characteristics.size(); k++) {
                    if (populationsInput.get(i).get(j).equals(characteristics.get(k))) {
                        found = true;
                        populations.get(k).add(i);
                        break;
                    }
                }

                if (!found) {
                    characteristics.add(populationsInput.get(i).get(j));
                    populations.add(new ArrayList<>());
                    populations.get(populations.size()-1).add(i);
                }
            }
        }

        // check every pair of characteristics to see if it the evolutionary tree is valid
        for (int i=0; i<characteristics.size(); i++) {
            for (int j=0; j<characteristics.size(); j++) {
                if (i==j || populations.get(i).size() == 1 || populations.get(j).size() == 1) {
                    continue;
                }

                // search for a common population
                if (hasCommonElement(populations.get(i), populations.get(j))) {
                    if (hasUniqueElement(populations.get(i), populations.get(j)) && hasUniqueElement(populations.get(j), populations.get(i))) {
                        return "no";
                    }
                }
            }
        }

        return "yes";
    }

    public static boolean hasCommonElement(ArrayList<Integer> arrList1, ArrayList<Integer> arrList2) {
        for (int int1: arrList1) {
            for (int int2: arrList2) {
                if (int1 == int2) {
                    return true;
                }
            }
        }

        return false;
    }
    public static boolean hasUniqueElement(ArrayList<Integer> arrList1, ArrayList<Integer> arrList2) {
        for (int int1: arrList1) {
            boolean found = false;

            for (int int2: arrList2) {
                if (int1 == int2) {
                    found = true;
                    break;
                }
            }

            if (!found) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) throws IOException {
        // input
        Scanner sc = new Scanner(new File("evolution.in"));

        numPopulations = sc.nextInt();
        for (int i=0; i<numPopulations; i++) {
            populationsInput.add(new ArrayList<>());
            int currNumCharacteristics = sc.nextInt();
            for (int j=0; j<currNumCharacteristics; j++) {
                populationsInput.get(i).add(sc.next());
            }
        }

        // algorithm
        String isProperEvolutionTree = evolution();

        // output
        PrintWriter out = new PrintWriter(new FileWriter("evolution.out"));
        out.println(isProperEvolutionTree);
        out.close();
    }
}
