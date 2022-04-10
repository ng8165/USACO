// Rectangular Pasture
// USACO Silver December 2020: http://www.usaco.org/index.php?page=viewproblem2&cpid=1063

#include <bits/stdc++.h>

using namespace std;

struct Cow {
    int x;
    int y;
};

bool cmpX(const Cow& a, const Cow& b) {
    return a.x < b.x;
}

bool cmpY(const Cow& a, const Cow& b) {
    return a.y < b.y;
}

const int maxN = 2500+1;

int N;
Cow cows[maxN];

int psum[maxN][maxN];

int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);

    cin >> N;
    for (int i=1; i<=N; i++)
        cin >> cows[i].x >> cows[i].y;
    
    // coordinate compression
    sort(cows+1, cows+1+N, cmpX);
    for (int i=1; i<=N; i++) cows[i].x = i;
    sort(cows+1, cows+1+N, cmpY);
    for (int i=1; i<=N; i++) cows[i].y = i;

    // generate prefix sum
    for (int i=1; i<=N; i++)
        psum[cows[i].x][cows[i].y]++;

    for (int i=1; i<=N; i++) {
        for (int j=1; j<=N; j++) {
            psum[i][j] += psum[i-1][j] + psum[i][j-1] - psum[i-1][j-1];
        }
    }

    long long result = N+1; // empty subset and subsets of a single cow
    
    for (int i=1; i<=N; i++) {
        for (int j=i+1; j<=N; j++) {
            int bottom = cows[i].y, top = cows[j].y;
            int left = min(cows[i].x, cows[j].x), right = max(cows[i].x, cows[j].x);

            int leftCnt = psum[left][top] - psum[left][bottom-1];
            int rightCnt = psum[N][top] - psum[N][bottom-1] - psum[right-1][top] + psum[right-1][bottom-1];

            result += leftCnt * rightCnt;
        }
    }

    cout << result << "\n";
}

