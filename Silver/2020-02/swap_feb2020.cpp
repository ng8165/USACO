// Swapity Swapity Swap
// USACO Silver February 2020: http://www.usaco.org/index.php?page=viewproblem2&cpid=1014

#include <bits/stdc++.h>

using namespace std;

const int maxN = 100000+1;

int N, M, K;

int cows[maxN]; // cows stores which cow is at each position
int order[maxN]; // order[i] = the index of the ith cow after the M-step swap
int result[maxN];

int main() {
    ifstream fin("swap.in");
    ofstream fout("swap.out");

    fin >> N >> M >> K;

    for (int i=1; i<=N; i++) cows[i] = i;

    for (int i=1; i<=M; i++) {
        int L, R; fin >> L >> R;
        reverse(cows+L, cows+R+1);
    }

    for (int i=1; i<=N; i++) order[cows[i]] = i;

    for (int i=1; i<=N; i++) {
        if (result[i] > 0) continue;

        vector<int> cycle;

        int curr = i; 
        do {
            cycle.push_back(curr);
            curr = order[curr];
        } while (curr != i);

        for (int j=0; j<cycle.size(); j++)
            result[cycle[(j+K)%cycle.size()]] = cycle[j];
    }

    for (int i=1; i<=N; i++)
        fout << result[i] << "\n";
}