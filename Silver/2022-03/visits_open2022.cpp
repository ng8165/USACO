// Visits
// USACO Silver US Open 2022: http://www.usaco.org/index.php?page=viewproblem2&cpid=1230

#include <bits/stdc++.h>

using namespace std;

typedef long long ll;

const int maxN = 1e5+1;

int N;
int a[maxN];
int v[maxN];

bool visited[maxN];
bool isInCycle[maxN];
ll sum = 0;

int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);

    cin >> N;
    for (int i=1; i<=N; i++) {
        cin >> a[i] >> v[i];
        sum += v[i];
    }

    for (int i=1; i<=N; i++) {
        if (isInCycle[i]) continue;

        memset(visited, false, sizeof(visited));

        // try to indentify a cycle
        int curr = i;

        do {
            visited[curr] = true;
            curr = a[curr];
        } while (!visited[curr] && !isInCycle[curr]);

        if (isInCycle[curr]) continue;

        // if cycle is found, remove the smallest moos
        int minSub = INT_MAX;
        int cycleStart = curr;

        do {
            isInCycle[curr] = true;
            minSub = min(minSub, v[curr]);
            curr = a[curr];
        } while (curr != cycleStart);

        sum -= minSub;
    }

    cout << sum << "\n";
}