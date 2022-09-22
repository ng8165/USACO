// Pizzeria Queries
// CSES Range Queries: https://cses.fi/problemset/task/2206
// XJOI Problem 14268: https://xjoi.net/contest/4368/problem/2?locale=en

#include <bits/stdc++.h>

using namespace std;

class SegmentTree {
    typedef long long ll;

    int n; // size of input array
    int cap; // represents how large the segment tree array should be
    vector<ll> arr;

    // functions for finding parent, left child, and right child in an array tree format
    inline int parent(int i) { return i/2; }
    inline int left(int i) { return 2*i; }
    inline int right(int i) { return 2*i+1; }

    inline int power2ceil(int num) {
        // returns the smallest power of 2 >= to num
        return 1 << (int) (ceil(log2(num)));
    }

    // build the segment tree
    void build(vector<int>& input) {
        // the last "row" on the segment tree in array format starts at cap and ends at cap+n-1
        for (int i=cap; i<cap+n; i++)
            arr[i] = input[i-cap];

        // build "up" backwards
        for (int i=cap-1; i>0; i--)
            arr[i] = min(arr[left(i)], arr[right(i)]);
    }

    ll query(int l, int r, int i, int rl, int rr) {
        // i represents the array index to examine
        // rl and rr represent the range of indices that the index represents

        if (rl > rr || i >= 2*cap)
            return LLONG_MAX;

        if (l <= rl && rr <= r)  {
            // current range is fully contained in query range
            return arr[i];
        } else if ((rl < l && rr < l) || (r < rl && r < rr)) {
            // current range is not contained in query range
            return LLONG_MAX;
        } else {
            // current range is partially contained in query range
            int mid = (rl+rr)/2;
            return min(query(l, r, left(i), rl, mid), query(l, r, right(i), mid+1, rr));
        }
    }

public:
    SegmentTree(int size) {
        n = size;
        cap = power2ceil(n);
        arr.resize(2*cap);
    }

    SegmentTree(vector<int>& input) {
        n = input.size();
        cap = power2ceil(n);
        arr.resize(2*cap);

        build(input);
    }

    // update an index in the segment tree to a value
    void update(int index, int value) {
        index += cap - 1; // change index to index in array tree format
        arr[index] = value;
        index = parent(index);

        // update and all parents
        while (index > 0) {
            arr[index] = min(arr[left(index)], arr[right(index)]);
            index = parent(index);
        }
    }

    ll query(int l, int r) {
        // see overloaded query method
        return query(l, r, 1, 1, cap);
    }
};

int n, q;
vector<int> upArr, downArr;

int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);

    cin >> n >> q;
    upArr.resize(n); downArr.resize(n);
    for (int i=0; i<n; i++) {
        int x; cin >> x;
        downArr[i] = x-(i+1);
        upArr[i] = x+(i+1);
    }
    
    SegmentTree up(upArr), down(downArr);

    for (int i=0; i<q; i++) {
        int type, k, x;
        cin >> type >> k;

        if (type == 1) {
            cin >> x;
            down.update(k, x-k);
            up.update(k, x+k);
        } else {
            cout << min(down.query(1, k)+k, up.query(k, n)-k) << "\n";
        }
    }
}