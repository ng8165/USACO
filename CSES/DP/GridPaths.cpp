// Grid Paths
// CSES Dynamic Programming: https://cses.fi/problemset/task/1638

#include <bits/stdc++.h>

using namespace std;

const int MOD = 1e9+7;
int n;
char grid[1005][1005];

int dp[1005][1005];

int main() {
    cin >> n;

    for (int i=1; i<=n; i++) {
        for (int j=1; j<=n; j++) {
            cin >> grid[i][j];
        }
    }

    if (grid[1][1] == '*') {
        cout << "0" << endl;
        return 0;
    }

    dp[1][1] = 1;

    for (int i=1; i<=n; i++) {
        for (int j=1; j<=n; j++) {
            if (i==1 && j==1) continue;

            dp[i][j] = 0;

            if (grid[i][j] == '*') continue;

            if (i>1) dp[i][j] = (dp[i][j] + dp[i-1][j]) % MOD;
            if (j>1) dp[i][j] = (dp[i][j] + dp[i][j-1]) % MOD;
        }
    }

    cout << dp[n][n] << endl;
}