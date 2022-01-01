// Review Site
// CodeForces Educational Round 107 Problem A: https://codeforces.com/contest/1511/problem/A

#include <iostream>

using namespace std;

int main() {
    int t;
    cin >> t;

    for (int i=0; i<t; i++) {
        int n;
        cin >> n;

        pair<int, int> server1;
        pair<int, int> server2;

        for (int j=0; j<n; j++) {
            int type;
            cin >> type;

            if (type == 1) {
                server1.first++;
            } else if (type == 2) {
                server1.second--;
            } else {
                if (server1.first >= server1.second) {
                    server1.first++;
                } else if (server2.first >= server2.second) {
                    server2.first++;
                } else {
                    server1.second--;
                }
            }
        }

        cout << server1.first + server2.first << endl;
    }
}