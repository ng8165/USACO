// Team Tic Tac Toe - USACO Bronze US Open 2018 (http://www.usaco.org/index.php?page=viewproblem2&cpid=831)
// This problem was completed on October 11, 2020, in 33 minutes, with all 10/10 test cases passed (first try)
// This problem was completed on October 17, 2020, in review, with all 10/10 test cases passed (review)

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class tttt_open2018 {
    static char[][] gameboard = new char[3][3];

    public static int[] tttt() {
        // first find the number of cows
        ArrayList<Character> cows = new ArrayList<>();

        for (int i=0; i<3; i++) {
            for (int j=0; j<3; j++) {
                if (searchDuplicateCows(gameboard[i][j], cows)) {
                    cows.add(gameboard[i][j]);
                }
            }
        }

        System.out.println(cows + "\n");

        // now find the number of cows that could win individually
        int individualCowWinsCnt = 0;

        for (char currCow: cows) {
            if (individualCowWins(currCow)) {
                System.out.println(currCow);
                individualCowWinsCnt++;
            }
        }

        // now find the number of cows that could win in a team
        int cowTeamWinsCnt = 0;
        for (int i=0; i<cows.size(); i++) {
            for (int j=i+1; j<cows.size(); j++) {
                if (cowTeamWins(cows.get(i), cows.get(j))) {
                    cowTeamWinsCnt++;
                    System.out.println(cows.get(i) + " " + cows.get(j));
                }
            }
        }

        return new int[] {individualCowWinsCnt, cowTeamWinsCnt};
    }
    public static boolean searchDuplicateCows(char cow, ArrayList<Character> cows) {
        for (int i=0; i<cows.size(); i++) {
            if (cows.get(i) == cow) {
                return false;
            }
        }

        return true;
    }
    public static boolean individualCowWins(char cow) {
        if ((gameboard[0][0] == cow) && (gameboard[0][1] == cow) && (gameboard[0][2] == cow)) {
            return true;
        } else if ((gameboard[1][0] == cow) && (gameboard[1][1] == cow) && (gameboard[1][2] == cow)) {
            return true;
        } else if ((gameboard[2][0] == cow) && (gameboard[2][1] == cow) && (gameboard[2][2] == cow)) {
            return true;
        } else if ((gameboard[0][0] == cow) && (gameboard[1][0] == cow) && (gameboard[2][0] == cow)) {
            return true;
        } else if ((gameboard[0][1] == cow) && (gameboard[1][1] == cow) && (gameboard[2][1] == cow)) {
            return true;
        } else if ((gameboard[0][2] == cow) && (gameboard[1][2] == cow) && (gameboard[2][2] == cow)) {
            return true;
        } else if ((gameboard[0][0] == cow) && (gameboard[1][1] == cow) && (gameboard[2][2] == cow)) {
            return true;
        } else if ((gameboard[0][2] == cow) && (gameboard[1][1] == cow) && (gameboard[2][0] == cow)) {
            return true;
        } else {
            return false;
        }
    }
    public static boolean cowTeamWins(char cow1, char cow2) {
        if ((gameboard[0][0] == cow1 || gameboard[0][0] == cow2) && (gameboard[0][1] == cow1 || gameboard[0][1] == cow2) && (gameboard[0][2] == cow1 || gameboard[0][2] == cow2)) {
            String line = gameboard[0][0] + "" + gameboard[0][1] + "" + gameboard[0][2];
            if (line.indexOf(cow1) != -1 && line.indexOf(cow2) != -1) {
                return true;
            }
        } if ((gameboard[1][0] == cow1 || gameboard[1][0] == cow2) && (gameboard[1][1] == cow1 || gameboard[1][1] == cow2) && (gameboard[1][2] == cow1 || gameboard[1][2] == cow2)) {
            String line = gameboard[1][0] + "" + gameboard[1][1] + "" + gameboard[1][2];
            if (line.indexOf(cow1) != -1 && line.indexOf(cow2) != -1) {
                return true;
            }
        } if ((gameboard[2][0] == cow1 || gameboard[2][0] == cow2) && (gameboard[2][1] == cow1 || gameboard[2][1] == cow2) && (gameboard[2][2] == cow1 || gameboard[2][2] == cow2)) {
            String line = gameboard[2][0] + "" + gameboard[2][1] + "" + gameboard[2][2];
            if (line.indexOf(cow1) != -1 && line.indexOf(cow2) != -1) {
                return true;
            }
        } if ((gameboard[0][0] == cow1 || gameboard[0][0] == cow2) && (gameboard[1][0] == cow1 || gameboard[1][0] == cow2) && (gameboard[2][0] == cow1 || gameboard[2][0] == cow2)) {
            String line = gameboard[0][0] + "" + gameboard[1][0] + "" + gameboard[2][0];
            if (line.indexOf(cow1) != -1 && line.indexOf(cow2) != -1) {
                return true;
            }
        } if ((gameboard[0][1] == cow1 || gameboard[0][1] == cow2) && (gameboard[1][1] == cow1 || gameboard[1][1] == cow2) && (gameboard[2][1] == cow1 || gameboard[2][1] == cow2)) {
            String line = gameboard[0][1] + "" + gameboard[1][1] + "" + gameboard[2][1];
            if (line.indexOf(cow1) != -1 && line.indexOf(cow2) != -1) {
                return true;
            }
        } if ((gameboard[0][2] == cow1 || gameboard[0][2] == cow2) && (gameboard[1][2] == cow1 || gameboard[1][2] == cow2) && (gameboard[2][2] == cow1 || gameboard[2][2] == cow2)) {
            String line = gameboard[0][2] + "" + gameboard[1][2] + "" + gameboard[2][2];
            if (line.indexOf(cow1) != -1 && line.indexOf(cow2) != -1) {
                return true;
            }
        } if ((gameboard[0][0] == cow1 || gameboard[0][0] == cow2) && (gameboard[1][1] == cow1 || gameboard[1][1] == cow2) && (gameboard[2][2] == cow1 || gameboard[2][2] == cow2)) {
            String line = gameboard[0][0] + "" + gameboard[1][1] + "" + gameboard[2][2];
            if (line.indexOf(cow1) != -1 && line.indexOf(cow2) != -1) {
                return true;
            }
        } if ((gameboard[0][2] == cow1 || gameboard[0][2] == cow2) && (gameboard[1][1] == cow1 || gameboard[1][1] == cow2) && (gameboard[2][0] == cow1 || gameboard[2][0] == cow2)) {
            String line = gameboard[0][2] + "" + gameboard[1][1] + "" + gameboard[2][0];
            if (line.indexOf(cow1) != -1 && line.indexOf(cow2) != -1) {
                return true;
            }
        }

        return false;

    }

    public static int[] tttt2() {
        // find individual wins first
        int individualWins = 0;

        for (int i=0; i<3; i++) {
            if ((gameboard[i][0] == gameboard[i][1]) && (gameboard[i][1] == gameboard[i][2])) {
                individualWins++;
            }
        }
        for (int i=0; i<3; i++) {
            if ((gameboard[0][i] == gameboard[1][i]) && (gameboard[1][i] == gameboard[2][i])) {
                individualWins++;
            }
        }
        if ((gameboard[0][0] == gameboard[1][1]) && (gameboard[1][1] == gameboard[2][2])) {
            individualWins++;
        }

        if ((gameboard[0][2] == gameboard[1][1]) && (gameboard[1][1] == gameboard[2][0])) {
            individualWins++;
        }

        // find the team wins second
        int teamWins = 0;

        for (int i=0; i<3; i++) {
            if (numDistinctCows(gameboard[i][0], gameboard[i][1], gameboard[i][2]) == 2) {
                teamWins++;
            }
        }
        for (int i=0; i<3; i++) {
            if (numDistinctCows(gameboard[0][i], gameboard[1][i], gameboard[2][i]) == 2) {
                teamWins++;
            }
        }
        if (numDistinctCows(gameboard[0][0], gameboard[1][1], gameboard[2][2]) == 2) {
            teamWins++;
        }

        if (numDistinctCows(gameboard[0][2], gameboard[1][1], gameboard[2][0]) == 2) {
            teamWins++;
        }

        return new int[] {individualWins, teamWins};
    }
    public static int numDistinctCows(char c1, char c2, char c3) {
        int numDistinctCows = 1; // c1 is already added in

        if (c2 != c1) {
            numDistinctCows++;
        }
        if (c3 != c2 && c3 != c1) {
            numDistinctCows++;
        }

        //System.out.println(numDistinctCows);

        return numDistinctCows;
    }

    public static int[] tttt3() {
        // first search for individual wins
        int individualWins = 0;

        for (char cow = 'A'; cow <= 'Z'; cow++) {
            if (individualCowWins2(cow)) {
                individualWins++;
            }
        }

        // after that search for team wins
        int teamWins = 0;

        for (char cow1 = 'A'; cow1 <= 'Z'; cow1++) {
            for (char cow2 = (char)(cow1+1); cow2 <= 'Z'; cow2++) {
                if (cowTeamWins2(cow1, cow2)) {
                    System.out.println(cow1 + "" + cow2);
                    teamWins++;
                }
            }
        }

        return new int[] {individualWins, teamWins};
    }
    public static boolean individualCowWins2(char cow) {
        // search rows
        for (int i=0; i<3; i++) {
            if ((gameboard[i][0] == cow) && (gameboard[i][1] == cow) && (gameboard[i][2] == cow)) {
                return true;
            }
        }

        // search columns
        for (int i=0; i<3; i++) {
            if ((gameboard[0][i] == cow) && (gameboard[1][i] == cow) && (gameboard[2][i] == cow)) {
                return true;
            }
        }

        // search diagonals
        if ((gameboard[0][0] == cow) && (gameboard[1][1] == cow) && (gameboard[2][2] == cow)) {
            return true;
        }
        if ((gameboard[0][2] == cow) && (gameboard[1][1] == cow) && (gameboard[2][0] == cow)) {
            return true;
        }

        return false;
    }
    public static boolean cowTeamWins2(char cow1, char cow2) {
        // search rows
        for (int i=0; i<3; i++) {
            int cow1Cnt = 0;
            int cow2Cnt = 0;

            for (int j=0; j<3; j++) {
                if (gameboard[i][j] == cow1) {
                    cow1Cnt++;
                } else if (gameboard[i][j] == cow2) {
                    cow2Cnt++;
                }
            }

            if ((cow1Cnt + cow2Cnt == 3) && (cow1Cnt > 0) && (cow2Cnt > 0)) {
                return true;
            }
        }

        // search columns
        for (int i=0; i<3; i++) {
            int cow1Cnt = 0;
            int cow2Cnt = 0;

            for (int j=0; j<3; j++) {
                if (gameboard[j][i] == cow1) {
                    cow1Cnt++;
                } else if (gameboard[j][i] == cow2) {
                    cow2Cnt++;
                }
            }

            if ((cow1Cnt + cow2Cnt == 3) && (cow1Cnt > 0) && (cow2Cnt > 0)) {
                return true;
            }
        }

        // search diagonals
        {
            int cow1Cnt = 0;
            int cow2Cnt = 0;

            for (int i=0; i<3; i++) {
                if (gameboard[i][i] == cow1) {
                    cow1Cnt++;
                } else if (gameboard[i][i] == cow2) {
                    cow2Cnt++;
                }
            }

            if ((cow1Cnt + cow2Cnt == 3) && (cow1Cnt > 0) && (cow2Cnt > 0)) {
                return true;
            }
        }
        {
            int cow1Cnt = 0;
            int cow2Cnt = 0;

            for (int i=0; i<3; i++) {
                if (gameboard[i][2-i] == cow1) {
                    cow1Cnt++;
                } else if (gameboard[i][2-i] == cow2) {
                    cow2Cnt++;
                }
            }

            if ((cow1Cnt + cow2Cnt == 3) && (cow1Cnt > 0) && (cow2Cnt > 0)) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) throws IOException {
        // input
        String problemName = "tttt";
        Scanner sc = new Scanner(new File(problemName + ".in"));

        for (int i=0; i<3; i++) {
            gameboard[i] = sc.nextLine().toCharArray();
        }

        // algorithm
        int[] cowVictories = tttt3();

        // output
        PrintWriter out = new PrintWriter(new FileWriter(problemName + ".out"));
        out.println(cowVictories[0]);
        out.println(cowVictories[1]);
        out.close();
    }
}
