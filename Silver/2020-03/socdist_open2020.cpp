// Social Distancing
// USACO Silver US Open 2020: http://www.usaco.org/index.php?page=viewproblem2&cpid=1038

#include <bits/stdc++.h>

using namespace std;

typedef long long ll;
typedef pair<ll, ll> pll;

int n, m;
pll grass[100001];

bool check(ll gap) {
    int cows = 1;
    ll lastCow = grass[1].first;

    for (int i=1; i<=m; i++) {
        while (grass[i].second - lastCow >= gap) {
            lastCow = max(lastCow + gap, grass[i].first);
            cows++;
        }
    }

    // cout << "gap of " << gap << " allows for " << cows << " cows" << endl;

    return cows >= n;
}

int main() {
    ifstream fin("socdist.in");
    ofstream fout("socdist.out");
    
    fin >> n >> m;

    for (int i=1; i<=m; i++) fin >> grass[i].first >> grass[i].second;
    sort(grass+1, grass+m+1);

    ll left = 0, right = grass[m].second - grass[1].first;
    ll maxD = 0;

    while (left <= right) {
        ll mid = left + (right-left)/2;

        if (check(mid)) {
            left = mid+1;
            maxD = max(maxD, mid);
        } else {
            right = mid-1;
        }
    }

    fout << maxD << endl;
}