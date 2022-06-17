#include <bits/stdc++.h>

using namespace std;

int main() {
    ifstream fin("teleport.in");
    ofstream fout("teleport.out");

    int a, b, x, y;
    fin >> a >> b >> x >> y;

    int no = abs(a-b);
    int xy = abs(a-x) + abs(b-y);
    int yx = abs(a-y) + abs(b-x);

    fout << min(no, min(xy, yx)) << endl;
}