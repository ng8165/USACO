// Moo Sick - USACO Bronze November 2011 (http://www.usaco.org/index.php?page=viewproblem2&cpid=86)
// This problem was completed as homework (with automation mode) for the USACO Silver 2 Class on 7/1/21.

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class L17_moosick {
    static String directoryName = "moosick";

    public static boolean isChord(int[] subArray, int[] chord) {
        Arrays.sort(subArray); // fix reordering

        int diff = subArray[0] - chord[0];

        for (int i=1; i<subArray.length; i++) {
            if (chord[i] + diff != subArray[i]) {
                return false;
            }
        }

        return true;
    }

    public static void solve(int testCaseIdx) throws IOException {
        // input
        BufferedReader br = new BufferedReader(new FileReader(directoryName + "/I." + testCaseIdx));

        int numNotes = Integer.parseInt(br.readLine());
        int[] notes = new int[numNotes];
        for (int i=0; i<numNotes; i++) {
            notes[i] = Integer.parseInt(br.readLine());
        }

        int chordLength = Integer.parseInt(br.readLine());
        int[] chord = new int[chordLength];
        for (int i=0; i<chordLength; i++) {
            chord[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(chord);

        // algorithm
        ArrayList<Integer> chordIdx = new ArrayList<>();

        for (int left=0; left<=numNotes-chordLength; left++) {
            if (isChord(Arrays.copyOfRange(notes, left, left+chordLength), chord)) {
                chordIdx.add(left);
            }
        }

        // output
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(directoryName + "/MyO." + testCaseIdx)));

        pw.println(chordIdx.size());
        for (int idx: chordIdx) {
            pw.println(idx+1);
        }

        br.close();
        pw.close();
    }

    public static boolean isCorrect(int testCaseIdx) throws IOException {
        // run algorithm
        long before = System.currentTimeMillis();
        solve(testCaseIdx);
        long after = System.currentTimeMillis();

        // read both outputs
        BufferedReader brOut = new BufferedReader(new FileReader(directoryName + "/O." + testCaseIdx));
        BufferedReader brMyOut = new BufferedReader(new FileReader(directoryName + "/MyO." + testCaseIdx));
        boolean isSame = true;

        while (true) {
            String correctResult = brOut.readLine();
            String myResult = brMyOut.readLine();

            if (correctResult == null && myResult == null) {
                // both outputs ended
                break;
            } else if ((correctResult == null ^ myResult == null) || !correctResult.equals(myResult)) {
                isSame = false;
                break;
            }
        }

        brOut.close();
        brMyOut.close();

        System.out.print("Test Case " + testCaseIdx);

        if (isSame) {
            System.out.println(": Correct - took " + (after-before) + " milliseconds");
            return true;
        } else {
            System.out.println(": Incorrect - took " + (after-before) + " milliseconds");
            return false;
        }
    }

    public static void main(String[] args) throws IOException {
        int numCorrect = 0;

        for (int i=1; i<=10; i++) {
            if (isCorrect(i)) {
                numCorrect++;
            }
        }

        System.out.println("\n" + numCorrect + " correct, " + (10-numCorrect) + " incorrect");
    }
}