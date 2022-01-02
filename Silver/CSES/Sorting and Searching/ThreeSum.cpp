// Three Sum
// CSES Sorting & Searching: https://cses.fi/problemset/task/1641
// XJOI Problem 14096: https://xjoi.net/contest/3335/problem/2?locale=en

#include <bits/stdc++.h>

using namespace std;

typedef pair<int, int> pii;

int n, x;
pii a[5001];

int main() {
    cin >> n >> x;
    for (int i=1; i<=n; i++) {
        int v; cin >> v;
        a[i] = make_pair(v, i);
    }
    sort(a+1, a+n+1);

    for (int i=1; i<=n; i++) {
        int j = i+1, k = n; // j is left, k is right

        while (j < k) {
            int sum = a[i].first + a[j].first + a[k].first;

            if (sum == x) {
                cout << a[i].second << " " << a[j].second << " " << a[k].second << endl;
                return 0;
            }

            if (sum < x) j++;
            else k--;
        }
    }

    cout << "IMPOSSIBLE" << endl;
}