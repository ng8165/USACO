#include <bits/stdc++.h>

using namespace std;

string directory = "stacking/";

void stacking(string testCase) {
    ifstream fin(directory + testCase + ".in");
    ofstream fout(directory + testCase + ".myout");

    int n, k;
    fin >> n >> k;

    int diff[n+1];
    memset(diff, 0, sizeof(diff));

    for (int i=0; i<k; i++) {
        int l, r;
        fin >> l >> r;

        diff[l]++;
        diff[r+1]--;
    }

    int prefix[n+1]; prefix[0] = 0;
    for (int i=1; i<=n; i++) prefix[i] = prefix[i-1] + diff[i];

    sort(prefix+1, prefix+n+1);
    fout << prefix[n/2+1] << endl;
}

int main() {
    // automation mode (download test data and put in same folder as .cpp file)

    int correctCnt = 0, wrongCnt = 0;

    for (int i=1; i<=10; i++) {
        string testCase = to_string(i);
        
        ifstream fin1(directory + testCase + ".out");
        ifstream fin2(directory + testCase + ".myout");

        stacking(testCase);

        int correct; fin1 >> correct;
        int my; fin2 >> my;

        cout << "Test Case " << testCase << ": ";

        if (my == correct) {
            cout << "Correct, both were " << to_string(my) << endl;
            correctCnt++;
        } else {
            cout << "Incorrect" << endl;
            wrongCnt++;
        }
    }

    cout << endl << to_string(correctCnt) << " Correct, " << to_string(wrongCnt) << " Incorrect" << endl;
}