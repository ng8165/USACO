// Build Gates
// USACO Silver January 2016: http://www.usaco.org/index.php?page=viewproblem2&cpid=596

#include <bits/stdc++.h>

using namespace std;

typedef pair<int, int> pii;

const int bound = 4005;
const int start = 2002;
const int dir[4][2] = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

int N;
bool blocked[bound][bound];
bool vis[bound][bound];

int lBound, rBound;

inline bool isInBounds(int r, int c) {
    return r >= lBound && r <= rBound && c >= lBound && c <= rBound;
}

void bfs(int sr, int sc) {
    // use bfs instead of dfs to avoid stack overflow
    queue<pii> q;
    q.push({sr, sc});
    vis[sr][sc] = true;

    while (!q.empty()) {
        auto [r, c] = q.front(); q.pop();

        for (int i=0; i<4; i++) {
            int nr = r+dir[i][0];
            int nc = c+dir[i][1];

            if (isInBounds(nr, nc) && !vis[nr][nc] && !blocked[nr][nc]) {
                q.push({nr, nc});
                vis[nr][nc] = true;
            }
        }
    }
}

int main() {
    ifstream fin("gates.in");
    ofstream fout("gates.out");

    fin >> N;

    int r = start, c = start;
    blocked[r][c] = true;

    for (int i=0; i<N; i++) {
        char dir; fin >> dir;

        // make two steps for each direction so that cells that are
        // enclosed on all sides will be detected too
        if (dir == 'N') {
            blocked[r--][c] = true;
            blocked[r--][c] = true;
        } else if (dir == 'S') {
            blocked[r++][c] = true;
            blocked[r++][c] = true;
        } else if (dir == 'W') {
            blocked[r][c--] = true;
            blocked[r][c--] = true;
        } else {
            blocked[r][c++] = true;
            blocked[r][c++] = true;
        }
    }

    // restrict search to the fence's possible locations
    lBound = start-2*N;
    rBound = start+2*N;

    int result = -1; // set to -1 to remove the group of cells around the fence enclosure

    for (int i=lBound; i<=rBound; i++) {
        for (int j=lBound; j<=rBound; j++) {
            if (!blocked[i][j] && !vis[i][j]) {
                bfs(i, j);
                result++;
            }
        }
    }

    fout << result << endl;
}