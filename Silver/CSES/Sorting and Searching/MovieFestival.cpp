// Movie Festival
// CSES Sorting and Searching: https://cses.fi/problemset/task/1629

#include <bits/stdc++.h>
#define end first
#define start second

using namespace std;

int n;
pair<int, int> movies[200001];

int main() {
    cin >> n;
    for (int i=1; i<=n; i++)
        cin >> movies[i].start >> movies[i].end;

    sort(movies+1, movies+n+1);

    int result = 0;
    int lastEnd = 0;

    for (int i=1; i<=n; i++) {
        if (movies[i].start >= lastEnd) {
            lastEnd = movies[i].end;
            result++;
        }
    }

    cout << result << endl;
}