#include <bits/stdc++.h>

using namespace std;

int n, m;
vector<int> adjList[100001];

int k = 1;
vector<int> result;

void bfs() {
    queue<pair<int, vector<int>>> q;
    q.push(make_pair(1, vector<int>()));

    bitset<100001> visited;
    visited.set(1);

    while (!q.empty()) {
        int levelSize = q.size();
        for (int i=0; i<levelSize; i++) {
            auto curr = q.front(); q.pop();

            int comp = curr.first;
            vector<int>& currPath = curr.second;
            currPath.push_back(comp);

            if (comp == n) {
                result = currPath;
                return;
            }

            for (int adj: adjList[comp]) {
                if (!visited[adj]) {
                    visited.set(adj);
                    q.push(make_pair(adj, currPath));
                }
            }
        }

        k++;
    }
}

int main() {
    cin >> n >> m;
    for (int i=0; i<m; i++) {
        int a, b; cin >> a >> b;
        adjList[a].push_back(b);
        adjList[b].push_back(a);
    }

    bfs();

    if (result.size() == 0) {
        cout << "IMPOSSIBLE" << endl;
    } else {
        cout << k << endl;
        for (int node: result) cout << node << " ";
        cout << endl;
    }
}