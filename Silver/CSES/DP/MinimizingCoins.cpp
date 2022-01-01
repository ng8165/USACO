#include <bits/stdc++.h>

using namespace std;

int n, x;
int c[105];

int dp[1000005];

int main() {
    cin >> n >> x;

    for (int i=1; i<=n; i++) cin >> c[i];

    dp[0] = 0;

    for (int i=1; i<=x; i++) {
        int minCoins = INT_MAX;

        for (int j=1; j<=n; j++) {
            if (i-c[j] < 0 || dp[i-c[j]] == -1) continue;
            minCoins = min(minCoins, 1+dp[i-c[j]]);
        }

        if (minCoins == INT_MAX) dp[i] = -1;
        else dp[i] = minCoins;
    }

    cout << dp[x] << endl;
}