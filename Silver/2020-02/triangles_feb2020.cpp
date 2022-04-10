// Triangles
// USACO Silver February 2020: http://www.usaco.org/index.php?page=viewproblem2&cpid=1015

#include <bits/stdc++.h>

using namespace std;

typedef long long ll;

struct Fence {
    int r, c;
    int rowSum, colSum;
};

bool cmpR(const Fence& a, const Fence& b) {
    if (a.r == b.r) return a.c < b.c;
    return a.r < b.r;
}

bool cmpC(const Fence& a, const Fence& b) {
    if (a.c == b.c) return a.r < b.r;
    return a.c < b.c;
}

const int MOD = 1e9+7;
const int maxN = 100000+1;
const int offset = 10000;
const int maxCoor = 10000+offset+1;

int N;
Fence f[maxN];

vector<int> row[maxCoor]; // row[r] stores the column for all fences in row r
vector<int> col[maxCoor]; // col[c] stores the row for all fences in column c

void calcRowSum() {
    // a rowSum is the sum of all distances from a selected point to all other points in the same row
    // the goal: use previous rowSums to quickly find a new rowSum if in the same row
    int rowSum[N+1];
    memset(rowSum, 0, sizeof(rowSum));

    sort(f+1, f+N+1, cmpR);

    int i = 1;
    while (i <= N) {
        // for the first fence in the row, use a loop to find the rowSum
        for (int& c: row[f[i].r])
            rowSum[i] += abs(f[i].c - c);

        f[i].rowSum = rowSum[i];

        // find how many other cows there are in the same row
        int j = i;
        while (j <= N && f[i].r == f[j].r) j++;
        int rowCows = j-i;

        // apply the formula to calculate the rowSum for the other cows in the same row
        j = i+1;
        int rowCnt = 1;
        while (j <= N && f[i].r == f[j].r) {
            int len = f[j].c - f[j-1].c; // how far the selected point shifts to the right (from previous location)
            rowSum[j] = rowSum[j-1] + rowCnt*len - (rowCows-rowCnt)*len;
            f[j].rowSum = rowSum[j];

            j++; rowCnt++;
        }

        i = j; // j marks a fence in a new row
    }
}

void calcColSum() {
    // to see comments, check calcRowSum()
    // essentially the same function, but switched rows and columns

    int colSum[N+1];
    memset(colSum, 0, sizeof(colSum));

    sort(f+1, f+N+1, cmpC);

    int i = 1;
    while (i <= N) {
        for (int& r: col[f[i].c])
            colSum[i] += abs(f[i].r - r);

        f[i].colSum = colSum[i];

        int j = i;
        while (j <= N && f[i].c == f[j].c) j++;
        int colCows = j-i;

        j = i+1;
        int colCnt = 1;
        while (j <= N && f[i].c == f[j].c) {
            int len = f[j].r - f[j-1].r;
            colSum[j] = colSum[j-1] + colCnt*len - (colCows-colCnt)*len;
            f[j].colSum = colSum[j];

            j++; colCnt++;
        }

        i = j;
    }
}

int main() {
    // input
    ifstream fin("triangles.in");
    ofstream fout("triangles.out");

    fin >> N;

    for (int i=1; i<=N; i++) {
        int &r = f[i].r, &c = f[i].c;
        fin >> r >> c; r += offset; c += offset;

        row[r].push_back(c);
        col[c].push_back(r);
    }

    calcRowSum();
    calcColSum();

    ll result = 0;

    for (int i=1; i<=N; i++) {
        int &r = f[i].r, &c = f[i].c;
        if (row[r].size() <= 1 || col[c].size() <= 1)
            // unable to create a triangle
            continue;
        
        result += (ll) f[i].rowSum * f[i].colSum;
    }

    fout << result % MOD << endl;
}