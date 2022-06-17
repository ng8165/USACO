// Remove Smallest
// CodeForces Round #661 Division 3 Problem A: https://codeforces.com/contest/1399/problem/A

#include <iostream>
#include <algorithm>

using namespace std;

int main() {
    int t;
    cin >> t;

    for (int i=0; i<t; i++) {
        int n;
        cin >> n;

        int input[n];
        for (int j=0; j<n; j++) cin >> input[j];
        sort(input, input+n);

        bool isPossible = true;

        for (int j=1; j<n; j++) {
            if (input[j]-input[j-1] > 1) {
                isPossible = false;
                break;
            }
        }

        if (isPossible) cout << "YES" << endl;
        else cout << "NO" << endl;
    }
}