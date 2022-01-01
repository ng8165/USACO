#include <bits/stdc++.h>

using namespace std;

int n, k;
int h[100005];

int dp[100005];

int main() {
    cin >> n >> k;

    for (int i=1; i<=n; i++) cin >> h[i];

    dp[1] = 0;

    for (int i=2; i<=n; i++) {
        dp[i] = INT_MAX;
        
        for (int j=max(1, i-k); j<i; j++) {
            dp[i] = min(dp[i], dp[j] + abs(h[i] - h[j]));
        }
    }

    cout << dp[n] << endl;
}