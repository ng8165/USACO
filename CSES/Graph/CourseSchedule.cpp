// Course Schedule
// CSES Graph Algorithms: https://cses.fi/problemset/task/1679

#include <bits/stdc++.h>

using namespace std;

const int maxn = 1e5+1;

int n, m;
vector<int> adjList[maxn];

int state[maxn]; // 0 = not processed, 1 = processing, 2 = already processed
stack<int> result;

bool dfs(int node) {
    if (state[node] == 1)
        // cycle detected
        return false;
    
    state[node] = 1;

    for (int& adj: adjList[node]) {
        if (state[adj] != 2 && !dfs(adj))
            return false;
    }

    state[node] = 2;
    result.push(node);

    return true;
}

// topological sort
// see CPH 16.1
void topSortDFS() {
    for (int i=1; i<=n; i++) {
        if (state[i] != 0) continue;

        if (!dfs(i)) {
            cout << "IMPOSSIBLE\n";
            return;
        }
    }

    while (!result.empty()) {
        cout << result.top() << " ";
        result.pop();
    }

    cout << "\n";
}


int indegree[maxn];

void topSortBFS() {
    queue<int> q;
    queue<int> result;

    for (int i=1; i<=n; i++) {
        if (indegree[i] == 0)
            q.push(i);
    }

    while (!q.empty()) {
        int& node = q.front(); q.pop();
        result.push(node);

        for (int& adj: adjList[node]) {
            indegree[adj]--;
            if (indegree[adj] == 0)
                q.push(adj);
        }
    }

    if (result.size() != n) {
        cout << "IMPOSSIBLE\n";
    } else {
        while (!result.empty()) {
            cout << result.front() << " ";
            result.pop();
        }

        cout << "\n";
    }
}

int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);

    cin >> n >> m;
    for (int i=0; i<m; i++) {
        int a, b; cin >> a >> b;
        adjList[a].push_back(b);
        indegree[b]++; // comment out if DFS
    }

    // topSortDFS();
    topSortBFS();
}