// The Meeting Place Cannot Be Changed
// CodeForces Round #403 (Div. 2): https://codeforces.com/contest/782/problem/B

#include <bits/stdc++.h>

using namespace std;

int n;
int x[60000];
int y[60000];

bool check(double time) {
    double left = x[0] - (y[0]*time);
    double right = x[0] + (y[0]*time);

    for (int i=1; i<n; i++) {
        left = max(left, x[i] - (y[i]*time));
        right = min(right, x[i] + (y[i]*time));

        if (left > right) return false;
    }

    return true;
}

int main() {
    cin.tie(0) -> sync_with_stdio(0);

    cin >> n;

    double left = 0, right = 0;

    for (int i=0; i<n; i++) {
        cin >> x[i];
        right = max(right, (double) x[i]);
    }

    for (int i=0; i<n; i++)
        cin >> y[i];

    double result = right;
    const double inc = 0.0000001; // 6 0s after decimal place

    // binary search on answer
    while (left <= right) {
        double mid = left + (right-left)/2;

        if (check(mid)) {
            right = mid - inc;
            result = min(result, mid);
        } else {
            left = mid + inc;
        }
    }

    // set precision to 7 so guaranteed 6 decimal places w/o rounding
    cout << setprecision(7) << result << endl;
}