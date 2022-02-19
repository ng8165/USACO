// Comfortable Cows
// USACO Silver February 2021: http://www.usaco.org/index.php?page=viewproblem2&cpid=1110

#include <bits/stdc++.h>

using namespace std;

typedef pair<int, int> pii;

const int dir[4][2] = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
const int maxCoor = 1000 + 500;

int N;
int neighbors[maxCoor][maxCoor];
bool pasture[maxCoor][maxCoor];

int tempCows;

inline void addCow(int x, int y) {
    // mark the cow as true so it exists in the pasture
    pasture[x][y] = true;

    // update neighbor counts
    for (int i=0; i<4; i++)
        neighbors[x+dir[i][0]][y+dir[i][1]]++;
}

inline pii addTempCowFor(int x, int y) {
    tempCows++;

    // find coordinates of the temporary cow
    int ax = -1, ay = -1;
    for (int i=0; i<4; i++) {
        int nx = x+dir[i][0], ny = y+dir[i][1];
        if (!pasture[nx][ny]) {
            ax = nx; ay = ny;
            break;
        }
    }
    
    // add cow to pasture and update neighbor counts
    addCow(ax, ay);

    // return cow to be added into queue
    return {ax, ay};
}

void bfs(int sx, int sy) {
    queue<pii> q;
    q.push({sx, sy});
    addCow(sx, sy);

    while (!q.empty()) {
        pii& curr = q.front(); q.pop();
        int &x = curr.first, &y = curr.second;

        // check if self is comfortable
        if (neighbors[x][y] == 3)
            q.push(addTempCowFor(x, y));

        // check if neighbors (if they exist) are comfortable
        for (int i=0; i<4; i++) {
            int nx = x+dir[i][0], ny = y+dir[i][1];

            if (pasture[nx][ny] && neighbors[nx][ny] == 3)
                q.push(addTempCowFor(nx, ny));
        }
    }
}

int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);

    cin >> N;

    for (int i=0; i<N; i++) {
        int x, y; cin >> x >> y;
        x += 250; y += 250; // add border around pasture

        // if cow is already in pasture, it must be a temporary cow,
        // so can remove it from the temporary cows count (as it is no longer a temporary cow)
        if (pasture[x][y]) {
            cout << --tempCows << "\n";
        } else {
            bfs(x, y);
            cout << tempCows << "\n";
        }
    }
}