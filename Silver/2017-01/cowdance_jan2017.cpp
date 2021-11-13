// Cow Dance Show
// USACO Silver January 2017: http://www.usaco.org/index.php?page=viewproblem2&cpid=690

#include <bits/stdc++.h>

using namespace std;

int n, maxt;
int d[10001];

int calcDanceTime(int t) {
    int time = 0;
    priority_queue<int, vector<int>, greater<int>> pq;

    // push initial cows into pq
    for (int i=1; i<=t; i++) pq.push(d[i]);

    // as cows finish, add end time for dance into pq
    for (int i=t+1; i<=n; i++) {
        time = pq.top(); pq.pop();
        pq.push(d[i] + time);
    }

    // no cows waiting to perform, clear pq
    while (!pq.empty()) {
        time = pq.top(); pq.pop();
    }

    return time;
}

int main() {
    ifstream fin("cowdance.in");
    ofstream fout("cowdance.out");

    fin >> n >> maxt;
    for (int i=1; i<=n; i++) fin >> d[i];

    int left = 1, right = n;
    int result = n;
    while (left <= right) {
        int mid = left + (right-left)/2;

        if (calcDanceTime(mid) <= maxt) {
            right = mid-1;
            result = min(result, mid);
        } else {
            left = mid+1;
        }
    }

    fout << result << endl;
}