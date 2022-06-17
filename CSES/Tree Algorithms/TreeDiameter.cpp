// Tree Diameter
// CSES Tree Algorithms: https://cses.fi/problemset/task/1131

#include <bits/stdc++.h>

using namespace std;

int n;
vector<int> adj[200001];

// DP Method: Algorithm 1 in CPH 14.2

int toLeaf[200001]; // depth of tree: max length from node to leaf
int maxLength[200001]; // max length of path whose highest point goes through node

void dfsDP(int node, int parent) {
    int max1 = 0, max2 = 0; // greatest and second greatest toLeaf for children
    int children = 0; // number of children

    for (int& child: adj[node]) {
        if (child != parent) { // not visited
            dfsDP(child, node);
            toLeaf[node] = max(toLeaf[node], toLeaf[child]+1);

            if (toLeaf[child] > max1) {
                max2 = max1;
                max1 = toLeaf[child];
            } else if (toLeaf[child] > max2) {
                max2 = toLeaf[child];
            }

            children++;
        }
    }

    if (children < 2) maxLength[node] = toLeaf[node];
    else maxLength[node] = max1+max2+2;
}

void dpMethod() {
    dfsDP(1, 0);

    int result = 0;
    for (int i=1; i<=n; i++) result = max(result, maxLength[i]);
    cout << result << endl;
}


// Two DFS Method: Algorithm 2 in CPH 14.2

int maxDist; // length of the longest path from the root
int maxNode; // end node of the longest path from the root

void dfsTwo(int node, int parent, int dist) {
    if (dist > maxDist) {
        maxDist = dist;
        maxNode = node;
    }
 
    for (int& child: adj[node]) {
        if (child != parent) {
            dfsTwo(child, node, dist+1);
        }
    }
}

void twoDfsMethod() {
    maxDist = 0;
    dfsTwo(1, 0, 0);
 
    maxDist = 0;
    dfsTwo(maxNode, 0, 0);
    
    cout << maxDist << endl;
}

// Two BFS Method: https://codeforces.com/blog/entry/79048 and https://youtu.be/MOy4UDjN8DM?t=470

typedef pair<int, int> pii;

pii bfsTwo(int start) {
    queue<pii> q;
    q.push({start, 0});

    vector<bool> visited(n+1);
    visited[start] = true;
    pii p;

    while (!q.empty()) {
        p = q.front(); q.pop();
        int &node = p.first, &dist = p.second;

        for (int& child: adj[node]) {
            if (!visited[child]) {
                visited[child] = true;
                q.push({child, dist+1});
            }
        }
    }

    return p;
}

void twoBfsMethod() {
    pii p1 = bfsTwo(1);
    pii p2 = bfsTwo(p1.first);
    cout << p2.second << endl;
}

int main() {
    cin >> n;
    for (int i=1; i<n; i++) {
        int a, b; cin >> a >> b;
        adj[a].push_back(b);
        adj[b].push_back(a);
    }

    // dpMethod();
    // twoDfsMethod();
    twoBfsMethod();
}