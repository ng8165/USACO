// Birthday Party
// Kattis: https://open.kattis.com/problems/birthday

#include <bits/stdc++.h>

using namespace std;

int p, c;
int a[5001], b[5001];

set<int> adjList[101];
bool visited[101];

void dfs(int person) {
    visited[person] = true;
    
    for (int fr: adjList[person]) {
        if (!visited[fr])
            dfs(fr);
    }
}

int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);

    while (true) {
        cin >> p >> c;
        if (p == 0 && c == 0) break;

        for (int i=1; i<=c; i++) {
            cin >> a[i] >> b[i];
            adjList[a[i]].insert(b[i]);
            adjList[b[i]].insert(a[i]);
        }

        bool stop = false;

        for (int i=1; i<=c; i++) {
            adjList[a[i]].erase(b[i]);
            adjList[b[i]].erase(a[i]);

            memset(visited, false, sizeof(visited));
            dfs(0);

            for (int i=0; i<p; i++) {
                if (!visited[i]) {
                    cout << "Yes\n";
                    stop = true;
                    break;
                }
            }

            if (stop) break;

            adjList[a[i]].insert(b[i]);
            adjList[b[i]].insert(a[i]);
        }

        if (!stop) cout << "No\n";

        for (int i=0; i<p; i++)
            adjList[i].clear();
    }
}