#include <iostream>

using namespace std;

typedef long long ll;

int main() {
    int n, q;
    cin >> n >> q;

    ll prefix[n+5];
    prefix[0] = 0;

    // prefix sum precomputation
    for (int i=1; i<=n; i++) {
        cin >> prefix[i];
        prefix[i] += prefix[i-1];
    }

    // process queries
    for (int i=0; i<q; i++) {
        int l, r;
        cin >> l >> r;

        cout << prefix[r]-prefix[l] << endl;
    }

    return 0;
}