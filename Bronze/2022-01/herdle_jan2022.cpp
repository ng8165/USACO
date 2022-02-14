// Herdle
// USACO Bronze January 2022: http://www.usaco.org/index.php?page=viewproblem2&cpid=1179

#include <bits/stdc++.h>

using namespace std;

char correct[3][3];
int correctChars[26];
char guess[3][3];
int guessChars[26];

int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);

    for (int i=0; i<3; i++) {
        for (int j=0; j<3; j++)
            cin >> correct[i][j];
    }

    for (int i=0; i<3; i++) {
        for (int j=0; j<3; j++)
            cin >> guess[i][j];
    }

    int green = 0, yellow = 0;

    for (int i=0; i<3; i++) {
        for (int j=0; j<3; j++) {
            if (correct[i][j] == guess[i][j]) {
                green++;
            } else {
                correctChars[correct[i][j]-'A']++;
                guessChars[guess[i][j] - 'A']++;
            }
        }
    }

    for (int i=0; i<26; i++)
        yellow += min(correctChars[i], guessChars[i]);

    cout << green << "\n" << yellow << "\n";
}