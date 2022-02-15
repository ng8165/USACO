// Stuck in a Rut
// USACO Silver December 2020: http://www.usaco.org/index.php?page=viewproblem2&cpid=1064

#include <bits/stdc++.h>

using namespace std;

struct Cow {
    int x, y, index;
    int blame = 0;
    bool stop = false;
};

bool cmpX(const Cow& a, const Cow& b) {
    return a.x < b.x;
}

bool cmpY(const Cow& a, const Cow& b) {
    return a.y < b.y;
}

int N;
vector<Cow> north, east;

int main() {
    // input
    ios::sync_with_stdio(0);
    cin.tie(0);

    cin >> N;
    for (int i=0; i<N; i++) {
        char dir; cin >> dir;
        Cow c; cin >> c.x >> c.y; c.index = i;
        
        // separate cows into north and east
        if (dir == 'N') north.push_back(c);
        else east.push_back(c);
    }

    // sort north cows by x coordinate
    sort(north.begin(), north.end(), cmpX);
    sort(east.begin(), east.end(), cmpY);

    for (Cow& n: north) {
        if (n.stop) continue;

        for (Cow& e: east) {
            if (e.stop) continue;

            // east cow must be above and to the left of the north cow
            if (e.x > n.x || e.y < n.y) continue;

            // find intersection and times
            int ix = n.x, iy = e.y;
            int tn = iy-n.y, te = ix-e.x;

            if (tn == te) continue; // both cows enter at same time so don't stop

            if (tn < te) {
                // north cow is blamed for stopping east cow
                n.blame += (e.blame + 1);
                e.stop = true;
            } else {
                // east cow is blamed for stopping north cow
                e.blame += (n.blame + 1);
                n.stop = true;
                break; // skips to next north cow
            }
        }
    }

    int result[N];
    for (Cow& c: north) result[c.index] = c.blame;
    for (Cow& c: east) result[c.index] = c.blame;
    for (int& r: result) cout << r << "\n";
}