// Mooyo Mooyo
// USACO Silver December 2018: http://www.usaco.org/index.php?page=viewproblem2&cpid=860

#include <bits/stdc++.h>

using namespace std;

int n, k;
char board[101][11];

const int dir[4][2] = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
bool visited[101][11];
int region;

inline bool isInBounds(int r, int c) {
    return r >= 1 && r <= n && c >= 1 && c <= 10;
}

void floodfill(int r, int c, char color) {
    if (!isInBounds(r, c) || board[r][c] != color) return;

    board[r][c] = '0';

    for (int i=0; i<4; i++) {
        floodfill(r + dir[i][0], c + dir[i][1], color);
    }
}

void count(int r, int c, char color) {
    visited[r][c] = true;
    region++;

    for (int i=0; i<4; i++) {
        int nr = r + dir[i][0], nc = c + dir[i][1];
        if (isInBounds(nr, nc) && !visited[nr][nc] && board[nr][nc] == color) {
            count(nr, nc, color);
        }
    }
}

int main() {
    ifstream fin("mooyomooyo.in");
    ofstream fout("mooyomooyo.out");

    fin >> n >> k;
    for (int i=1; i<=n; i++) {
        for (int j=1; j<=10; j++) {
            fin >> board[i][j];
        }
    }

    while (true) {
        int regionCnt = 0;

        // find all connected regions >= k and floodfill them to '0'
        for (int i=1; i<=n; i++) { for (int j=1; j<=10; j++) {
            if (board[i][j] != '0') {
                region = 0;
                memset(visited, false, sizeof(visited));

                count(i, j, board[i][j]);

                if (region >= k) {
                    floodfill(i, j, board[i][j]);
                    regionCnt++;
                }
            }
        }}

        if (regionCnt == 0) break;

        // drop haybales from gravity
        for (int c=1; c<=10; c++) {
            int w = n;

            for (int r=n; r>=1; r--) {
                if (board[r][c] != '0') {
                    board[w--][c] = board[r][c];
                }
            }

            while (w >= 1) {
                board[w--][c] = '0';
            }
        }
    }

    for (int i=1; i<=n; i++) {
        for (int j=1; j<=10; j++) {
            fout << board[i][j];
        }

        fout << endl;
    }
}