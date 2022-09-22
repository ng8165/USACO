// Salary Queries
// CSES Range Queries: https://cses.fi/problemset/task/1144
// XJOI Problem 14048: https://xjoi.net/contest/4411/problem/2?locale=en

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

struct ArraySet {
    vector<int> arr;

    ArraySet(vector<int> input) { 
        // assuming input is 1-indexed
        input[0] = INT_MIN;
        arr.push_back(INT_MIN);

        sort(input.begin(), input.end());

        for (int i=1; i<input.size(); i++) {
            if (input[i-1] != input[i])
                arr.push_back(input[i]);
        }
    }

    int size() {
        return arr.size();
    }

    int index(int& val) {
        return lower_bound(arr.begin(), arr.end(), val) - arr.begin();
    }
};

int n, q;
vector<int> p;
vector<vector<int>> queries; // queries[i][0] = type, queries[i][1] and queries[i][2] = info

int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);

    cin >> n >> q;

    p.resize(n+1);
    for (int i=1; i<=n; i++)
        cin >> p[i];

    vector<int> compressVals = p;
    
    // save all queries into an array
    for (int i=0; i<q; i++) {
        char type; cin >> type;
        vector<int> query(3); cin >> query[1] >> query[2];
        query[0] = type == '!' ? 1 : 2;
        queries.push_back(query);

        // for update queries, add the new value to the compress array
        if (query[0] == 1) {
            compressVals.push_back(query[2]);
        }
    }

    ArraySet as(compressVals);

    // initialize the BIT
    vector<int> bitArr(as.size(), 0);
    for (int i=1; i<=n; i++) {
        bitArr[as.index(p[i])]++;
    }

    BIT bit(bitArr);

    for (vector<int>& query: queries) {
        if (query[0] == 1) {
            bit.update(as.index(p[query[1]]), -1);
            bit.update(as.index(query[2]), 1);
            p[query[1]] = query[2];
        } else {
            int idx1 = as.index(query[1]);

            // if query[2] is not found, must use the lower index
            int idx2 = as.index(query[2]);
            if (as.arr[idx2] != query[2]) idx2--;

            cout << bit.query(idx1, idx2) << "\n";
        }
    }
}