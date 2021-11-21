// Flight Routes Check
// CSES Graph Algorithms: https://cses.fi/problemset/task/1682/
// Passed 17/18 test cases

#include <bits/stdc++.h>

using namespace std;

int n, m;
vector<int> adjList[100001];

bitset<100001> visited;
bitset<100001> visitAll;

bool dfs(int src) {
    visited[src] = true;
    if (visitAll[src]) return true;

    for (int dest: adjList[src]) {
        if (!visited[dest]) {
            if (dfs(dest)) {
                visitAll[src] = true;
                return true;
            }
        }
    }

    return false;
}

int main() {
    cin >> n >> m;
    for (int i=0; i<m; i++) {
        int u, v; cin >> u >> v;
        adjList[u].push_back(v);
    }

    for (int i=1; i<=n; i++) {
        if (visitAll[i]) continue;

        visited.reset();
        dfs(i);

        if (visitAll[i]) continue;

        for (int j=1; j<=n; j++) {
            if (!visited[j]) {
                cout << "NO" << endl << i << " " << j << endl;
                return 0;
            }
        }

        visitAll[i] = true;
    }

    cout << "YES" << endl;
}