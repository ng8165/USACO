// COW Operations
// USACO Silver US Open 2022: http://www.usaco.org/index.php?page=viewproblem2&cpid=1232

#include <bits/stdc++.h>

using namespace std;

const int maxLen = 2e5+1;

string s;
int Q;
int l, r;

int csum[maxLen], osum[maxLen], wsum[maxLen];

void solve() {
    cin >> l >> r;

    // determine number of characters in the range
    // if the numbers are [odd, even, even] or [even, odd, odd], then can be turned to 'C'
    int c = (csum[r]-csum[l-1])%2;
    int o = (osum[r]-osum[l-1])%2;
    int w = (wsum[r]-wsum[l-1])%2;

    if (c == 1 && o == 0 && w == 0)
        cout << "Y";
    else if (c == 0 && o == 1 && w == 1)
        cout << "Y";
    else
        cout << "N";
}

int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);

    cin >> s;

    // generate prefix sums for C, O, and W
    for (int i=0; i<s.size(); i++) {
        csum[i+1] = csum[i] + (s[i] == 'C');
        osum[i+1] = osum[i] + (s[i] == 'O');
        wsum[i+1] = wsum[i] + (s[i] == 'W');
    }

    cin >> Q;

    for (int i=0; i<Q; i++)
        solve();

    cout << "\n";
}