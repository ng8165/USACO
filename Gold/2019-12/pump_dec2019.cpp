// Milk Pumping
// USACO Gold December 2019: http://www.usaco.org/index.php?page=viewproblem2&cpid=969
// XJOI Problem 15362: https://xjoi.net/contest/4793/problem/2?locale=en

#include <bits/stdc++.h>

using namespace std;

struct Pipe {
    int farm, cost, flow;
};

struct State {
    int farm;
    int costSum, minFlow;
    double res;

    bool operator<(const State& other) const {
        return res < other.res;
    }
};

const int maxN = 1001;
int N, M;
vector<Pipe> adjList[maxN];
double maxResult[maxN];

inline double calcRes(const int& f, const int& c) { return (double) f / c; }

void dijkstra() {
    priority_queue<State> pq;
    pq.push({1, 0, INT_MAX, 0}); // set flow to max so that minimum flow is correct
    maxResult[1] = 0;

    while (!pq.empty()) {
        State s = pq.top(); pq.pop();

        if (s.res < maxResult[s.farm])
            continue;
        
        for (Pipe& p: adjList[s.farm]) {
            int newCost = s.costSum + p.cost, newFlow = min(s.minFlow, p.flow);
            double newRes = calcRes(newFlow, newCost);

            if (newRes > maxResult[p.farm]) {
                maxResult[p.farm] = newRes;
                pq.push({p.farm, newCost, newFlow, newRes});
            }
        }
    }
}

int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);

    // freopen("pump.in", "r", stdin);
    // freopen("pump.out", "w", stdout);

    cin >> N >> M;
    for (int i=0; i<M; i++) {
        int a, b, c, f; cin >> a >> b >> c >> f;
        adjList[a].push_back({b, c, f});
        adjList[b].push_back({a, c, f});
    }

    dijkstra();

    cout << (int) (1e6 * maxResult[N]) << "\n";
}