// Collecting Numbers
// CSES Sorting and Searching: https://cses.fi/problemset/task/2216

#include <bits/stdc++.h>

using namespace std;

int main() {
    int n, x;
    cin >> n;

    set<int> s;

    for (int i=0; i<n; i++) {
        cin >> x;

        if (s.count(x-1)) {
            s.erase(x-1);
        }

        s.insert(x);
    }

    cout << s.size() << endl;
}