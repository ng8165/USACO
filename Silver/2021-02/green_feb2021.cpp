// Just Green Enough
// USACO Silver February 2021: http://usaco.org/index.php?page=viewproblem2&cpid=1112
// XJOI Problem 15477: https://xjoi.net/contest/3606/problem/2

#include <bits/stdc++.h>

using namespace std;

typedef long long ll;

int N;
bitset<501> grid100[501]; // if grid100[i][j] == 1, then that number >= 100
bitset<501> grid101[501]; // if grid101[i][j] == 1, then that number >= 101

// finds the number of rectangles in row
ll rowSum(bitset<501>& row) {
    int length = 0;
    ll result = 0;

    for (int i=1; i<=N; i++) {
        if (row[i] == 0 && length > 0) {
            // trailing segment of 1s has ended
            // number of rectangles present is length*(length+1)/2
            result += length*(length+1)/2;
            length = 0;
        }
        
        length += row[i];
    }

    // if last segment of 1s has not been added
    if (length > 0)
        result += length*(length+1)/2;

    return result;
}

// finds the number of rectangles in a grid
ll findRectangles(bitset<501>* grid) {
    ll result = 0;
    bitset<501> row;

    for (int i=1; i<=N; i++) {
        row.set(); // sets all bits in row to 1

        // scans from row i to row j
        for (int j=i; j<=N; j++) {
            // row[k] == 1 means that there is a rectangle from grid[i][k] to grid[j][k]
            row &= grid[j];

            // if there are consecutive indices in row that are 1, then adds multiple rectangles
            result += rowSum(row);
        }
    }

    return result;
}

int main() {
    cin.tie(0) -> sync_with_stdio(0);

    cin >> N;

    for (int i=1; i<=N; i++) {
        for (int j=1; j<=N; j++) {
            int num; cin >> num;

            if (num >= 100) grid100[i].set(j);
            if (num >= 101) grid101[i].set(j);
        }
    }

    // use complementary counting to find number of rectangles that must have 100
    // subtract number of rectangles in grid101 from grid100
    cout << findRectangles(grid100) - findRectangles(grid101) << endl;
}