// Subordinates
// CSES Tree Algorithms: https://cses.fi/problemset/task/1674
// XJOI Problem 14124: https://xjoi.net/contest/3385/problem/1

#include <bits/stdc++.h>

using namespace std;

int n;
vector<int> child[200001];
int dp[200001];

void dfs(int e) {
    for (int c: child[e]) {
        dfs(c);
        dp[e] += (dp[c] + 1); // c itself and c's children
    }
}

int main() {
    cin >> n;
    for (int e=2; e<=n; e++) { // construct tree
        int b; cin >> b;
        child[b].push_back(e);
    }

    dfs(1);

    for (int i=1; i<=n; i++) cout << dp[i] << " ";
    cout << endl;
}