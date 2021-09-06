#include <bits/stdc++.h>

using namespace std;

struct Mountain {
    int start;
    int end;
};

bool cmp(const Mountain& m1, const Mountain& m2) {
    if (m1.start == m2.start) return m1.end > m2.end;
    return m1.start < m2.start;
}

int n;
Mountain mountains[100005];

int main() {
    ifstream fin("mountains.in");
    ofstream fout("mountains.out");

    fin >> n;
    for (int i=1; i<=n; i++) {
        int x, y;
        fin >> x >> y;

        mountains[i] = {x-y, x+y}; // find start and end points of mountain from peak
    }

    sort(mountains+1, mountains+n+1, cmp);

    int maxEnd = -1;
    int result = 0;

    for (int i=1; i<=n; i++) {
        if (mountains[i].end > maxEnd) {
            // every time there is a new end in the mountain, that mountain is visible
            // obscured mountains will not go over maxEnd and will not be seen
            maxEnd = mountains[i].end;
            result++;
        }
    }

    fout << result << endl;
}