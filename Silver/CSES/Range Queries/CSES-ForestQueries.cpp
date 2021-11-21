#include <bits/stdc++.h>

using namespace std;

int n, q;
int prefix[1005][1005];

int main() {
    cin >> n >> q;
    for (int i=1; i<=n; i++) {
        for (int j=1; j<=n; j++) {
            char c; cin >> c;

            int add = 0;
            if (c == '*') add = 1;

            prefix[i][j] = prefix[i-1][j] + prefix[i][j-1] - prefix[i-1][j-1] + add;
        }
    }

    for (int i=0; i<q; i++) {
        int x1, x2, y1, y2;
        cin >> x1 >> y1 >> x2 >> y2;

        cout << prefix[x2][y2] - prefix[x1-1][y2] - prefix[x2][y1-1] + prefix[x1-1][y1-1] << endl;
    }
}