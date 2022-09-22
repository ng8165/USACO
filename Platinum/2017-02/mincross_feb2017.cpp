// Why Did the Cow Cross the Road
// USACO Platinum February 2017: http://www.usaco.org/index.php?page=viewproblem2&cpid=720
// XJOI Problem 15282: https://xjoi.net/contest/4450/problem/2?locale=en

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
int l[maxN], r[maxN];
int indexMapL[maxN], indexMapR[maxN]; // indexMapL[i] = j indicates that l[j] = i, and likewise for indexMapR

ll countInversions() {
    BIT bit(N);
    ll result = 0;

    for (int i=1; i<=N; i++) {
        int& val = indexMapL[r[i]];
        result += bit.query(val, N);
        bit.update(val, 1);
    }
    
    return result;
}

int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);

    // freopen("mincross.in", "r", stdin);
    // freopen("mincross.out", "w", stdout);

    cin >> N;
    for (int i=1; i<=N; i++)
        cin >> l[i];
    for (int i=1; i<=N; i++)
        cin >> r[i];

    for (int i=1; i<=N; i++) {
        indexMapL[l[i]] = i;
        indexMapR[r[i]] = i;
    }

    ll inversions = countInversions();
    ll result = inversions;
    for (int i=1; i<=N; i++) {
        int& val = r[i];
        inversions += ((N-indexMapL[val]) - (indexMapL[val]-1));
        result = min(result, inversions);
    }

    inversions = countInversions();
    for (int i=1; i<=N; i++) {
        int& val = l[i];
        inversions += ((N-indexMapR[val]) - (indexMapR[val]-1));
        result = min(result, inversions);
    }
    
    cout << result << "\n";
}