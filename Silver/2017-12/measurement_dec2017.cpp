// Milk Measurement
// USACO Silver December 2017: http://www.usaco.org/index.php?page=viewproblem2&cpid=763

#include <bits/stdc++.h>

using namespace std;

typedef pair<int, int> pii;

struct Log {
    int day;
    int ID;
    int change;

    bool operator<(const Log& other) const {
        return day < other.day;
    }
};

int N, G;
Log logs[100001];

map<int, int> cows; // key: ID, value: milk output
map<int, set<int>> milk; // key: milk output, value: set of IDs

int main() {
    ifstream fin("measurement.in");
    ofstream fout("measurement.out");

    fin >> N >> G;
    for (int i=1; i<=N; i++) {
        fin >> logs[i].day >> logs[i].ID >> logs[i].change;
        cows[logs[i].ID] = G; // set cow's milk output to G
        milk[G].insert(logs[i].ID); // add cow to milk output of G
    }

    milk[G].insert(0); // represents other cows that don't change (not logged)

    // sort measurements by day
    sort(logs+1, logs+N+1);

    int result = 0;
    set<int> largest = milk[G];

    for (int i=1; i<=N; i++) {
        int& ID = logs[i].ID;

        // update cow ID's milk output
        int old = cows[ID];
        cows[ID] += logs[i].change;

        // erase cow ID from old milk output
        milk[old].erase(ID);
        if (milk[old].empty()) milk.erase(old);

        // add cow ID to new milk output
        milk[cows[ID]].insert(ID);

        // see if the leaderboard has changed or not
        set<int>& curr = (--milk.end())->second;
        if (largest != curr) {
            largest = curr;
            result++;
        }
    }

    fout << result << "\n";
}