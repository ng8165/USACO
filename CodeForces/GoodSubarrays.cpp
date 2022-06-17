// Good Subarrays
// Educational CodeForces Round 93 (Div. 2) Problem C: https://codeforces.com/contest/1398/problem/C

#include <bits/stdc++.h>

using namespace std;

int t;
int n;

int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);

    cin >> t;

    for (int i=0; i<t; i++) {
        cin >> n;

        long long result = 0, psum = 0, diff = -1;
        map<int, int> m;

        for (int j=1; j<=n; j++) {
            char c; cin >> c;
            int num = c - '0';

            psum += num;
            diff = j - psum;

            if (diff == 0)
                result++;

            if (m.count(diff))
                result += m[diff];

            m[diff]++;
        }

        cout << result << "\n";
    }
}