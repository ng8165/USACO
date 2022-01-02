// Subarray Sums II
// CSES Sorting and Searching: https://cses.fi/problemset/task/1661

#include <iostream>
#include <map>

using namespace std;

typedef long long ll;

int main() {
    int n, x;
    cin >> n >> x;

    int input[n];
    for (int i=0; i<n; i++) cin >> input[i];

    map<ll, int> sumFreq;
    sumFreq[0] = 1;
    ll pSum = 0;
    ll result = 0;

    for (int num: input) {
        pSum += num;

        result += sumFreq[pSum-x];

        sumFreq[pSum]++;
    }

    cout << result << endl;
}