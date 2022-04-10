// COW Operations
// USACO Silver US Open 2022: http://www.usaco.org/index.php?page=viewproblem2&cpid=1232

#include <bits/stdc++.h>

using namespace std;

const int maxLen = 2e5+1;

string s;
int Q;
int l, r;

int csum[maxLen];
int osum[maxLen];
int wsum[maxLen];

int cCount, oCount, wCount;

void solve() {
    cin >> l >> r;

    cCount = csum[r]-csum[l-1];
    oCount = osum[r]-osum[l-1];
    wCount = wsum[r]-wsum[l-1];

    cCount %= 2;
    oCount %= 2;
    wCount %= 2;

    if (cCount == 1 && oCount == 0 && wCount == 0) cout << "Y";
    else if (cCount == 0 && oCount == 1 && wCount == 1) cout << "Y";
    else cout << "N";
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