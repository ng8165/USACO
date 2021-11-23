// Milk Pails
// USACO Silver February 2016: http://www.usaco.org/index.php?page=viewproblem2&cpid=620

#include <bits/stdc++.h>

using namespace std;

int x, y, k, m;
int result = INT_MAX;

int visited[105][105][105];

void fill(int amtx, int amty, int op) {
    if (op > k || visited[amtx][amty][op]) return;

    visited[amtx][amty][op] = true;
    result = min(result, abs(amtx + amty - m));

    // fill either pail
    fill(x, amty, op+1);
    fill(amtx, y, op+1);

    // empty either pail
    fill(0, amty, op+1);
    fill(amtx, 0, op+1);

    // pour from one to other
    int pour = y-amty > amtx ? amtx : y-amty;
    fill(amtx-pour, amty+pour, op+1);
    pour = x-amtx > amty ? amty : x-amtx;
    fill(amtx+pour, amty-pour, op+1);
}

int main() {
    ifstream fin("pails.in");
    ofstream fout("pails.out");

    fin >> x >> y >> k >> m;

    fill(0, 0, 0);

    fout << result << endl;
}