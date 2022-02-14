// Searching for Soulmates
// USACO Silver January 2022: http://www.usaco.org/index.php?page=viewproblem2&cpid=1182

#include <bits/stdc++.h>

using namespace std;

int N;

typedef long long ll;
typedef pair<ll, int> CowPair; // first is personality, second is number of operations

ll start, goal;
vector<CowPair> v1, v2;

int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);
    
    cin >> N;

    for (int i=0; i<N; i++) {
        cin >> start >> goal;
        v1.clear(); v2.clear();

        // reverse change from cow 1 and cow 2
        // then, from cow 1, attempt to cross over to cow 2
        // and add the number of operations (second in a CowPair)

        // perform reverse change on cow 1
        // will go down from cow 1
        int operations = 0;
        v1.push_back({start, operations});

        while (start > 1) {
            if (start%2 == 0) {
                // even so divide by 2 (1 step)
                start /= 2;
                operations++;
            } else {
                // odd so add 1, then divide by 2 (2 steps)
                start = (start+1)/2;
                operations += 2;
            }

            v1.push_back({start, operations});
        }

        // perform reverse change on cow 2
        // will go up from cow 2 (so instead of adding 1, subtract 1)
        operations = 0;
        v2.push_back({goal, operations});

        while (goal > 1) {
            if (goal%2 == 0) {
                // even so divide by 2 (1 step)
                goal /= 2;
                operations++;
            } else {
                // odd so subtract 1, then divide by 2 (2 steps)
                operations += 2;
                goal = (goal-1)/2;
            }

            v2.push_back({goal, operations});
        }

        reverse(v1.begin(), v1.end());
        reverse(v2.begin(), v2.end());

        int result = INT_MAX;

        // use binary search to find lowest crossing point
        for (CowPair& p1: v1) {
            CowPair search = {p1.first, 0}; // search by personality, then by operations
            auto it = lower_bound(v2.begin(), v2.end(), search);

            if (it == v2.end()) break; // no crossing point

            int final = p1.second; // operations for cow 1
            final += (it->first)-(p1.first); // cross over operations (add 1)
            final += it->second; // operations for cow 2

            result = min(result, final);
        }

        cout << result << "\n";
    }
}