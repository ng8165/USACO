// Towers
// CSES Sorting and Searching: https://cses.fi/problemset/task/1073

#include <bits/stdc++.h>

using namespace std;

int n, k;
vector<int> towers;

int main() {
    cin >> n;

    for (int i=1; i<=n; i++) {
        cin >> k;

        auto it = upper_bound(towers.begin(), towers.end(), k);

        if (it == towers.end()) {
            // add a new tower
            towers.push_back(k);
        } else {
            // put smaller cube on top
            towers.erase(it);
            towers.insert(lower_bound(towers.begin(), towers.end(), k), k);
        }
    }

    cout << towers.size() << endl;
}