// Sleepy Cow Herding
// USACO Silver February 2019: http://www.usaco.org/index.php?page=viewproblem2&cpid=918
// XJOI Problem 15381: https://xjoi.net/contest/3353/problem/3?locale=en

#include <bits/stdc++.h>

using namespace std;

int n;
int cows[100001];

int main() {
    ifstream fin("herding.in");
    ofstream fout("herding.out");

    fin >> n;
    for (int i=1; i<=n; i++) fin >> cows[i];

    sort(cows+1, cows+n+1);

    // min moves
    int moves = 0;

    if ((cows[n-1]-cows[1]+1 == n-1 && cows[n]-cows[n-1] > 2) ||
        (cows[n]-cows[2]+1 == n-1 && cows[2]-cows[1] > 2)) {
        // if one cow is an outlier (gap > 2), requires 2 moves to be placed consecutively
        moves = 2;
    } else {
        // otherwise, requires how ever many empty spaces are in the window with largest number of cows
        for (int left=1, right=1; left<=n; left++) {
            while (right <= n && cows[right] - cows[left] < n) right++;
            moves = max(moves, right-left);
        }
    
        moves = n - moves;
    }

    fout << moves << endl;

    // max moves
    // after moving cow at rightmost position, we can move each cow so that it can
    // reaches every single empty space in the line until it is consecutive at the left
    int moveRight = (cows[n-1] - cows[1] + 1) - (n-1);
    int moveLeft = (cows[n] - cows[2] + 1) - (n-1); // similar process for moving from left to right

    moves = max(moveLeft, moveRight);
    fout << moves << endl;
}