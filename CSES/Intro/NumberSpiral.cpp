// Number Spiral
// CSES Introductory Problems: https://cses.fi/problemset/task/1071

#include <bits/stdc++.h>

using namespace std;

int main() {
    long long t, y, x;
    cin >> t;

    for (int i=0; i<t; i++) {
        cin >> y >> x;
        y--; x--;

        if (y > x) {
            if (y%2 == 1) cout << (y+1)*(y+1)-x;
            else cout << y*y+1+x;
        } else {
            if (x%2 == 0) cout << (x+1)*(x+1)-y;
            else cout << x*x+1+y;
        }

        cout << "\n";
    }

    cout << flush;
}