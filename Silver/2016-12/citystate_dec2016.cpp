// Cities and States
// USACO Silver December 2016: http://www.usaco.org/index.php?page=viewproblem2&cpid=667

#include <bits/stdc++.h>

using namespace std;

const int s = 26*26;

int n;
int cities[s][s];

inline int toInt(string str) {
    return 26*(str[0]-'A') + (str[1]-'A');
}

int main() {
    ifstream fin("citystate.in");
    ofstream fout("citystate.out");

    fin >> n;

    string city, state;
    for (int i=0; i<n; i++) {
        fin >> city >> state;
        cities[toInt(city)][toInt(state)]++;
    }

    int result = 0;

    for (int i=0; i<s; i++) {
        for (int j=i+1; j<s; j++) {
            result += cities[i][j] * cities[j][i];
        }
    }

    fout << result << endl;
}