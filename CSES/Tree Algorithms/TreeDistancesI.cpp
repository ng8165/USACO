// Tree Distances I
// CSES Tree Algorithms: https://cses.fi/problemset/task/1132
// Algorithm: https://codeforces.com/blog/entry/79048 and https://youtu.be/MOy4UDjN8DM?t=470

#include <bits/stdc++.h>

using namespace std;

typedef pair<int, int> pii;

int n;
vector<int> adj[200001];

int result[200001];

// finds furthest node from start
int bfs1(int start) {
    queue<pii> q;
    q.push({start, 0});
    int node = -1;

    while (!q.empty()) {
        pii p = q.front(); q.pop();
        node = p.first;
        int& parent = p.second;

        for (int& child: adj[node]) {
            if (child != parent) {
                q.push({child, node});
            }
        }
    }

    return node;
}

// records distance from start to all traversed nodes
void bfs2(int start) {
    queue<pii> q;
    q.push({start, 0});

    vector<int> visited(n+1);
    visited[start] = true;

    while (!q.empty()) {
        pii p = q.front(); q.pop();
        int &node = p.first, &dist = p.second;
        result[node] = max(result[node], dist);

        for (int& child: adj[node]) {
            if (!visited[child]) {
                visited[child] = true;
                q.push({child, dist+1});
            }
        }
    }
}

int main() {
    cin >> n;
    for (int i=1; i<n; i++) {
        int a, b; cin >> a >> b;
        adj[a].push_back(b);
        adj[b].push_back(a);
    }

    int end1 = bfs1(1);
    int end2 = bfs1(end1);

    bfs2(end1);
    bfs2(end2);

    for (int i=1; i<=n; i++) cout << result[i] << " ";
    cout << endl;
}