// Submit at https://www.spoj.com/problems/HAYBALE/

#include <bits/stdc++.h>

using namespace std;

int n, k;
int diff[1000005];

int main() {
    cin >> n >> k;

    for (int i=0; i<k; i++) {
        int l, r;
        cin >> l >> r;

        diff[l]++;
        diff[r+1]--;
    }

    int prefix[n+1]; prefix[0] = 0;
    for (int i=1; i<=n; i++) prefix[i] = prefix[i-1] + diff[i];

    sort(prefix+1, prefix+n+1);
    cout << prefix[n/2+1] << endl;
}