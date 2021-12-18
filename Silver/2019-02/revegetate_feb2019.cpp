// The Great Revegetation
// USACO Silver February 2019: http://www.usaco.org/index.php?page=viewproblem2&cpid=920

#include <bits/stdc++.h>

using namespace std;

int n, m;
vector<pair<int, char>> related[100001]; // first contains field number, second contains 1='D' or 0='S'

int visited[100001]; // 0 = not visited, 1 or 2 = grass type selected and visited

bool dfs(int field, int grass) {
    visited[field] = grass;

    for (auto [other, relation]: related[field]) {
        if (!visited[other]) {
            int otherGrass = relation == 'S' ? grass : 3-grass;
            if (!dfs(other, otherGrass)) return false;
        } else {
            if (relation == 'S' && visited[other] != grass) return false;
            if (relation == 'D' && visited[other] == grass) return false;
        }
    }

    return true;
}

int main() {
    ifstream fin("revegetate.in");
    ofstream fout("revegetate.out");

    fin >> n >> m;
    for (int i=0; i<m; i++) {
        char c; int a, b;
        fin >> c >> a >> b;
        related[a].push_back({b, c});
        related[b].push_back({a, c});
    }

    int result = 0;

    for (int i=1; i<=n; i++) {
        if (visited[i]) continue;

        if (!dfs(i, 1)) {
            fout << 0 << endl;
            return 0;
        }

        result++;
    }

    fout << 1;
    for (int i=0; i<result; i++) fout << 0;
    fout << endl;
}