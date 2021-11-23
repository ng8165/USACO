// Why Did the Cow Cross the Road III
// USACO Silver February 2017: http://www.usaco.org/index.php?page=viewproblem2&cpid=716

#include <bits/stdc++.h>

using namespace std;

int n, k, r;
vector<pair<int, int>> roads[105][105];
bool hasCow[105][105];
int cows[105][2];

int nonDistant;
const int dir[4][2] = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
bool visited[105][105];

inline bool isInBounds(int row, int col) {
    return row >= 1 && row <= n && col >= 1 && col <= n;
}

bool isFound(int fr, int fc, int dr, int dc) {
    for (auto dest: roads[fr][fc]) {
        if (dest.first == dr && dest.second == dc) return true;
    }

    return false;
}

void floodfill(int row, int col) {
    visited[row][col] = true;
    if (hasCow[row][col]) nonDistant++;

    for (int i=0; i<4; i++) {
        int nrow = row + dir[i][0];
        int ncol = col + dir[i][1];

        if (isInBounds(nrow, ncol) && !visited[nrow][ncol] && !isFound(row, col, nrow, ncol)) {
            floodfill(nrow, ncol);
        }
    }
}

int main() {
    ifstream fin("countcross.in");
    ofstream fout("countcross.out");

    fin >> n >> k >> r;

    for (int i=1; i<=r; i++) {
        int r1, c1, r2, c2;
        fin >> r1 >> c1 >> r2 >> c2;
        roads[r1][c1].push_back({r2, c2});
        roads[r2][c2].push_back({r1, c1});
    }

    for (int i=1; i<=k; i++) {
        fin >> cows[i][0] >> cows[i][1];
        hasCow[cows[i][0]][cows[i][1]] = true;
    }

    int result = 0;

    for (int i=1; i<=k; i++) {
        nonDistant = 0;
        memset(visited, false, sizeof(visited));

        floodfill(cows[i][0], cows[i][1]);
        
        result += (k-nonDistant);
    }

    fout << result/2 << endl;
}