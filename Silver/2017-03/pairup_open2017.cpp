#include <bits/stdc++.h>

using namespace std;

typedef pair<int, int> pii;

int n, m = 0;
pii cows[100001];

int main() {
    ifstream fin("pairup.in");
    ofstream fout("pairup.out");

    fin >> n;
    for (int i=1; i<=n; i++) {
        int x, y; fin >> x >> y;
        cows[i] = pii(y, x);
        m += x;
    }

    sort(cows+1, cows+n+1);

    int result = 0;
    int left = 1, right = n;

    while (left <= right) {
        int& leftCows = cows[left].second;
        int& rightCows = cows[right].second;

        result = max(result, cows[left].first + cows[right].first);

        if (leftCows == rightCows) {
            left++;
            right--;
        } else if (leftCows < rightCows) {
            left++;
            rightCows -= leftCows;
        } else {
            right--;
            leftCows -= rightCows;
        }
    }

    fout << result << endl;
}