// Daisy Chains
// USACO Bronze December 2020: http://www.usaco.org/index.php?page=viewproblem2&cpid=1060

#include <bits/stdc++.h>

using namespace std;

int N;
int p[101];
int psum[101];

int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);

    cin >> N;
    for (int i=1; i<=N; i++) {
        cin >> p[i];
        psum[i] = psum[i-1] + p[i];
    }

    int result = 0;

    for (int i=1; i<=N; i++) {
        for (int j=i; j<=N; j++) {
            double avg = (psum[j]-psum[i-1])/(j-i+1.0);

            for (int k=i; k<=j; k++) {
                if (p[k] == avg) {
                    result++;
                    break;
                }
            }
        }
    }

    cout << result << "\n";
}