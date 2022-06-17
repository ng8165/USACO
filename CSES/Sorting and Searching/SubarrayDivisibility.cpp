// Subarray Divisibility
// CSES Sorting and Searching: https://cses.fi/problemset/task/1662

#include <bits/stdc++.h>

using namespace std;

int n;
int prevSum[200000];

int mod(int num) {
    int m = num%n;
    if (m < 0) return m+n;
    else return m;
}

int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);

    cin >> n;

    int psum = 0;
    long long result = 0;

    for (int i=0; i<n; i++) {
        int a; cin >> a;
        psum = mod(psum + a);

        if (psum == 0) result++;

        result += prevSum[psum];

        prevSum[psum]++;
    }

    cout << result << endl;
}