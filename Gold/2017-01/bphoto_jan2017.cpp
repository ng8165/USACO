// Balanced Photo
// USACO Gold January 2017: http://www.usaco.org/index.php?page=viewproblem2&cpid=693
// XJOI Problem 15291: https://xjoi.net/contest/4411/problem/3?locale=en

#include <bits/stdc++.h>

using namespace std;

typedef pair<int, int> pii;

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

int N;
vector<pii> h; // first = height, second = index

int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);

    // freopen("bphoto.in", "r", stdin);
    // freopen("bphoto.out", "w", stdout);

    cin >> N;
    h.resize(N+1);
    for (int i=1; i<=N; i++) {
        cin >> h[i].first;
        h[i].second = i;
    }

    sort(h.begin()+1, h.end(), greater<pii>());

    BIT bit(N);
    int result = 0;

    for (int i=1; i<=N; i++) {
        // because h is sorted in descending order, it is guaranteed that there are i-1 cows that are taller than the current cow.
        // the number of taller cows to the left can be queried using the BIT.

        int& index = h[i].second;
        int tallLeft = bit.query(index-1);
        int tallRight = i-1 - tallLeft;

        if (min(tallLeft, tallRight) * 2 < max(tallLeft, tallRight)) // this cow is unbalanced
            result++;

        bit.update(index, 1);
    }

    cout << result << "\n";
}