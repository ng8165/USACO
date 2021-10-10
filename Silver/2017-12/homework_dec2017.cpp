#include <bits/stdc++.h>

using namespace std;

int main() {
    ifstream fin("homework.in");
    ofstream fout("homework.out");

    int n;
    fin >> n;

    int score[n];
    for (int& s: score) fin >> s;

    int ssum[n+1]; ssum[n] = 0;
    int smin[n+1]; smin[n] = 10000; // max score
    for (int i=n-1; i>=0; i--) {
        ssum[i] = ssum[i+1] + score[i];
        smin[i] = min(smin[i+1], score[i]);
    }

    vector<int> result;
    double maxScore = 0.0;

    for (int k=1; k<=n-2; k++) {
        double score = (ssum[k]-smin[k])/(n-k-1.0);

        if (score > maxScore) {
            maxScore = score;
            result.clear();
            result.push_back(k);
        } else if (score == maxScore) result.push_back(k);
    }

    for (int& k: result) fout << k << endl;
}