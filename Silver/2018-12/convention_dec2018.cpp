// Convention
// USACO Silver December 2018: http://www.usaco.org/index.php?page=viewproblem2&cpid=858
// Binary Search on Answer

#include <bits/stdc++.h>

using namespace std;

int n, m, c;
int times[100001];

bool check(int gap) {
    int buses = 1;
    int cnt = 1, start = times[1];

    for (int i=2; i<=n; i++) {
        if (buses > m) return false;

        if (cnt >= c || times[i] - start > gap) {
            /// bus is full or gap is too large, so new bus
            buses++;
            cnt = 1;
            start = times[i];
        } else {
            // bus can still fit current cow
            cnt++;
        }
    }

    return buses <= m;
}

int main() {
    ifstream fin("convention.in");
    ofstream fout("convention.out");

    fin >> n >> m >> c;

    for (int i=1; i<=n; i++) fin >> times[i];
    sort(times+1, times+1+n);

    int left = 0, right = times[n];
    int result = right;

    while (left <= right) {
        int mid = left + (right-left)/2;

        if (check(mid)) {
            right = mid-1;
            result = min(result, mid);
        } else {
            left = mid+1;
        }
    }

    fout << result << endl;
}