// Building Roads
// CSES Graph Algorithms: https://cses.fi/problemset/task/1666

#include <bits/stdc++.h>

using namespace std;

int n, m;
vector<int> adjList[100001];
bitset<100001> visited;

void dfs(int pos) {
    visited[pos] = true;

    for (int neighbor: adjList[pos]) {
        if (!visited[neighbor]) {
            dfs(neighbor);
        }
    }
}

int main() {
    cin >> n >> m;
    for (int i=0; i<m; i++) {
        int a, b; cin >> a >> b;
        adjList[a].push_back(b);
        adjList[b].push_back(a);
    }

    int result = 0;
    int prev = -1;
    vector< pair<int, int> > bridges;

    for (int i=1; i<=n; i++) {
        if (!visited[i]) {
            if (prev != -1) {
                bridges.push_back({prev, i});
                result++;
            }

            dfs(i);

            prev = i;
        }
    }

    cout << result << endl;
    for (auto bridge: bridges) {
        cout << bridge.first << " " << bridge.second << endl;
    }
}