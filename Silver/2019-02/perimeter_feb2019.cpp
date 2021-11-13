#include <bits/stdc++.h>

using namespace std;

int n;
char grid[1000][1000]; // # = ice cream, . = empty, * = visited
const int dirs[4][2] = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

int area, perimeter;

inline bool isOutOfBounds(int r, int c) {
    return r < 0 || r >= n || c < 0 || c >= n;
}

int countEdge(int r, int c) {
    int edge = 0;

    for (int i=0; i<4; i++) {
        int nr = r + dirs[i][0];
        int nc = c + dirs[i][1];

        if (isOutOfBounds(nr, nc) || grid[nr][nc] == '.') {
            edge++;
        }
    }

    return edge;
}

void floodfill(int r, int c) {
    area++;
    perimeter += countEdge(r, c);

    grid[r][c] = '*';

    for (int i=0; i<4; i++) {
        int nr = r + dirs[i][0];
        int nc = c + dirs[i][1];

        if (!isOutOfBounds(nr, nc) && grid[nr][nc] == '#') {
            floodfill(nr, nc);
        }
    }
}

int main() {
    ifstream fin("perimeter.in");
    ofstream fout("perimeter.out");

    fin >> n;
    for (int i=0; i<n; i++) {
        for (int j=0; j<n; j++) {
            fin >> grid[i][j];
        }
    }

    int maxArea = 0, maxPerimeter = 0;

    for (int i=0; i<n; i++) {
        for (int j=0; j<n; j++) {
            if (grid[i][j] == '#') {
                area = perimeter = 0;

                floodfill(i, j);

                if (area > maxArea) {
                    maxArea = area;
                    maxPerimeter = perimeter;
                } else if (area == maxArea && perimeter < maxPerimeter) {
                    maxPerimeter = perimeter;
                }
            }
        }
    }

    fout << maxArea << " " << maxPerimeter << endl;
}