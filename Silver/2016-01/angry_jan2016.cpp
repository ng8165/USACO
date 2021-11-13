#include <bits/stdc++.h>

using namespace std;

int n, k;
int x[50001];

bool canExplodeCows(int r) { // r is explosion range
    int cows = 1, start = x[1];

    for (int i=2; i<=n; i++) {
        if (x[i] - start >= r) {
            // this haybale will not be exploded, start another explosion
            start = x[i];
            cows++;
        } // else: this haybale will be exploded in the current explosion
    }

    return cows <= k;
}

int main() {
    ifstream fin("angry.in");
    ofstream fout("angry.out");

    fin >> n >> k;

    for (int i=1; i<=n; i++) fin >> x[i];
    sort(x+1, x+n+1);

    int left = 0, right = x[n];
    int result = INT_MAX;

    while (left <= right) {
        int mid = left + (right-left)/2;

        if (canExplodeCows(2*mid+1)) {
            right = mid-1;
            result = min(result, mid);
        } else {
            left = mid+1;
        }
    }

    fout << result << endl;
}