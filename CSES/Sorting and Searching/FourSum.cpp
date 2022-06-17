// Sum of Four Values
// CSES Sorting & Searching: https://cses.fi/problemset/task/1642/
// XJOI Problem 14097: https://xjoi.net/contest/3513/problem/2

#include <bits/stdc++.h>

using namespace std;

#define val first
#define idx second

int n, x;
pair<int, int> a[1001];

int main() {
    cin >> n >> x;
    for (int i=1; i<=n; i++) {
        cin >> a[i].val;
        a[i].idx = i;
    }

    sort(a+1, a+n+1);

    for (int i=1; i<=n; i++) {
        for (int j=i+1; j<=n; j++) {
            int left = j+1, right = n;

            while (left < right) {
                int sum = a[i].val + a[j].val + a[left].val + a[right].val;

                if (sum == x) {
                    cout << a[i].idx << " " << a[j].idx << " " << a[left].idx << " " << a[right].idx << endl;
                    return 0;
                } else if (sum < x) {
                    left++;
                } else {
                    right--;
                }
            }
        }
    }

    cout << "IMPOSSIBLE" << endl;
}