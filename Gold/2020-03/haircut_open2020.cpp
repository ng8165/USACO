// Haircut
// USACO Gold US Open 2020: http://www.usaco.org/index.php?page=viewproblem2&cpid=1041
// XJOI Problem 15448: https://xjoi.net/contest/4450/problem/3?locale=en

#include <bits/stdc++.h>

using namespace std;

typedef long long ll;

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

    // increases a value in the BIT
    void update(int index, int inc) {
        while (index <= n) {
            arr[index] += inc;
            index += LSB(index);
        }
    }

    // queries the sum from index 1 to right
    ll query(int right) {
        return sum(right);
    }

    // queries the sum between two elements
    ll query(int left, int right) {
        return sum(right) - sum(left-1);
    }
};

const int maxN = 1e5+1;
int N;
int A[maxN];
ll freq[maxN];

void countInversions() {
    BIT bit(maxN);

    for (int i=1; i<=N; i++) {
        int& val = A[i];
        int bitIndex = val+1; // BIT is 1-indexed
        freq[val] += bit.query(bitIndex+1, maxN);
        bit.update(bitIndex, 1);
    }
}

int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);

    // freopen("haircut.in", "r", stdin);
    // freopen("haircut.out", "w", stdout);

    cin >> N;
    for (int i=1; i<=N; i++)
        cin >> A[i];
    
    countInversions();
    
    ll result = 0;
    for (int j=0; j<N; j++) {
        cout << result << "\n";
        result += freq[j];
    }
}