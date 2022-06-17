#include <bits/stdc++.h>

using namespace std;

int n;
int lifeguards[105][2];

int main() {
    ifstream fin("lifeguards.in");
    ofstream fout("lifeguards.out");

    fin >> n;
    for (int i=1; i<=n; i++) fin >> lifeguards[i][0] >> lifeguards[i][1];

    pair<int, int> timeline[2*n];

    int result = 0;

    for (int i=1; i<=n; i++) {
        for (int j=1, idx=1; j<=n; j++) {
            if (i == j) continue;

            timeline[idx++] = make_pair(lifeguards[j][0], 1);
            timeline[idx++] = make_pair(lifeguards[j][1], -1);
        }

        sort(timeline+1, timeline+2*n-1);

        int guardCnt = 0;
        int time = 0;

        for (int j=1; j<=2*n-2; j++) {
            if (guardCnt > 0)
                time += (timeline[j].first - timeline[j-1].first);
            
            guardCnt += timeline[j].second;
        }

        result = max(result, time);
    }

    fout << result << endl;
}