#include <bits/stdc++.h>

using namespace std;

int n, m;
vector<int> adjList[3001];

bitset<3001> visited;
bitset<3001> closed;

ifstream fin("closing.in");
ofstream fout("closing.out");

void print() {
    for (int i=1; i<=n; i++) {
        if (!closed[i] && !visited[i]) {
            fout << "NO" << endl;
            return;
        }
    }

    fout << "YES" << endl;
}

void dfs(int city) {
    visited[city] = true;

    for (int neighbor: adjList[city]) {
        if (!closed[neighbor] && !visited[neighbor]) {
            dfs(neighbor);
        }
    }
}

int main() {
    fin >> n >> m;
    for (int i=0; i<m; i++) {
        int a, b; fin >> a >> b;
        adjList[a].push_back(b);
        adjList[b].push_back(a);
    }

    dfs(1); print();

    for (int i=1; i<n; i++) {
        int a; fin >> a;
        closed.set(a);
        visited.reset();

        int start = -1;
        for (int i=1; i<=n; i++) {
            if (!closed[i]) {
                start = i;
                break;
            }
        }

        dfs(start);

        print();
    }
}