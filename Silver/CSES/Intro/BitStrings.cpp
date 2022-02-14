// Bit Strings
// CSES Introductory Problems: https://cses.fi/problemset/task/1617

#include <bits/stdc++.h>

using namespace std;

const int MOD = 1e9 + 7;
int n;

int main() {
    cin >> n;

    int result = 1;

    for (int i=0; i<n; i++)
        result = (result << 1) % MOD;

    cout << result << endl;
}