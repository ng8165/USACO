// Cowntagion
// USACO Silver December 2020: http://usaco.org/index.php?page=viewproblem2&cpid=1062

#include <bits/stdc++.h>

using namespace std;

int N;
vector<int> adj[100001];
bool visited[100001];

int result = 0;

void dfs(int node) {
    int neighbors = 0; // find the number of unvisited farms

    for (int& neighbor: adj[node]) {
        if (!visited[neighbor]) {
            neighbors++;
            visited[neighbor] = true;
            dfs(neighbor);
        }
    }

    if (neighbors == 0) return;

    result += (int) (log2(neighbors) + 1); // double until can spread to all adjacent farms
    result += neighbors; // send one infected cow to all neighboring farms
}

int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);

    cin >> N;

    for (int i=1; i<N; i++) {
        int a, b; cin >> a >> b;
        adj[a].push_back(b);
        adj[b].push_back(a);
    }

    visited[1] = true;
    dfs(1);

    cout << result << "\n";
}