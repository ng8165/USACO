// Closest Cow Wins
// USACO Silver December 2021: http://usaco.org/index.php?page=viewproblem2&cpid=1158

#include <bits/stdc++.h>

using namespace std;

struct Grass {
    int loc;
    int rMin;
    int rMax;
    int tasty;

    Grass(int loc, int tasty) {
        this->loc = loc;
        this->tasty = tasty;
    }

    bool operator<(const Grass& other) {
        if (rMin == other.rMin) return rMax < other.rMax;
        return rMin < other.rMin;
    }
};

struct Group {
    int tasty;
    vector<Grass> grasses;

    bool cmpGroup(const Group& a, const Group& b) const {
        return a.tasty < b.tasty;
    }
};

bool cmpGrass(const Grass& a, const Grass& b) {
    return a.loc < b.loc;
}

int K, M, N;
vector<Grass> grass;
set<int> nhoj;

int main() {
    // input
    ios::sync_with_stdio(0);
    cin.tie(0);

    cin >> K >> M >> N;
    
    for (int i=0; i<K; i++) {
        int loc, tasty;
        cin >> loc >> tasty;
        grass.push_back(Grass(loc, tasty));
    }
    
    for (int i=0; i<M; i++) {
        int loc; cin >> loc;
        nhoj.insert(loc);
    }

    // find range for all grass patches
    for (Grass& g: grass) {
        auto it = nhoj.lower_bound(g.loc);
        int gap = INT_MAX;

        if (it != nhoj.end()) gap = min(gap, abs(*it - g.loc));
        if (it != nhoj.begin()) gap = min(gap, abs(g.loc - *(--it)));

        g.rMin = max(0, g.loc-gap);
        g.rMax = g.loc+gap;
    }

    sort(grass.begin(), grass.end());
    
    // form groups based on range overlaps
    int idx = 0;
    priority_queue<Group> pq;

    while (idx < K) {
        Group gr;
        int& tasty = gr.tasty;
        vector<Grass>& grasses = gr.grasses;

        int origMax = grass[idx].rMax;
        grasses.push_back(grass[idx]);
        tasty = grass[idx++].tasty;

        while (idx < K && grass[idx].rMin < origMax) {
            tasty += grass[idx].tasty;
            grasses.push_back(grass[idx++]);
        }

        pq.push(gr);
    }

    // pop groups based on highest tasty
    int cnt = 0;
    long long result = 0;
    set<Grass, decltype(&cmpGrass)> taken(&cmpGrass);
    
    while (!pq.empty() && cnt < N) {
        Group gr = pq.top(); pq.pop();
        int& tasty = gr.tasty;
        int newTasty = 0;
        vector<Grass>& grasses = gr.grasses;

        for (auto it=grasses.begin(); it!=grasses.end();) {
            if (taken.count(*it)) {
                it = grasses.erase(it);
            } else {
                newTasty += it->tasty;
                it++;
            }
        }

        if (newTasty == tasty) {
            result += newTasty;
            cnt++;
            taken.insert(grasses.begin(), grasses.end());
        } else {
            pq.push(gr);
        }
    }

    cout << result << endl;
}