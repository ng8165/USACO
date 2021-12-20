// USB vs. PS/2
// CodeForces Edu Round 17 Problem B: https://codeforces.com/contest/762/problem/B

#include <bits/stdc++.h>
#define price first
#define type second

using namespace std;

int a, b, c;
int m;
pair<int, string> mice[300001];

int main() {
    cin >> a >> b >> c;
    cin >> m;
    for (int i=1; i<=m; i++)
        cin >> mice[i].price >> mice[i].type;

    sort(mice+1, mice+m+1);

    int result = 0;
    long long cost = 0;

    for (int i=1; i<=m; i++) {
        if (mice[i].type == "USB" && a > 0) {
            cost += mice[i].price;
            a--; result++;
        } else if (mice[i].type == "PS/2" && b > 0) {
            cost += mice[i].price;
            b--; result++;
        } else if (c > 0) {
            cost += mice[i].price;
            c--; result++;
        }
    }

    cout << result << " " << cost << endl;
}