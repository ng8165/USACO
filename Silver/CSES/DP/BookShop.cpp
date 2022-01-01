#include <bits/stdc++.h>

using namespace std;

int n, x;
int h[1005];
int s[1005];

int dp[1005][100005];

int main() {
    cin >> n >> x;
    for (int i=1; i<=n; i++) cin >> h[i];
    for (int i=1; i<=n; i++) cin >> s[i];

    memset(dp[0], 0, sizeof(dp[0]));

    for (int i=1; i<=n; i++) {
        for (int j=0; j<=x; j++) {
            dp[i][j] = dp[i-1][j]; // using j money to buy i-1 books

            int oldPrice = j-h[i]; // oldPrice is curr money minus money to buy book i
            
            if (oldPrice >= 0) {
                // use oldPrice money to buy i-1 books and add pages from book i
                dp[i][j] = max(dp[i-1][j], dp[i-1][oldPrice]+s[i]);
            }
        }
    }

    cout << dp[n][x] << endl;
}