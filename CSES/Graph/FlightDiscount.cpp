// Flight Discount
// CSES Graph Algorithms: https://cses.fi/problemset/task/1195/
// XJOI Problem 14069: https://xjoi.net/contest/4691/problem/1?locale=en

#include <bits/stdc++.h>

using namespace std;

typedef unsigned long long ll;
typedef pair<int, unsigned int> pii;

struct State {
    int loc;
    ll cost;
    int used;

    bool operator<(const State& other) const {
        return cost > other.cost;
    }
};

const int maxn = 1e5+1;
int n, m;
vector<pii> adjList[maxn];
ll shortest[maxn][2];

void dijkstra() {
    priority_queue<State> pq;
    pq.push({1, 0, 0});
    shortest[1][0] = shortest[1][1] = 0;

    while (!pq.empty()) {
        State s = pq.top(); pq.pop();

        if (s.cost > shortest[s.loc][s.used])
            continue;
        
        for (auto& [adj, pathCost]: adjList[s.loc]) {
            ll cost = pathCost / 2 + s.cost;
            if (!s.used && cost < shortest[adj][1]) {
                shortest[adj][1] = cost;
                pq.push({adj, cost, 1});
            }
            
            cost = pathCost + s.cost;
            if (cost < shortest[adj][s.used]) {
                shortest[adj][s.used] = cost;
                pq.push({adj, cost, s.used});
            }
        }
    }
}

int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);

    memset(shortest, ULLONG_MAX, sizeof(shortest));

    cin >> n >> m;
    for (int i=0; i<m; i++) {
        int a, b, c; cin >> a >> b >> c;
        adjList[a].push_back({b, c});
    }

    dijkstra();

    cout << min(shortest[n][0], shortest[n][1]) << "\n";
}