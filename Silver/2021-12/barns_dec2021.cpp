// Connecting Two Barns
// USACO Silver December 2021: http://www.usaco.org/index.php?page=viewproblem2&cpid=1159

#include <bits/stdc++.h>

using namespace std;

typedef long long ll;

int T;
int N, M;
vector<int> adjList[100001];

bool visited[100001];
int group[100001];
int groups;

void reset() {
    groups = 0;
    memset(visited, false, sizeof(visited));
    memset(group, 0, sizeof(group));
    for (int j=1; j<=N; j++)
        adjList[j].clear();
}

void dfs(int farm, int groupNum) {
    if (visited[farm]) return;

    visited[farm] = true;
    group[farm] = groupNum;

    for (int& adj: adjList[farm])
        dfs(adj, groupNum);
}

void distOneDir(int& from, int inc, vector<ll>& dist) {
    int last = 0;
    int curr = (inc == 1) ? 1 : N;

    while (curr >= 1 && curr <= N) {
        int& g = group[curr];

        if (g == group[from]) {
            last = curr;
        } else if (last != 0) {
            dist[g] = min(dist[g], (ll) (curr-last)*(curr-last));
        }

        curr += inc;
    }
}

vector<ll> distToOthers(int from) {
    vector<ll> dist(groups+1, LLONG_MAX);

    distOneDir(from, 1, dist);
    distOneDir(from, -1, dist);

    return dist;
}

int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);

    cin >> T;

    for (int i=0; i<T; i++) {
        // input
        cin >> N >> M;

        for (int j=0; j<M; j++) {
            int a, b; cin >> a >> b;
            adjList[a].push_back(b);
            adjList[b].push_back(a);
        }

        // assign the N cows to groups
        for (int j=1; j<=N; j++) {
            if (!visited[j])
                dfs(j, ++groups);
        }

        // special case: farm 1 and N are already connected
        if (group[N] == 1) {
            cout << "0\n";
            reset();
            continue;
        }
        
        // find the cost of connecting farm 1 to all other groups and farm N to all other groups
        vector<ll> dist1 = distToOthers(1);
        vector<ll> distN = distToOthers(N);

        // set result to cost of connecting farm 1's group to farm N's group
        ll result = dist1[group[N]];

        // iterate through all groups that are not farm 1's group or farm N's group to determine a middle group
        for (int j=1; j<=groups; j++) {
            if (j == 1 || j == group[N]) continue;
            if (dist1[j] == INT_MAX || distN[j] == INT_MAX) continue;
            result = min(result, dist1[j] + distN[j]);
        }

        cout << result << "\n";

        reset();    
    }
}