// Static Range Sum Queries
// CSES Range Queries: https://cses.fi/problemset/task/1646

#include <bits/stdc++.h>

using namespace std;

const int maxn = 2e5+1;
int n, q;
long long x[maxn];

int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);

    cin >> n >> q;

    // create prefix sum
    for (int i=1; i<=n; i++) {
        cin >> x[i];
        x[i] += x[i-1];
    }

    for (int i=0; i<q; i++) {
        int a, b; cin >> a >> b;
        cout << x[b] - x[a-1] << "\n";
    }
}