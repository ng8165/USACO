// Splitting the Field
// USACO Gold US Open 2016: http://www.usaco.org/index.php?page=viewproblem2&cpid=645

#include <bits/stdc++.h>

using namespace std;

typedef long long ll;

struct Cow {
    int x, y;

    bool operator<(const Cow& other) const {
        return x < other.x;
    }
};

int N;
Cow cows[50001];

ll result = LLONG_MAX;
ll oneFence;

void findMinArea() {
    int i = 1;
    int minY = INT_MAX, maxY = 0;
    map<int, ll> m; // key: x coordinate, value: area of fence which encloses all cows with x coordinate <= key

    // iterate through cows in x increasing order
    while (i <= N) {
        // iterate through all cows with the same x coordinate
        // and update minY and maxY accordingly
        int j = i;
        while (j <= N && cows[i].x == cows[j].x) {
            minY = min(minY, cows[j].y);
            maxY = max(maxY, cows[j].y);
            j++;
        }

        // area = length * width
        m[cows[i].x] = (ll) (maxY-minY) * (cows[i].x - cows[1].x);

        i = j; // j is the index of the next cow with a larger x coordinate
    }

    i = N;
    minY = INT_MAX; maxY = 0;

    // iterate through cows in x decreasing order
    while (i > 0) {
        // iterate through all cows with the same x coordinate
        // and update minY and maxY accordingly
        int j = i;
        while (j > 0 && cows[i].x == cows[j].x) {
            minY = min(minY, cows[j].y);
            maxY = max(maxY, cows[j].y);
            j--;
        }

        // area = length * width
        ll area = (ll) (maxY-minY) * (cows[N].x - cows[i].x);

        // update result by adding areas of the two fences (other area has next smallest x coordinate, which is cows[j].x)
        result = min(result, m[cows[j].x] + area);

        i = j; // j is the index of the next cow with a smaller x coordinate
    }

    oneFence = m[cows[N].x]; // area of one large fence to enclose all cows
}

int main() {
    ifstream fin("split.in");
    ofstream fout("split.out");

    fin >> N;
    for (int i=1; i<=N; i++)
        fin >> cows[i].x >> cows[i].y;

    // find min area by sweeping through x
    sort(cows+1, cows+N+1);
    findMinArea();

    // find min area by sweeping through y
    // swap x and y coordinates, so can use same sorting comparator and findMinArea function
    for (int i=1; i<=N; i++) swap(cows[i].x, cows[i].y);
    sort(cows+1, cows+N+1);
    findMinArea();

    fout << oneFence - result << "\n";
}