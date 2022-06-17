// Trailing Zeros
// CSES Introductory Problems

#include <bits/stdc++.h>

using namespace std;

int main() {
    int n; cin >> n;

    int div = 5;
    int result = 0;

    while (div <= n) {
        result += n/div;
        div *= 5;
    }

    cout << result << endl;
}