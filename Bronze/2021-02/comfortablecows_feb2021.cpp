// Comfortable Cows
// USACO Bronze February 2021: http://www.usaco.org/index.php?page=viewproblem2&cpid=1108

#include <bits/stdc++.h>

using namespace std;

const int dir[4][2] = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

int N;
int pasture[1001][1001];
bool hasCow[1001][1001];

int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);

    cin >> N;

    int result = 0;

    for (int i=0; i<N; i++) {
        int x, y; cin >> x >> y;
        hasCow[x][y] = true;

        for (int j=0; j<4; j++) {
            int nx = x+dir[j][0];
            int ny = y+dir[j][1];

            if (nx >= 0 && nx <= 1000 && ny >= 0 && ny <= 1000) {
                if (hasCow[nx][ny] && pasture[nx][ny] == 3)
                    result--;

                pasture[nx][ny]++;

                if (hasCow[nx][ny] && pasture[nx][ny] == 3)
                    result++;
            }
        }

        cout << result << "\n";
    }
}