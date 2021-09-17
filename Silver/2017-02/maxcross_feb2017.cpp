#include <bits/stdc++.h>

using namespace std;

int main() {
    ifstream fin("maxcross.in");
    ofstream fout("maxcross.out");

    int n, k, b;
    fin >> n >> k >> b;

    unordered_set<int> s;
    for (int i=0; i<b; i++) {
        int signal; fin >> signal;
        s.insert(signal);
    }

    int prefix[n+1]; prefix[0] = 0;
    for (int i=1; i<=n; i++) {
        if (s.count(i) == 1) prefix[i] = prefix[i-1]+1;
        else prefix[i] = prefix[i-1];
    }

    int result = INT_MAX;
    for (int i=1, j=k; j<=n; i++, j++) {
        result = min(result, prefix[j]-prefix[i-1]);
    }

    fout << result << endl;
}