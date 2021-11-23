// Switching on the Lights
// USACO Silver December 2015: http://www.usaco.org/index.php?page=viewproblem2&cpid=570

#include <bits/stdc++.h>

using namespace std;

int n, m;
vector<pair<int, int>> switches[105][105];

bool on[105][105];
bool visited[105][105];
int flipped;
const int dir[4][2] = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

inline bool isInBounds(int r, int c) {
    return r >= 0 && r < n && c >= 0 && c < m;
}

void floodfill(int r, int c) {
    visited[r][c] = true;

    for (auto& s: switches[r][c]) {
        if (!on[s.first][s.second]) {
            on[s.first][s.second] = true;
            flipped++;
        }
    }

    for (int i=0; i<4; i++) {
        int nr = r + dir[i][0];
        int nc = c + dir[i][1];

        if (isInBounds(nr, nc) && on[nr][nc] && !visited[nr][nc]) {
            floodfill(nr, nc);
        }
    }
}

int main() {
    ifstream fin("lightson.in");
    ofstream fout("lightson.out");

    fin >> n >> m;
    for (int i=0; i<m; i++) {
        int x, y, a, b; fin >> x >> y >> a >> b;
        switches[x][y].push_back(make_pair(a, b));
    }

    on[1][1] = true;

    while (true) {
        memset(visited, false, sizeof(visited));
        flipped = 0;

        floodfill(1, 1);

        if (flipped == 0) {
            break;
        }
    }

    int lightsOn = 0;
    for (int i=1; i<=n; i++) {
        for (int j=1; j<=n; j++) {
            if (on[i][j]) {
                lightsOn++;
            }
        }
    }

    fout << lightsOn << endl;
}