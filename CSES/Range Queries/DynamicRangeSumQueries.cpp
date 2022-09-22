// Dynamic Range Sum Queries
// CSES Range Queries: https://cses.fi/problemset/task/1648
// XJOI Problem 14103: https://xjoi.net/contest/4411/problem/1?locale=en

#include <bits/stdc++.h>

using namespace std;

class BIT {
    // Binary Indexed Tree/Fenwick Tree
    // input array is 1-indexed and all indices should be 1-indexed

    typedef long long ll;

    int n;
    vector<ll> arr; // BIT (not input array)

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
    BIT(vector<int>& input) {
        n = input.size()-1;
        arr.resize(n+1);

        for (int i=1; i<=n; i++)
            update(i, input[i]);
    }

    // increases a value in the BIT
    void update(int index, int inc) {
        while (index <= n) {
            arr[index] += inc;
            index += LSB(index);
        }
    }

    // queries the sum between two elements
    ll query(int left, int right) {
        return sum(right) - sum(left-1);
    }
};

int n, q;
vector<int> arr;

int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);

    cin >> n >> q;
    arr.resize(n+1);
    for (int i=1; i<=n; i++)
        cin >> arr[i];

    BIT bit(arr);

    for (int i=0; i<q; i++) {
        int type; cin >> type;

        if (type == 1) {
            int k, u; cin >> k >> u;
            int inc = u-arr[k];
            arr[k] = u;
            bit.update(k, inc);
        } else {
            int a, b; cin >> a >> b;
            cout << bit.query(a, b) << "\n";
        }
    }
}