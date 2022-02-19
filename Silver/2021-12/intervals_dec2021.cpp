// Convoluted Intervals
// USACO Silver December 2021: http://www.usaco.org/index.php?page=viewproblem2&cpid=1160

#include <bits/stdc++.h>

using namespace std;

typedef long long ll;

const int maxM = 5000+1;

int N, M;

ll aFreq[maxM], bFreq[maxM];
ll event[2*maxM];

int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);

    cin >> N >> M;

    for (int i=0; i<N; i++) {
        int a, b; cin >> a >> b;

        // generate frequency tables
        aFreq[a]++;
        bFreq[b]++;
    }

    // instead of iterating over intervals O(n^2)
    // iterate over possible starting and ending points for intervals O(m^2)
    // and use frequency tables to know how many intervals there are
    
    for (int i=0; i<=M; i++) {
        for (int j=0; j<=M; j++)
            event[i+j] += aFreq[i] * aFreq[j];
    }

    for (int i=0; i<=M; i++) {
        for (int j=0; j<=M; j++)
            event[i+j+1] -= bFreq[i] * bFreq[j];
    }

    // generate prefix sum
    ll psum = 0;
    for (int i=0; i<=2*M; i++) {
        psum += event[i];
        cout << psum << "\n";
    }
}