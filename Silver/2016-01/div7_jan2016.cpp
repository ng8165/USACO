#include <fstream>

using namespace std;

typedef long long ll;

int main() {
    ifstream fin("div7.in");
    ofstream fout("div7.out");
    int n;
    fin >> n;

    int input[n+1];
    for (int i=1; i<=n; i++) fin >> input[i];

    ll prefixSum = 0;
    int result = 0;
    pair<int, int> mod[7];

    for (int i=1; i<=n; i++) {
        prefixSum += input[i];

        int modIdx = ((prefixSum%7)+7)%7;

        int& minIdx = mod[modIdx].first;
        int& maxIdx = mod[modIdx].second;

        if (minIdx == 0 && maxIdx == 0) {
            minIdx = i; maxIdx = i;
        } else {
            minIdx = min(minIdx, i);
            maxIdx = max(maxIdx, i);
        }

        result = max(result, mod[modIdx].second-mod[modIdx].first);
    }

    fout << result << endl;
}