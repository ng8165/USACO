// Dance Mooves
// USACO Silver January 2021: http://usaco.org/index.php?page=viewproblem2&cpid=1086

#include <bits/stdc++.h>

using namespace std;

int N, K;

int curr[100001];
int dance[100001]; // dance[i] stores the index of a cow at index i after k swaps
set<int> s[100001]; // s[i] stores the set of indices a cow at index i will visit within k swaps

int main() {
    ios::sync_with_stdio(false);
    cin.tie(0);

    cin >> N >> K;

    for (int i=1; i<=N; i++) {
        curr[i] = i; // here, initializes curr as the line of cows to simulate k swaps
        s[i].insert(i);
    }

    for (int i=1; i<=K; i++) {
        int m1, m2; cin >> m1 >> m2;
        s[curr[m1]].insert(m2);
        s[curr[m2]].insert(m1);
        swap(curr[m1], curr[m2]);
    }

    for (int i=1; i<=N; i++)
        dance[curr[i]] = i;

    // now curr stores the result (0 means not processed yet)
    memset(curr, 0, sizeof(curr));
    
    for (int i=1; i<=N; i++) {
        if (curr[i] > 0) continue;

        int idx = i;
        set<int> result;

        // simulate k swaps at once until the cow index returns back to original
        do {
            result.insert(s[idx].begin(), s[idx].end());
            idx = dance[idx];
        } while (idx != i);

        // all of the indices that reached after k swaps at once will be able to
        // reach the same number of indices
        do {
            curr[idx] = result.size();
            idx = dance[idx];
        } while (idx != i);
    }

    for (int i=1; i<=N; i++) cout << curr[i] << "\n";
    cout << flush;
}