// Dice Combinations
// CSES Dynamic Programming: https://cses.fi/problemset/task/1633

#include <bits/stdc++.h>

using namespace std;

const int MOD = 1000000007;

int n;

int dp[1000005];

int main() {
    cin >> n;

    for (int i=1; i<=n; i++) {
        dp[i] = 0;
        if (i >= 1 && i <= 6) dp[i] = 1;

        for (int j=max(1, i-6); j<i; j++) {
            dp[i] = (dp[i] + dp[j]) % MOD;
        }
    }

    cout << dp[n] << endl;
}