// Why Did the Cow Cross the Road
// USACO Gold February 2017: http://www.usaco.org/index.php?page=viewproblem2&cpid=717
// XJOI Problem 15279: https://xjoi.net/contest/4691/problem/2?locale=en

#include <bits/stdc++.h>

using namespace std;

struct Bessie {
    int r, c;
    unsigned int time;

    bool operator<(const Bessie& other) const {
        return time > other.time;
    }
};

const int dir[16][2] = {{-3, 0}, {-2, -1}, {-2, 1}, {-1, -2}, {-1, 0}, {-1, 2}, {0, -3}, {0, -1}, {0, 1}, {0, 3}, {1, -2}, {1, 0}, {1, 2}, {2, -1}, {2, 1}, {3, 0}};
const int maxN = 101;
int N, T;
int grid[maxN][maxN];
unsigned int shortest[maxN][maxN];

inline bool isInBounds(int r, int c) {
    return r > 0 && r <= N && c > 0 && c <= N;
}

void dijkstra() {
    priority_queue<Bessie> pq;
    pq.push({1, 1, 0});
    shortest[1][1] = 0;

    while (!pq.empty()) {
        Bessie b = pq.top(); pq.pop();

        if (b.time > shortest[b.r][b.c])
            continue;
        
        for (int i=0; i<16; i++) {
            int r = b.r+dir[i][0], c = b.c+dir[i][1];
            auto time = b.time + 3*T + grid[r][c];

            if (isInBounds(r, c) && time < shortest[r][c]) {
                shortest[r][c] = time;
                pq.push({r, c, time});
            }
        }
    }
}

int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);

    memset(shortest, UINT_MAX, sizeof(shortest));

    // freopen("visitfj.in", "r", stdin);
    // freopen("visitfj.out", "w", stdout);

    cin >> N >> T;
    for (int i=1; i<=N; i++) {
        for (int j=1; j<=N; j++) {
            cin >> grid[i][j];
        }
    }

    dijkstra();

    cout << min(shortest[N][N], // at destination
            min(T + min(shortest[N-1][N], shortest[N][N-1]), // 1 step from destination
                2*T + min(shortest[N-2][N], min(shortest[N-1][N-1], shortest[N][N-2])))) << "\n"; // 2 steps from destination
}