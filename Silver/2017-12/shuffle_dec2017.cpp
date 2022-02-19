// The Bovine Shuffle
// USACO Silver December 2017: http://www.usaco.org/index.php?page=viewproblem2&cpid=764

#include <bits/stdc++.h>

using namespace std;

int N;
int a[100001];

bool cycles[100001];
bool visited[100001];

int main() {
    ifstream fin("shuffle.in");
    ofstream fout("shuffle.out");

    fin >> N;
    for (int i=1; i<=N; i++)
        fin >> a[i];

    int size = 0;

    for (int i=1; i<=N; i++) {
        memset(visited, false, sizeof(visited));

        // identify a cycle
        int pos = i;

        while (!visited[pos] && !cycles[pos]) {
            visited[pos] = true;
            pos = a[pos];
        }

        // if cycle was already found, continue
        if (cycles[pos]) continue;

        // add this cycle to the result
        int cycleStart = pos;

        do {
            cycles[pos] = true;
            size++;
            pos = a[pos];
        } while (pos != cycleStart);
    }

    fout << size << "\n";
}