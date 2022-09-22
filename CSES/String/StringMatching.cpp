// String Matching
// CSES String Algorithms: https://cses.fi/problemset/task/1753
// XJOI Problem 14195: https://xjoi.net/contest/4450/problem/1?locale=en

#include <bits/stdc++.h>

using namespace std;

typedef long long ll;

const ll PRIME = 29;
const ll MOD = 1e9+7;
string s1, s2;
int n, m;
vector<ll> pows = {1};

ll mod(ll num) {
    // supports negative mod
    ll m = num % MOD;
    if (m < 0) return m + MOD;
    else return m;
}

void convert(string& s) {
    // converts 'a'-'z' in s to 1-26 (easier to use for hashing)
    for (char& c: s)
        c -= 'a' - 1;
}

ll generateHash(string& s, int& len) {
    // hash: for "pie", 29^2 * 'p' + 29^1 * 'i' + 29^0 * 'e'
    ll hash = 0;

    for (int i=0; i<len; i++)
        hash = mod(hash + mod(pows[i] * s[len-i-1]));
    
    return hash;
}

int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);

    // read strings and modify
    cin >> s1 >> s2;
    n = s1.length();
    m = s2.length();
    convert(s1);
    convert(s2);

    // precompute all powers of PRIME
    for (int i=1; i<m; i++)
        pows.push_back(mod(pows.back() * PRIME));
    
    // compute hash for s2 and the first m characters of s1
    ll hash1 = generateHash(s1, m);
    ll hash2 = generateHash(s2, m);

    // start rolling hash
    ll result = (hash1 == hash2); // if initially equal, set result as 1, otherwise 0

    for (int i=0; i<n-m; i++) {
        hash1 = mod(hash1 - mod(pows[m-1]*s1[i])); // remove the i-th character
        hash1 = mod(hash1 * PRIME); // "shift" the hash
        hash1 = mod(hash1 + s1[i+m]); // add the i+m-th character

        if (hash1 == hash2)
            result++;
    }

    cout << result << "\n";
}