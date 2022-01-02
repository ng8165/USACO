// Road Construction
// CSES Graph Algorithms: https://cses.fi/problemset/task/1676

#include <bits/stdc++.h>

using namespace std;

int n, m;
int parent[100001];

int components;
int largest = 0;

int find(int node) {
    if (parent[node] < 0) return node;

    // path compression
    parent[node] = find(parent[node]);

    return parent[node];
}

void unite(int a, int b) {
	int parentA = find(a);
	int parentB = find(b);

	if (parentA == parentB) return;

	// union by size (if parent[i] is negative, abs(parent[i]) is the size)
	int big = max(parentA, parentB);
	int small = min(parentA, parentB);

	parent[big] += parent[small];
	parent[small] = big;

    components--;
	largest = max(largest, -parent[big]);
}

int main() {
    memset(parent, -1, sizeof(parent));

    cin >> n >> m;
    components = n;

    for (int i=0; i<m; i++) {
        int a, b; cin >> a >> b;
        unite(a, b);

        cout << components << " " << largest << "\n";
    }

    cout << flush;
}