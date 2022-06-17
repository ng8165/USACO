// Subarray Sums II
// CSES Sorting and Searching: https://cses.fi/problemset/task/1661

#include <bits/stdc++.h>

using namespace std;

typedef long long ll;

int n, x;
map<ll, int> m;

int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);

    cin >> n >> x;

    ll result = 0, psum = 0;

    for (int i=0; i<n; i++) {
        int a; cin >> a;
        psum += a;

        if (psum == x)
            result++;

        if (m.count(psum-x))
            result += m[psum-x];
        
        m[psum]++;
    }

    cout << result << endl;
}