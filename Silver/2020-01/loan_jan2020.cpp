#include <bits/stdc++.h>

using namespace std;

typedef long long ll;

ll n, k, m;

bool check(ll x) {
    ll remain = n;
    ll daysLeft = k;

    while (daysLeft > 0 && remain > 0) {
        ll amt = remain/x;

        if (amt <= m) {
            // if amt <= m, amt = m from this point on as remain gets smaller
            return daysLeft*m >= remain;
        } else {
            ll daysSame = (remain-(amt*x))/amt+1; // number of days with same amt
            daysLeft -= daysSame;
            remain -= amt*daysSame;
        }
    }

    return remain <= 0;
}

int main() {
    ifstream fin("loan.in");
    ofstream fout("loan.out");

    fin >> n >> k >> m;

    ll left = 1, right = n;
    ll x = 0;

    while (left <= right) {
        ll mid = left + (right-left)/2;

        if (check(mid)) {
            x = max(x, mid);
            left = mid+1;
        } else {
            right = mid-1;
        }
    }

    fout << x << endl;
}