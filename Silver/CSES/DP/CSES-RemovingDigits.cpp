#include <bits/stdc++.h>

using namespace std;

int n;

int dp[1000005];

int main() {
    cin >> n;

    dp[0] = 0;

    for (int i=1; i<=n; i++) {
        int temp = i;
        dp[i] = INT_MAX;

        while (temp > 0) {
            dp[i] = min(dp[i], dp[i-temp%10]);
            temp /= 10;
        }

        dp[i]++;
    }

    cout << dp[n] << endl;
}