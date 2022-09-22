// Why Did the Cow Cross the Road III
// USACO Gold Feburary 2017: http://www.usaco.org/index.php?page=viewproblem2&cpid=719
// XJOI Problem 15281: https://xjoi.net/contest/4601/problem/3?locale=en

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

    // queries the sum between two elements
    ll query(int left, int right) {
        return sum(right) - sum(left-1);
    }
};

const int maxN = 5e4+1;
int N;
int ID[2*maxN];
int endIdx[maxN];

int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);

    // freopen("circlecross.in", "r", stdin);
    // freopen("circlecross.out", "w", stdout);

    cin >> N;
    for (int i=1; i<=2*N; i++) {
        cin >> ID[i];
        endIdx[ID[i]] = i;
    }

    BIT bit(2*N);
    int result = 0;

    for (int i=1; i<=2*N; i++) {
        if (i == endIdx[ID[i]])
            continue;
        
        result += bit.query(i, endIdx[ID[i]]);
        bit.update(endIdx[ID[i]], 1);
    }

    cout << result << "\n";
}