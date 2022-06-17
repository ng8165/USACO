// Stuck in a Rut
// USACO Bronze December 2020: http://www.usaco.org/index.php?page=viewproblem2&cpid=1061

#include <bits/stdc++.h>
#define x first
#define y second

using namespace std;

typedef pair<int, int> Location;

struct Cow {
    Location loc;
    char dir;
    int travel = -1; // travel distance until stopped, -1 if Infinity
};

struct Event {
    Location loc;
    Cow* cow;
    int time;

    bool operator<(const Event& other) const {
        return time < other.time;
    }
};

int N;
Cow cows[50];
vector<Event> events;
set<Location> eaten;

int main() {
    // input
    ios::sync_with_stdio(0);
    cin.tie(0);

    cin >> N;
    for (int i=0; i<N; i++) {
        Cow& c = cows[i];
        cin >> c.dir >> c.loc.x >> c.loc.y;
    }

    // find intersections
    for (int i=0; i<N; i++) {
        for (int j=i+1; j<N; j++) {
            if (cows[i].dir == cows[j].dir) continue;

            Cow *n = &cows[i], *e = &cows[j];
            if (e->dir == 'N' && n->dir == 'E') swap(n, e);

            Location &ln = n->loc, &le = e->loc;

            if (le.x <= ln.x && le.y >= ln.y) {
                // east is to the left and up of north, so can intersect
                Location intersect = {ln.x, le.y};

                // calculate times
                int tn = intersect.y - ln.y;
                int te = intersect.x - le.x;
                if (tn == te) continue; // if cows intersect at the same time, the intersection doesn't happen

                events.push_back({intersect, n, tn});
                events.push_back({intersect, e, te});
            }
        }
    }

    // sort by time
    sort(events.begin(), events.end());

    for (Event& e: events) {
        if (e.cow->travel > -1)
            // the cow is already stopped, so the event does not happen
            continue;

        if (eaten.count(e.loc))
            // this grass has been eaten, so the cow stops
            e.cow->travel = e.time;
        else
            // this grass has not been eaten, so mark it as eaten
            eaten.insert(e.loc);
    }

    for (int i=0; i<N; i++) {
        if (cows[i].travel == -1)
            cout << "Infinity\n";
        else
            cout << cows[i].travel << "\n";
    }
}