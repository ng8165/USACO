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

        if (isInBounds(nr, nc) && !visited[nr][nc] && abs(elev[r][c] -elev[nr][nc]) <= D) {
            elev[nr][nc] = D;
            floodfill(nr, nc, D);
        }
    }
}

int main() {
    ifstream fin("ccski.in");
    ofstream fout("ccski.out");

    fin >> m >> n;
    int left = 0, right = 0;
    for (int i=0; i<m; i++) {
        for (int j=0; j<n; j++) {
            cin >> elev[i][j];
            right = max(right, elev[i][j]);
        }
    }
    for (int i=0; i<m; i++) {
        for (int j=0; j<n; j++) {
            int r, c; cin >> r >> c;
            way.push_back(make_pair(r, c));
        }
    }

    int sr = way[0].first, sc = way[0].second;

    int result = -1;

    while (left <= right) {
        int mid = (left + right) / 2;

        cout << "trying " << mid << endl;
        
        floodfill(sr, sc, mid);

        bool ok = true;
        for (auto w: way) {
            if (!visited[w.first][w.second]) {
                ok = false;
                break;
            }
        }

        if (ok) {
            result = right;
            right = mid - 1;
        } else {
            left = mid + 1;
        }

        memset(visited, false, sizeof(visited));
    }

    fout << result << endl;
}