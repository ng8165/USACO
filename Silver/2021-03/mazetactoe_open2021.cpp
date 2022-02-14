// Maze Tac Toe
// USACO Silver US Open 2021: http://www.usaco.org/index.php?page=viewproblem2&cpid=1134

#include <bits/stdc++.h>

using namespace std;

struct Cell {
    char ch;
    int r, c;
};

int N;
Cell maze[26][26];
char board[4][4];
bool visited[26][26][19683]; // 19683 = 3^9 = number of different boards

const int dir[4][2] = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
const int pow3[9] = {1, 3, 9, 27, 81, 243, 729, 2187, 6561};
set<int> result;

// checks if the board is winning
bool check() {
    // horizontally
    for (int i=1; i<=3; i++) {
        if ((board[i][1] == 'M' && board[i][2] == 'O' && board[i][3] == 'O') ||
            (board[i][1] == 'O' && board[i][2] == 'O' && board[i][3] == 'M'))
            return true;
    }

    // vertically
    for (int i=1; i<=3; i++) {
        if ((board[1][i] == 'M' && board[2][i] == 'O' && board[3][i] == 'O') ||
            (board[1][i] == 'O' && board[2][i] == 'O' && board[3][i] == 'M'))
            return true;
    }

    // diagonally
    return (board[1][1] == 'M' && board[2][2] == 'O' && board[3][3] == 'O') || 
           (board[1][1] == 'O' && board[2][2] == 'O' && board[3][3] == 'M') ||
           (board[1][3] == 'M' && board[2][2] == 'O' && board[3][1] == 'O') || 
           (board[1][3] == 'O' && board[2][2] == 'O' && board[3][1] == 'M');
}

// state = encoded board in base 3 (0 = empty, 1 = M, 2 = O)
void floodfill(int r, int c, int state) {
    if (r < 1 || r > N || c < 1 || c > N || maze[r][c].ch == '#' || visited[r][c][state])
        return;
    
    if (check()) {
        result.insert(state);
        return;
    }
    
    Cell& cell = maze[r][c];
    char& ch = cell.ch;
    int &br = cell.r, &bc = cell.c;
    bool revert = false;

    // change state if needed
    if (ch == 'M' && board[br][bc] == 0) {
        board[br][bc] = 'M';
        state += pow3[(br-1)*3+(bc-1)];
        revert = true;
    } else if (ch == 'O' && board[br][bc] == 0) {
        board[br][bc] = 'O';
        state += pow3[(br-1)*3+(bc-1)]*2;
        revert = true;
    }

    visited[r][c][state] = true;

    // floodfill neighbors
    for (int i=0; i<4; i++)
        floodfill(r+dir[i][0], c+dir[i][1], state);

    // backtracking
    if (revert)
        board[br][bc] = 0;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(0);

    cin >> N;

    int sr = -1, sc = -1;

    for (int i=1; i<=N; i++) {
        for (int j=1; j<=N; j++) {
            Cell& cell = maze[i][j];
            char r, c;
            cin >> cell.ch >> r >> c;

            if (cell.ch == 'M' || cell.ch == 'O') {
                cell.r = r-'0';
                cell.c = c-'0';
            } else if (cell.ch == 'B') {
                sr = i;
                sc = j;
                cell.ch = '.'; // set bessie to an empty cell
            }
        }
    }

    floodfill(sr, sc, 0);

    cout << result.size() << endl;
}