#include <bits/stdc++.h>

using namespace std;

int xo[2], yo[2], xt[2], yt[2];

int main() {
    ifstream fin("square.in");
    ofstream fout("square.out");

    fin >> xo[0] >> yo[0] >> xt[0] >> yt[0];
    fin >> xo[1] >> yo[1] >> xt[1] >> yt[1];

    int side1 = abs(max(xt[0], xt[1]) - min(xo[0], xo[1]));
    int side2 = abs(max(yt[0], yt[1]) - min(yo[0], yo[1]));
    
    int side = max(side1, side2);
    fout << side * side << endl;
}