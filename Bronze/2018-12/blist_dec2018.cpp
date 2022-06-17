#include <bits/stdc++.h>

using namespace std;

int n;
pair<int, int> timeline[205]; // MAX_N is 100, so maximum of 200 events

int main() {
    ifstream fin("blist.in");
    ofstream fout("blist.out");

    fin >> n;
    for (int i=1; i<=2*n; i+=2) {
        int s, t, b;
        fin >> s >> t >> b;

        timeline[i] = make_pair(s, b); // at time s, take b buckets
        timeline[i+1] = make_pair(t, -b); // at time t, return b buckets
    }
    
    sort(timeline+1, timeline+(2*n)+1);

    int availBuckets = 0, usedBuckets = 0;

    for (int i=1; i<=2*n; i++) {
        int bucketsNeeded = timeline[i].second;

        usedBuckets += bucketsNeeded;

        if (availBuckets < bucketsNeeded) availBuckets = 0;
        else availBuckets -= bucketsNeeded;
    }

    fout << availBuckets << endl;
}