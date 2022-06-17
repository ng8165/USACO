// Closing the Farm
// USACO Gold US Open 2016: http://www.usaco.org/index.php?page=viewproblem2&cpid=646

#include <bits/stdc++.h>

using namespace std;

int n, m;
vector<int> adjList[200001];
int close[200000];

int parent[200001];
int components = 0;

int find(int node) {
    if (parent[node] < 0) return node;

    // path compression
    parent[node] = find(parent[node]);

    return parent[node];
}

void unite(int u, int v) {
	int parentU = find(u);
	int parentV = find(v);

	if (parentU == parentV) return;

	int big = max(parentU, parentV);
	int small = min(parentU, parentV);
		
	// union by size
	parent[big] += parent[small];
	parent[small] = big;
    components--;
}

int main() {
    ifstream fin("closing.in");
    ofstream fout("closing.out");

    fin >> n >> m;

    for (int i=0; i<m; i++) {
        int a, b; fin >> a >> b;
        adjList[a].push_back(b);
        adjList[b].push_back(a);
    }

    for (int i=n; i>0; i--) fin >> close[i];

    stack<string> result;

    for (int i=1; i<=n; i++) {
        int& barn = close[i];
        parent[barn] = -1;
        components++;

        for (int& adj: adjList[barn]) {
            if (parent[adj] != 0) {
                unite(barn, adj);
            }
        }

        result.push(components <= 1 ? "YES" : "NO");
    }

    while (!result.empty()) {
        fout << result.top() << "\n";
        result.pop();
    }

    cout << flush;
}