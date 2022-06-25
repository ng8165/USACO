// Coin Combinations II
// CSES DP: https://cses.fi/problemset/task/1636

#include <bits/stdc++.h>

using namespace std;

const int maxn = 100+1;
const int maxx = 1e6+1;
const int MOD = 1e9+7;

int n, x;
int c[maxn];
int dp[maxx];

int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);

    cin >> n >> x;
    for (int i=1; i<=n; i++)
        cin >> c[i];
    
    sort(c+1, c+n+1);
    dp[0] = 1;

    for (int i=1; i<=n; i++) {
        for (int j=c[i]; j<=x; j++) {
            dp[j] += dp[j-c[i]];
            dp[j] %= MOD;
        }
    }

    cout << dp[x] << "\n";
}