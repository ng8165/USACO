// The Party and Sweets
// CodeForces Round #559 (Div. 1): https://codeforces.com/problemset/problem/1158/A

#include <bits/stdc++.h>

using namespace std;

typedef long long ll;

int n, m;
int b[100000];
int g[100000];

int remain[100000]; // how many of boy i's candy's are minimum amount

int main() {
    cin.tie(0) -> sync_with_stdio(0);

    cin >> n >> m;

    ll sum = 0;
    int compare = 0;

    for (int i=0; i<n; i++) {
        cin >> b[i];
        remain[i] = m;
        sum += (ll) b[i] * m;
        compare = max(compare, b[i]);
    }

    for (int i=0; i<m; i++) cin >> g[i];

    sort(b, b+n, greater<int>());
    sort(g, g+m, greater<int>());

    for (int i=0, j=0; i<m && j<n; i++) { // index i for girls, index j for boys
        // update index until able to replace
        while (j<n && remain[j] <= 1) j++;
        if (j >= n) break;

        int &boy = b[j], &girl = g[i], &cnt = remain[j];

        if (compare > girl) {
            // minimum that boy gives is too much for girl, so impossible
            cout << "-1" << endl;
            return 0;
        } else if (compare < girl) {
            // boy must give more, update sum
            sum += (girl - boy);
            cnt--;
        }
    }

    cout << sum << endl;
}