// Restaurant Customers
// CSES Sorting and Searching: https://cses.fi/problemset/task/1619

#include <bits/stdc++.h>

using namespace std;

typedef pair<int, int> pii;

int main() {
    int n;
    cin >> n;

    pii timeline[2*n];
    for (int i=0; i<2*n; i+=2) {
        int start, end; cin >> start >> end;
        timeline[i] = make_pair(start, 1);
        timeline[i+1] = make_pair(end, -1);
    }
    sort(timeline, timeline+2*n);

    int customers = 0, result = 0;
    for (pii p: timeline) {
        customers += p.second;
        result = max(result, customers);
    }

    cout << result << endl;
}