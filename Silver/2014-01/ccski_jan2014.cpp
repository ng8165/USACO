// Cross Country Skiing
// USACO Silver January 2014: http://www.usaco.org/index.php?page=viewproblem2&cpid=380

#include <bits/stdc++.h>

using namespace std;

int m, n;
int elev[500][500];
vector<pair<int, int>> way;
bool visited[500][500];

const int dir[4][2] = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

inline bool isInBounds(int r, int c) {
    return r >= 0 && r < m && c >= 0 && c < n;
}

void floodfill(int r, int c, int D) {
    visited[r][c] = true;

    for (int i=0; i<4; i++) {
        int nr = r + dir[i][0];
        int nc = c + dir[i][1];

        if (isInBounds(nr, nc) && !visited[nr][nc] && abs(elev[r][c] - elev[nr][nc]) <= D) {
            floodfill(nr, nc, D);
        }
    }
}

bool check(int D) {
    memset(visited, false, sizeof(visited));

    floodfill(way[0].first, way[0].second, D);

    for (auto w: way) {
        if (!visited[w.first][w.second]) {
            return false;
        }
    }

    return true;
}

int main() {
    ifstream fin("ccski.in");
    ofstream fout("ccski.out");

    fin >> m >> n;
    int left = 0, right = 0;

    for (int i=0; i<m; i++) {
        for (int j=0; j<n; j++) {
            fin >> elev[i][j];
            right = max(right, elev[i][j]);
        }
    }

    for (int i=0; i<m; i++) {
        for (int j=0; j<n; j++) {
            bool isWay; fin >> isWay;
            if (isWay) way.push_back(make_pair(i, j));
        }
    }

    int result = right;

    while (left <= right) {
        int mid = (left + right) / 2;
                
        if (check(mid)) {
            result = min(result, mid);
            right = mid-1;
        } else {
            left = mid+1;
        }
    }

    fout << result << endl;
}