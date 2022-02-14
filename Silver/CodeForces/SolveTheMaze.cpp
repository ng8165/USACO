// Solve the Maze
// CodeForces Round 648 Div. 2 Problem D: https://codeforces.com/contest/1365/problem/D

#include <bits/stdc++.h>

using namespace std;

int t;
int n, m;
char grid[51][51]; // * denotes visited

const int dir[4][2] = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
int good;

inline bool isInBounds(int& r, int& c) {
    return r >= 1 && r <= n && c >= 1 && c <= m;
}

void floodfill(int r, int c) {
    if (!isInBounds(r, c) || grid[r][c] == '*' || grid[r][c] == '#') return;

    if (grid[r][c] == 'G')
        good--;

    grid[r][c] = '*';

    for (int i=0; i<4; i++)
        floodfill(r + dir[i][0], c + dir[i][1]);
}

// returns true if preliminary check passed, false if failed
bool check() {
    // iterate through all cells
    for (int i=1; i<=n; i++) {
    for (int j=1; j<=m; j++) {
        if (grid[i][j] == 'B') {
            // this cell marks a bad person
            // try to mark all neighboring cells as walls to enclose the bad person
            // if a good person is found in neighboring cells, fails
            for (int k=0; k<4; k++) {
                int nr = i+dir[k][0];
                int nc = j+dir[k][1];

                if (isInBounds(nr, nc) && grid[nr][nc] == 'G') {
                    cout << "No\n";
                    return false;
                } else if (grid[nr][nc] == '.') {
                    grid[nr][nc] = '#';
                }
            }
        }
    }}

    return true;
}

int main() {
    cin.tie(0) -> sync_with_stdio(0);

    cin >> t;

    for (int i=0; i<t; i++) {
        cin >> n >> m;
        good = 0;

        for (int j=1; j<=n; j++) {
            for (int k=1; k<=m; k++) {
                cin >> grid[j][k];
                if (grid[j][k] == 'G') good++;
            }
        }

        if (!check()) continue;

        // floodfill from exit to see how many good people were reached
        floodfill(n, m);

        if (good == 0) // means that all good people were reached
            cout << "Yes\n";
        else
            cout << "No\n";
    }

    cout << flush;
}