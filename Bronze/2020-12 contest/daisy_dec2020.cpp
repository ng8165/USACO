#include <bits/stdc++.h>

using namespace std;

int n;
int petals[105];
int prefix[105];

inline bool searchPetal(int start, int stop, double avg) {
    for (int i=start; i<=stop; i++) {
        if (avg == petals[i]) return true;
    }
    
    return false;
}

int main() {
    cin >> n;
    prefix[0] = 0;
    for (int i=1; i<=n; i++) {
        cin >> petals[i];
        prefix[i] = petals[i] + prefix[i-1];
    }

    int result = n;

    for (int i=1; i<=n; i++) { for (int j=i+1; j<=n; j++) {
        double avg = (prefix[j]-prefix[i-1])/(j-i+1.0);
        if (searchPetal(i, j, avg)) result++;
    }}

    cout << result << endl;
}