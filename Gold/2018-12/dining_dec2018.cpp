// Fine Dining
// USACO Gold December 2018: http://www.usaco.org/index.php?page=viewproblem2&cpid=861
// XJOI Problem 15315: https://xjoi.net/contest/4691/problem/3?locale=en

#include <bits/stdc++.h>

using namespace std;

typedef long long ll;
typedef pair<int, int> pii;

struct State {
    int loc;
    ll cost;
    int used;

    bool operator<(const State& other) const {
        return cost > other.cost;
    }
};

const int maxN = 5e4+1;
int N, M, K;
vector<pii> adjList[maxN];
int haybales[maxN];
vector<vector<ll>> shortest(maxN, vector<ll>(2, LLONG_MAX));

void dijkstra() {
    priority_queue<State> pq;
    pq.push({N, 0, 0});
    shortest[N][0] = shortest[N][1] = 0;

    while (!pq.empty()) {
        State s = pq.top(); pq.pop();

        if (s.cost > shortest[s.loc][s.used])
            continue;
        
        for (auto& [adj, pathCost]: adjList[s.loc]) {
            ll cost = pathCost + s.cost - haybales[adj];
            if (!s.used && haybales[adj] > 0 && cost < shortest[adj][1]) {
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

    // freopen("dining.in", "r", stdin);
    // freopen("dining.out", "w", stdout);

    cin >> N >> M >> K;
    for (int i=0; i<M; i++) {
        int a, b, t; cin >> a >> b >> t;
        adjList[a].push_back({b, t});
        adjList[b].push_back({a, t});
    }
    for (int i=0; i<K; i++) {
        int pasture, yum; cin >> pasture >> yum;
        haybales[pasture] = max(haybales[pasture], yum);
    }

    dijkstra();

    for (int i=1; i<N; i++)
        cout << (shortest[i][0] < shortest[i][1] ? 0 : 1) << "\n";
}