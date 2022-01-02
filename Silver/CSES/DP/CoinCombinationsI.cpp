// Coin Combinations I
// CSES Dynamic Programming: https://cses.fi/problemset/task/1635

#include <bits/stdc++.h>

using namespace std;

const int MOD = 1000000007;
int n, x;
int c[105];

int dp[1000005];

int main() {
    cin >> n >> x;
    
    for (int i=1; i<=n; i++) cin >> c[i];

    dp[0] = 1;

    for (int i=1; i<=x; i++) {
        dp[i] = 0;

        for (int j=1; j<=n; j++) {
            if (i-c[j] < 0) continue;

            dp[i] = (dp[i] + dp[i-c[j]]) % MOD;
        }
    }

    cout << dp[x] << endl;
}