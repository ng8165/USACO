// Counting Rooms
// CSES Graph Algorithms: https://cses.fi/problemset/task/1192

#include <bits/stdc++.h>

using namespace std;

int n, m;
char grid[1000][1000];

void dfs(int r, int c) {
    if (r < 0 || r >= n || c < 0 || c >= m || grid[r][c] == '#') return;

    grid[r][c] = '#';

    dfs(r-1, c);
    dfs(r+1, c);
    dfs(r, c-1);
    dfs(r, c+1);
}

int main() {
    cin >> n >> m;
    for (int i=0; i<n; i++) {
        for (int j=0; j<m; j++) {
            cin >> grid[i][j];
        }
    }

    int result = 0;

    for (int i=0; i<n; i++) {
        for (int j=0; j<m; j++) {
            if (grid[i][j] == '#') continue;
            
            dfs(i, j);
            result++;
        }
    }

    cout << result << endl;
}