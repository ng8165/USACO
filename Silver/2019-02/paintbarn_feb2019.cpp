// Painting the Barn
// USACO Silver February 2019: http://www.usaco.org/index.php?page=viewproblem2&cpid=919

#include <bits/stdc++.h>

using namespace std;

int n, k;
int prefix[1005][1005];

int main() {
    ifstream fin("paintbarn.in");
    ofstream fout("paintbarn.out");

    fin >> n >> k;
    for (int i=0; i<n; i++) {
        int x1, y1, x2, y2;
        fin >> x1 >> y1 >> x2 >> y2;

        prefix[x2][y2]++;
        prefix[x1][y1]++;
        prefix[x1][y2]--;
        prefix[x2][y1]--;
    }

    int result = 0;

    for (int i=0; i<=1000; i++) {
        for (int j=0; j<=1000; j++) {
            if (i>0) prefix[i][j] += prefix[i-1][j];
            if (j>0) prefix[i][j] += prefix[i][j-1];
            if (i>0 && j>0) prefix[i][j] -= prefix[i-1][j-1];

            if (prefix[i][j] == k) result++;
        }
    }

    fout << result << endl;
}