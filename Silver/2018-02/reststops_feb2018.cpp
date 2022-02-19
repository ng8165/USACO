// Rest Stops
// USACO Silver February 2018: http://www.usaco.org/index.php?page=viewproblem2&cpid=810

#include <bits/stdc++.h>

using namespace std;

typedef long long ll;

int L, N, rF, rB;
int x[100001], c[100001];

int sMax[100001]; // sMax[i] stores the index of the stops with the greatest tastiness from i to N (suffix max)

int main() {
    ifstream fin("reststops.in");
    ofstream fout("reststops.out");

    fin >> L >> N >> rF >> rB;
    for (int i=1; i<=N; i++)
        fin >> x[i] >> c[i];
    
    // find suffix max
    int maxTasty = 0, maxIndex = -1;
    
    for (int i=N; i>0; i--) {
        if (c[i] > maxTasty) {
            maxTasty = c[i];
            maxIndex = i;
        }

        sMax[i] = maxIndex;
    }

    int i = 1;
    ll result = 0;
    int pos = 0;

    while (i <= N) {
        // jump to the next stop with the most tastiness
        i = sMax[i];
        
        // find how much tastiness units bessie can get
        ll time = (ll) (rF-rB)*(x[i]-pos);
        result += time*c[i];

        // update bessie's position and increment i
        pos = x[i];        
        i++;
    }

    fout << result << "\n";
}