// Censoring (Bronze) - USACO Bronze February 2015 (http://www.usaco.org/index.php?page=viewproblem2&cpid=526)
// This problem was completed as classwork for the USACO Silver 1 Class on 4/22/21.

import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

public class L12_censor {
    public static void main(String[] args) throws IOException {
        // input
        String problemName = "censor";
        BufferedReader br = new BufferedReader(new FileReader(problemName + ".in"));

        char[] charArr = br.readLine().toCharArray();
        char[] str = br.readLine().toCharArray();

        // algorithm
        Deque<Character> stack = new ArrayDeque<>();

        for (char ch: charArr) {
            stack.push(ch);

            if (stack.size() > str.length) {
                Deque<Character> temp = new ArrayDeque<>();

                for (int i=0; i<str.length; i++) {
                    if (stack.peek() == str[str.length-i-1]) {
                        temp.push(stack.pop());
                    } else {
                        while (!temp.isEmpty()) {
                            stack.push(temp.pop());
                        }

                        break;
                    }
                }
            }
        }

        // output
        ArrayList<Character> result = new ArrayList<>();
        while (!stack.isEmpty()) {
            result.add(stack.pop());
        }

        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(problemName + ".out")));
        for (int i=result.size()-1; i>=0; i--) {
            pw.print(result.get(i));
        }

        br.close();
        pw.close();
    }
}
