// High Card Wins
// USACO Silver December 2015: http://www.usaco.org/index.php?page=viewproblem2&cpid=571

#include <bits/stdc++.h>

using namespace std;

int n;
set<int> elsie;

set<int> bessie;

int main() {
    ifstream fin("highcard.in");
    ofstream fout("highcard.out");

    fin >> n;
    for (int i=1; i<=n; i++) {
        int card; fin >> card;
        elsie.insert(card);
    }

    for (int i=1; i<=2*n; i++) {
        if (!elsie.count(i)) {
            bessie.insert(i);
        }
    }

    int points = 0;

    for (int card: elsie) {
        auto it = bessie.upper_bound(card);

        if (it == bessie.end()) break;

        bessie.erase(it);
        points++;
    }

    fout << points << endl;
}