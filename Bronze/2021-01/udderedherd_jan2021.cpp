// Uddered but not Herd
// USACO Bronze January 2021: http://www.usaco.org/index.php?page=viewproblem2&cpid=1083

#include <bits/stdc++.h>

using namespace std;

int idx[26];
string str;

int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);

    for (int i=0; i<26; i++) {
        char c; cin >> c;
        idx[c-'a'] = i;
    }

    cin >> str;

    int prev = 26; // out of bounds
    int result = 0;

    for (char c: str) {
        if (prev >= idx[c-'a'])
            result++;
        
        prev = idx[c-'a'];
    }

    cout << result << "\n";
}