// Palindrome Queries
// CSES String Algorithms: https://cses.fi/problemset/task/2420/
// XJOI Problem 14288: https://xjoi.net/contest/4479/problem/2?locale=en

#include <bits/stdc++.h>

using namespace std;

class BIT {
    // Binary Indexed Tree/Fenwick Tree
    // input array is 1-indexed and all indices should be 1-indexed

    typedef long long ll;

    int n;
    vector<ll> arr;

    // lowest significant bit (rightmost bit that is 1)
    inline int LSB(int x) { return x & -x; }

    // calculates sum of elements from 1 to index (inclusive)
    ll sum(int index) {
        ll result = 0;

        while (index > 0) {
            result += arr[index];
            index -= LSB(index);
        }

        return result;
    }

public:
    BIT(int size) {
        n = size;
        arr.resize(n+1);
    }

    BIT(vector<int>& input) {
        n = input.size()-1;
        arr.resize(n+1);

        for (int i=1; i<=n; i++)
            update(i, input[i]);
    }

    // increments a value in the BIT
    void update(int index, int inc) {
        while (index <= n) {
            arr[index] += inc;
            index += LSB(index);
        }
    }

    // queries the sum between two elements
    ll query(int left, int right) {
        if (left > right) return 0;
        return sum(right) - sum(left-1);
    }
};

typedef long long ll;

const ll PRIME = 29;
const ll MOD = 1e9+7;

const int maxn = 2e5+1;
int n, m;
int str[maxn];
vector<ll> pows = {1};

inline ll mod(ll num) {
    // supports negative mod
    ll m = num % MOD;
    if (m < 0) return m + MOD;
    else return m;
}

inline int convert(char c) {
    return c - 'a' + 1;
}

int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);

    cin >> n >> m;
    for (int i=1; i<=n; i++) {
        char c; cin >> c;
        str[i] = convert(c);
        pows.push_back(mod(pows.back() * PRIME));
    }

    // set up BIT
    BIT front(n), back(n);
    int frontArr[n+1], backArr[n+1];

    for (int i=1; i<=n; i++) {
        frontArr[i] = mod(pows[i-1] * str[n-i+1]);
        backArr[i] = mod(pows[i-1] * str[i]);

        front.update(i, frontArr[i]);
        back.update(i, backArr[i]);
    }

    // process queries
    for (int i=0; i<m; i++) {
        int type; cin >> type;

        if (type == 1) {
            int k, x; char c;
            cin >> k >> c;
            x = convert(c);

            int l = n - k + 1;
            int newFront = mod(pows[l-1] * x);
            int newBack = mod(pows[k-1] * x);

            front.update(l, mod(newFront - frontArr[l]));
            back.update(k, mod(newBack - backArr[k]));

            frontArr[l] = newFront;
            backArr[k] = newBack;
        } else {
            int a, b; cin >> a >> b;
            int c = n-b+1, d = n-a+1;

            ll frontHash = mod(mod(front.query(c, d)) * pows[n-d]);
            ll backHash = mod(mod(back.query(a, b)) * pows[n-b]);

            cout << (frontHash == backHash ? "YES" : "NO") << "\n";
        }
    }
}