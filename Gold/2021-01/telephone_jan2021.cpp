// Telephone
// USACO Gold January 2021: http://www.usaco.org/index.php?page=viewproblem2&cpid=1090

#include <bits/stdc++.h>

using namespace std;

typedef unsigned int uint;

struct State {
    int cow;
    uint time;

    bool operator<(const State& other) const {
        return time > other.time;
    }
};

const int maxN = 5e4+1, maxK = 51;
int N, K;
int b[maxN]; // b[i] indicates the breed of cow i
vector<int> talk[maxK]; // talk[i] represents all breeds that breed i talks to

vector<int> cows[maxK]; // cows[i] represents indices of cows of breed i
uint shortest[maxN];

void dijkstra() {
    priority_queue<State> pq;
    pq.push({1, 0});
    shortest[1] = 0;

    while (!pq.empty()) {
        State s = pq.top(); pq.pop();

        if (s.time > shortest[s.cow])
            continue;
        
        for (int& adjBreed: talk[b[s.cow]]) {
            for (int& adj: cows[adjBreed]) {
                if (adj == s.cow) continue;

                uint time = s.time + abs(s.cow - adj);
                if (time < shortest[adj]) {
                    shortest[adj] = time;
                    pq.push({adj, time});
                }
            }
        }
    }
}

int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);

    memset(shortest, UINT_MAX, sizeof(shortest));

    cin >> N >> K;
    
    for (int i=1; i<=N; i++) {
        cin >> b[i];
        cows[b[i]].push_back(i);
    }

    for (int i=1; i<=K; i++) {
        for (int j=1; j<=K; j++) {
            char S; cin >> S;
            if (S == '1') talk[i].push_back(j);
        }
    }

    dijkstra();

    if (shortest[N] == UINT_MAX)
        cout << "-1\n";
    else
        cout << shortest[N] << "\n";
}