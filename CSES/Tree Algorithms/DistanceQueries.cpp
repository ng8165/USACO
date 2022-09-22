// Distance Queries
// CSES Tree Algorithms: https://cses.fi/problemset/task/1135
// XJOI Problem 14039: https://xjoi.net/contest/4793/problem/1?locale=en

#include <bits/stdc++.h>

using namespace std;

const int maxn = 2e5+1;
const int expo = 20; // 2^20 is around 1 million
int parent[maxn][expo]; // parent[i][j] = the parent of i that is 2^j above
int level[maxn]; // level of each node
bool visited[maxn];

int n, q;
vector<int> adjList[maxn];

void dfs(int node) {
    // perform binary lifting
    for (int exp=1; exp<expo; exp++) {
        parent[node][exp] = parent[parent[node][exp-1]][exp-1];
    }
    
    // set level and dfs to children
    for (int& adj: adjList[node]) {
        if (!visited[adj]) {
            level[adj] = level[node]+1;
            parent[adj][0] = node;
            visited[adj] = true;
            dfs(adj);
        }
    }
}

int jump(int node, int levels) {
    for (int i=0; i<expo; i++) {
        if (levels <= 0) break;

        // when looking at levels in binary format, jump where the bit is 1
        if ((levels & 1) == 1)
            node = parent[node][i];
        
        levels >>= 1;
    }

    return node;
}

int LCA(int node1, int node2) {
    // assumming that node1 has a lower level than node2
    if (level[node2] < level[node1]) swap(node1, node2);

    // jump node2 so it is at the same level as node1
    node2 = jump(node2, level[node2] - level[node1]);

    if (node1 == node2) {
        // special case: node1 is the LCA of node2
        return node1;
    }

    // jump up while node1 and node2 are not equal
    // this means that they will be at the node underneath the LCA
    for (int i=expo-1; i>=0; i--) {
        if (parent[node1][i] == parent[node2][i])
            continue;

        node1 = parent[node1][i];
        node2 = parent[node2][i];
    }

    return parent[node1][0]; // return parent of node1 to give the LCA
}

int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);

    cin >> n >> q;

    // setup adjacency list
    for (int i=1; i<n; i++) {
        int a, b; cin >> a >> b;
        adjList[a].push_back(b);
        adjList[b].push_back(a);
    }

    level[1] = 0;
    visited[1] = true;
    dfs(1);

    // process queries
    for (int i=0; i<q; i++) {
        int a, b; cin >> a >> b;

        int lca = LCA(a, b);
        cout << (level[a] - level[lca]) + (level[b] - level[lca]) << "\n";
    }
}