#include <bits/stdc++.h>

using namespace std;

typedef long long ll;

ll n, t;
ll k[200001];

bool check(ll time) {
    ll products = 0;
    for (ll i=1; i<=n; i++) {
        products += time/k[i];
    }

    // cout << "time " << time << " allows to produce " << products << " products" << endl;

    return products >= t;
}

int main() {
    cin >> n >> t;

    ll minK = INT_MAX;
    for (ll i=1; i<=n; i++) {
        cin >> k[i];
        minK = min(minK, k[i]);
    }

    ll left = 0, right = minK*t;
    // cout << left << " " << right << endl;
    while (left < right) {
        ll mid = left + (right-left)/2;

        if (check(mid)) {
            right = mid;
        } else {
            left = mid+1;
        }
    }

    cout << left << endl;
}