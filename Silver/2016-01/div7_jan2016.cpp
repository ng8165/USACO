// Subsequences Summing to Sevens
// USACO Silver January 2016: http://www.usaco.org/index.php?page=viewproblem2&cpid=595

#include <bits/stdc++.h>

using namespace std;

typedef long long ll;
typedef pair<int, int> pii;

int n;
ll psum[50001];

map<int, int> mods;

int main() {
    ifstream fin("div7.in");
    ofstream fout("div7.out");

    fin >> n;
    for (int i=1; i<=n; i++) {
        fin >> psum[i];
        psum[i] += psum[i-1];
    }

    int result = 0;

    for (int i=1; i<=n; i++) {
        int mod = psum[i] % 7;

        if (mods.count(mod)) {
            result = max(result, i-mods[mod]);
        } else {
            mods[mod] = i;
        }
    }

    fout << result << endl;
}