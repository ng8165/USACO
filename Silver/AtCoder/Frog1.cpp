#include <bits/stdc++.h>

using namespace std;

int n;
int h[100005];

int dp[100005];

inline int cost(int idx1, int idx2) {
    return abs(h[idx1] - h[idx2]);
}

int main() {
    cin >> n;

    for (int i=1; i<=n; i++) cin >> h[i];

    dp[1] = 0;
    dp[2] = cost(1, 2);

    for (int i=3; i<=n; i++) {
        dp[i] = min(dp[i-1]+cost(i, i-1), dp[i-2]+cost(i, i-2));
    }

    cout << dp[n] << endl;
}