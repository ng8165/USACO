// Intersection Points
// CSES Additional Problems: https://cses.fi/problemset/task/1740 (Sweepline)
// XJOI Problem 14182: https://xjoi.net/contest/3422/problem/1?locale=en

#include <bits/stdc++.h>

using namespace std;

struct Event {
    char type; // 's' means horizontal start, 'e' means horizontal end, 'v' means vertical
    int x;
    int y; // used only if horizontal line
    int y1, y2; // used only if vertical lines

    // constructor for horizontal line
    Event(int type, int x, int y) {
        this->type = type;
        this->x = x;
        this->y = y;
    }

    // constructor for vertical line
    Event(int type, int x, int y1, int y2) {
        this->type = type;
        this->x = x;
        this->y1 = y1;
        this->y2 = y2;
    }

    // comparator
    bool operator<(const Event& other) {
        return x < other.x;
    }
};

int n; // number of lines
vector<Event> eois; // events of interest

int main() {
    cin >> n;

    int x1, y1, x2, y2;
    for (int i=0; i<n; i++) {
        cin >> x1 >> y1 >> x2 >> y2;

        if (x1 == x2) {
            // vertical
            eois.push_back(Event('v', x1, y1, y2));
        } else {
            // horizontal
            eois.push_back(Event('s', x1, y1));
            eois.push_back(Event('e', x2, y1));
        }
    }

    sort(eois.begin(), eois.end());

    vector<int> set; // stores y coordinates of all horizontal lines
    long long intersections = 0;

    for (Event eoi: eois) {
        if (eoi.type == 's') {
            // start of a horizontal line
            set.insert(upper_bound(set.begin(), set.end(), eoi.y), eoi.y);
        } else if (eoi.type == 'e') {
            // end of a horizontal line
            set.erase(lower_bound(set.begin(), set.end(), eoi.y));
        } else {
            // vertical line
            auto itlow = lower_bound(set.begin(), set.end(), eoi.y1);
            auto ithigh = upper_bound(itlow, set.end(), eoi.y2);

            intersections += (ithigh - itlow);
        }
    }

    cout << intersections << endl;
}