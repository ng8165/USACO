// Berry Picking
// USACO Silver January 2020: http://www.usaco.org/index.php?page=viewproblem2&cpid=990

#include <bits/stdc++.h>

using namespace std;

int n, k;
int b[1001];

int quantity[1001];

int main() {
    ifstream fin("berries.in");
    ofstream fout("berries.out");

    int maxB = 0;

    fin >> n >> k;
    for (int i=1; i<=n; i++) {
        fin >> b[i];
        maxB = max(maxB, b[i]);
    }

    int result = 0;

    for (int bucket=1; bucket<=maxB; bucket++) {
        memset(quantity, 0, sizeof(quantity));

        for (int i=1; i<=n; i++) {
            quantity[bucket] += b[i] / bucket;
            quantity[b[i] % bucket]++;
        }

        int idx = maxB, cnt = k, berries = 0;

        while (idx > 0 && cnt > 0) {
            if (quantity[idx] > 0) {
                if (cnt <= k/2) berries += idx;
                
                quantity[idx]--;
                cnt--;
            }

            if (quantity[idx] == 0) idx--;
        }

        result = max(result, berries);
    }

    fout << result << endl;
}