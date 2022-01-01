// Missing Number
// CSES Introductory Problems: https://cses.fi/problemset/task/1083

#include <bits/stdc++.h>

using namespace std;

int n;
bool nums[200001];

int main() {
    cin >> n;
    for (int i=1; i<n; i++) {
        int num; cin >> num;
        nums[num] = true;
    }

    for (int i=1; i<=n; i++) {
        if (!nums[i]) {
            cout << i << endl;
            break;
        }
    }
}