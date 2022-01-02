// Diamond Collector
// USACO Silver 2016 US Open: http://www.usaco.org/index.php?page=viewproblem2&cpid=643
// XJOI Problem 15262: https://xjoi.net/contest/3353/problem/2?locale=en

#include <bits/stdc++.h>

using namespace std;

int n, k;
int diamonds[50001];
int valid[50001]; // for a valid[i], i = left, valid[i] = diamonds in case, i+valid[i]-1 = right

int main() {
    ifstream fin("diamond.in");
    ofstream fout("diamond.out");

    fin >> n >> k;
    for (int i=1; i<=n; i++) fin >> diamonds[i];

    sort(diamonds+1, diamonds+n+1);

    for (int left=1, right=1; left<=n; left++) {
        while (right <= n && diamonds[right] - diamonds[left] <= k) right++;
        valid[left] = right-left;
    }

    int result = 0;

    for (int i=1; i<=n; i++) {
        for (int j=i+valid[i]; j<=n; j++) {
            result = max(result, valid[i]+valid[j]);
        }
    }

    fout << result << endl;
}