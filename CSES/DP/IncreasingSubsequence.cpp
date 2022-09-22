// Increasing Subsequence
// CSES DP: https://cses.fi/problemset/task/1145
// XJOI Problem 14049: https://xjoi.net/contest/4368/problem/1?locale=en

#include <bits/stdc++.h>

using namespace std;

class SegmentTree {
    typedef long long ll;

    int n; // size of input array
    int cap; // represents how large the segment tree array should be
    vector<ll> arr; // segment tree internal array

    // functions for finding parent, left child, and right child in an array tree format
    inline int parent(int i) { return i/2; }
    inline int left(int i) { return 2*i; }
    inline int right(int i) { return 2*i+1; }

    inline int power2ceil(int num) {
        // returns the smallest power of 2 >= num
        return 1 << (int) (ceil(log2(num)));
    }

    // build the segment tree
    void build(vector<int>& input) {
        // the last "row" on the segment tree in array format starts at cap and ends at cap+n-1
        for (int i=cap; i<cap+n; i++)
            arr[i] = input[i-cap];

        // build "up" backwards
        for (int i=cap-1; i>0; i--)
            arr[i] = max(arr[left(i)], arr[right(i)]);
    }

    ll query(int l, int r, int i, int rl, int rr) {
        // i represents the array index
        // rl and rr represent the range of indices that the index represents

        if (rl > rr || i >= 2*cap)
            return LLONG_MIN;

        if (l <= rl && rr <= r)  {
            // current range is fully contained in query range
            return arr[i];
        } else if (rr < l || r < rl) {
            // current range is not contained in query range
            return LLONG_MIN;
        } else {
            // current range is partially contained in query range
            int mid = (rl+rr)/2;
            return max(query(l, r, left(i), rl, mid), query(l, r, right(i), mid+1, rr));
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
            arr[index] = max(arr[left(index)], arr[right(index)]);
            index = parent(index);
        }
    }

    ll query(int l, int r) {
        // see overloaded query method
        return query(l, r, 1, 1, cap);
    }
};

int n;
vector<pair<int, int>> x;

vector<int> arr; // compressed version of x
vector<int> dp; // dp[i] represents the length of the longest increasing subsequence ending at index i

int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);

    cin >> n;
    x.resize(n);
    for (int i=0; i<n; i++) {
        cin >> x[i].first;
        x[i].second = i;
    }

    // compress values (only matters relative to others)
    sort(x.begin(), x.end());
    int prev = 0, newSize = 0;
    arr.resize(n);
    for (auto& p: x) {
        arr[p.second] = (p.first > prev) ? ++newSize : newSize;
        prev = p.first;
    }

    // DP portion
    SegmentTree st(newSize);
    dp.resize(n);
    int result = 0;

    for (int i=0; i<n; i++) { // sequence ending at index i
        int longest = st.query(1, arr[i]-1);
        dp[i] = longest+1;
        st.update(arr[i], dp[i]);
        result = max(result, dp[i]);
    }

    cout << result << "\n";
}