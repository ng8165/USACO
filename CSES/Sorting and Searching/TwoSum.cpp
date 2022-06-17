// Two Sum
// CSES Sorting & Searching: https://cses.fi/problemset/task/1640/
// LeetCode Problem 167: https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/
// XJOI Problem 14095: https://xjoi.net/contest/3335/problem/1?locale=en

#include <bits/stdc++.h>

using namespace std;

typedef pair<int, int> pii;

int n, x;
pii a[200001];

int main() {
    cin >> n >> x;
    for (int i=1; i<=n; i++) {
        int v; cin >> v;
        a[i] = make_pair(v, i);
    }
    sort(a+1, a+n+1);

    int left = 1, right = n;

    while (left < right) {
        int sum = a[left].first + a[right].first;
        
        if (sum == x) {
            cout << a[left].second << " " << a[right].second << endl;
            return 0;
        }

        if (sum <= x) left++;
        else right--;
    }

    cout << "IMPOSSIBLE" << endl;
}