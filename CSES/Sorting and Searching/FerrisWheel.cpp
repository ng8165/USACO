// Ferris Wheel
// CSES Sorting and Searching: https://cses.fi/problemset/task/1090

#include <bits/stdc++.h>

using namespace std;

int n, x;
int p[200001];

int main() {
    cin >> n >> x;

    for (int i=1; i<=n; i++) cin >> p[i];
    sort(p+1, p+n+1);

    int left = 1, right = n;
    int gondolas = 0;

    while (left < right) {
        if (p[left] + p[right] <= x) {
            left++;
            right--;
        } else {
            right--;
        }

        gondolas++;
    }

    if (left == right) gondolas++;

    cout << gondolas << endl;
}