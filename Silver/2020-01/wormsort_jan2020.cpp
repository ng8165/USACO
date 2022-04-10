// Wormhole Sort
// USACO Silver January 2020: http://www.usaco.org/index.php?page=viewproblem2&cpid=992

#include <bits/stdc++.h>

using namespace std;

typedef pair<int, int> pii;

const int maxN = 100000 + 1;

int N, M;
int p[maxN];
vector<pii> adjList[maxN]; // first = node, second = width
int w[maxN];

int component[maxN]; // 0 = not visited, > 0 = component index

void dfs(int node, int& minWidth, int& compIdx) {
    component[node] = compIdx;

    for (auto& [loc, width]: adjList[node]) {
        if (component[loc] == 0 && width >= minWidth) // pruning
            dfs(loc, minWidth, compIdx);
    }
}

bool check(int& minWidth) {
    memset(component, 0, sizeof(component));

    // separate cows into connected components
    int compIdx = 0;

    for (int i=1; i<=N; i++) {
        if (component[i] == 0)
            dfs(i, minWidth, ++compIdx);
    }

    for (int i=1; i<=N; i++) {
        // if a cow is not in its correct location
        // and the location it should be at is not in the same component
        // then this minWidth is too large
        if (i != p[i] && component[i] != component[p[i]])
            return false;
    }

    return true;
}

int main() {
    ifstream fin("wormsort.in");
    ofstream fout("wormsort.out");

    fin >> N >> M;

    // special case: cows are already sorted
    bool isSorted = true;

    for (int i=1; i<=N; i++) {
        fin >> p[i];
        if (p[i] != i) isSorted = false;
    }

    if (isSorted) {
        fout << "-1\n";
        return 0;
    }
    
    // construct adjacency list
    for (int i=1; i<=M; i++) {
        int a, b;
        fin >> a >> b >> w[i];

        adjList[a].push_back({b, w[i]});
        adjList[b].push_back({a, w[i]});
    }

    sort(w+1, w+M+1);

    // binary search
    int left = 1, right = M;
    int result = 0;

    while (left <= right) {
        int mid = left + (right-left)/2;

        if (check(w[mid])) {
            left = mid+1;
            result = max(result, mid);
        } else {
            right = mid-1;
        }
    }

    fout << w[result] << "\n";
}