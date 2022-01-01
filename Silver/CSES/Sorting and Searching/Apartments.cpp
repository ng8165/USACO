// Apartments
// CSES Sorting and Searching: https://cses.fi/problemset/task/1084

#include <bits/stdc++.h>

using namespace std;

int n, m, k;
int a[200001];
int b[200001];

int main() {
    cin >> n >> m >> k;

    for (int i=1; i<=n; i++) cin >> a[i];
    sort(a+1, a+n+1);

    for (int i=1; i<=m; i++) cin >> b[i];
    sort(b+1, b+m+1);

    int result = 0;
    int i=1, j=1;

    while (i <= n && j <= m) {
        if (a[i]-k > b[j]) {
            // current apartment is too small for any remaining applicant
            j++;
        } else if (a[i]+k < b[j]) {
            // current desired size is too small for any remaining apartment
            i++;
        } else {
            // current apartment is ok for current applicant
            i++;
            j++;
            result++;
        }
    }

    cout << result << endl;
}