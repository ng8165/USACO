// Books
// CodeForces Round 171 Div. 2 Problem B: https://codeforces.com/contest/279/problem/B

#include <bits/stdc++.h>

using namespace std;

int n, t;
int a[100001];

int main() {
    cin >> n >> t;
    for (int i=1; i<=n; i++) cin >> a[i];

    int left = 1, right = 1;
    int result = 0;
    int time = 0;

    while (right <= n) {
        while (right <= n && time + a[right] <= t) {
            time += a[right++];
        }

        result = max(result, right-left);
        time -= a[left++];
    }

    cout << result << endl;
}