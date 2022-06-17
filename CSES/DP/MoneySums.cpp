// Money Sums
// CSES DP Problem Set: https://cses.fi/problemset/task/1745/

#include <bits/stdc++.h>

using namespace std;

int n;
int x[101];

int main() {
    cin >> n;
    for (int i=1; i<=n; i++) cin >> x[i];

    bitset<100001> dp; // MAX_N*MAX_X = 100*1000 = 100000

    for (int i=1; i<=n; i++) {
        dp |= dp << x[i];
        dp.set(x[i]);
    }

    int k = 0;
    vector<int> sums;
    for (int i=1; i<=100000; i++) {
        if (dp[i]) {
            k++;
            sums.push_back(i);
        }
    }

    cout << k << endl;
    for (int sum: sums) cout << sum << " ";
    cout << endl;
}