// Union Find
// Yosupo: https://judge.yosupo.jp/problem/unionfind

#include <bits/stdc++.h>

using namespace std;

int parent[200001];
int height[200001];

int find(int node) {
    if (parent[node] == -1) return node;

    // path compression
    parent[node] = find(parent[node]);

    return parent[node];
}

void unite(int u, int v) {
    int parentU = find(u);
    int parentV = find(v);

    if (parentU == parentV) return;
    
    // union by rank
    if (height[parentU] > height[parentV]) {
        // add v to u
        parent[parentV] = parentU;
        height[parentU] = max(height[parentU], height[parentV] + 1);
    } else {
        // add u to v
        parent[parentU] = parentV;
        height[parentV] = max(height[parentV], height[parentU] + 1);
    }
}

int main() {
    int N, Q, t, u, v;
    cin >> N >> Q;

    memset(parent, -1, sizeof(parent));
    memset(height, 1, sizeof(height));

    for (int i=0; i<Q; i++) {
        cin >> t >> u >> v;

        if (t == 0) unite(u, v);
        else cout << (find(u) == find(v)) << "\n";
    }

    cout << flush;
}