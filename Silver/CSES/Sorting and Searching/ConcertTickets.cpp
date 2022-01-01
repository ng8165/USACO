// Concert Tickets
// CSES Sorting and Searching: https://cses.fi/problemset/task/1091

#include <bits/stdc++.h>

using namespace std;

int main() {
    int n, m; cin >> n >> m;

    map<int, int> h;
    for (int i=0; i<n; i++) {
        int ticket; cin >> ticket;
        h[ticket]++;
    }

    for (int i=0; i<m; i++) {
        int t; cin >> t;

        auto it = h.upper_bound(t);

        if (it == h.begin()) {
            cout << "-1\n";
        } else {
            cout << (--it)->first << "\n";

            it->second--;
            if (it->second <= 0) h.erase(it);
        }
    }

    cout << flush;
}