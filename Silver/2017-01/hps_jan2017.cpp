#include <bits/stdc++.h>

using namespace std;

int n;
int h[100005], p[100005], s[100005];

char fj;

int main() {
    ifstream fin("hps.in");
    ofstream fout("hps.out");

    fin >> n;

    h[0] = 0; p[0] = 0; s[0] = 0;

    // construct prefix sum
    for (int i=1; i<=n; i++) {
        h[i] = h[i-1];
        p[i] = p[i-1];
        s[i] = s[i-1];

        fin >> fj;

        if (fj == 'H')
            h[i]++;
        else if (fj == 'P')
            p[i]++;
        else
            s[i]++;
    }

    int result = max(h[n], max(p[n], s[n]));
    int currMax;

    for (int i=1; i<n; i++) {
        currMax = max(0, h[i] + (p[n] - p[i-1]));
        currMax = max(currMax, h[i] + (s[n] - s[i-1]));
        currMax = max(currMax, p[i] + (h[n] - h[i-1]));
        currMax = max(currMax, p[i] + (s[n] - s[i-1]));
        currMax = max(currMax, s[i] + (h[n] - h[i-1]));
        currMax = max(currMax, s[i] + (p[n] - p[i-1]));

        result = max(result, currMax);
    }

    fout << result << endl;
}