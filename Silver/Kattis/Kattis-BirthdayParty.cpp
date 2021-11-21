#include <bits/stdc++.h>

using namespace std;

int p, c;
int adjMat[100][100];
bitset<100> visited;
pair<int, int> edge[5000];

inline bool match(int a, int b, int rm) {
    return (a != edge[rm].first && b != edge[rm].second) ||
            (b != edge[rm].first && a != edge[rm].second);
}


void dfs(int curr, int rm) {
    visited[curr] = true;

    for (int i=0; i<p; i++) {
        if (adjMat[curr][i] == 1 && !visited[i] && !match(curr, i, rm)) {
            dfs(i, rm);
        }
    }
}

string bday() {
    for (int i=0; i<c; i++) {
        int a, b; cin >> a >> b;
        edge[i] = make_pair(a, b);
        adjMat[a][b] = adjMat[b][a] = 1;
    }

    for (int i=0; i<c; i++) {
        bool ok;

        for (int j=0; j<p; j++) {
            dfs(j, i);

            ok = true;

            for (int k=0; k<p; k++) {
                if (!visited[k]) {
                    cout << "can't visit " << k << ", remove " << i << ", start from " << j << endl;
                    ok = false;
                }
            }

            if (ok) break;

            visited.reset();
        }

        if (!ok) return "Yes";
    }

    memset(adjMat, 0, sizeof(adjMat));

    return "No";
}

int main() {
    while (true) {
        cin >> p >> c;
        if (p == 0 && c == 0) break;

        cout << bday() << endl;
    }
}