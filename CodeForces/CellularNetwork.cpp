// Cellular Network
// CodeForces Edu Round 15 Problem C: https://codeforces.com/contest/702/problem/C

#include <bits/stdc++.h>

using namespace std;

int n, m;
set<int> a; // cities
set<int> b; // cell towers

int main() {
    cin >> n >> m;
    for (int i=1; i<=n; i++) {
        int x; cin >> x;
        a.insert(x);
    }
    for (int i=1; i<=m; i++) {
        int x; cin >> x;
        b.insert(x);
    }

    int r = 0;

    for (int c: a) {
        auto it = b.lower_bound(c);

        int minDist;

        if (it == b.begin()) {
            minDist = abs(c - *it);
        } else if (it == b.end()) {
            minDist = abs(c - *(--it));
        } else {
            minDist = abs(c - *it); it--;
            minDist = min(minDist, abs(c - *it));
        }
        
        r = max(r, minDist);
    }

    cout << r << endl;
}