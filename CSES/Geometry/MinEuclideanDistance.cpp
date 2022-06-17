// Minimum Euclidean Distance
// CSES Geometry: https://cses.fi/problemset/task/2194/
// XJOI Problem 14265: https://xjoi.net/contest/3510/problem/1?locale=en
// 13/15 cases passed in CSES, Accepted in XJOI

#include <bits/stdc++.h>
 
using namespace std;
 
#define x first
#define y second
 
typedef long long ll;
typedef pair<ll, ll> pll;
 
int n;
vector<pll> eois;
 
inline bool cmp(const pll& a, const pll& b) {
    if (a.y == b.y) return a.x < b.x;
    return a.y < b.y;
}
 
int main() {
    cin >> n;
 
    for (int i=0; i<n; i++) {
        ll xCoor, yCoor; cin >> xCoor >> yCoor;
        eois.push_back({xCoor, yCoor});
    }
 
    sort(eois.begin(), eois.end()); // eois vector sorted by x coordinate
 
    ll d = 8e18;
    set<pll, decltype(&cmp)> active(&cmp); // set sorted by y coordinate
 
    for (pll eoi: eois) {
        // find points in between y-d and y+d
        auto itLow = active.lower_bound({LLONG_MIN, eoi.y-d});
        auto itHigh = active.upper_bound({LLONG_MAX, eoi.y+d});
 
        for (auto it = itLow; it != itHigh;) {
            if (it->x <= eoi.x-d) {
                // this point is out of range in terms of x
                it = active.erase(it);
                continue;
            }

            // this point has distance to eoi that is smaller than d so update d
            ll dx = abs(it->x - eoi.x), dy = abs(it->y - eoi.y);
            d = min(d, dx*dx + dy*dy);
            it++;
        }
 
        active.insert(eoi); // insert eoi into active set
    }
 
    cout << d << endl;
}