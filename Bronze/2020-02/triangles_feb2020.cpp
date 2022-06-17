#include <bits/stdc++.h>

using namespace std;

int n;
int x[105], y[105];

int main() {
    ifstream fin("triangles.in");
    ofstream fout("triangles.out");

    fin >> n;
    for (int i=1; i<=n; i++) {
        fin >> x[i] >> y[i];
    }

    int result = 0;

    for (int i=1; i<=n; i++) { for (int j=i+1; j<=n; j++) { for (int k=j+1; k<=n; k++) {
        int length = 0, width = 0;

        if (y[i] == y[j]) length = abs(x[i] - x[j]);
        else if (y[i] == y[k]) length = abs(x[i] - x[k]);
        else if (y[j] == y[k]) length = abs(x[j] - x[k]);

        if (x[i] == x[j]) width = abs(y[i] - y[j]);
        else if (x[i] == x[k]) width = abs(y[i] - y[k]);
        else if (x[j] == x[k]) width = abs(y[j] - y[k]);

        result = max(result, length*width);
    }}}

    fout << result << endl;
}