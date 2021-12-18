// Convention II
// USACO Silver December 2018: http://www.usaco.org/index.php?page=viewproblem2&cpid=859

#include <bits/stdc++.h>

using namespace std;

struct Cow {
    int arrival, duration, seniority;

    Cow(int a, int d, int s) {
        arrival = a;
        duration = d;
        seniority = s;
    }

    bool operator<(const Cow& other) {
        return arrival < other.arrival;
    }
};

bool cmp(const Cow& a, const Cow& b) {
    return a.seniority < b.seniority;
}

int n;
vector<Cow> cows;

int main() {
    ifstream fin("convention2.in");
    ofstream fout("convention2.out");

    fin >> n;
    for (int s=n; s>=1; s--) {
        int a, d; fin >> a >> d;
        cows.push_back(Cow(a, d, s));
    }

    sort(cows.begin(), cows.end());

    priority_queue<Cow, vector<Cow>, decltype(&cmp)> pq(&cmp);
    int result = 0, idx = 0, time = 0;

    while (idx < n) {
        if (pq.empty()) {
            pq.push(cows[idx]);
            time = cows[idx++].arrival;
            continue;
        }

        Cow curr = pq.top(); pq.pop();

        result = max(result, time-curr.arrival);
        time += curr.duration;

        while (idx < n && cows[idx].arrival <= time) {
            pq.push(cows[idx++]);
        }
    }

    while (!pq.empty()) {
        Cow curr = pq.top(); pq.pop();
        result = max(result, time-curr.arrival);
        time += curr.duration;
    }

    fout << result << endl;
}