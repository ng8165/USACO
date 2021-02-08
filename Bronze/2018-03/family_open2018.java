import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class family_bronzebooster {
    static int numRelationships;
    static String[][] relationships;

    public static String family(String startingCow, String intendedCow) {
        ArrayList<ArrayList<String>> familyTree = new ArrayList<>();

        // first find all mother(ish) cows
        familyTree.add(new ArrayList<>());
        familyTree.get(0).add(startingCow);

        while (true) {
            boolean found = false;

            for (int i = 0; i < numRelationships; i++) {
                ArrayList<String> currBranch = familyTree.get(familyTree.size() - 1);

                if (relationships[i][1].equals(currBranch.get(currBranch.size() - 1))) {
                    familyTree.add(new ArrayList<>());
                    familyTree.get(familyTree.size() - 1).add(relationships[i][0]);

                    found = true;
                    break;
                }
            }

            if (!found) {
                break;
            }
        }

        // start finding aunt(ish) and cousin cows
        for (int i=1; i<familyTree.size(); i++) {
            while (true) {
                boolean found = false;

                for (int j=0; j<numRelationships; j++) {
                    ArrayList<String> pastBranch = familyTree.get(i-1);
                    ArrayList<String> currBranch = familyTree.get(i);

                    if (!(relationships[j][1].equals(pastBranch.get(0))) && (relationships[j][0].equals(currBranch.get(currBranch.size()-1)))) {
                        familyTree.get(i).add(relationships[j][1]);

                        found = true;
                        break;
                    }
                }

                if (!found) {
                    break;
                }
            }
        }

        // start searching for intendedCow
        for (int i=0; i<familyTree.size(); i++) {
            for (int j=0; j<familyTree.get(i).size(); j++) {
                if (familyTree.get(i).get(j).equals(intendedCow)) {
                    if (j==0) {
                        return (intendedCow + " is the " + printRelationship("mother", i) + "mother of " + startingCow);
                    } else if (j==1 && i>1) {
                        return (intendedCow + " is the " + printRelationship("aunt", i) + "aunt of " + startingCow);
                    } else if (j==1 && i==1) {
                        return "SIBLINGS";
                    } else if (j>1) {
                        return "COUSINS";
                    }
                }
            }
        }

        return "NOT RELATED";
    }

    public static String printRelationship(String relationshipType, int level) {
        StringBuilder greatAndGrand =  new StringBuilder();

        if (relationshipType.equals("mother")) {
            for (int i=0; i<level-1; i++) {
                if (i==level-2) {
                    greatAndGrand.append("grand-");
                } else {
                    greatAndGrand.append("great-");
                }
            }
        } else if (relationshipType.equals("aunt")) {
            for (int i=0; i<level-2; i++) {
                greatAndGrand.append("great-");
            }
        }

        return greatAndGrand.toString();
    }

    public static void main(String[] args) throws IOException {
        // input
        Scanner sc = new Scanner(new File("family.in"));

        numRelationships = sc.nextInt();
        String cow1 = sc.next();
        String cow2 = sc.next();
        relationships = new String[numRelationships][2];
        for (int i=0; i<numRelationships; i++) {
            relationships[i][0] = sc.next();
            relationships[i][1] = sc.next();
        }

        // algorithm
        String case1 = family(cow1, cow2);
        String case2 = family(cow2, cow1);

        // output
        PrintWriter out = new PrintWriter(new FileWriter("family.out"));

        if (case2.contains("the") && (case1.equals("COUSINS") || case1.equals("NOT RELATED"))) {
            out.println(case2);
        } else {
            out.println(case1);
        }

        out.close();
    }
}
