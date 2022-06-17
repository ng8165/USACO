// Permutations
// CSES Introductory Problems: https://cses.fi/problemset/task/1070

#include <bits/stdc++.h>

using namespace std;

int main() {
    int n; cin >> n;

    if (n == 2 || n == 3) {
        cout << "NO SOLUTION" << endl;
        return 0;
    } else if (n == 4) {
        cout << "3 1 4 2" << endl;
        return 0;
    }

    int num = 1;

    while (num <= n) {
        cout << num << " ";
        num += 2;
    }

    num = 2;

    while (num <= n) {
        cout << num << " ";
        num += 2;
    }

    cout << endl;
}