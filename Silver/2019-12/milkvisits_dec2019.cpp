// Milk Visits
// USACO Silver December 2019: http://www.usaco.org/index.php?page=viewproblem2&cpid=968

#include <bits/stdc++.h>

using namespace std;

int N, M;
char cows[100001];
vector<int> adjList[100001];

int group[100001]; // 0 = not visited, else group number

void dfs(int node, char& ch, int& groupNum) {
    if (group[node] || cows[node] != ch) return;

    group[node] = groupNum;

    for (int& neighbor: adjList[node])
        dfs(neighbor, ch, groupNum);
}

int main() {
    ifstream fin("milkvisits.in");
    ofstream fout("milkvisits.out");

    fin >> N >> M;
    for (int i=1; i<=N; i++) fin >> cows[i];

    // create adjacency list
    for (int i=1; i<N; i++) {
        int X, Y; fin >> X >> Y;
        adjList[X].push_back(Y);
        adjList[Y].push_back(X);
    }

    for (int i=1; i<=N; i++) {
        if (!group[i])
            // form components of barns with same cow breed
            dfs(i, cows[i], i);
    }

    for (int i=0; i<M; i++) {
        int A, B; char C;
        fin >> A >> B >> C;

        if (group[A] == group[B] && cows[A] != C) {
            // if cow A and B are in this group, and the breed is not C, then the farmer cannot be satisfied
            fout << 0;
        } else {
            // otherwise, the farmer can be satisfied
            fout << 1;
        }
    }

    fout << endl;
}