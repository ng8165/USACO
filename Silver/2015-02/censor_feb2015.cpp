// Censoring (Silver)
// USACO Silver February 2015: http://www.usaco.org/index.php?page=viewproblem2&cpid=529
// XJOI Problem 15193: https://xjoi.net/contest/4601/problem/2?locale=en

#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
#define rLen result.length()

const ll PRIME = 29;
const ll MOD = 1e9+7;
vector<ll> pows = {1};

inline ll mod(ll num) {
    // supports negative mod
    ll m = num % MOD;
    if (m < 0) return m + MOD;
    else return m;
}

inline void convert(string& s) {
    for (char& c: s)
        c -= 'a'-1;
}

ll hashStr(string& s, int from, int to) {
    int len = to-from+1;
    ll hash = 0;

    for (int i=from; i<=to && i<s.length(); i++)
        hash = mod(hash + mod(pows[--len] * s[i]));
    
    return hash;
}

string S, T;
int n, m;

int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);

    // freopen("censor.in", "r", stdin);
    // freopen("censor.out", "w", stdout);

    cin >> S >> T;
    n = S.length();
    m = T.length();
    convert(S); convert(T);

    // precompute all powers of PRIME
    for (int i=1; i<m; i++)
        pows.push_back(mod(pows.back() * PRIME));
    
    // hash T and first m characters of S
    ll hashT = hashStr(T, 0, m-1);
    ll hashRes = hashStr(S, 0, m-1);
    string result = S.substr(0, m);

    // start rebuilding S without T into result
    int i = m;
    while (i < n) {
        if (hashT == hashRes) {
            // if hashes are equal, remove the last m characters from the result
            // then rehash the last m characters from result

            result.erase(rLen-m, m);

            while (rLen < m && i < n)
                result += S[i++];
                        
            hashRes = hashStr(result, rLen-m, rLen-1);
        } else {
            // if not equal, execute rolling hash

            hashRes = mod(hashRes - mod(pows[m-1]*result[rLen-m])); // remove the last character
            hashRes = mod(hashRes * PRIME); // "shift" the hash
            hashRes = mod(hashRes + S[i]); // add the next character
            result += S[i++];
        }
    }

    // special case: match of T in the last position of S
    if (hashT == hashRes)
        result.erase(rLen-m, m);
    
    // reconvert result back to 'a'-'z'
    for (char& c: result)
        cout << (char) (c+'a'-1);
    cout << "\n";
}