// Maximum Subarray Sum
// CSES Sorting and Searching: https://cses.fi/problemset/task/1643

#include <bits/stdc++.h>

using namespace std;

typedef long long ll;

int n;
ll prefix[200005];

int main() {
    cin >> n;

    ll minSum = 0;
    ll result = LLONG_MIN;

    for (int i=1; i<=n; i++) {
        cin >> prefix[i];
        prefix[i] += prefix[i-1];

        result = max(result, prefix[i]-minSum);
        minSum = min(minSum, prefix[i]);
    }

    cout << result << endl;
}