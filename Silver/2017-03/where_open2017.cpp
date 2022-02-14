// Where's Bessie?
// USACO Silver US Open 2017: http://www.usaco.org/index.php?page=viewproblem2&cpid=740
// XJOI Problem 15310: https://xjoi.net/contest/3606/problem/1

#include <bits/stdc++.h>

using namespace std;

struct PCL {
    int r1, c1, r2, c2;

    void set(int r1, int c1, int r2, int c2) {
        this->r1 = r1;
        this->c1 = c1;
        this->r2 = r2;
        this->c2 = c2;
    }

    bool operator==(const PCL &other) const {
        return r1 == other.r1 && c1 == other.c1 && r2 == other.r2 && c2 == other.c2;
    }
};

int N;
char grid[20][20];

bool visited[20][20];
vector<PCL> pcls;

PCL check;

inline bool isInBounds(int& r, int& c) {
    return r >= check.r1 && r <= check.r2 && c >= check.c1 && c <= check.c2;
}

void floodfill(int r, int c, char& ch) {
    if (!isInBounds(r, c) || visited[r][c] || grid[r][c] != ch)
        return;

    visited[r][c] = true;

    floodfill(r-1, c, ch);
    floodfill(r+1, c, ch);
    floodfill(r, c-1, ch);
    floodfill(r, c+1, ch);
}

bool isValidPCL() {
    memset(visited, false, sizeof(visited));
    map<char, int> colors; // key: color, value: number of regions

    // floodfill from all connected regions of same color
    for (int r=check.r1; r<=check.r2; r++) {
        for (int c=check.c1; c<=check.c2; c++) {
            if (!visited[r][c]) {
                colors[grid[r][c]]++;
                floodfill(r, c, grid[r][c]);
            }
        }
    }

    // condition 1: pcl must only have 2 colors
    if (colors.size() != 2) return false;

    // condition 2: pcl must have one contiguous region of one color and at least 2
    // contiguous regions of the other color
    int color1 = colors.begin()->second, color2 = (++colors.begin())->second;

    return (color1 == 1 && color2 >= 2) || (color2 == 1 && color1 >= 2);
}

bool isPCLLargest(PCL& match) {
    for (PCL& pcl: pcls) {
        if (pcl == match) continue;

        if (pcl.r1 <= match.r1 && pcl.c1 <= match.c1 &&
            match.r2 <= pcl.r2 && match.c2 <= pcl.c2)
            return false;
    }

    return true;
}

int main() {
    cin.tie(0) -> sync_with_stdio(0);

    cin >> N;
    
    for (int i=0; i<N; i++) {
        for (int j=0; j<N; j++)
            cin >> grid[i][j];
    }

    for (int r1=0; r1<N; r1++) {
    for (int c1=0; c1<N; c1++) {
        for (int r2=r1; r2<N; r2++) {
        for (int c2=c1; c2<N; c2++) {
            check.set(r1, c1, r2, c2);

            if (isValidPCL())
                pcls.push_back(check);
        }}
    }}

    int result = 0;

    for (PCL& pcl: pcls) {
        if (isPCLLargest(pcl))
            result++;
    }

    cout << result << endl;
}