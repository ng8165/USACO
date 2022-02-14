// Why Did the Cow Cross the Road III
// USACO Silver February 2017: http://www.usaco.org/index.php?page=viewproblem2&cpid=716

#include <bits/stdc++.h>

using namespace std;

int N, K, R;
char farm[200][200]; // x = road, c = cow, else = empty
int cows[100][2];

bool visited[200][200];

inline bool isInBounds(int& r, int& c) {
    return r >= 0 && r < N && c >= 0 && c < N;
}

// returns number of cows that were reached
int floodfill(int r, int c) {
    if (!isInBounds(r, c) || visited[r][c] || farm[r][c] == 'x') return 0;

    visited[r][c] = true;

    int sum = farm[r][c] == 'c' ? 1 : 0;
    sum += floodfill(r-1, c);
    sum += floodfill(r+1, c);
    sum += floodfill(r, c-1);
    sum += floodfill(r, c+1);

    return sum;
}

// converts 1-indexed to 0-indexed, then doubles
inline void convert(int& i) {
    i = (i-1)*2;
}

int main() {
    ifstream fin("countcross.in");
    ofstream fout("countcross.out");

    fin >> N >> K >> R;
    N *= 2;

    // insert roads into farm
    for (int i=0; i<R; i++) {
        int r1, c1, r2, c2;
        fin >> r1 >> c1 >> r2 >> c2;
        convert(r1); convert(c1); convert(r2); convert(c2);

        // ensure that smaller coordinate is (r1, c1) and larger is (r2, c2)
        if (r1+c1 > r2+c2) {
            swap(r1, r2);
            swap(c1, c2);
        }

        if (r1 == r2) {
            // same row, so vertical block
            farm[r1][c1+1] = 'x';
            farm[r1+1][c1+1] = 'x';
        } else {
            // same columns, so horizontal block
            farm[r1+1][c1] = 'x';
            farm[r1+1][c1+1] = 'x';
        }
    }

    // insert cows into farm
    for (int i=0; i<K; i++) {
        int &r = cows[i][0], &c = cows[i][1];
        fin >> r >> c; convert(r); convert(c);

        farm[r][c] = 'c';
    }

    // floodfill from each cow
    int result = 0;

    for (int i=0; i<K; i++) {
        memset(visited, false, sizeof(visited));
        result += (K-floodfill(cows[i][0], cows[i][1])); // adds number of cows that weren't reached
    }

    result /= 2; // double counted all distant pairs

    fout << result << endl;
}