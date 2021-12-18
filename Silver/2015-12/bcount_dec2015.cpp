// Breed Counting
// USACO Silver December 2015: http://www.usaco.org/index.php?page=viewproblem2&cpid=572

#include <bits/stdc++.h>

using namespace std;

int n, q;
int psum[4][100001]; // psum[i][j] = how many cows with breed i in first j cows

int main() {
    ifstream fin("bcount.in");
    ofstream fout("bcount.out");

    fin >> n >> q;

    for (int i=1; i<=n; i++) {
        for (int j=1; j<=3; j++)
            psum[j][i] = psum[j][i-1];

        int breed; fin >> breed;
        psum[breed][i]++;
    }

    for (int i=1; i<=q; i++) {
        int a, b; fin >> a >> b;
        
        for (int j=1; j<=3; j++) {
            fout << psum[j][b] - psum[j][a-1];
            if (j < 3) fout << " ";
        }
        
        fout << endl;
    }
}