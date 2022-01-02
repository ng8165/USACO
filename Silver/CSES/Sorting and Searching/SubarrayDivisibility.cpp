// Subarray Divisibility
// CSES Sorting and Searching: https://cses.fi/problemset/task/1662

#include <iostream>
#include <vector>

using namespace std;

typedef long long ll;

int main() {
    int n;
    cin >> n;

    int input[n];
    for (int i=0; i<n; i++) cin >> input[i];
    
    vector<int> modFreq(n);
    modFreq[0] = 1;

    ll pSum = 0;
    ll result = 0;

    for (int num: input) {
        pSum += num;

        int modIdx = ((pSum%n)+n)%n;

        result += modFreq[modIdx];

        modFreq[modIdx]++;
    }

    cout << result << endl;
}