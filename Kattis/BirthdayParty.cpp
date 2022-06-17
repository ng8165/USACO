// Birthday Party
// Kattis: https://open.kattis.com/problems/birthday

#include <bits/stdc++.h>

using namespace std;

int p, c;
int a[5001], b[5001];

bool adj[101][101];
bool visited[101];

void dfs(int person) {
    visited[person] = true;
    
    for (int i=0; i<p; i++) {
        if (adj[person][i] && !visited[i])
            dfs(i);
    }
}

void solve() {
    for (int i=1; i<=c; i++) {
        // disconnect two friends
        adj[a[i]][b[i]] = adj[b[i]][a[i]] = false;

        // perform DFS
        memset(visited, false, sizeof(visited));
        dfs(0);

        // if any friend is not visited, then someone misses his party
        for (int i=0; i<p; i++) {
            if (!visited[i]) {
                cout << "Yes\n";
                return;
            }
        }

        // reconnect two friends
        adj[a[i]][b[i]] = adj[b[i]][a[i]] = true;
    }

    cout << "No\n";
}

int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);

    while (true) {
        cin >> p >> c;
        if (p == 0 && c == 0) break;

        memset(adj, false, sizeof(adj));

        for (int i=1; i<=c; i++) {
            cin >> a[i] >> b[i];
            adj[a[i]][b[i]] = adj[b[i]][a[i]] = true;
        }

        solve();
    }
}