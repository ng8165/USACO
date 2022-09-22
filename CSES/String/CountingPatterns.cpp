// Counting Patterns
// CSES String Algorithms: https://cses.fi/problemset/task/2103
// XJOI Problem 14218: https://xjoi.net/contest/4479/problem/3?locale=en

#include <bits/stdc++.h>

using namespace std;

typedef long long ll;

const ll PRIME = 29;
const ll MOD = 1e9+7;
vector<ll> pows = {1};
const int maxn = 1e5+1;
const int maxk = 5e5+1;

string str;
int n, k;
ll strHashes[maxn];
ll patterns[maxk];

unordered_map<int, unordered_set<ll>> lengthHashes;
unordered_map<ll, int> result;

inline ll mod(ll num) {
    // supports negative mod
    ll m = num % MOD;
    if (m < 0) return m + MOD;
    else return m;
}

string read() {
    string s; cin >> s;
    for (char& c: s)
        c -= 'a' - 1;
    return s;
}

ll generateHash(string& s) {
    // hash: for "pie", 29^2 * 'p' + 29^1 * 'i' + 29^0 * 'e'
    ll hash = 0;
    int len = s.length();

    for (int i=0; i<len; i++)
        hash = mod(hash + mod(pows[i] * s[len-i-1]));
    
    return hash;
}

void rollingHash(unordered_set<ll>& hashes, int len) {
    ll hash = 0;
    for (int i=0; i<len; i++)
        hash = mod(hash + mod(pows[len-i-1] * str[i]));
    
    if (hashes.count(hash))
        result[hash]++;

    for (int i=0; i<n-len; i++) {
        hash = mod(hash - mod(pows[len-1]*str[i])); // remove the i-th character
        hash = mod(hash * PRIME); // "shift" the hash
        hash = mod(hash + str[i+len]); // add the i+len-th character

        if (hashes.count(hash))
            result[hash]++;
    }
}

int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);

    str = read();
    n = str.length();
    cin >> k;

    // precompute all powers of PRIME
    for (int i=1; i<maxk; i++)
        pows.push_back(mod(pows.back() * PRIME));
    
    // read strings, generate hash, and place in map ordered by length
    for (int i=1; i<=k; i++) {
        string p = read();
        patterns[i] = generateHash(p);
        lengthHashes[p.length()].insert(patterns[i]);
    }

    for (auto& [len, hashes]: lengthHashes)
        rollingHash(hashes, len);

    for (int i=1; i<=k; i++)
        cout << result[patterns[i]] << "\n";
}