// MooBuzz
// USACO Silver December 2019: http://www.usaco.org/index.php?page=viewproblem2&cpid=966

#include <bits/stdc++.h>

using namespace std;

int main() {
    ifstream fin("moobuzz.in");
    ofstream fout("moobuzz.out");

    int n; fin >> n; n--;

    map<int, int> m;
    m[0] = 1; m[1] = 2;  m[2] = 4;  m[3] = 7;
    m[4] = 8; m[5] = 11; m[6] = 13; m[7] = 14;

    int result = 15 * (n/8);
    result += m[n%8];

    fout << result << endl;
}