// Mahmoud and Ehab and the bipartiteness (Bipartiteness)
// CodeForces Round 435 Div 2 Problem B: https://codeforces.com/contest/862/problem/B

#include <bits/stdc++.h>

using namespace std;

int n;
set<int> adjList[100001];
int visited[100001]; // 0 = not visited, 1 and 2 = color

void dfs(int node, int color) {
    visited[node] = color;

    for (int neighbor: adjList[node]) {
        if (!visited[neighbor]) { // assuming that given graph is bipartite
            dfs(neighbor, 3-color); // 3-color is the opposite color
        }
    }
}

int main() {
    cin >> n;
    for (int i=1; i<n; i++) {
        int u, v; cin >> u >> v;
        adjList[u].insert(v);
        adjList[v].insert(u);
    }

    // first color the graph
    dfs(1, 1);

    // count number of nodes of each color
    int c1 = 0, c2 = 0;
    for (int i=1; i<=n; i++) {
        if (visited[i] == 1) c1++;
        else c2++;
    }

    long long result = 0;

    for (int node=1; node<=n; node++) {
        if (visited[node] == 1) {
            // connect to all color 2 nodes that aren't already connected
            result += (c2-adjList[node].size());
        } else {
            // connect to all color 1 nodes that aren't already connected
            result += (c1-adjList[node].size());
        }
    }

    cout << result/2 << endl;
}