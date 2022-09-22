// Moocast
// USACO Gold December 2016: http://www.usaco.org/index.php?page=viewproblem2&cpid=669
// XJOI Problem 15221: https://xjoi.net/contest/4766/problem/3?locale=en

#include <bits/stdc++.h>

using namespace std;

const int maxN = 1001;

struct DSU {
    vector<int> parent; // if negative, size of component = abs(parent[i]), otherwise parent
    int components;

    DSU(int size) {
        parent = vector<int>(size+1, -1);
        components = size;
    }

    int find(int node) {
        if (parent[node] < 0)
            return node;
        
        // path compression
        parent[node] = find(parent[node]);
    
        return parent[node];
    }
    
    void merge(int a, int b) {
        int pa = find(a), pb = find(b);
    
        if (pa == pb) return;
    
        // union by size
        if (-parent[pb] > -parent[pa]) swap(pa, pb);
        parent[pa] += parent[pb];
        parent[pb] = pa;

        components--;
    }
};

struct Edge {
    int node1, node2;
    int weight;

    bool operator<(const Edge& other) const {
        return weight < other.weight;
    }
};

int N;
int cows[maxN][2];
Edge edges[maxN*maxN];

int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);

    freopen("moocast.in", "r", stdin);
    freopen("moocast.out", "w", stdout);

    cin >> N;
    for (int i=1; i<=N; i++)
        cin >> cows[i][0] >> cows[i][1];

    DSU dsu(N);
    int edgeCnt = 0;
    for (int i=1; i<=N; i++) {
        for (int j=1; j<i; j++) {
            int dx = cows[i][0] - cows[j][0], dy = cows[i][1] - cows[j][1];
            edges[edgeCnt].node1 = i;
            edges[edgeCnt].node2 = j;
            edges[edgeCnt++].weight = (dx*dx) + (dy*dy);
        }
    }

    sort(edges, edges+edgeCnt);

    int i = 0, result = 0;
    while (dsu.components > 1 && i < edgeCnt) {
        dsu.merge(edges[i].node1, edges[i].node2);
        result = max(result, edges[i++].weight);
    }

    cout << result << "\n";
}