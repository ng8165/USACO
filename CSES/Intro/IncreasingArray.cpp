// Increasing Array
// CSES Introductory Problems: https://cses.fi/problemset/task/1094

#include <bits/stdc++.h>

using namespace std;

int n, x;

int main() {
    cin >> n;

    int prev; cin >> prev;
    long long result = 0;

    for (int i=1; i<n; i++) {
        cin >> x;

        if (prev > x) {
            result += prev - x;
            x = prev;
        }

        prev = x;
    }

    cout << result << endl;
}