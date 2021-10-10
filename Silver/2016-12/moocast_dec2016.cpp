// Moocast
// USACO Silver December 2016: http://www.usaco.org/index.php?page=viewproblem2&cpid=668

#include <bits/stdc++.h>

using namespace std;

int n;
int x[201], y[201], p[201];
vector<int> adjList[201];
bitset<201> visited;

void constructAdjList() {
    for (int i=1; i<=n; i++) {
        for (int j=1; j<=n; j++) {
            if (i == j) continue;

            int dx = abs(x[i]-x[j]);
            int dy = abs(y[i]-y[j]);

            if (dx*dx + dy*dy <= p[i]*p[i]) {
                adjList[i].push_back(j);
            }
        }
    }
}

int dfs(int idx) {
    visited.set(idx);
    int cows = 0;

    for (int neighbor: adjList[idx]) {
        if (!visited[neighbor]) {
            cows += dfs(neighbor);
        }
    }

    return cows+1;
}

int main() {
    ifstream fin("moocast.in");
    ofstream fout("moocast.out");

    fin >> n;

    for (int i=1; i<=n; i++)
        fin >> x[i] >> y[i] >> p[i];

    constructAdjList();

    int maxCows = 0;

    for (int i=1; i<=n; i++) {
        maxCows = max(maxCows, dfs(i));
        visited.reset();
    }
    
    fout << maxCows << endl;
}