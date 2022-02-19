// MooTube
// USACO Silver January 2018: http://www.usaco.org/index.php?page=viewproblem2&cpid=788

#include <bits/stdc++.h>

using namespace std;

typedef long long ll;

int N, Q;
vector<int> adjList[5001];
bool visited[5001];
ll relevance[5001][5001];

int bfs(ll k, int v) {
    queue<int> q;
    q.push(v);

    memset(visited, false, sizeof(visited));
    visited[v] = true;

    int result = 0;

    while (!q.empty()) {
        int& vid = q.front(); q.pop();

        for (int& adj: adjList[vid]) {
            if (!visited[adj] && relevance[adj][vid] >= k) {
                result++;
                visited[adj] = true;
                q.push(adj);
            }
        }
    }

    return result;
}

int main() {
    ifstream fin("mootube.in");
    ofstream fout("mootube.out");

    fin >> N >> Q;

    for (int i=1; i<N; i++) {
        int p, q; ll r;
        fin >> p >> q >> r;
        adjList[p].push_back(q);
        adjList[q].push_back(p);
        relevance[p][q] = relevance[q][p] = r;
    }

    for (int i=0; i<Q; i++) {
        ll k; int v;
        fin >> k >> v;

        fout << bfs(k, v) << "\n";
    }
}