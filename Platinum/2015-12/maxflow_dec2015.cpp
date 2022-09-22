// Max Flow
// USACO Platinum December 2015: http://www.usaco.org/current/index.php?page=viewproblem2&cpid=576
// XJOI Problem 15183: https://xjoi.net/contest/4793/problem/3?locale=en

#include <bits/stdc++.h>

using namespace std;

const int maxN = 5e4+1;
const int expo = 17; // 2^16 is around 60000
int parent[maxN][expo]; // parent[i][j] = the parent of i that is 2^j above
int level[maxN]; // level of each node
bool visited[maxN];

int N, K;
vector<int> adjList[maxN];
int increment[maxN];
int result[maxN];

void dfs1(int node) {
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
            dfs1(adj);
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

void dfs2(int node) {
    result[node] = increment[node];

    for (int& adj: adjList[node]) {
        if (!visited[adj]) {
            visited[adj] = true;
            dfs2(adj);
            result[node] += result[adj];
        }
    }
}

int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);

    // freopen("maxflow.in", "r", stdin);
    // freopen("maxflow.out", "w", stdout);

    cin >> N >> K;

    // setup adjacency list
    for (int i=1; i<N; i++) {
        int x, y; cin >> x >> y;
        adjList[x].push_back(y);
        adjList[y].push_back(x);
    }

    level[1] = 0;
    visited[1] = true;
    dfs1(1);

    // process queries
    for (int i=0; i<K; i++) {
        int s, t; cin >> s >> t;

        int lca = LCA(s, t);
        increment[s]++;
        increment[t]++;
        increment[lca]--;
        increment[parent[lca][0]]--;
    }

    memset(visited, false, sizeof(visited));
    visited[1] = true;
    dfs2(1);

    int res = 0;
    for (int i=1; i<=N; i++)
        res = max(res, result[i]);
    cout << res << "\n";
}