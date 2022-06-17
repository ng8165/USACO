#include <bits/stdc++.h>

using namespace std;

int k, n, rankings[15][25];

bool isConsistent(int c1, int c2) {
    for (int i=1; i<=k; i++) {
        for (int j=1; j<=n; j++) {
            if (rankings[i][j] == c1) break; // c1 found first, c2 must be after
            else if (rankings[i][j] == c2) return false; // c2 found first, not consistent
        }
    }

    return true;
}

int main() {
    ifstream fin("gymnastics.in");
    ofstream fout("gymnastics.out");

    fin >> k >> n;
    for (int i=1; i<=k; i++) {
        for (int j=1; j<=n; j++) {
            fin >> rankings[i][j];
        }
    }

    int result = 0;

    for (int c1=1; c1<=n; c1++) {
        for (int c2=1; c2<=n; c2++) {
            if (c1 == c2) continue;

            if (isConsistent(c1, c2)) result++;
        }
    }

    fout << result << endl;
}